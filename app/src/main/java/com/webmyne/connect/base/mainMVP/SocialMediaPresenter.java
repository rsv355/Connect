package com.webmyne.connect.base.mainMVP;

import android.app.Activity;
import android.content.Intent;

import com.facebook.CallbackManager;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.webmyne.connect.base.model.UserProfile;

/**
 * Created by priyasindkar on 07-03-2016.
 */
public interface SocialMediaPresenter {
    public void doFacebookLogin(Activity activity);
    public void doGoogleLogin(Activity mContext, GoogleApiClient mGoogleApiClient);
    public void handleSignInResult(GoogleSignInResult result, GoogleApiClient mGoogleApiClient);
    public void socialMediaActivityResultHandler(int requestCode, int resultCode, Intent data, GoogleApiClient mGoogleApiClient);

}
