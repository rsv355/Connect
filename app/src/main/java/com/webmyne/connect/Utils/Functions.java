package com.webmyne.connect.Utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.widget.ImageView;

import com.webmyne.connect.customUI.textDrawableIcons.ColorGenerator;
import com.webmyne.connect.customUI.textDrawableIcons.TextDrawable;

/**
 * Created by priyasindkar on 11-02-2016.
 */
public class Functions {

    public static String fontFamilyPathThin = "fonts/HelveticaNeue-ThinExt.otf";
    public static String referCodeFontPathThin = "fonts/KaushanScript-Regular.otf";


    public static Typeface getTypeFace(Context ctx){
        Typeface typeface = Typeface.createFromAsset(ctx.getAssets(), fontFamilyPathThin);
        return  typeface;
    }

    public static Typeface getReferCodeTypeFace(Context ctx){
        Typeface typeface = Typeface.createFromAsset(ctx.getAssets(), referCodeFontPathThin);
        return  typeface;
    }

}
