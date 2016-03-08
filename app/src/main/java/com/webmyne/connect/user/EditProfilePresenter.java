package com.webmyne.connect.user;

import android.app.Activity;

import com.webmyne.connect.login.model.UserLoginOutput;

/**
 * Created by priyasindkar on 22-02-2016.
 */
public interface EditProfilePresenter {
    void initUserData(Activity activity);
    void showDatePicker(Activity activity);
    void validateFormFields(Activity activity, String name, String emailId, String DOB, String mobile,
                            String zipcode, String location, String gender, int userId);
    void doUpdateUser(Activity activity, UserLoginOutput userLoginOutput);
    void onUpdateUser(boolean isSuccess, String success, String error);
    void showEnterReferCodeDialog();
    void onDestroy();
}
