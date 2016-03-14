package com.webmyne.connect.base;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import com.daimajia.androidanimations.library.Techniques;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.webmyne.connect.R;
import com.webmyne.connect.Utils.Functions;
import com.webmyne.connect.customUI.CustomAwesomeSplash;
import com.webmyne.connect.customUI.CustomConfigSplash;
import com.webmyne.connect.login.MainActivity;
import com.webmyne.connect.user.EditProfileActivity;
import com.webmyne.connect.user.presenter.EditProfileView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class SplashActivity extends CustomAwesomeSplash {
    public static String printKeyHash(Activity context) {
        PackageInfo packageInfo;
        String key = null;
        try {
            //getting application package name, as defined in manifest
            String packageName = context.getApplicationContext().getPackageName();

            //Retriving package info
            packageInfo = context.getPackageManager().getPackageInfo(packageName,
                    PackageManager.GET_SIGNATURES);

            Log.e("Package Name=", context.getApplicationContext().getPackageName());

            for (Signature signature : packageInfo.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                key = new String(Base64.encode(md.digest(), 0));

                // String key = new String(Base64.encodeBytes(md.digest()));
                Log.e("Key Hash=", key);
            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("Name not found", e1.toString());
        }
        catch (NoSuchAlgorithmException e) {
            Log.e("No such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("Exception", e.toString());
        }

        return key;
    }


    @Override
    public void initSplash(CustomConfigSplash configSplash) {
        /* you don't have to override every property */

        //Customize Circular Reveal

        //printKeyHash(SplashActivity.this);
        configSplash.setBackgroundColor(R.color.splashRippleBackground); //any color you want form colors.xml
        configSplash.setBackgroundResource(R.drawable.kk);

        configSplash.setAnimCircularRevealDuration(800); //int ms
        configSplash.setRevealFlagX(Flags.REVEAL_LEFT);  //or Flags.REVEAL_LEFT
        configSplash.setRevealFlagY(Flags.REVEAL_TOP); //or Flags.REVEAL_TOP

        //Choose LOGO OR PATH; if you don't provide String value for path it's logo by default

        //Customize Logo
        configSplash.setLogoSplash(R.drawable.budget2); //or any other drawable
        configSplash.setAnimLogoSplashDuration(500); //int ms
        configSplash.setAnimLogoSplashTechnique(Techniques.RollIn); //choose one form Techniques (ref: https://github.com/daimajia/AndroidViewAnimations)

        //Customize Title
        configSplash.setTitleSplash(getResources().getString(R.string.app_name));
        configSplash.setTitleTextColor(R.color.white);
        configSplash.setTitleFont(Functions.fontFamilyPathThin);
        configSplash.setTitleTextSize(48f); //float value
        configSplash.setAnimTitleDuration(500);
        configSplash.setAnimTitleTechnique(Techniques.SlideInUp);
    }

    @Override
    public void animationsFinished() {
        SharedPreferences preferences = getSharedPreferences("login-user-prefs", MODE_PRIVATE);
        boolean isUserLoggedIn = preferences.getBoolean("isUserLoggedIn", false);
        Intent intent = null;

        SharedPreferences sharedPreferences1 = getSharedPreferences("user-prefs", MODE_PRIVATE);
        boolean isFirstTimeLogin = sharedPreferences1.getBoolean("isFirstTimeLogin", false);
        if(isFirstTimeLogin) {
            intent = new Intent(SplashActivity.this, EditProfileActivity.class);
        } else {
            if( !isUserLoggedIn) {
                intent = new Intent(SplashActivity.this, MainActivity.class);
            } else {
                intent = new Intent(SplashActivity.this, DrawerActivity.class);
            }
        }
        startActivity(intent);
        finish();
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
}
