package com.webmyne.connect.revenuePayment.model;

/**
 * Created by priyasindkar on 12-02-2016.
 */
public class PaymentHistoryDataObject {
    private String leadName ,dateTime, customerName;
    private int color;
    private double amount, redeeemAmount;

    public PaymentHistoryDataObject() {
    }

    public PaymentHistoryDataObject(String leadName, double amount, String dateTime, double redeeemAmount, String customerName, int color) {
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public double getRedeeemAmount() {
        return redeeemAmount;
    }

    public void setRedeeemAmount(double redeeemAmount) {
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
