package com.webmyne.connect.commission;

/**
 * Created by priyasindkar on 12-02-2016.
 */
public class CommissionHistoryDataObject {
    private String leadName ,dateTime, customerName;
    private int color;
    private long commissionAmount, boughtAmount;

    public CommissionHistoryDataObject() {
    }

    public CommissionHistoryDataObject(String leadName, long boughtAmount, long commissionAmount, String dateTime, String customerName, int color) {
        this.leadName = leadName;
        this.dateTime = dateTime;
        this.customerName = customerName;
        this.color = color;
        this.commissionAmount = commissionAmount;
        this.boughtAmount = boughtAmount;
    }

    public String getLeadName() {
        return leadName;
    }

    public void setLeadName(String leadName) {
        this.leadName = leadName;
    }

    public long getCommissionAmount() {
        return commissionAmount;
    }

    public void setCommissionAmount(long commissionAmount) {
        this.commissionAmount = commissionAmount;
    }

    public long getBoughtAmount() {
        return boughtAmount;
    }

    public void setBoughtAmount(long boughtAmount) {
        this.boughtAmount = boughtAmount;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
