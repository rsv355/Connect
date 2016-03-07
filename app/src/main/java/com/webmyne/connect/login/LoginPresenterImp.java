package com.webmyne.connect.login;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.util.Log;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.plus.People;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;
import com.webmyne.connect.R;
import com.webmyne.connect.Utils.Constants;
import com.webmyne.connect.Utils.Functions;
import com.webmyne.connect.base.MyApplication;
import com.webmyne.connect.login.model.MainUserLoginResponse;
import com.webmyne.connect.login.model.UserLoginOutput;
import com.webmyne.connect.login.model.UserProfile;

import org.json.JSONObject;

import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by priyasindkar on 07-03-2016.
 */
public class LoginPresenterImp implements LoginPresenter {

    private LoginView loginView;
    //facebook login
    public CallbackManager callbackManager;

    //google login
    public int RC_SIGN_IN = 0;
    public UserProfile userProfile = new UserProfile();
    private Activity mContext;

    //GCM
    private GoogleCloudMessaging gcm;
    private String GCM_ID;
    private String deviceId;

    public LoginPresenterImp(LoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void doFacebookLogin(final Activity activity) {
        LoginManager.getInstance().logInWithReadPermissions(activity, Arrays.asList(/*"email", "public_profile", "user_friends"*/Constants.FB_READ_PERMISSIONS));
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Profile profile = Profile.getCurrentProfile();
                        Uri profileUri;
                        userProfile = new UserProfile();
                        if (profile != null) {
                            profileUri = profile.getProfilePictureUri(640, 640);
                            //userProfile.setPhotoUri(profileUri);
                        }

                        GraphRequest request = GraphRequest.newMeRequest(
                                loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(JSONObject user, GraphResponse response) {
                                        if (response.getError() != null) {
                                            // handle error
                                            onFacebookLogin(userProfile, false, "", activity.getString(R.string.facebook_connection_error));
                                        } else {
                                            try {
                                                JSONObject profile = response.getJSONObject();
                                                userProfile.setName(profile.getString("first_name").toString() + " " + profile.getString("last_name").toString());
                                                userProfile.setGender(profile.getString("gender").toString());
                                                userProfile.setEmail(profile.getString("email").toString());
                                                userProfile.setSignupById("fb");
                                                userProfile.setSignupWith("Facebook");

                                                onFacebookLogin(userProfile, true, activity.getString(R.string.success), "");
                                            } catch (Exception e) {
                                                onFacebookLogin(userProfile, false, "", activity.getString(R.string.facebook_connection_error));
                                            }
                                        }
                                    }
                                });
                        Bundle parameters = new Bundle();
                        parameters.putString("fields", Constants.FB_PARAM_FIELDS);
                        request.setParameters(parameters);
                        request.executeAsync();
                    }

