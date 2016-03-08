package com.webmyne.connect.leads.model;

import java.util.Arrays;

/**
 * Created by priyasindkar on 08-03-2016.
 */
public class PostLeadInput {
    public String Description, REmail, RMobile, RName;
    public int UserID;
    public int[] lstLeadTypeId;

    public PostLeadInput() {
    }

    public PostLeadInput(String description, String REmail, String RMobile, String RName, int userID, int[] lstLeadTypeId) {
        Description = description;
        this.REmail = REmail;
        this.RMobile = RMobile;
        this.RName = RName;
        UserID = userID;
        this.lstLeadTypeId = lstLeadTypeId;
    }

    @Override
    public String toString() {
        return "PostLeadInput{" +
                "Description='" + Description + '\'' +
                ", REmail='" + REmail + '\'' +
                ", RMobile='" + RMobile + '\'' +
                ", RName='" + RName + '\'' +
                ", UserID=" + UserID +
                ", lstLeadTypeId=" + Arrays.toString(lstLeadTypeId) +
                '}';
    }
}
