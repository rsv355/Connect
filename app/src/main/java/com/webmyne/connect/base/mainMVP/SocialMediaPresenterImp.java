package com.webmyne.connect.base.mainMVP;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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
import com.google.android.gms.plus.People;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;
import com.webmyne.connect.base.model.UserProfile;

import org.json.JSONObject;

import java.util.Arrays;

/**
 * Created by priyasindkar on 07-03-2016.
 */
public class SocialMediaPresenterImp implements SocialMediaPresenter {

    private LoginView loginView;
    //facebook login
    public CallbackManager callbackManager;

    //google login
    public int RC_SIGN_IN = 0;
    public String personName = "";
    public UserProfile userProfile = new UserProfile();

    public static String TAG = "SOCIAL_MEDIA";

    public SocialMediaPresenterImp(LoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void doFacebookLogin(Activity activity) {
        LoginManager.getInstance().logInWithReadPermissions(activity, Arrays.asList("email", "public_profile", "user_friends"));
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Profile profile = Profile.getCurrentProfile();
                        Uri profileUri;
                        userProfile = new UserProfile();
                        if(profile!=null) {
                            profileUri = profile.getProfilePictureUri(640, 640);
                            userProfile.setPhotoUri(profileUri);

                        }

                        GraphRequest request = GraphRequest.newMeRequest(
                                loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(JSONObject user, GraphResponse response) {
                                        Log.e("#### FB RESPONSE", response.getJSONObject().toString());
                                        if (response.getError() != null) {
                                            // handle error
                                            Log.e("#### FB ERROR", response.getError().toString());
                                        } else {
                                            try {
                                                JSONObject profile = response.getJSONObject();

                                                String email = profile.getString("email").toString();
                                                String gender = profile.getString("gender").toString();
                                                //   String DOB = profile.getString("birthday").toString();
                                                String fName = profile.getString("first_name").toString();
                                                String lName = profile.getString("last_name").toString();
                                                String id = profile.getString("id").toString();
                                                String link = profile.getString("link").toString();
                                                Log.e("mFBEmail", email);
                                                Log.e("mFBGender", gender);
                                                // Log.e("mFBDOB", DOB);
                                                Log.e("mFBFName", fName);
                                                Log.e("mFBLName", lName);
                                                Log.e("mFBId", link);
                                                Log.e("mFBLink", link);
                                                personName = fName + " " + lName;
                                                userProfile.setFirstName(fName);
                                                userProfile.setLastName(lName);
                                                userProfile.setEmailId(email);

                                                onFacebookLogin(userProfile, true, personName, "");

                                            } catch (Exception e) {
                                                Log.e("#### FB EXP", e.toString());
                                            }
                                        }
                                    }
                                });
                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "id, first_name,last_name, email, gender, birthday, link");
                        request.setParameters(parameters);
                        request.executeAsync();

                        /*SharedPreferences preferences = getSharedPreferences("user_login", 0);
                        preferences.edit().putBoolean("isUserLoggedIn", true).commit();
                        Intent intent = new Intent(activity, DrawerActivity.class);
                        startActivity(intent);
                        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);*/
                    }

                    @Override
                    public void onCancel() {
                        onFacebookLogin(userProfile, false, "", "Facebook Connection Cancelled");
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        onFacebookLogin(userProfile, false, "", "Facebook Connection Failed");
                    }
                });
    }

    @Override
    public void doGoogleLogin(Activity mContext, GoogleApiClient mGoogleApiClient) {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        mContext.startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    public void handleSignInResult(GoogleSignInResult result, GoogleApiClient mGoogleApiClient) {
        Log.e("G+ ", "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            final GoogleSignInAccount acct = result.getSignInAccount();
            Log.e("Name ", acct.getDisplayName());
            Log.e("Email ", acct.getEmail());
            Log.e("Photo ", acct.getPhotoUrl() + "");
            Log.e("Id ", acct.getId() + "");
            if(acct.getPhotoUrl() != null) {
                Log.e("PhotoURL ", acct.getPhotoUrl().toString());
            }

            // G+
            Plus.PeopleApi.load(mGoogleApiClient, acct.getId()).setResultCallback(new ResultCallback<People.LoadPeopleResult>() {
                @Override
                public void onResult(@NonNull People.LoadPeopleResult loadPeopleResult) {
                    Person person = loadPeopleResult.getPersonBuffer().get(0);
                    Log.e(TAG, "Person loaded");
                    Log.e(TAG, person.getName().getGivenName());
                    Log.e(TAG, person.getName().getFamilyName());
                    Log.e(TAG, person.getDisplayName());
                    Log.e(TAG, person.getGender() + "");
                    Log.e(TAG, person.getImage() + "");

                    userProfile = new UserProfile();
                    userProfile.setFirstName(acct.getDisplayName());
                    userProfile.setLastName(person.getName().getFamilyName());
                    userProfile.setEmailId(acct.getEmail());
                    if (acct.getPhotoUrl() != null) {
                        userProfile.setPhotoUri(acct.getPhotoUrl());
                    }
                    if (person.getGender() == 0) {
                        userProfile.setEmailId("Male");
                    } else if (person.getGender() == 1) {
                        userProfile.setEmailId("Female");
                    }
                }
            });
            onGoogleLogin(userProfile, true, acct.getDisplayName(), "");
        } else {
            onGoogleLogin(userProfile, false, "", "Google Connection Failed");
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
}
