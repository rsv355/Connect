package com.webmyne.connect.user;

import com.webmyne.connect.login.model.UserLoginOutput;

/**
 * Created by priyasindkar on 22-02-2016.
 */
public interface EditProfileView {
    void onDateSet(String date);
    void setError(String errorString);
    void setNameError(String errorString);
    void setEmailError(String errorString);
    void setMobileError(String errorString);
    void onValidationSuccess(boolean isValid, UserLoginOutput userLoginOutput);
    void onUpdateUserSuccess(String successString);
    void onUpdateUserFail(String errorString);
    void initUserData(UserLoginOutput currentUser);
    void showReferCodeAlert();
    void showProgress();
    void hideProgress();
}