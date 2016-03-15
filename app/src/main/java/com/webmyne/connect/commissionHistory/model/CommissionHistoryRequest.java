package com.webmyne.connect.commissionHistory.model;

/**
 * Created by krishnakumar on 08-03-2016.
 */
public class CommissionHistoryRequest {
    public long UserID, LastLeadID;
    public String EndDate,  SearchInput, StartDate;
    public int[] lstLeadTypeId;

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

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String endDate) {
        EndDate = endDate;
    }

    public String getSearchInput() {
        return SearchInput;
    }

    public void setSearchInput(String searchInput) {
        SearchInput = searchInput;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public int[] getLstLeadTypeId() {
        return lstLeadTypeId;
    }

    public void setLstLeadTypeId(int[] lstLeadTypeId) {
        this.lstLeadTypeId = lstLeadTypeId;
    }
}
