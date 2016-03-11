package com.webmyne.connect.Utils;

import java.util.Arrays;
import java.util.List;

/**
 * Created by priyasindkar on 12-02-2016.
 */
public class Constants {
    public static int TOTAL_VERTICALS = 6;
    public static final List<String> VERTICAL_NAMES = Arrays.asList("Auto Insurance", "Auto Finance", "Health Insurance",
            "Life Insurance" , "Home Insurance", "New Car");
    public static final List<String> VERTICAL_SHORT_NAMES = Arrays.asList("AI", "AF", "HI", "LI" , "HO", "NC");
    //public static final int[] VERTICAL_IDS = new int[] {14, 13, 16, 17, 18, 15};
    public static final List<Integer> VERTICAL_IDS = Arrays.asList(14, 13, 16, 17, 18, 15);

    public static String getVerticalShortName(int veticalID){
        int pos =0;
        for(int i=0;i<VERTICAL_IDS.size();i++){
            if(veticalID == VERTICAL_IDS.get(i))
             pos = i;
        }

        return VERTICAL_SHORT_NAMES.get(pos);
    }

    public static enum VERTICALS {
        AUTO_INSURANCE(0),
        AUTO_FINANCE(1),
        HEALTH_INSURANCE(2),
        LIFE_INSURANCE(3),
        HOME_INSURANCE(4),
        NEW_CAR(5);

        private int value;

        VERTICALS(int i) {
            i = value;
        }

        public int getValue() {
            return value;
        }
    }



    public static int KEYBOARD_CELL_ITEM_ANIMATION_DURATION = 800;

    public static String[] FB_READ_PERMISSIONS = new String[]{"email", "public_profile", "user_friends"};
    public static String FB_PARAM_FIELDS = "id, first_name,last_name, email, gender, birthday, link";

    public static String DOLLAR_PREFIX = "$ ";
    public static String SP_PREFIX = "Lead Sold Price ";
}
