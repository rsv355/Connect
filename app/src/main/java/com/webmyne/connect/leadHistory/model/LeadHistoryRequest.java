package com.webmyne.connect.leadHistory.model;

import java.util.Arrays;

/**
 * Created by krishnakumar on 08-03-2016.
 */
public class LeadHistoryRequest {
    public long UserID, LastLeadID;
    public String EndDate, SearchInput, StartDate;
    public int[] lstLeadStatus, lstLeadTypeId;

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

    public int[] getLstLeadStatus() {
        return lstLeadStatus;
    }

    public void setLstLeadStatus(int[] lstLeadStatus) {
        this.lstLeadStatus = lstLeadStatus;
    }

    public int[] getLstLeadTypeId() {
        return lstLeadTypeId;
    }

    public void setLstLeadTypeId(int[] lstLeadTypeId) {
        this.lstLeadTypeId = lstLeadTypeId;
    }

    @Override
    public String toString() {
        return "LeadHistoryRequest{" +
                "UserID=" + UserID +
                ", LastLeadID=" + LastLeadID +
                ", EndDate='" + EndDate + '\'' +
                ", SearchInput='" + SearchInput + '\'' +
                ", StartDate='" + StartDate + '\'' +
                ", lstLeadStatus=" + Arrays.toString(lstLeadStatus) +
                ", lstLeadTypeId=" + Arrays.toString(lstLeadTypeId) +
                '}';
    }
}
