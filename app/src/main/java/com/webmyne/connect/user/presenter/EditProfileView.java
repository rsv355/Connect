package com.webmyne.connect.user.presenter;

import android.app.Activity;

import com.webmyne.connect.user.model.IndustryModel;
import com.webmyne.connect.user.model.UserLoginOutput;
import com.webmyne.connect.user.model.UserUpdateInput;

import java.util.List;

/**
 * Created by priyasindkar on 22-02-2016.
 */
public interface EditProfileView {
    void onDateSet(String date);
    void setError(String errorString);
    void setNameError(String errorString);
    void setEmailError(String errorString);
    void setMobileError(String errorString);
    void setDOBError(String errorString);
    void setZipcodError(String errorString);
    void setIndustryError(String errorString);
    void setAddressError(String errorString);
    void setAddressList(String[] addresses);
    void onIndustryListFetch(boolean isSuccess, String[] industryList);
    void onValidationSuccess(boolean isValid, UserUpdateInput userUpdateInput);
    void onReferCodeAdd(boolean isReferCodeAdded,String referCode);
    void onUpdateUserSuccess(String successString);
    void onUpdateUserFail(String errorString);
    void initUI(UserLoginOutput currentUser);
    void showReferCodeAlert();
    void showProgress();
    void hideProgress();
    void showNoInternetDialog(Activity activity);
}
