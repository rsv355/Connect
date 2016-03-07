package com.webmyne.connect.base.mainMVP;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.plus.People;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;
import com.webmyne.connect.Utils.Functions;
import com.webmyne.connect.base.model.UserProfile;
import com.webmyne.connect.customUI.viewPager.SCPositionAnimation;
import com.webmyne.connect.customUI.viewPager.SCViewAnimation;
import com.webmyne.connect.customUI.viewPager.SCViewAnimationUtil;
import com.webmyne.connect.customUI.viewPager.SCViewPager;

/**
 * Created by priyasindkar on 03-03-2016.
 */
public class MainPresenterImpl {
    private LoginView loginView;

    //facebook login
    private CallbackManager callbackManager;

    //google login
    private int RC_SIGN_IN = 0;
    private String personName;
    private UserProfile userProfile;

    private static String TAG = "SOCIAL_MEDIA";

    //GCM
    private GoogleCloudMessaging gcm;
    private String GCM_ID;
    private String deviceId;

    public MainPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
    }

    public void callLoginUser(Context mContext) {
        try {
            if (gcm == null) {
                gcm = GoogleCloudMessaging.getInstance(mContext);
                GCM_ID = "dd";

                Log.e("GCM ID :", GCM_ID);
                if (GCM_ID == null || GCM_ID == "") {

                } else {
                    deviceId = Settings.Secure.getString(mContext.getContentResolver(), Settings.Secure.ANDROID_ID);
                    // call webservice
                }
            }
        } catch (Exception e) {

        }
    }

    public void animateGuidePageView(Context mContext, View guideText1, Point size, SCViewPager mViewPager) {
        TextView textView = (TextView) guideText1;
        textView.setTypeface(Functions.getTypeFace(mContext));
        SCViewAnimation nameTagAnimation = new SCViewAnimation(guideText1);
        nameTagAnimation.addPageAnimation(new SCPositionAnimation(mContext, 0, 0, -size.y / 2));
        nameTagAnimation.addPageAnimation(new SCPositionAnimation(mContext, 0, 0, size.y));
        mViewPager.addAnimation(nameTagAnimation);
    }

    public void animateGuidePageImageSkewType(Context mContext, View guideImage, Point size, SCViewPager mViewPager) {
        SCViewAnimationUtil.prepareViewToGetSize(guideImage);
        SCViewAnimation atSkexAnimation = new SCViewAnimation(guideImage);
        atSkexAnimation.addPageAnimation(new SCPositionAnimation(mContext, 0, 0, -(size.y - guideImage.getHeight())));
        atSkexAnimation.addPageAnimation(new SCPositionAnimation(mContext, 0, -size.x, 0));
        mViewPager.addAnimation(atSkexAnimation);
    }

    public void animateGuidePageTextDjangoType(Context mContext, View guideText, Point size, int forPageStart, int forPageEnd, SCViewPager mViewPager) {
        TextView textView = (TextView) guideText;
        textView.setTypeface(Functions.getTypeFace(mContext));
        SCViewAnimation djangoAnimation = new SCViewAnimation(guideText);
        djangoAnimation.startToPosition(null, -size.y);
        djangoAnimation.addPageAnimation(new SCPositionAnimation(mContext, forPageStart, 0, size.y));
        djangoAnimation.addPageAnimation(new SCPositionAnimation(mContext, forPageEnd, 0, size.y));
        mViewPager.addAnimation(djangoAnimation);
    }

    public void animateGuidePageImageMobileType(Context mContext, View guideImage, Point size, int forPageStart, int forPageEnd, SCViewPager mViewPager) {
        SCViewAnimation mobileAnimation = new SCViewAnimation(guideImage);
        mobileAnimation.startToPosition((int) (size.x * 1.5), null);
        mobileAnimation.addPageAnimation(new SCPositionAnimation(mContext, forPageStart, -(int) (size.x * 1.5), 0));
        mobileAnimation.addPageAnimation(new SCPositionAnimation(mContext, forPageEnd, -(int) (size.x * 1.5), 0));
        mViewPager.addAnimation(mobileAnimation);
    }

    public void animateGuidePageRasberryType(Context mContext, View loginView, Point size, int forPageStart, int forPageEnd, SCViewPager mViewPager) {
        SCViewAnimation raspberryAnimation = new SCViewAnimation(loginView);
        raspberryAnimation.startToPosition(-size.x, null);
        raspberryAnimation.addPageAnimation(new SCPositionAnimation(mContext, forPageStart, size.x, 0));
        raspberryAnimation.addPageAnimation(new SCPositionAnimation(mContext, forPageEnd, -size.x, 0));
        mViewPager.addAnimation(raspberryAnimation);
    }

   /* public void onFacebookLogin(boolean isSuccess, String success, String error) {
        if (loginView != null) {
            if (isSuccess) {
                loginView.onFacebookLoginSuccess(success);
            } else {
                loginView.onFacebookLoginError(error);
            }
        }
    }*/

    public void socialMediaActivityResultHandler(int requestCode, int resultCode, Intent data, GoogleApiClient mGoogleApiClient) {
        if (requestCode == RC_SIGN_IN) {  //handles Google+ Result
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result, mGoogleApiClient);
        } else { //handles Facebook Result
            callbackManager.onActivityResult(requestCode, resultCode, data);
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
/*
    public void doFacebookLogin(final Activity activity) {
        LoginManager.getInstance().logInWithReadPermissions(activity, Arrays.asList("email", "public_profile", "user_friends"));
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Profile profile = Profile.getCurrentProfile();
                        final Uri profileUri = profile.getProfilePictureUri(640, 640);

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
                                                userProfile = new UserProfile();
                                                userProfile.setFirstName(fName);
                                                userProfile.setLastName(lName);
                                                userProfile.setEmailId(email);
                                                userProfile.setPhotoUri(profileUri);


                                                onFacebookLogin(true, personName, "");

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

                        *//*SharedPreferences preferences = getSharedPreferences("user_login", 0);
                        preferences.edit().putBoolean("isUserLoggedIn", true).commit();
                        Intent intent = new Intent(activity, DrawerActivity.class);
                        startActivity(intent);
                        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);*//*
                    }

                    @Override
                    public void onCancel() {
                        onFacebookLogin(false, "", "Facebook Connection Cancelled");
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        onFacebookLogin(false, "", "Facebook Connection Failed");
                    }
                });
    } */

    public void doGoogleLogin(Activity mContext, GoogleApiClient mGoogleApiClient) {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        mContext.startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void handleSignInResult(GoogleSignInResult result, GoogleApiClient mGoogleApiClient) {
        Log.e("G+ ", "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            final GoogleSignInAccount acct = result.getSignInAccount();
            Log.e("Name ", acct.getDisplayName());
            Log.e("Email ", acct.getEmail());
            Log.e("Photo ", acct.getPhotoUrl() + "");
            Log.e("Id ", acct.getId() + "");
            Log.e("PhotoURL ", acct.getPhotoUrl().toString());

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
                    userProfile.setFirstName(person.getName().getGivenName());
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

}
