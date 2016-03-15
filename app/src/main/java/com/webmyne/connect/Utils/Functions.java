package com.webmyne.connect.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.webmyne.connect.R;
import com.webmyne.connect.listeners.OnAlertButtonClicked;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
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
    private static final float PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR = 0.9f;
    private static final int ALPHA_ANIMATIONS_DURATION = 200;
    private static boolean mIsTheToolbarVisible = false;

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
        builder.setMessage(title);
        builder.setPositiveButton(positiveMessage, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                onAlertButtonClicked.onAlertButtonClicked();
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

    /* Collapsible toolbar offset change handling */
    public static void setToolBarOffset(Activity activity, AppBarLayout appBarLayout, int offset, Toolbar toolbar) {
        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(offset) / (float) maxScroll;
        handleToolbarVisibility(percentage, toolbar);
    }

    public static void handleToolbarVisibility(float percentage, Toolbar toolbar) {
        if (percentage >= PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR || percentage == 0) {
            if (!mIsTheToolbarVisible) {
                startAlphaAnimation(toolbar, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsTheToolbarVisible = true;
            }
        } else {
            if (mIsTheToolbarVisible) {
                startAlphaAnimation(toolbar, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mIsTheToolbarVisible = false;
            }
        }
    }

    public static void startAlphaAnimation(View v, long duration, int visibility) {
        AlphaAnimation alphaAnimation = (visibility == View.VISIBLE)
                ? new AlphaAnimation(0f, 1f)
                : new AlphaAnimation(1f, 0f);

        alphaAnimation.setDuration(duration);
        alphaAnimation.setFillAfter(true);
        v.startAnimation(alphaAnimation);
    }

    public static boolean checkInternet(Context _ctx) {
        ConnectivityManager connectivityManager = (ConnectivityManager) _ctx.getSystemService(_ctx.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED) /*||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_ETHERNET).getState() == NetworkInfo.State.CONNECTED) */ {
            return true;
        } else {
            return false;
        }
    }

    public static void showSnackMessage(Context _ctx, View v, String msg) {
        Snackbar snackbar = Snackbar.make(v, msg, Snackbar.LENGTH_LONG)
                .setActionTextColor(_ctx.getResources().getColor(R.color.white));

        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(_ctx.getResources().getColor(R.color.accent_A100));
        snackbar.show();
    }

    public static int[] convertHashMapToIntArray(HashMap<Integer, Integer> map) {
        int[] array = new int[map.size()];
        int i = 0;
        Set entries = map.entrySet();
        Iterator entriesIterator = entries.iterator();
        while (entriesIterator.hasNext()) {
            Map.Entry pair = (Map.Entry) entriesIterator.next();
            array[i] = (int) pair.getKey();
            i++;
        }
        return array;
    }

    public static boolean validateStartEndDates(String startDateStr, String endDateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate, endDate = null;
        boolean result = false;
        try {
            endDate = sdf.parse(endDateStr.trim());
            startDate = sdf.parse(startDateStr);

            if (startDate.after(endDate)) {
                result = false;
            } else {
                result = true;
            }
        } catch (ParseException e) {
        }
        return result;
    }
}
