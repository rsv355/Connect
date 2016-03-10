package com.webmyne.connect.leadHistory.model;

/**
 * Created by krishnakumar on 08-03-2016.
 */
public class LeadHistoryRequest {
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
