package com.webmyne.connect.base.login;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;

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
import com.webmyne.connect.R;
import com.webmyne.connect.Utils.Constants;
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
    public UserProfile userProfile = new UserProfile();
    private Activity mContext;

    public SocialMediaPresenterImp(LoginView loginView) {
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
                        if(profile!=null) {
                            profileUri = profile.getProfilePictureUri(640, 640);
                            userProfile.setPhotoUri(profileUri);
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
                                                userProfile.setFirstName(profile.getString("first_name").toString());
                                                userProfile.setLastName(profile.getString("last_name").toString());
                                                userProfile.setGender(profile.getString("gender").toString());
                                                userProfile.setEmailId(profile.getString("email").toString());

                                                onFacebookLogin(userProfile, true, activity.getString(R.string.success), "");
                                            } catch (Exception e) {
                                                onFacebookLogin(userProfile, false, "", activity.getString(R.string.facebook_connection_error));
                                            }
                                        }
                                    }
                                });
                        Bundle parameters = new Bundle();
                        parameters.putString("fields", /*"id, first_name,last_name, email, gender, birthday, link"*/Constants.FB_PARAM_FIELDS);
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
            if(acct.getPhotoUrl() != null) {
                userProfile.setPhotoUri(acct.getPhotoUrl());
            }

            Plus.PeopleApi.load(mGoogleApiClient, acct.getId()).setResultCallback(new ResultCallback<People.LoadPeopleResult>() {
                @Override
                public void onResult(@NonNull People.LoadPeopleResult loadPeopleResult) {
                    Person person = loadPeopleResult.getPersonBuffer().get(0);

                    userProfile.setFirstName( person.getName().getGivenName());
                    userProfile.setLastName(person.getName().getFamilyName());
                    userProfile.setEmailId(acct.getEmail());
                    if (acct.getPhotoUrl() != null) {
                        userProfile.setPhotoUri(acct.getPhotoUrl());
                    }
                    if (person.getGender() == 0) {
                        userProfile.setGender("Male");
                    } else if (person.getGender() == 1) {
                        userProfile.setGender("Female");
                    }
                }
            });
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
}
