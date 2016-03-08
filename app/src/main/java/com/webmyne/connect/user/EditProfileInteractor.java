package com.webmyne.connect.user;

import android.app.Activity;

import com.webmyne.connect.login.model.UserLoginOutput;

/**
 * Created by priyasindkar on 22-02-2016.
 */
public interface EditProfileInteractor {
    void doCallUpdateUserService(Activity activity, UserLoginOutput userLoginOutput);
}
