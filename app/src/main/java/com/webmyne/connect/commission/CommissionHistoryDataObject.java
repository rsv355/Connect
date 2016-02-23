package com.webmyne.connect.commission;

/**
 * Created by priyasindkar on 12-02-2016.
 */
public class CommissionHistoryDataObject {
    private String leadName ,dateTime, customerName;
    private int color;
    private double commissionAmount, boughtAmount;

    public CommissionHistoryDataObject() {
    }

    public CommissionHistoryDataObject(String leadName, double boughtAmount, double commissionAmount, String dateTime, String customerName, int color) {
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

    public double getCommissionAmount() {
        return commissionAmount;
    }

    public void setCommissionAmount(double commissionAmount) {
        this.commissionAmount = commissionAmount;
    }

    public double getBoughtAmount() {
        return boughtAmount;
    }

    public void setBoughtAmount(double boughtAmount) {
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
