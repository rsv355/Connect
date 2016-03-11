package com.webmyne.connect.commissionHistory.model;

/**
 * Created by krishnakumar on 08-03-2016.
 */
public class CommissionHistoryRequest {
    public long UserID;
    public long LastLeadID;

    public long getUserID() {
        return UserID;
    }

    public void setUserID(long UserID) {
        this.UserID = UserID;
    }

    public long getLastLeadID() {
        return LastLeadID;
    }

    public void setLastLeadID(long lastLeadID) {
        LastLeadID = lastLeadID;
    }
}
