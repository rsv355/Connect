package com.webmyne.connect.base.login;

import android.app.Activity;
import android.content.Intent;

import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by priyasindkar on 07-03-2016.
 */
public interface SocialMediaPresenter {
    void doFacebookLogin(Activity activity);
    void doGoogleLogin(Activity mContext, GoogleApiClient mGoogleApiClient);
    void handleSignInResult(GoogleSignInResult result, GoogleApiClient mGoogleApiClient);
    void socialMediaActivityResultHandler(int requestCode, int resultCode, Intent data, GoogleApiClient mGoogleApiClient);
}
