package com.webmyne.connect.postLead.model;

import com.webmyne.connect.R;

/**
 * Created by priyasindkar on 11-03-2016.
 */
public class Verticals {

    public enum verticals {
        AUTO_INSURANCE(0, "Auto Insurance", "AI", 14),
        AUTO_FINANCE(1,  "Auto Finance", "AF", 13),
        HEALTH_INSURANCE(2,  "Health Insurance", "HI", 16),
        LIFE_INSURANCE(3,  "Life Insurance", "LI", 17),
        HOME_INSURANCE(4,  "Home Insurance", "HO", 18),
        NEW_CAR(5,  "New Car", "NC", 15);
        Object[] values;

        verticals(Object... vals) {
            values = vals;
        }

        public int returnCODE() {
            return (int) values[0];
        }

        public String returnName() {
            return (String) values[1];
        }

        public String returnShortName() {
            return (String) values[2];
        }

        public int returnVerticalId() {
            return (int) values[3];
        }
    }

    public static int getVerticalCode(int code) {
        switch (code) {
            case 0:
                return verticals.AUTO_INSURANCE.returnCODE();

            case 1:
                return verticals.AUTO_FINANCE.returnCODE();

            case 2:
                return verticals.HEALTH_INSURANCE.returnCODE();

            case 3:
                return verticals.LIFE_INSURANCE.returnCODE();

            case 4:
                return verticals.HOME_INSURANCE.returnCODE();

            case 5:
                return verticals.NEW_CAR.returnCODE();

            default:
                return -1;

        }
    }

    public static String getVerticalName(int code) {
        switch (code) {
            case 0:
                return verticals.AUTO_INSURANCE.returnName();

            case 1:
                return verticals.AUTO_FINANCE.returnName();

            case 2:
                return verticals.HEALTH_INSURANCE.returnName();

            case 3:
                return verticals.LIFE_INSURANCE.returnName();

            case 4:
                return verticals.HOME_INSURANCE.returnName();

            case 5:
                return verticals.NEW_CAR.returnName();

            default:
                return "NA";
        }

    }


    public static String getVerticalShortName(int code) {
        switch (code) {
            case 0:
                return verticals.AUTO_INSURANCE.returnShortName();

            case 1:
                return verticals.AUTO_FINANCE.returnShortName();

            case 2:
                return verticals.HEALTH_INSURANCE.returnShortName();

            case 3:
                return verticals.LIFE_INSURANCE.returnShortName();

            case 4:
                return verticals.HOME_INSURANCE.returnShortName();

            case 5:
                return verticals.NEW_CAR.returnShortName();

            default:
                return "NA";

        }
    }

    public static int getVerticalId(int code) {
        switch (code) {
            case 0:
                return verticals.AUTO_INSURANCE.returnVerticalId();

            case 1:
                return verticals.AUTO_FINANCE.returnVerticalId();

            case 2:
                return verticals.HEALTH_INSURANCE.returnVerticalId();

            case 3:
                return verticals.LIFE_INSURANCE.returnVerticalId();

            case 4:
                return verticals.HOME_INSURANCE.returnVerticalId();

            case 5:
                return verticals.NEW_CAR.returnVerticalId();

            default:
                return -1;

        }
    }


}
