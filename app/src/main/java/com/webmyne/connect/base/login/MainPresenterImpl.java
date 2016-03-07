package com.webmyne.connect.base.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.plus.People;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;
import com.webmyne.connect.Utils.Functions;
import com.webmyne.connect.base.model.UserProfile;
import com.webmyne.connect.customUI.viewPager.SCPositionAnimation;
import com.webmyne.connect.customUI.viewPager.SCViewAnimation;
import com.webmyne.connect.customUI.viewPager.SCViewAnimationUtil;
import com.webmyne.connect.customUI.viewPager.SCViewPager;

/**
 * Created by priyasindkar on 03-03-2016.
 */
public class MainPresenterImpl {
    private LoginView loginView;

    //GCM
    private GoogleCloudMessaging gcm;
    private String GCM_ID;
    private String deviceId;

    public MainPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
    }

    public void callLoginUser(Context mContext) {
        try {
            if (gcm == null) {
                gcm = GoogleCloudMessaging.getInstance(mContext);
                GCM_ID = "dd";

                Log.e("GCM ID :", GCM_ID);
                if (GCM_ID == null || GCM_ID == "") {

                } else {
                    deviceId = Settings.Secure.getString(mContext.getContentResolver(), Settings.Secure.ANDROID_ID);
                    // call webservice
                }
            }
        } catch (Exception e) {

        }
    }

    public void animateGuidePageView(Context mContext, View guideText1, Point size, SCViewPager mViewPager) {
        TextView textView = (TextView) guideText1;
        textView.setTypeface(Functions.getTypeFace(mContext));
        SCViewAnimation nameTagAnimation = new SCViewAnimation(guideText1);
        nameTagAnimation.addPageAnimation(new SCPositionAnimation(mContext, 0, 0, -size.y / 2));
        nameTagAnimation.addPageAnimation(new SCPositionAnimation(mContext, 0, 0, size.y));
        mViewPager.addAnimation(nameTagAnimation);
    }

    public void animateGuidePageImageSkewType(Context mContext, View guideImage, Point size, SCViewPager mViewPager) {
        SCViewAnimationUtil.prepareViewToGetSize(guideImage);
        SCViewAnimation atSkexAnimation = new SCViewAnimation(guideImage);
        atSkexAnimation.addPageAnimation(new SCPositionAnimation(mContext, 0, 0, -(size.y - guideImage.getHeight())));
        atSkexAnimation.addPageAnimation(new SCPositionAnimation(mContext, 0, -size.x, 0));
        mViewPager.addAnimation(atSkexAnimation);
    }

    public void animateGuidePageTextDjangoType(Context mContext, View guideText, Point size, int forPageStart, int forPageEnd, SCViewPager mViewPager) {
        TextView textView = (TextView) guideText;
        textView.setTypeface(Functions.getTypeFace(mContext));
        SCViewAnimation djangoAnimation = new SCViewAnimation(guideText);
        djangoAnimation.startToPosition(null, -size.y);
        djangoAnimation.addPageAnimation(new SCPositionAnimation(mContext, forPageStart, 0, size.y));
        djangoAnimation.addPageAnimation(new SCPositionAnimation(mContext, forPageEnd, 0, size.y));
        mViewPager.addAnimation(djangoAnimation);
    }

    public void animateGuidePageImageMobileType(Context mContext, View guideImage, Point size, int forPageStart, int forPageEnd, SCViewPager mViewPager) {
        SCViewAnimation mobileAnimation = new SCViewAnimation(guideImage);
        mobileAnimation.startToPosition((int) (size.x * 1.5), null);
        mobileAnimation.addPageAnimation(new SCPositionAnimation(mContext, forPageStart, -(int) (size.x * 1.5), 0));
        mobileAnimation.addPageAnimation(new SCPositionAnimation(mContext, forPageEnd, -(int) (size.x * 1.5), 0));
        mViewPager.addAnimation(mobileAnimation);
    }

    public void animateGuidePageRasberryType(Context mContext, View loginView, Point size, int forPageStart, int forPageEnd, SCViewPager mViewPager) {
        SCViewAnimation raspberryAnimation = new SCViewAnimation(loginView);
        raspberryAnimation.startToPosition(-size.x, null);
        raspberryAnimation.addPageAnimation(new SCPositionAnimation(mContext, forPageStart, size.x, 0));
        raspberryAnimation.addPageAnimation(new SCPositionAnimation(mContext, forPageEnd, -size.x, 0));
        mViewPager.addAnimation(raspberryAnimation);
    }
}
