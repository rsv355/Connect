package com.webmyne.connect.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v4.media.VolumeProviderCompat;
import android.support.v7.app.AlertDialog;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.webmyne.connect.R;
import com.webmyne.connect.base.DrawerActivity;
import com.webmyne.connect.customUI.textDrawableIcons.ColorGenerator;
import com.webmyne.connect.customUI.textDrawableIcons.TextDrawable;
import com.webmyne.connect.listeners.OnAlertButtonClicked;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by priyasindkar on 11-02-2016.
 */
public class Functions {

    public static String fontFamilyPathThin = "fonts/HelveticaNeue-ThinExt.otf";
    public static String referCodeFontPathThin = "fonts/KaushanScript-Regular.otf";
    static String regid;
    static String driverIMEI_Number;
    static GoogleCloudMessaging gcm;
    private static Context ctx;

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static Pattern pattern;
    private static Matcher matcher;
    private static OnAlertButtonClicked onAlertButtonClicked;


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

    public static AlertDialog.Builder showAlterDialog(Context ctx, String title, String positiveMessage) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(ctx, R.style.MaterialBaseTheme_Light_AlertDialog);
        //builder.setTitle(title);
        builder.setMessage(title);
        builder.setPositiveButton(positiveMessage, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                onAlertButtonClicked.onAlertButtonClicked();
                dialog.dismiss();
            }
        });
        return builder;
    }


    public static boolean isGooglePlayServiceAvailable(Context mContext) {
        boolean flag = false;
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(mContext.getApplicationContext());
        if (status == ConnectionResult.SUCCESS) {
            flag = true;
        } else {
            flag = false;
        }
        return flag;
    }

    public static boolean isEmailValid(String emailId) {
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(emailId);
        return matcher.matches();
    }

    public static void setOnAlertButtonClicked (OnAlertButtonClicked _onAlertButtonClicked) {
        onAlertButtonClicked = _onAlertButtonClicked;
    }

    public static DisplayMetrics getDeviceMetrics(Activity context) {
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        display.getMetrics(metrics);
        return metrics;
    }
}
