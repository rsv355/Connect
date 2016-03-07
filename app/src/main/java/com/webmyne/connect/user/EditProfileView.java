package com.webmyne.connect.user;

import com.webmyne.connect.login.model.UserLoginOutput;

/**
 * Created by priyasindkar on 22-02-2016.
 */
public interface EditProfileView {
    void setError(String errorString);
    void setNameError(String errorString);
    void setEmailError(String errorString);
    void setMobileError(String errorString);
    void onSuccess(String successString);
    void initUserData(UserLoginOutput currentUser);
    void showReferCodeAlert();
    void showProgress();
    void hideProgress();
}
