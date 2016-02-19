package com.webmyne.connect.shareAndEarn;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.flipboard.bottomsheet.BottomSheetLayout;
import com.flipboard.bottomsheet.commons.IntentPickerSheetView;
import com.webmyne.connect.R;
import com.webmyne.connect.Utils.Functions;

import java.util.Collections;
import java.util.Comparator;

/**
 * Created by priyasindkar on 18-02-2016.
 */
public class SharAndEarnActivity extends AppCompatActivity implements AccelerometerListener {
    private TextView txtReferCode;
    private BottomSheetLayout bottomSheetLayout;
    private CardView cardReferCode, cardShakeInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_and_earn);

        initToolBar();
        init();

    }

    private void init() {
        ImageView imgReferAndEarn = (ImageView) findViewById(R.id.imgReferAndEarn);
        Glide.with(SharAndEarnActivity.this).load(R.drawable.dummy_share_and_earn).into(imgReferAndEarn);
        TextView txtShareEarnInfo = (TextView) findViewById(R.id.txtShareEarnInfo);
        txtShareEarnInfo.setTypeface(Functions.getTypeFace(this), Typeface.BOLD);
        TextView txtShakeText = (TextView) findViewById(R.id.txtShakeText);
        txtShakeText.setTypeface(Functions.getTypeFace(this), Typeface.BOLD_ITALIC);
        txtReferCode = (TextView) findViewById(R.id.txtReferCode);
        txtReferCode.setTypeface(Functions.getTypeFace(this), Typeface.BOLD);
        bottomSheetLayout = (BottomSheetLayout) findViewById(R.id.bottomsheet);
        cardReferCode = (CardView) findViewById(R.id.cardReferCode);
        cardShakeInfo = (CardView) findViewById(R.id.cardShakeInfo);

        Animator codeAnim = ObjectAnimator.ofFloat(cardReferCode, "translationX",-500,0);
        final AnimatorSet animatorSetCode = new AnimatorSet();
        animatorSetCode.play(codeAnim);
        codeAnim.setDuration(1000);
        cardReferCode.setVisibility(View.VISIBLE);
        cardReferCode.setAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in));
        animatorSetCode.start();

        animatorSetCode.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Animator codeAnim = ObjectAnimator.ofFloat(cardShakeInfo, "translationY",-200,0);
                final AnimatorSet animatorSetCode = new AnimatorSet();
                animatorSetCode.play(codeAnim);
                codeAnim.setDuration(1000);
                cardShakeInfo.setVisibility(View.VISIBLE);
                cardShakeInfo.setAnimation(AnimationUtils.loadAnimation(SharAndEarnActivity.this, android.R.anim.fade_in));
                animatorSetCode.start();

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

    }

    private void initToolBar() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("Hi, John Doe");
        mToolbar.setSubtitle("$100.00");
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        mToolbar.setSubtitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mToolbar.setNavigationIcon(R.drawable.ic_navigation_close);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onAccelerationChanged(float x, float y, float z) {

    }

    @Override
    public void onShake(float force) {
        // Hide the keyboard
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(txtReferCode.getWindowToken(), 0);

        final Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, txtReferCode.getText().toString());
        shareIntent.setType("text/plain");
        IntentPickerSheetView intentPickerSheet = new IntentPickerSheetView(SharAndEarnActivity.this, shareIntent, "Share Refer...", new IntentPickerSheetView.OnIntentPickedListener() {
            @Override
            public void onIntentPicked(IntentPickerSheetView.ActivityInfo activityInfo) {
                bottomSheetLayout.dismissSheet();
                startActivity(activityInfo.getConcreteIntent(shareIntent));
            }
        });
        // Filter out built in sharing options such as bluetooth and beam.
       /* intentPickerSheet.setFilter(new IntentPickerSheetView.Filter() {
            @Override
            public boolean include(IntentPickerSheetView.ActivityInfo info) {
                return !info.componentName.getPackageName().startsWith("com.android");
            }
        });*/
        // Sort activities in reverse order for no good reason
        intentPickerSheet.setSortMethod(new Comparator<IntentPickerSheetView.ActivityInfo>() {
            @Override
            public int compare(IntentPickerSheetView.ActivityInfo lhs, IntentPickerSheetView.ActivityInfo rhs) {
                return rhs.label.compareTo(lhs.label);
            }
        });

        bottomSheetLayout.showWithSheetView(intentPickerSheet);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (AccelerometerManager.isSupported(this)) {
            AccelerometerManager.startListening(this);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (AccelerometerManager.isListening()) {
            AccelerometerManager.stopListening();
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (AccelerometerManager.isListening()) {
            AccelerometerManager.stopListening();
        }
    }
}
