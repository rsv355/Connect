package com.webmyne.connect.user.api;

import com.webmyne.connect.Utils.APIConstants;
import com.webmyne.connect.user.model.IndustryModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by priyasindkar on 15-03-2016.
 */
public interface FetchIndustryService {
    @GET(APIConstants.FETCH_INDUSTRY_URL)
    Call<FetchIndustryResult> listIndustries(@Path("Searchstr") String searchString);
}
