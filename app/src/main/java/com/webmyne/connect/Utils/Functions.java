package com.webmyne.connect.Utils;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.ImageView;

import com.webmyne.connect.R;
import com.webmyne.connect.customUI.textDrawableIcons.ColorGenerator;
import com.webmyne.connect.customUI.textDrawableIcons.TextDrawable;

/**
 * Created by priyasindkar on 11-02-2016.
 */
public class Functions {

    public static String fontFamilyPathThin = "fonts/HelveticaNeue-ThinExt.otf";
    public static String referCodeFontPathThin = "fonts/KaushanScript-Regular.otf";


    public static Typeface getTypeFace(Context ctx) {
        Typeface typeface = Typeface.createFromAsset(ctx.getAssets(), fontFamilyPathThin);
        return typeface;
    }

    public static Typeface getReferCodeTypeFace(Context ctx) {
        Typeface typeface = Typeface.createFromAsset(ctx.getAssets(), referCodeFontPathThin);
        return typeface;
    }

    public static AlertDialog.Builder getSimpleOkAlterDialog(Context ctx, String title, String positiveMessage) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx, R.style.MaterialBaseTheme_Light_AlertDialog);
        builder.setMessage(title);
        builder.setPositiveButton(positiveMessage, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        return builder;

    }
}
