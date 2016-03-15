package com.webmyne.connect.user.presenter;

import android.app.Activity;

import com.webmyne.connect.user.model.UserLoginOutput;
import com.webmyne.connect.user.model.UserUpdateInput;

/**
 * Created by priyasindkar on 22-02-2016.
 */
public interface EditProfileInteractor {
    void doCallUpdateUserService(Activity activity, UserUpdateInput userUpdateInput);
    void getIndustryList(Activity activity, String searchString);
}
