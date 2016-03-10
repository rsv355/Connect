package com.webmyne.connect.user.presenter;

import android.app.Activity;

import com.webmyne.connect.R;
import com.webmyne.connect.Utils.ComplexPreferences;
import com.webmyne.connect.base.MyApplication;
import com.webmyne.connect.user.model.MainUserLoginResponse;
import com.webmyne.connect.user.model.UserLoginOutput;
import com.webmyne.connect.user.api.UpdateUserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by priyasindkar on 22-02-2016.
 */
public class EditProfileInteractorImpl implements EditProfileInteractor {

    private EditProfilePresenter editProfilePresenter;

    public EditProfileInteractorImpl(EditProfilePresenter editProfilePresenter) {
        this.editProfilePresenter = editProfilePresenter;
    }

    @Override
    public void doCallUpdateUserService(final Activity activity, UserLoginOutput userLoginOutput) {
        if (userLoginOutput != null) {
            UpdateUserService updateUserService = MyApplication.retrofit.create(UpdateUserService.class);
            Call<MainUserLoginResponse> call = updateUserService.doUserProfileUpdate(userLoginOutput);

            call.enqueue(new Callback<MainUserLoginResponse>() {
                @Override
                public void onResponse(Call<MainUserLoginResponse> call, Response<MainUserLoginResponse> response) {
                    if (response.body() != null) {
                        if (response.body().UserLoginOutput != null) {
                            UserLoginOutput user = response.body().UserLoginOutput;
                            if (user.ResponseMessage.equalsIgnoreCase(activity.getString(R.string.success_response_code))) {
                                saveUpdatedUser(activity, user);
                                onUpdateUser(true, activity.getString(R.string.update_user_success), "");
                            } else {
                                onUpdateUser(false, "", activity.getString(R.string.update_user_failed));
                            }
                        } else {
                            onUpdateUser(false, "", activity.getString(R.string.update_user_error));
                        }
                    }
                }

                @Override
                public void onFailure(Call<MainUserLoginResponse> call, Throwable t) {
                    onUpdateUser(false, "", activity.getString(R.string.update_user_error));
                }
            });
        }
    }

    private void onUpdateUser(boolean isSuccess, String success, String error) {
        if (editProfilePresenter != null) {
            editProfilePresenter.onUpdateUser(isSuccess, success, error);
        }
    }

    private void saveUpdatedUser(Activity activity, UserLoginOutput user) {
        ComplexPreferences complexPreferences = new ComplexPreferences(activity, "login-user", activity.MODE_PRIVATE);
        complexPreferences.putObject("loggedInUser", user);
        complexPreferences.commit();
    }
}
