package com.webmyne.connect.leadHistory.model;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LeadHistoryIntf {

    @POST("json/LeadHistory")
    Call<LeadHistoryResponse> getSearchResult(@Body LeadHistoryRequest requestClass);
}
