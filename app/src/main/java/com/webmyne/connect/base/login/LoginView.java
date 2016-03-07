package com.webmyne.connect.base.login;

import com.webmyne.connect.base.model.UserProfile;

/**
 * Created by priyasindkar on 03-03-2016.
 */
public interface LoginView {
    void onGoogleLoginSuccess(UserProfile userProfile, String success);
    void onGoogleLoginError(String error);
    void onFacebookLoginError(String error);
    void onFacebookLoginSuccess(UserProfile userProfile, String success);
}
