package com.webmyne.connect.leadHistory.model;

/**
 * Created by priyasindkar on 11-03-2016.
 */
public enum test{
    IN_PROGRESS(0,"In Process","description 1"),
    VERIFICATION_DONE(1,"Verification Done","description 1"),
    ACTIVE(2,"Active","description 1"),
    SOLD(3,"Sold","description 1"),
    EXPIRED(4,"Expired","description 1"),
    USER_NOT_INTERESTED(5,"User Denied","description 1");

    test(int i,String val,String desc) {
    }

    public String getStatusMSG(int code){
        String msg="";

        return msg;
    }

    public String getStatusDesc(int code){
        String desc="";

        return desc;
    }
}