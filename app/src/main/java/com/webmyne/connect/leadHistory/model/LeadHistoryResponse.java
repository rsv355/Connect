package com.webmyne.connect.leadHistory.model;

import java.util.ArrayList;

/**
 * Created by krishnakumar on 08-03-2016.
 */
public class LeadHistoryResponse {
    public ArrayList<LeadHistoryData> LeadHistoryOutput;

    public ArrayList<LeadHistoryData> getLeadData() {
        return LeadHistoryOutput;
    }

    public void setLeadData(ArrayList<LeadHistoryData> leadData) {
        this.LeadHistoryOutput = leadData;
    }
}