                    @Override
                    public void onCancel() {
                        onFacebookLogin(userProfile, false, "", activity.getString(R.string.facebook_connection_cancelled));
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        onFacebookLogin(userProfile, false, "", activity.getString(R.string.facebook_connection_failed));
                    }
                });
    }

    @Override
    public void doGoogleLogin(Activity mContext, GoogleApiClient mGoogleApiClient) {
        this.mContext = mContext;
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        mContext.startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    public void handleSignInResult(GoogleSignInResult result, GoogleApiClient mGoogleApiClient) {
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            final GoogleSignInAccount acct = result.getSignInAccount();
            userProfile = new UserProfile();
            userProfile.setName(acct.getDisplayName());
            if (acct.getPhotoUrl() != null) {
                //userProfile.setPhotoUri(acct.getPhotoUrl());
            }

            Plus.PeopleApi.load(mGoogleApiClient, acct.getId()).setResultCallback(new ResultCallback<People.LoadPeopleResult>() {
                @Override
                public void onResult(@NonNull People.LoadPeopleResult loadPeopleResult) {
                    Person person = loadPeopleResult.getPersonBuffer().get(0);

                    userProfile.setEmail(acct.getEmail());
                    /*if (acct.getPhotoUrl() != null) {
                        userProfile.setPhotoUri(acct.getPhotoUrl());
                    }*/
                    if (person.getGender() == 0) {
                        userProfile.setGender("Male");
                    } else if (person.getGender() == 1) {
                        userProfile.setGender("Female");
                    }
                    userProfile.setSignupById("g+");
                    userProfile.setSignupWith("G+");
                }
            });
          /*  //signout
            Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                    new ResultCallback<Status>() {
                        @Override
                        public void onResult(Status status) {
                        }
                    });*/
            onGoogleLogin(userProfile, true, mContext.getString(R.string.success), "");
        } else {
            onGoogleLogin(userProfile, false, "", mContext.getString(R.string.google_connection_failed));
        }
    }

    @Override
    public void socialMediaActivityResultHandler(int requestCode, int resultCode, Intent data, GoogleApiClient mGoogleApiClient) {
        if (requestCode == RC_SIGN_IN) {  //handles Google+ Result
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result, mGoogleApiClient);
        } else { //handles Facebook Result
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void onFacebookLogin(UserProfile userProfile, boolean isSuccess, String success, String error) {
        if (loginView != null) {
            if (isSuccess) {
                loginView.onFacebookLoginSuccess(userProfile, success);
            } else {
                loginView.onFacebookLoginError(error);
            }
        }
    }

    public void onGoogleLogin(UserProfile userProfile, boolean isSuccess, String success, String error) {
        if (loginView != null) {
            if (isSuccess) {
                loginView.onGoogleLoginSuccess(userProfile, success);
            } else {
                loginView.onGoogleLoginError(error);
            }
        }
    }

    @Override
    public void doLogin(final Activity activity, final UserProfile userProfile) {
        if (loginView != null) {
            loginView.showProgressDialog();
        }
        try {
            if (gcm == null) {

                if(Functions.isGooglePlayServiceAvailable(activity)) {
                    new AsyncTask<Void, Void, String>() {
                        @Override
                        protected String doInBackground(Void... params) {
                            try {
                                if (gcm == null) {
                                    gcm = GoogleCloudMessaging.getInstance(activity);
                                }
                                GCM_ID = gcm.register(activity.getString(R.string.project_id));
                            } catch (Exception ex) {
                                GCM_ID = "";
                            }
                            return GCM_ID;
                        }

                        @Override
                        protected void onPostExecute(String regId) {
                            super.onPostExecute(regId);
                            if (!regId.equals("")) {
                                GCM_ID = regId;
                                processLogin(activity, userProfile);
                            } else {
                                onLogin(null, false, "", activity.getString(R.string.gcm_error));
                            }

                        }
                    }.execute();
                } else {
                    onLogin(null, false, "", activity.getString(R.string.gcm_error));
                }
            } else {
                processLogin(activity, userProfile);
            }
        } catch (Exception e) {
            onLogin(null, false, "",activity.getString(R.string.gcm_error));
        }
    }

    private void processLogin(final Activity activity, UserProfile userProfile) {
        if (GCM_ID == null || GCM_ID == "") {
            onLogin(null, false, "", activity.getString(R.string.gcm_error));
        } else {
            deviceId = Settings.Secure.getString(activity.getContentResolver(), Settings.Secure.ANDROID_ID);
            // call webservice
            userProfile.setGCMID(GCM_ID);
            userProfile.setDeviceID(deviceId);
            LoginService loginService = MyApplication.retrofit.create(LoginService.class);
            Call<MainUserLoginResponse> call = loginService.doUserLogin(userProfile);
            call.enqueue(new Callback<MainUserLoginResponse>() {
                @Override
                public void onResponse(Call<MainUserLoginResponse> call, Response<MainUserLoginResponse> response) {
                    if (response.body() != null) {
                        if (response.body().UserLoginOutput.getResponseMessage().equals("Successful")) {
                            UserLoginOutput userLoginOutput = response.body().UserLoginOutput;
                            Log.e("user ", userLoginOutput.toString());
                            onLogin(userLoginOutput, true, activity.getString(R.string.login_successful), "");
                        } else {
                            onLogin(null, false, "", activity.getString(R.string.login_failed));
                        }
                    } else {
                        onLogin(null, false, "", activity.getString(R.string.login_failed));
                    }
                }

                @Override
                public void onFailure(Call<MainUserLoginResponse> call, Throwable t) {
                    onLogin(null, false, "", activity.getString(R.string.login_failed));
                }
            });
        }
    }

    public void onLogin(UserLoginOutput userLoginOutput, boolean isSuccess, String success, String error) {
        if (loginView != null) {
            loginView.hideProgressDialog();
            if (isSuccess) {
                loginView.onLoginSuccess(userLoginOutput, success);
            } else {
                loginView.onLoginError(error);
            }
        }
    }

    @Override
    public void onDestroy() {
        loginView = null;
    }
}
