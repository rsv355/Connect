package com.webmyne.connect.login;

import android.app.Activity;
import android.content.Intent;

import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.webmyne.connect.login.model.UserProfile;

/**
 * Created by priyasindkar on 07-03-2016.
 */
public interface LoginPresenter {
    //social media
    void doFacebookLogin(Activity activity);
    void doGoogleLogin(Activity mContext, GoogleApiClient mGoogleApiClient);
    void handleSignInResult(GoogleSignInResult result, GoogleApiClient mGoogleApiClient);
    void socialMediaActivityResultHandler(int requestCode, int resultCode, Intent data, GoogleApiClient mGoogleApiClient);

    //API integration
    void doLogin(Activity activity, UserProfile userProfile);
    void onDestroy();
}
