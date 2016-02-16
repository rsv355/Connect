package com.webmyne.connect.dashboard;

/**
 * Created by priyasindkar on 12-02-2016.
 */
public class LeadDataObject {
    private String leadName, verticalName ,dateTime, status, customerName;
    private int color;

    public LeadDataObject() {
    }

    public LeadDataObject(String leadName, String verticalName, String dateTime, String status, String customerName, int color) {
        this.leadName = leadName;
        this.verticalName = verticalName;
        this.dateTime = dateTime;
        this.status = status;
        this.customerName = customerName;
        this.color = color;
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
}
