package com.webmyne.connect.commissionHistory.model;

/**
 * Created by priyasindkar on 12-02-2016.
 */
public class CommissionDataObject {
    private String leadName, verticalName ,dateTime, status, customerName, soldPrice, commissionEarned;
    private int color;

    public CommissionDataObject() {
    }

    public CommissionDataObject(String leadName, String verticalName, String dateTime, String status, String customerName, String soldPrice, String commissionEarned ,int color) {
        this.leadName = leadName;
        this.verticalName = verticalName;
        this.dateTime = dateTime;
        this.status = status;
        this.customerName = customerName;
        this.soldPrice = soldPrice;
        this.commissionEarned = commissionEarned;
        this.color = color;
    }

    public String getLeadID() {
        //removing vertical short name
        return leadName.substring(2,leadName.length());
    }

    public String getLeadName() {
        return leadName;
    }

    public void setLeadName(String leadName) {
        this.leadName = leadName;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getVerticalName() {
        return verticalName;
    }

    public void setVerticalName(String verticalName) {
        this.verticalName = verticalName;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getSoldPrice() {
        return soldPrice;
    }

    public void setSoldPrice(String soldPrice) {
        this.soldPrice = soldPrice;
    }

    public String getCommissionEarned() {
        return commissionEarned;
    }

    public void setCommissionEarned(String commissionEarned) {
        this.commissionEarned = commissionEarned;
    }
}
