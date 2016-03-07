package com.webmyne.connect.login;

import com.webmyne.connect.login.model.UserLoginOutput;
import com.webmyne.connect.login.model.UserProfile;

/**
 * Created by priyasindkar on 03-03-2016.
 */
public interface LoginView {
    void onGoogleLoginSuccess(UserProfile userProfile, String success);
    void onGoogleLoginError(String error);
    void onFacebookLoginError(String error);
    void onFacebookLoginSuccess(UserProfile userProfile, String success);
    void onLoginSuccess(UserLoginOutput userLoginOutput, String success);
    void onLoginError(String error);
    void showProgressDialog();
    void hideProgressDialog();
}
