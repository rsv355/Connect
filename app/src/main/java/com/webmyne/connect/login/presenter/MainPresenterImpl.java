package com.webmyne.connect.login.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.view.View;
import android.widget.TextView;

import com.webmyne.connect.Utils.ComplexPreferences;
import com.webmyne.connect.Utils.Functions;
import com.webmyne.connect.user.model.UserLoginOutput;
import com.webmyne.connect.customUI.viewPager.SCPositionAnimation;
import com.webmyne.connect.customUI.viewPager.SCViewAnimation;
import com.webmyne.connect.customUI.viewPager.SCViewAnimationUtil;
import com.webmyne.connect.customUI.viewPager.SCViewPager;

/**
 * Created by priyasindkar on 03-03-2016.
 */
public class MainPresenterImpl implements MainPresenter{
    private LoginView loginView;

    public MainPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
    }

    public void callLoginUser(Context mContext) {

    }

    @Override
    public void animateGuidePageView(Context mContext, View guideText1, Point size, SCViewPager mViewPager) {
        TextView textView = (TextView) guideText1;
        textView.setTypeface(Functions.getTypeFace(mContext));
        SCViewAnimation nameTagAnimation = new SCViewAnimation(guideText1);
        nameTagAnimation.addPageAnimation(new SCPositionAnimation(mContext, 0, 0, -size.y / 2));
        nameTagAnimation.addPageAnimation(new SCPositionAnimation(mContext, 0, 0, size.y));
        mViewPager.addAnimation(nameTagAnimation);
    }

    @Override
    public void animateGuidePageImageSkewType(Context mContext, View guideImage, Point size, SCViewPager mViewPager) {
        SCViewAnimationUtil.prepareViewToGetSize(guideImage);
        SCViewAnimation atSkexAnimation = new SCViewAnimation(guideImage);
        atSkexAnimation.addPageAnimation(new SCPositionAnimation(mContext, 0, 0, -(size.y - guideImage.getHeight())));
        atSkexAnimation.addPageAnimation(new SCPositionAnimation(mContext, 0, -size.x, 0));
        mViewPager.addAnimation(atSkexAnimation);
    }

    @Override
    public void animateGuidePageTextDjangoType(Context mContext, View guideText, Point size, int forPageStart, int forPageEnd, SCViewPager mViewPager) {
        TextView textView = (TextView) guideText;
        textView.setTypeface(Functions.getTypeFace(mContext));
        SCViewAnimation djangoAnimation = new SCViewAnimation(guideText);
        djangoAnimation.startToPosition(null, -size.y);
        djangoAnimation.addPageAnimation(new SCPositionAnimation(mContext, forPageStart, 0, size.y));
        djangoAnimation.addPageAnimation(new SCPositionAnimation(mContext, forPageEnd, 0, size.y));
        mViewPager.addAnimation(djangoAnimation);
    }

    @Override
    public void animateGuidePageImageMobileType(Context mContext, View guideImage, Point size, int forPageStart, int forPageEnd, SCViewPager mViewPager) {
        SCViewAnimation mobileAnimation = new SCViewAnimation(guideImage);
        mobileAnimation.startToPosition((int) (size.x * 1.5), null);
        mobileAnimation.addPageAnimation(new SCPositionAnimation(mContext, forPageStart, -(int) (size.x * 1.5), 0));
        mobileAnimation.addPageAnimation(new SCPositionAnimation(mContext, forPageEnd, -(int) (size.x * 1.5), 0));
        mViewPager.addAnimation(mobileAnimation);
    }

    @Override
    public void animateGuidePageRasberryType(Context mContext, View loginView, Point size, int forPageStart, int forPageEnd, SCViewPager mViewPager) {
        SCViewAnimation raspberryAnimation = new SCViewAnimation(loginView);
        raspberryAnimation.startToPosition(-size.x, null);
        raspberryAnimation.addPageAnimation(new SCPositionAnimation(mContext, forPageStart, size.x, 0));
        raspberryAnimation.addPageAnimation(new SCPositionAnimation(mContext, forPageEnd, -size.x, 0));
        mViewPager.addAnimation(raspberryAnimation);
    }

    @Override
    public void saveLoggedInUser(Activity activity, UserLoginOutput userLoginOutput) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences("login-user-prefs", activity.MODE_PRIVATE);
        sharedPreferences.edit().putBoolean("isUserLoggedIn", true).commit();

        if(userLoginOutput.isNewAccount()) {
            SharedPreferences sharedPreferences1 = activity.getSharedPreferences("user-prefs", activity.MODE_PRIVATE);
            sharedPreferences1.edit().putBoolean("isFirstTimeLogin", true).commit();
        } else {
            SharedPreferences sharedPreferences1 = activity.getSharedPreferences("user-prefs", activity.MODE_PRIVATE);
            sharedPreferences1.edit().clear().commit();
        }

        ComplexPreferences complexPreferences = new ComplexPreferences(activity, "login-user", activity.MODE_PRIVATE );
        complexPreferences.putObject("loggedInUser", userLoginOutput);
        complexPreferences.commit();


    }
}
