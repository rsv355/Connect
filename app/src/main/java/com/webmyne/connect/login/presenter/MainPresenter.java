package com.webmyne.connect.login.presenter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.view.View;

import com.webmyne.connect.user.model.UserLoginOutput;
import com.webmyne.connect.customUI.viewPager.SCViewPager;

/**
 * Created by priyasindkar on 07-03-2016.
 */
public interface MainPresenter {
    void animateGuidePageView(Context mContext, View guideText1, Point size, SCViewPager mViewPager);
    void animateGuidePageImageSkewType(Context mContext, View guideImage, Point size, SCViewPager mViewPager);
    void animateGuidePageTextDjangoType(Context mContext, View guideText, Point size, int forPageStart, int forPageEnd, SCViewPager mViewPager);
    void animateGuidePageImageMobileType(Context mContext, View guideImage, Point size, int forPageStart, int forPageEnd, SCViewPager mViewPager);
    void animateGuidePageRasberryType(Context mContext, View loginView, Point size, int forPageStart, int forPageEnd, SCViewPager mViewPager);

    void saveLoggedInUser(Activity activity, UserLoginOutput userLoginOutput);
}
