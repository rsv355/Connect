package com.webmyne.connect.user.presenter;

import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.android.gms.gcm.Task;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.webmyne.connect.R;
import com.webmyne.connect.Utils.ComplexPreferences;
import com.webmyne.connect.Utils.Functions;
import com.webmyne.connect.base.MyApplication;
import com.webmyne.connect.user.api.FetchIndustryResult;
import com.webmyne.connect.user.api.FetchIndustryService;
import com.webmyne.connect.user.model.IndustryModel;
import com.webmyne.connect.user.model.MainUserLoginResponse;
import com.webmyne.connect.user.model.UserLoginOutput;
import com.webmyne.connect.user.api.UpdateUserService;
import com.webmyne.connect.user.model.UserUpdateInput;

import java.lang.reflect.Type;
import java.util.List;

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
    public void doCallUpdateUserService(final Activity activity, UserUpdateInput userLoginOutput) {


        Log.e("edit prof input", Functions.jsonString(userLoginOutput));

        if (userLoginOutput != null) {
            UpdateUserService updateUserService = MyApplication.retrofit.create(UpdateUserService.class);
            Call<MainUserLoginResponse> call = updateUserService.doUserProfileUpdate(userLoginOutput);

            call.enqueue(new Callback<MainUserLoginResponse>() {
                @Override
                public void onResponse(Call<MainUserLoginResponse> call, Response<MainUserLoginResponse> response) {

                    Log.e("edit prof output", response.body().toString());

                    if (response.body() != null) {
                        if (response.body().UserLoginOutput != null) {
                            UserLoginOutput user = response.body().UserLoginOutput;
                            if (user.ResponseMessage.equalsIgnoreCase(activity.getString(R.string.success_response_message))) {
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
                   // onUpdateUser(false, "", activity.getString(R.string.update_user_error));
                    onUpdateUser(false, "", t.toString());
                }
            });
        }
    }

    @Override
    public void getIndustryList(Activity activity, String searchString) {
        FetchIndustryService fetchIndustryService = MyApplication.retrofit.create(FetchIndustryService.class);
        Call<FetchIndustryResult> call = fetchIndustryService.listIndustries(searchString);

        call.enqueue(new Callback<FetchIndustryResult>() {
            @Override
            public void onResponse(Call<FetchIndustryResult> call, Response<FetchIndustryResult> response) {
                if (response.body() != null) {
                    List<IndustryModel> industryList = response.body().FetchIndustryResult;
                    editProfilePresenter.onIndustryListFetch(true, industryList);
                } else {
                    editProfilePresenter.onIndustryListFetch(false, null);
                }
            }

            @Override
            public void onFailure(Call<FetchIndustryResult> call, Throwable t) {
                Log.e("onFailure", t.toString());
                editProfilePresenter.onIndustryListFetch(false, null);
            }
        });


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

        SharedPreferences sharedPreferences1 = activity.getSharedPreferences("user-prefs", activity.MODE_PRIVATE);
        sharedPreferences1.edit().clear().commit();
    }
}
