package com.webmyne.connect.leadHistory.adapter.presenter;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.webmyne.connect.R;

/**
 * Created by priyasindkar on 11-03-2016.
 */
public class LeadHistoryAdapterPresenterImpl implements LeadHistoryAdapterPresenter {
    private Context mContext;

    public LeadHistoryAdapterPresenterImpl(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public Drawable expand(TextView text, ImageView imgArrow) {
        text.setVisibility(View.VISIBLE);
        text.setAnimation(AnimationUtils.loadAnimation(mContext, android.R.anim.fade_in));
        final int widthSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        final int heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        text.measure(widthSpec, heightSpec);
        ValueAnimator mAnimator = slideAnimator(text, 0, text.getMeasuredHeight());
        //  mAnimator.setDuration(2000);
        Drawable drawable = mContext.getResources().getDrawable(R.drawable.ic_arrow_up);
        drawable.setColorFilter(mContext.getResources().getColor(R.color.walletDividerLine), PorterDuff.Mode.SRC_ATOP);
        mAnimator.start();

        return drawable;
    }

    @Override
    public Drawable collapse(final TextView text, ImageView imgArrow) {
        int finalHeight = text.getHeight();
        ValueAnimator mAnimator = slideAnimator(text, finalHeight, 0);
        Drawable drawable = mContext.getResources().getDrawable(R.drawable.ic_arrow_down);
        drawable.setColorFilter(mContext.getResources().getColor(R.color.walletDividerLine), PorterDuff.Mode.SRC_ATOP);

        mAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                text.setVisibility(View.GONE);

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        mAnimator.start();

        return drawable;
    }

    private ValueAnimator slideAnimator(final TextView text, final int start, int end) {
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                //Update Height
                int value = (Integer) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = text.getLayoutParams();
                layoutParams.height = 50;//value;
                text.setLayoutParams(layoutParams);
            }
        });
        return animator;
    }
}
