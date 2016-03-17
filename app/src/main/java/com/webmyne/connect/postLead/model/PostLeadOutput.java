package com.webmyne.connect.postLead.model;

/**
 * Created by priyasindkar on 08-03-2016.
 */
public class PostLeadOutput {
    public boolean IsActiveLead;
    public String ResponseCode, ResponseMessage;
    public float WalletBalance;

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
