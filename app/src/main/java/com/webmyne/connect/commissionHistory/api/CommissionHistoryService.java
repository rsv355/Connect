package com.webmyne.connect.commissionHistory.api;

import com.webmyne.connect.Utils.APIConstants;
import com.webmyne.connect.commissionHistory.model.CommissionHistoryRequest;
import com.webmyne.connect.commissionHistory.model.CommissionHistoryResponse;
import com.webmyne.connect.leadHistory.model.LeadHistoryRequest;
import com.webmyne.connect.leadHistory.model.LeadHistoryResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CommissionHistoryService {

    @POST(APIConstants.FETCH_COMMISSION_HISTORY)
    Call<CommissionHistoryResponse> getSearchResult(@Body CommissionHistoryRequest requestClass);
}
