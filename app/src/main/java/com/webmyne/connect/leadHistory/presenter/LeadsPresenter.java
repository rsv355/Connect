package com.webmyne.connect.leadHistory.presenter;

/**
 * Created by krishnakumar on 10-03-2016.
 */
public interface LeadsPresenter {
    void fetchLeadData(long userID);
    void loadMoreData(long userID,long lastLeadID);
}
