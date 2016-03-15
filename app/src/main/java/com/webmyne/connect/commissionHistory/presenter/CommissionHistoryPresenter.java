package com.webmyne.connect.commissionHistory.presenter;

import android.app.Activity;

import com.webmyne.connect.commissionHistory.model.CommissionHistoryRequest;

/**
 * Created by krishnakumar on 10-03-2016.
 */
public interface CommissionHistoryPresenter {
    void fetchLeadData(boolean isRefreshed, CommissionHistoryRequest commissionHistoryRequest);
    void loadMoreData(CommissionHistoryRequest commissionHistoryRequest);
    void showNoInternetDialog(Activity activity);
    void onDestroy();
}
