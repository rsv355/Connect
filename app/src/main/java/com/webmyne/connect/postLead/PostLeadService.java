package com.webmyne.connect.postLead;

import com.webmyne.connect.Utils.APIConstants;
import com.webmyne.connect.postLead.model.MainPostLeadOutput;
import com.webmyne.connect.postLead.model.PostLeadInput;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by priyasindkar on 08-03-2016.
 */
public interface PostLeadService {
    @POST(APIConstants.POST_LEAD_URL)
    Call<MainPostLeadOutput> doUserProfileUpdate(@Body PostLeadInput postLeadInput);
}
