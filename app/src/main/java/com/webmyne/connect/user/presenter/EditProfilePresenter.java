package com.webmyne.connect.user.presenter;

import android.app.Activity;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.webmyne.connect.user.model.IndustryModel;
import com.webmyne.connect.user.model.UserLoginOutput;
import com.webmyne.connect.user.model.UserUpdateInput;

import java.util.List;

/**
 * Created by priyasindkar on 22-02-2016.
 */
public interface EditProfilePresenter {
    void initUI(Activity activity);
    void showDatePicker(Activity activity);
    void setToolBarOffset(Activity activity, AppBarLayout appBarLayout, int offset, Toolbar toolbar);
    void startAlphaAnimation (View v, long duration, int visibility);
    void getIndustryList(Activity activity, String searchString);
    void onIndustryListFetch(boolean isSuccess, List<IndustryModel> industryList);
    void validateFormFields(Activity activity, String name, String emailId, String DOB, String mobile,
                            String zipcode, String location, String streetNumber, String industry, String gender, int userId);
    void getAddressesFromZipcode(Activity activity, String zipcode);
    void doUpdateUser(Activity activity, UserUpdateInput userUpdateInput);
    void onUpdateUser(boolean isSuccess, String success, String error);
    void showEnterReferCodeDialog();
    void onDestroy();
    void showNoInternetDialog(Activity activity);
}
