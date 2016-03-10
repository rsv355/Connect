package com.webmyne.connect.user.presenter;

import android.app.Activity;

import com.webmyne.connect.user.model.UserLoginOutput;

/**
 * Created by priyasindkar on 22-02-2016.
 */
public interface EditProfileInteractor {
    void doCallUpdateUserService(Activity activity, UserLoginOutput userLoginOutput);
}
