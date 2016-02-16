package com.webmyne.connect.customUI;

import com.viksaa.sssplash.lib.cnst.Flags;

/**
 * Created by varsovski on 27-Sep-15.
 */
public class CustomValidationUtil {

    public static int hasPath(CustomConfigSplash cs) {
        if (cs.getPathSplash().isEmpty())
            return Flags.WITH_LOGO;
        else
            return Flags.WITH_PATH;
    }
}
