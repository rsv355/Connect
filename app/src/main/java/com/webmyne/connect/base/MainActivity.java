package com.webmyne.connect.base;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.renderscript.BaseObj;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.andexert.library.RippleView;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.webmyne.connect.R;
import com.webmyne.connect.Utils.Functions;
import com.webmyne.connect.customUI.viewPager.DotsView;
import com.webmyne.connect.customUI.viewPager.SCPositionAnimation;
import com.webmyne.connect.customUI.viewPager.SCViewAnimation;
import com.webmyne.connect.customUI.viewPager.SCViewAnimationUtil;
import com.webmyne.connect.customUI.viewPager.SCViewPager;
import com.webmyne.connect.customUI.viewPager.SCViewPagerAdapter;

import org.json.JSONObject;

import java.util.Arrays;


public class MainActivity extends FragmentActivity implements View.OnClickListener, RippleView.OnRippleCompleteListener {
    private static final int NUM_PAGES = 4;

    private SCViewPager mViewPager;
    private SCViewPagerAdapter mPageAdapter;
    private DotsView mDotsView;
    CallbackManager callbackManager;
    ProfileTracker profileTracker;
    GoogleApiClient mGoogleApiClient;
    private int RC_SIGN_IN = 101;

    private void initScoialLogins(){
        // Intialize Facebook
        FacebookSdk.sdkInitialize(getApplicationContext());
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(ConnectionResult connectionResult) {
                        Toast.makeText(MainActivity.this, "Google+ Connection Failed.", Toast.LENGTH_SHORT).show();
                    }
                } /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                Log.e("currentProfile",""+currentProfile.getId());
                Log.e("currentProfile",""+currentProfile.getName());
                Log.e("currentProfile",""+currentProfile.getProfilePictureUri(600, 600));
                Toast.makeText(MainActivity.this,"Welcome, "+currentProfile.getName(),Toast.LENGTH_SHORT).show();
            }
        };

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initScoialLogins();
        setContentView(R.layout.activity_main);

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
        TextView textView = (TextView) guideText1;
        textView.setTypeface(Functions.getTypeFace(MainActivity.this));
        SCViewAnimation nameTagAnimation = new SCViewAnimation(guideText1);
        nameTagAnimation.addPageAnimation(new SCPositionAnimation(this, 0, 0, -size.y / 2));
        nameTagAnimation.addPageAnimation(new SCPositionAnimation(this, 0, 0, size.y));
        mViewPager.addAnimation(nameTagAnimation);

        View guideImage = findViewById(R.id.guideImage1);
        SCViewAnimationUtil.prepareViewToGetSize(guideImage);
        SCViewAnimation atSkexAnimation = new SCViewAnimation(guideImage);
        atSkexAnimation.addPageAnimation(new SCPositionAnimation(this, 0, 0, -(size.y - guideImage.getHeight())));
        atSkexAnimation.addPageAnimation(new SCPositionAnimation(this, 0, -size.x, 0));
        mViewPager.addAnimation(atSkexAnimation);

        View guideText2 = findViewById(R.id.guideText2);
        textView = (TextView) guideText2;
        textView.setTypeface(Functions.getTypeFace(MainActivity.this));
        SCViewAnimation djangoAnimation = new SCViewAnimation(guideText2);
        djangoAnimation.startToPosition(null, -size.y);
        djangoAnimation.addPageAnimation(new SCPositionAnimation(this, 0, 0, size.y));
        djangoAnimation.addPageAnimation(new SCPositionAnimation(this, 1, 0, size.y));
        mViewPager.addAnimation(djangoAnimation);


        View guideImage2 = findViewById(R.id.guideImage2);
        SCViewAnimation mobileAnimation = new SCViewAnimation(guideImage2);
        mobileAnimation.startToPosition((int) (size.x * 1.5), null);
        mobileAnimation.addPageAnimation(new SCPositionAnimation(this, 0, -(int) (size.x * 1.5), 0));
        mobileAnimation.addPageAnimation(new SCPositionAnimation(this, 1, -(int) (size.x * 1.5), 0));
        mViewPager.addAnimation(mobileAnimation);


        View guideText3 = findViewById(R.id.guideText3);
        textView = (TextView) guideText3;
        textView.setTypeface(Functions.getTypeFace(MainActivity.this));
        SCViewAnimation djangoAnimation1 = new SCViewAnimation(guideText3);
        djangoAnimation1.startToPosition(null, -size.y);
        djangoAnimation1.addPageAnimation(new SCPositionAnimation(this, 1, 0, size.y));
        djangoAnimation1.addPageAnimation(new SCPositionAnimation(this, 2, 0, size.y));
        mViewPager.addAnimation(djangoAnimation1);

        View guideImage3 = findViewById(R.id.guideImage3);
        SCViewAnimation mobileAnimation1 = new SCViewAnimation(guideImage3);
        mobileAnimation1.startToPosition((int) (size.x * 1.5), null);
        mobileAnimation1.addPageAnimation(new SCPositionAnimation(this, 1, -(int) (size.x * 1.5), 0));
        mobileAnimation1.addPageAnimation(new SCPositionAnimation(this, 2, -(int) (size.x * 1.5), 0));
        mViewPager.addAnimation(mobileAnimation1);

        View loginView = findViewById(R.id.loginLayout);
        SCViewAnimation raspberryAnimation = new SCViewAnimation(loginView);
        raspberryAnimation.startToPosition(-size.x, null);
        raspberryAnimation.addPageAnimation(new SCPositionAnimation(this, 2, size.x, 0));
        raspberryAnimation.addPageAnimation(new SCPositionAnimation(this, 3, -size.x, 0));
        mViewPager.addAnimation(raspberryAnimation);

        LinearLayout fbLogin = (LinearLayout) loginView.findViewById(R.id.linearFbLogin);
        RippleView facebookRipple = (RippleView) loginView.findViewById(R.id.facebookRipple);
        facebookRipple.setOnRippleCompleteListener(this);
        RippleView googleRipple = (RippleView) loginView.findViewById(R.id.googleRipple);
        googleRipple.setOnRippleCompleteListener(this);
        //fbLogin.setOnClickListener(this);
        TextView txtLoginGuide = (TextView) loginView.findViewById(R.id.txtLoginGuide);
        txtLoginGuide.setTypeface(Functions.getTypeFace(MainActivity.this), Typeface.BOLD);

        TextView txtFbLogin = (TextView) loginView.findViewById(R.id.txtFbLogin);
        txtFbLogin.setTypeface(Functions.getTypeFace(MainActivity.this));



        TextView txtGoogleLogin = (TextView) loginView.findViewById(R.id.txtGoogleLogin);
        txtGoogleLogin.setTypeface(Functions.getTypeFace(MainActivity.this));

            /*SignInButton signInButton = (SignInButton) findViewById(R.id.sign_in_button);
            signInButton.setSize(SignInButton.SIZE_STANDARD);
            signInButton.setScopes(gso.getScopeArray());
            signInButton.setOnClickListener(this);*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //handles Google+ Result
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }//handles Facebook Result
        else {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        profileTracker.stopTracking();
    }

    @Override
    public void onComplete(RippleView rippleView) {
        switch (rippleView.getId()) {
            case R.id.facebookRipple:

                facebookSingIn();

                break;
            case R.id.googleRipple:
               /* SharedPreferences preferences = getSharedPreferences("user_login", 0);
                preferences = getSharedPreferences("user_login", 0);
                preferences.edit().putBoolean("isUserLoggedIn", true).commit();
                Intent intent = new Intent(MainActivity.this, DrawerActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);*/
                googleSignIn();
                break;
        }
    }

    private void facebookSingIn() {

        LoginManager.getInstance().logInWithReadPermissions(MainActivity.this, Arrays.asList("email","public_profile", "user_friends"));
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {

                        GraphRequest.newMeRequest(
                                loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(JSONObject user, GraphResponse response) {
                                        if (response.getError() != null) {
                                            // handle error
                                        } else {
                                            try{
                                                JSONObject json = response.getJSONObject();
                                                Log.e("#### FB RESPONSE",json.toString());
                                            }catch (Exception e){}

                                        }
                                    }
                                }).executeAsync();


                        Toast.makeText(MainActivity.this, "Login Sucess", Toast.LENGTH_LONG).show();

                        /*SharedPreferences preferences = getSharedPreferences("user_login", 0);
                        preferences.edit().putBoolean("isUserLoggedIn", true).commit();
                        Intent intent = new Intent(MainActivity.this, DrawerActivity.class);
                        startActivity(intent);
                        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);*/
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(MainActivity.this, "Login Cancel", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Toast.makeText(MainActivity.this, exception.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.e("GOOGLE SIGN IN", "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            Log.e("GOOGLE,ID",""+acct.getId());
            Log.e("GOOGLE,Email",""+acct.getEmail());
            Log.e("GOOGLE,Photo url",""+acct.getPhotoUrl());
            Log.e("GOOGLE,Display Name",""+acct.getDisplayName());
            Toast.makeText(MainActivity.this,"Welcome, "+acct.getDisplayName(),Toast.LENGTH_SHORT).show();
          //  mStatusTextView.setText(getString(R.string.signed_in_fmt, acct.getDisplayName()));
            //updateUI(true);
        } else {
            // Signed out, show unauthenticated UI.
           // updateUI(false);
        }
    }

    private void googleSignIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    //end of class
}
