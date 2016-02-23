package com.webmyne.connect.revenue;

/**
 * Created by priyasindkar on 12-02-2016.
 */
public class PaymentHistoryDataObject {
    private String leadName ,dateTime, redeeemAmount, customerName;
    private int color;
    private long amount;

    public PaymentHistoryDataObject() {
    }

    public PaymentHistoryDataObject(String leadName, long amount, String dateTime, String redeeemAmount, String customerName, int color) {
        this.leadName = leadName;
        this.amount = amount;
        this.dateTime = dateTime;
        this.redeeemAmount = redeeemAmount;
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

    public String getRedeeemAmount() {
        return redeeemAmount;
    }

    public void setRedeeemAmount(String redeeemAmount) {
        this.redeeemAmount = redeeemAmount;
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
