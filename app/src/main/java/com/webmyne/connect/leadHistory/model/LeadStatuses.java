package com.webmyne.connect.leadHistory.model;

import com.webmyne.connect.R;

/**
 * Created by priyasindkar on 11-03-2016.
 */
public class LeadStatuses {

    public enum test1 {
        IN_PROGRESS(0, "InProcess", "Your submission is in process", R.color.accent_A200),
        VERIFICATION_DONE(1, "Verification Done", "User verification done", R.color.cyan_color),
        ACTIVE(2, "Active", "Your lead is now active!", R.color.green_color),
        SOLD(3, "Sold", "Congratulations! Your need sold", R.color.appYellowColor),
        EXPIRED(4, "Expired", "Sorry! Your lead expired", R.color.google),
        USER_NOT_INTERESTED(5, "User Denied", "Your reference denied interest", R.color.theme_500);

        Object[] values;

        test1(Object... vals) {
            values = vals;
        }


        public int returnCODE() {
            return (int) values[0];
        }

        public String returnMSG() {
            return (String) values[1];
        }

        public String returnDESC() {
            return (String) values[2];
        }

        public int returnCOLOR() {
            return (int) values[3];
        }
    }

    public static int getStatusCode(int code) {
        switch (code) {
            case 0:
                return test1.IN_PROGRESS.returnCODE();

            case 1:
                return test1.VERIFICATION_DONE.returnCODE();

            case 2:
                return test1.ACTIVE.returnCODE();

            case 3:
                return test1.SOLD.returnCODE();

            case 4:
                return test1.EXPIRED.returnCODE();

            case 5:
                return test1.USER_NOT_INTERESTED.returnCODE();

            default:
                return -1;

        }
    }

    public static String getStatusMSG(int code) {
        switch (code) {
            case 0:
                return test1.IN_PROGRESS.returnMSG();

            case 1:
                test1.VERIFICATION_DONE.returnMSG();

            case 2:
                return test1.ACTIVE.returnMSG();

            case 3:
                return test1.SOLD.returnMSG();

            case 4:
                return test1.EXPIRED.returnMSG();

            case 5:
                return test1.USER_NOT_INTERESTED.returnMSG();

            default:
                return "NA";
        }

    }


    public static String getStatusDesc(int code) {
        switch (code) {
            case 0:
                return test1.IN_PROGRESS.returnDESC();

            case 1:
                return test1.VERIFICATION_DONE.returnDESC();

            case 2:
                return test1.ACTIVE.returnDESC();

            case 3:
                return test1.SOLD.returnDESC();

            case 4:
                return test1.EXPIRED.returnDESC();

            case 5:
                return test1.USER_NOT_INTERESTED.returnDESC();

            default:
                return "NA";

        }

    }

    public static int getStatusColor(int code) {
        switch (code) {
            case 0:
                return test1.IN_PROGRESS.returnCOLOR();

            case 1:
                return test1.VERIFICATION_DONE.returnCOLOR();

            case 2:
                return test1.ACTIVE.returnCOLOR();

            case 3:
                return test1.SOLD.returnCOLOR();

            case 4:
                return test1.EXPIRED.returnCOLOR();

            case 5:
                return test1.USER_NOT_INTERESTED.returnCOLOR();

            default:
                return -1;

        }
    }


}
