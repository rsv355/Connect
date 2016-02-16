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


    public static Typeface getTypeFace(Context ctx){
        Typeface typeface = Typeface.createFromAsset(ctx.getAssets(), fontFamilyPathThin);
        return  typeface;
    }

    public static void setVerticalView (Context _ctx, ImageView verticalImageView, String shortName, int index) {
        int color = ColorGenerator.MATERIAL.getColorAtIndex(index);
        TextDrawable drawable2 = TextDrawable.builder().buildRound(shortName, color);
        verticalImageView.setImageDrawable(drawable2);
    }
}
