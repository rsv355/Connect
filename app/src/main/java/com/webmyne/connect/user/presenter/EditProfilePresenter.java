package com.webmyne.connect.user.presenter;

import android.app.Activity;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.webmyne.connect.user.model.UserLoginOutput;

/**
 * Created by priyasindkar on 22-02-2016.
 */
public interface EditProfilePresenter {
    void initUI(Activity activity);
    void showDatePicker(Activity activity);
    void setToolBarOffset(Activity activity, AppBarLayout appBarLayout, int offset, Toolbar toolbar);
    void startAlphaAnimation (View v, long duration, int visibility);
    void validateFormFields(Activity activity, String name, String emailId, String DOB, String mobile,
                            String zipcode, String location, String gender, int userId);
    void doUpdateUser(Activity activity, UserLoginOutput userLoginOutput);
    void onUpdateUser(boolean isSuccess, String success, String error);
    void showEnterReferCodeDialog();
    void onDestroy();
    void showNoInternetDialog(Activity activity);
}
