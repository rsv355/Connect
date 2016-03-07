package com.webmyne.connect.Utils;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v4.media.VolumeProviderCompat;
import android.support.v7.app.AlertDialog;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.webmyne.connect.R;
import com.webmyne.connect.customUI.textDrawableIcons.ColorGenerator;
import com.webmyne.connect.customUI.textDrawableIcons.TextDrawable;

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

    public static String getGCMid(final Context mContext) {
        if (isGooglePlayServiceAvailable(mContext)) {
           new AsyncTask<Void, Void, String>() {
                @Override
                protected String doInBackground(Void... params) {
                    Log.e("doInBackground :", "doInBackground");
                    try {
                        if (gcm == null) {
                            gcm = GoogleCloudMessaging.getInstance(mContext);
                        }
                        regid = gcm.register(mContext.getString(R.string.project_id));
                        //regid ="dd";

                        Log.e("regid :", regid);
                        if (regid == null || regid == "") {
                            regid = "";
                            AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
                            alert.setTitle("Error");
                            alert.setMessage("Internal Server Error");
                            alert.setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    getGCMid(mContext);
                                    dialog.dismiss();
                                }
                            });
                            alert.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            alert.show();
                        } else {
                            return regid;
                        }
                    } catch (Exception ex) {
                        Log.e("GCM EXP", ex.toString());
                    }
                    return null;
                }

               @Override
               protected void onPostExecute(String s) {
                   super.onPostExecute(s);

               }
           }.execute();

        } else {
            Toast.makeText(mContext, "Play Services not found.", Toast.LENGTH_LONG).show();
        }
        return regid;
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
}
