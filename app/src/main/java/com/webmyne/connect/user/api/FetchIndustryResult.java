package com.webmyne.connect.user.api;

import com.webmyne.connect.user.model.IndustryModel;

import java.util.List;

/**
 * Created by priyasindkar on 15-03-2016.
 */
public class FetchIndustryResult {
    public List<IndustryModel> FetchIndustryResult;

    public List<IndustryModel> getFetchIndustryResult() {
        return FetchIndustryResult;
    }

    public void setFetchIndustryResult(List<IndustryModel> fetchIndustryResult) {
        FetchIndustryResult = fetchIndustryResult;
    }
}
