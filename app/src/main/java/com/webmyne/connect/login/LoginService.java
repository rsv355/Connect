package com.webmyne.connect.login;

import com.webmyne.connect.Utils.APIConstants;
import com.webmyne.connect.login.model.MainUserLoginResponse;
import com.webmyne.connect.login.model.UserProfile;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by priyasindkar on 07-03-2016.
 */
public interface LoginService {
    @POST(APIConstants.USER_LOGIN_URL)
    Call<MainUserLoginResponse> doUserLogin(@Body UserProfile userProfile);
}
