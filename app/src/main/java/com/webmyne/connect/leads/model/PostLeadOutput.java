package com.webmyne.connect.leads.model;

/**
 * Created by priyasindkar on 08-03-2016.
 */
public class PostLeadOutput {
    public boolean IsActiveLead;
    public String ResponseCode, ResponseMessage;
    public long WalletBalance;

    @Override
    public String toString() {
        return "PostLeadOutput{" +
                "IsActiveLead=" + IsActiveLead +
                ", ResponseCode='" + ResponseCode + '\'' +
                ", ResponseMessage='" + ResponseMessage + '\'' +
                ", WalletBalance=" + WalletBalance +
                '}';
    }
}
