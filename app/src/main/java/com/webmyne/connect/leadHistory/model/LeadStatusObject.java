package com.webmyne.connect.leadHistory.model;

/**
 * Created by priyasindkar on 14-03-2016.
 */
public class LeadStatusObject {
    int leadStatusId;
    String leadStatusName, leadStatusDescription;

    public int getLeadStatusId() {
        return leadStatusId;
    }

    public void setLeadStatusId(int leadStatusId) {
        this.leadStatusId = leadStatusId;
    }

    public String getLeadStatusName() {
        return leadStatusName;
    }

    public void setLeadStatusName(String leadStatusName) {
        this.leadStatusName = leadStatusName;
    }

    public String getLeadStatusDescription() {
        return leadStatusDescription;
    }

    public void setLeadStatusDescription(String leadStatusDescription) {
        this.leadStatusDescription = leadStatusDescription;
    }
}
