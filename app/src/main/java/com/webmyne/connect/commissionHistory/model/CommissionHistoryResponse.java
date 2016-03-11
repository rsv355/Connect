package com.webmyne.connect.commissionHistory.model;

import com.webmyne.connect.leadHistory.model.LeadHistoryData;

import java.util.ArrayList;

/**
 * Created by krishnakumar on 08-03-2016.
 */
public class CommissionHistoryResponse {
    public ArrayList<CommissionHistoryData> CommissionHistoryOutput;

    public ArrayList<CommissionHistoryData> getCommissionData() {
        return CommissionHistoryOutput;
    }

    public void setCommissionData(ArrayList<CommissionHistoryData> commissionData) {
        this.CommissionHistoryOutput = commissionData;
    }
}
