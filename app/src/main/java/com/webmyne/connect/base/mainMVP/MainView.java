package com.webmyne.connect.base.mainMVP;

/**
 * Created by priyasindkar on 03-03-2016.
 */
public interface MainView {
    public void onGoogleLoginSuccess(String success);

    public void onGoogleLoginError(String error);

    public void onFacebookLoginError(String error);

    public void onFacebookLoginSuccess(String success);

}
