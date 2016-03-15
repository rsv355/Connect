package com.webmyne.connect.leadHistory.presenter;

import android.app.Activity;

import com.webmyne.connect.leadHistory.model.LeadHistoryRequest;

/**
 * Created by krishnakumar on 10-03-2016.
 */
public interface LeadsHistoryPresenter {
    void fetchLeadData(boolean isRefreshed, LeadHistoryRequest leadHistoryRequest);

    void loadMoreData(LeadHistoryRequest leadHistoryRequest);

    void showNoInternetDialog(Activity activity);

    void onDestroy();
}
