package com.webmyne.connect.user.api;

import com.webmyne.connect.Utils.APIConstants;
import com.webmyne.connect.user.model.MainUserLoginResponse;
import com.webmyne.connect.user.model.UserLoginOutput;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by priyasindkar on 07-03-2016.
 */
public interface UpdateUserService {
    @POST(APIConstants.USER_PROFILE_UPDATE_URL)
    Call<MainUserLoginResponse> doUserProfileUpdate(@Body UserLoginOutput userLoginOutput);
}
