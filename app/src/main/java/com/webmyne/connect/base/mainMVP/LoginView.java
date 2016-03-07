package com.webmyne.connect.base.mainMVP;

import com.webmyne.connect.base.model.UserProfile;

/**
 * Created by priyasindkar on 03-03-2016.
 */
public interface LoginView {
    public void onGoogleLoginSuccess(UserProfile userProfile, String success);
    public void onGoogleLoginError(String error);
    public void onFacebookLoginError(String error);
    public void onFacebookLoginSuccess(UserProfile userProfile, String success);
}
