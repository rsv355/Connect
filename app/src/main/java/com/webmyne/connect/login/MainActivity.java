package com.webmyne.connect.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.andexert.library.RippleView;
import com.facebook.FacebookSdk;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.plus.Plus;
import com.webmyne.connect.R;
import com.webmyne.connect.Utils.Functions;
import com.webmyne.connect.Utils.PrefUtils;
import com.webmyne.connect.base.DrawerActivity;
import com.webmyne.connect.login.presenter.MainPresenterImpl;
import com.webmyne.connect.login.presenter.LoginView;
import com.webmyne.connect.login.presenter.LoginPresenter;
import com.webmyne.connect.login.presenter.LoginPresenterImp;
import com.webmyne.connect.user.EditProfileActivity;
import com.webmyne.connect.user.model.UserLoginOutput;
import com.webmyne.connect.login.model.UserProfile;
import com.webmyne.connect.customUI.CustomProgressDialog;
import com.webmyne.connect.customUI.viewPager.DotsView;
import com.webmyne.connect.customUI.viewPager.SCViewAnimationUtil;
import com.webmyne.connect.customUI.viewPager.SCViewPager;
import com.webmyne.connect.customUI.viewPager.SCViewPagerAdapter;


public class MainActivity extends FragmentActivity implements RippleView.OnRippleCompleteListener,
        LoginView, GoogleApiClient.OnConnectionFailedListener {
    private static final int NUM_PAGES = 4;

    private SCViewPager mViewPager;
    private SCViewPagerAdapter mPageAdapter;
    private DotsView mDotsView;
    //google
    private GoogleApiClient mGoogleApiClient;

    //MVP
    private MainPresenterImpl mainPresenterImpl;
    private LoginPresenter loginPresenter;

    private ProgressDialog progressDialog;

    private void initSocialLogins() {
        // Initialize Facebook
        FacebookSdk.sdkInitialize(getApplicationContext());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        initSocialLogins();
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
    /* Google Login */
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestScopes(new Scope(Scopes.PROFILE))
                .build();

        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .enableAutoManage(this, this)
                    .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                    .addApi(Plus.API)
                    .build();
        }

        mainPresenterImpl = new MainPresenterImpl(this);
        loginPresenter = new LoginPresenterImp(this);
        progressDialog = new CustomProgressDialog(MainActivity.this);
        progressDialog.setCancelable(false);

        mViewPager = (SCViewPager) findViewById(R.id.viewPager);
        mDotsView = (DotsView) findViewById(R.id.dotsView);
        mDotsView.setDotRessource(R.drawable.dot_selected, R.drawable.dot_unselected);
        mDotsView.setNumberOfPage(NUM_PAGES);

        mPageAdapter = new SCViewPagerAdapter(getSupportFragmentManager());
        mPageAdapter.setNumberOfPage(NUM_PAGES);
        mPageAdapter.setFragmentBackgroundColor(R.color.splashRippleBackground);
        mViewPager.setAdapter(mPageAdapter);

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                mDotsView.selectDot(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        final Point size = SCViewAnimationUtil.getDisplaySize(this);

        View guideText1 = findViewById(R.id.guideText1);
        mainPresenterImpl.animateGuidePageView(MainActivity.this, guideText1, size, mViewPager);

        View guideImage = findViewById(R.id.guideImage1);
        mainPresenterImpl.animateGuidePageImageSkewType(MainActivity.this, guideImage, size, mViewPager);

        View guideText2 = findViewById(R.id.guideText2);
        mainPresenterImpl.animateGuidePageTextDjangoType(MainActivity.this, guideText2, size, 0, 1, mViewPager);

        View guideImage2 = findViewById(R.id.guideImage2);
        mainPresenterImpl.animateGuidePageImageMobileType(MainActivity.this, guideImage2, size, 0, 1, mViewPager);

        View guideText3 = findViewById(R.id.guideText3);
        mainPresenterImpl.animateGuidePageTextDjangoType(MainActivity.this, guideText3, size, 1, 2, mViewPager);

        View guideImage3 = findViewById(R.id.guideImage3);
        mainPresenterImpl.animateGuidePageImageMobileType(MainActivity.this, guideImage3, size, 1, 2, mViewPager);

        View loginView = findViewById(R.id.loginLayout);
        mainPresenterImpl.animateGuidePageRasberryType(MainActivity.this, loginView, size, 2, 3, mViewPager);

        RippleView facebookRipple = (RippleView) loginView.findViewById(R.id.facebookRipple);
        facebookRipple.setOnRippleCompleteListener(this);
        RippleView googleRipple = (RippleView) loginView.findViewById(R.id.googleRipple);
        googleRipple.setOnRippleCompleteListener(this);
        TextView txtLoginGuide = (TextView) loginView.findViewById(R.id.txtLoginGuide);
        txtLoginGuide.setTypeface(Functions.getTypeFace(MainActivity.this), Typeface.BOLD);

        TextView txtFbLogin = (TextView) loginView.findViewById(R.id.txtFbLogin);
        txtFbLogin.setTypeface(Functions.getTypeFace(MainActivity.this));

        TextView txtGoogleLogin = (TextView) loginView.findViewById(R.id.txtGoogleLogin);
        txtGoogleLogin.setTypeface(Functions.getTypeFace(MainActivity.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        loginPresenter.socialMediaActivityResultHandler(requestCode, resultCode, data, mGoogleApiClient);
    }

    @Override
    public void onComplete(RippleView rippleView) {
        switch (rippleView.getId()) {
            case R.id.facebookRipple:
                loginPresenter.doFacebookLogin(MainActivity.this);
                break;
            case R.id.googleRipple:
                loginPresenter.doGoogleLogin(MainActivity.this, mGoogleApiClient);
                break;
        }
    }

    @Override
    public void onGoogleLoginSuccess(UserProfile userProfile, String success) {
        loginPresenter.doLogin(MainActivity.this, userProfile);
    }

    @Override
    public void onGoogleLoginError(String error) {
        Functions.getSimpleOkAlterDialog(MainActivity.this, error, "Ok").show();
    }

    @Override
    public void onFacebookLoginError(String error) {
        Functions.getSimpleOkAlterDialog(MainActivity.this, error, "Ok").show();
    }

    @Override
    public void onFacebookLoginSuccess(UserProfile userProfile, String success) {
        loginPresenter.doLogin(MainActivity.this, userProfile);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Functions.getSimpleOkAlterDialog(MainActivity.this, getString(R.string.google_connection_failed), "Ok").show();
    }

    @Override
    public void onLoginSuccess(UserLoginOutput userLoginOutput, String success) {

        Log.e("login response", userLoginOutput.toString());
        mainPresenterImpl.saveLoggedInUser(MainActivity.this, userLoginOutput);
        Intent intent = null;
        if(userLoginOutput.getZipCode() == null || userLoginOutput.getZipCode().trim().length() == 0) {

            SharedPreferences sharedPreferences1 = getSharedPreferences("user-prefs", MODE_PRIVATE);
            sharedPreferences1.edit().putBoolean("isFirstTimeLogin", true).commit();
            intent = new Intent(MainActivity.this, EditProfileActivity.class);

            PrefUtils.setActiveLead(this,userLoginOutput.isActiveLead());

        } else {
            intent = new Intent(MainActivity.this, DrawerActivity.class);
        }
        startActivity(intent);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

    @Override
    public void onLoginError(String error) {
        Functions.getSimpleOkAlterDialog(MainActivity.this, error, "Ok").show();
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        progressDialog.hide();
    }
    //end of class
}
