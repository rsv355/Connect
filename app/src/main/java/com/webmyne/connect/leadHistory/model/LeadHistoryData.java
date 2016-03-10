package com.webmyne.connect.leadHistory.model;

/**
 * Created by krishnakumar on 08-03-2016.
 */
public class LeadHistoryData {

    public String LeadDateTime;
    public long LeadID;
    public String LeadTypeID;
    public String RMobile;
    public String RName;
    public String SoldPrice;
    public String Status;

    public String getLeadDateTime() {
        return LeadDateTime;
    }

    public void setLeadDateTime(String leadDateTime) {
        LeadDateTime = leadDateTime;
    }

    public long getLeadID() {
        return LeadID;
    }

    public void setLeadID(long leadID) {
        LeadID = leadID;
    }

    public String getLeadTypeID() {
        return LeadTypeID;
    }

    public void setLeadTypeID(String leadTypeID) {
        LeadTypeID = leadTypeID;
    }

    public String getRMobile() {
        return RMobile;
    }

    public void setRMobile(String RMobile) {
        this.RMobile = RMobile;
    }

    public String getRName() {
        return RName;
    }

    public void setRName(String RName) {
        this.RName = RName;
    }

    public String getSoldPrice() {
        return SoldPrice;
    }

    public void setSoldPrice(String soldPrice) {
        SoldPrice = soldPrice;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
