package com.webmyne.connect.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Android on 23-02-2015.
 */
public class PrefUtils {


    public static boolean isActiveLead(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getBoolean("ActiveLeadStatus", false);
    }

    public static void setActiveLead(Context context, boolean val) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putBoolean("ActiveLeadStatus", val).commit();
    }


}
