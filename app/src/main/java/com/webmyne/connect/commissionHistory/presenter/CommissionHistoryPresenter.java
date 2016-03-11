package com.webmyne.connect.commissionHistory.presenter;

import android.app.Activity;

/**
 * Created by krishnakumar on 10-03-2016.
 */
public interface CommissionHistoryPresenter {
    void fetchLeadData(boolean isRefreshed, long userID);
    void loadMoreData(long userID, long lastLeadID);
    void showNoInternetDialog(Activity activity);
    void onDestroy();
}
