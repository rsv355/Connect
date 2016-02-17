package com.webmyne.connect.revenue;

/**
 * Created by priyasindkar on 12-02-2016.
 */
public class RevenueHistoryDataObject {
    private String leadName ,dateTime, status, customerName;
    private int color;
    private long amount;

    public RevenueHistoryDataObject() {
    }

    public RevenueHistoryDataObject(String leadName, long amount, String dateTime, String status, String customerName, int color) {
        this.leadName = leadName;
        this.amount = amount;
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

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getDateTime() {
        return dateTime;
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
