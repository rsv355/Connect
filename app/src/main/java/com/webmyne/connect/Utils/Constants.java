package com.webmyne.connect.Utils;

import java.util.Arrays;
import java.util.List;

/**
 * Created by priyasindkar on 12-02-2016.
 */
public class Constants {
    public static final List<String> VERTICAL_NAMES = Arrays.asList("Auto Insurance", "Auto Finance", "Health Insurance",
            "Life Insurance" , "Home Insurance", "New Car");
    public static final List<String> VERTICAL_SHORT_NAMES = Arrays.asList("AI", "AF", "HI", "LI" , "HO", "NC");

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
}
