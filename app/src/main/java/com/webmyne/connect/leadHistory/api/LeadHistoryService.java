package com.webmyne.connect.leadHistory.api;

import com.webmyne.connect.Utils.APIConstants;
import com.webmyne.connect.leadHistory.model.LeadHistoryRequest;
import com.webmyne.connect.leadHistory.model.LeadHistoryResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LeadHistoryService {

    @POST(APIConstants.FETCH_LEAD_HISTORY)
    Call<LeadHistoryResponse> getSearchResult(@Body LeadHistoryRequest requestClass);
}
