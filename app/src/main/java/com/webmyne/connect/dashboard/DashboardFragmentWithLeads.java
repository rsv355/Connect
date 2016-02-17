package com.webmyne.connect.dashboard;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.andexert.library.RippleView;

import java.util.ArrayList;

import com.webmyne.connect.R;
import com.webmyne.connect.Utils.Constants;
import com.webmyne.connect.Utils.Functions;
import com.webmyne.connect.customUI.textDrawableIcons.ColorGenerator;
import com.webmyne.connect.customUI.textDrawableIcons.TextDrawable;
import com.webmyne.connect.leads.LeadsListActivity;
import com.webmyne.connect.leads.PostLeadActivity;

/**
 * Created by priyasindkar on 12-02-2016.
 */
public class DashboardFragmentWithLeads extends Fragment  {
    private View aiVerticalView, afVerticalView, hiVerticalView, liVerticalView, hoVerticalView, ncVerticalView;
    private ImageView aiImgVertical, afImgVertical, hiImgVertical, liImgVertical, hoiImgVertical, ncImgVertical;
    private TextView txtDashboardMessage, aiTextVertical, afTextVertical, hiTextVertical, liTextVertical, hoiTextVertical, ncTextVertical, txtViewMoreButton;
    private RippleView aiRippleView, afRippleView, hiRippleView, liRippleView, hoRippleView, ncRippleView;
    private LinearLayout linearTop,linearMiddle,linearBottom;
    private Activity activity;
    private RippleView viewMoreRipple;

    private OnVerticalClickListener onVerticalClickListener;

    public static DashboardFragmentWithLeads newInstance() {
        DashboardFragmentWithLeads fragment = new DashboardFragmentWithLeads();
        return fragment;
    }


    public DashboardFragmentWithLeads() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard2, null);
        activity = getActivity();
        init(view);

        return view;
    }

    private void init(View view) {
        txtDashboardMessage = (TextView) view.findViewById(R.id.txtDashboardMessage);
        txtDashboardMessage.setTypeface(Functions.getTypeFace(getActivity()));

        aiVerticalView = view.findViewById(R.id.aiVerticalView);
        afVerticalView = view.findViewById(R.id.afVerticalView);
        hiVerticalView = view.findViewById(R.id.hiVerticalView);
        liVerticalView = view.findViewById(R.id.liVerticalView);
        hoVerticalView = view.findViewById(R.id.hoiVerticalView);
        ncVerticalView = view.findViewById(R.id.ncVerticalView);

        viewMoreRipple = (RippleView) view.findViewById(R.id.viewMoreRipple);

        ArrayList<Integer> color = ColorGenerator.MATERIAL.getRandomColor();

        aiImgVertical = (ImageView) aiVerticalView.findViewById(R.id.imgVertical);
        aiTextVertical = (TextView) aiVerticalView.findViewById(R.id.txtVerticalName);
        aiRippleView = (RippleView) aiVerticalView.findViewById(R.id.verticalRippleItem);
        aiRippleView.setOnRippleCompleteListener(new OnVerticalClickListener(0, color.get(0)));
        setVerticalView(aiImgVertical, aiTextVertical, Constants.VERTICAL_NAMES.get(0), Constants.VERTICAL_SHORT_NAMES.get(0), color.get(0));

        afImgVertical = (ImageView) afVerticalView.findViewById(R.id.imgVertical);
        afTextVertical = (TextView) afVerticalView.findViewById(R.id.txtVerticalName);
        afRippleView = (RippleView) afVerticalView.findViewById(R.id.verticalRippleItem);
        afRippleView.setOnRippleCompleteListener(new OnVerticalClickListener(1, color.get(1)));
        setVerticalView(afImgVertical, afTextVertical, Constants.VERTICAL_NAMES.get(1), Constants.VERTICAL_SHORT_NAMES.get(1), color.get(1));

        hiImgVertical = (ImageView) hiVerticalView.findViewById(R.id.imgVertical);
        hiTextVertical = (TextView) hiVerticalView.findViewById(R.id.txtVerticalName);
        hiRippleView = (RippleView) hiVerticalView.findViewById(R.id.verticalRippleItem);
        hiRippleView.setOnRippleCompleteListener(new OnVerticalClickListener(2, color.get(2)));
        setVerticalView(hiImgVertical, hiTextVertical, Constants.VERTICAL_NAMES.get(2), Constants.VERTICAL_SHORT_NAMES.get(2), color.get(2));

        liImgVertical = (ImageView) liVerticalView.findViewById(R.id.imgVertical);
        liTextVertical = (TextView) liVerticalView.findViewById(R.id.txtVerticalName);
        liRippleView = (RippleView) liVerticalView.findViewById(R.id.verticalRippleItem);
        liRippleView.setOnRippleCompleteListener(new OnVerticalClickListener(3, color.get(3)));
        setVerticalView(liImgVertical, liTextVertical, Constants.VERTICAL_NAMES.get(3), Constants.VERTICAL_SHORT_NAMES.get(3), color.get(3));

        hoiImgVertical = (ImageView) hoVerticalView.findViewById(R.id.imgVertical);
        hoiTextVertical = (TextView) hoVerticalView.findViewById(R.id.txtVerticalName);
        hoRippleView = (RippleView) hoVerticalView.findViewById(R.id.verticalRippleItem);
        hoRippleView.setOnRippleCompleteListener(new OnVerticalClickListener(4, color.get(4)));
        setVerticalView(hoiImgVertical, hoiTextVertical, Constants.VERTICAL_NAMES.get(4), Constants.VERTICAL_SHORT_NAMES.get(4), color.get(4));

        ncImgVertical = (ImageView) ncVerticalView.findViewById(R.id.imgVertical);
        ncTextVertical = (TextView) ncVerticalView.findViewById(R.id.txtVerticalName);
        ncRippleView = (RippleView) ncVerticalView.findViewById(R.id.verticalRippleItem);
        ncRippleView.setOnRippleCompleteListener(new OnVerticalClickListener(5, color.get(5)));
        setVerticalView(ncImgVertical, ncTextVertical, Constants.VERTICAL_NAMES.get(5), Constants.VERTICAL_SHORT_NAMES.get(5), color.get(5));

        //Left to right animation
        Animator AIanim = ObjectAnimator.ofFloat(aiVerticalView, "translationX",-200,0);
        Animator HIanim = ObjectAnimator.ofFloat(hiVerticalView, "translationX",-200,0);
        Animator HOanim = ObjectAnimator.ofFloat(hoVerticalView, "translationX",-200,0);

        AIanim.setDuration(500);HIanim.setDuration(500);HOanim.setDuration(500);

        //Right to left animation
        Animator AFanim = ObjectAnimator.ofFloat(afVerticalView, "translationX",200,0);
        Animator LIanim = ObjectAnimator.ofFloat(liVerticalView, "translationX",200,0);
        Animator NCanim = ObjectAnimator.ofFloat(ncVerticalView, "translationX",200,0);

        AFanim.setDuration(500);LIanim.setDuration(500);NCanim.setDuration(500);

        final AnimatorSet animatorSetTopRow = new AnimatorSet();
        final AnimatorSet animatorSetMiddleRow = new AnimatorSet();
        final AnimatorSet animatorSetBottomRow = new AnimatorSet();

        animatorSetTopRow.playTogether(AIanim, AFanim);
        animatorSetMiddleRow.playTogether(HIanim, LIanim);
        animatorSetBottomRow.playTogether(HOanim, NCanim);

        linearTop = (LinearLayout)view.findViewById(R.id.linearTop);
        linearMiddle = (LinearLayout)view.findViewById(R.id.linearMiddle);
        linearBottom = (LinearLayout)view.findViewById(R.id.linearBottom);

        linearTop.setVisibility(View.VISIBLE);
        linearTop.setAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_in));
        animatorSetTopRow.start();
        animatorSetTopRow.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                linearMiddle.setVisibility(View.GONE);

                // linearTop.setAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_in));
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                linearMiddle.setVisibility(View.VISIBLE);
                linearMiddle.setAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_in));
                animatorSetMiddleRow.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        animatorSetMiddleRow.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                linearBottom.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                linearBottom.setVisibility(View.VISIBLE);
                linearBottom.setAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_in));
                animatorSetBottomRow.start();

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        viewMoreRipple.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                Intent intent = new Intent(getActivity(), LeadsListActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.push_up_in, R.anim.push_down_out);
            }
        });
    }

    private void setVerticalView (ImageView verticalImageView, TextView verticalTextView, String verticalName, String shortName, int index) {
        int color = ColorGenerator.MATERIAL.getColorAtIndex(index);
        TextDrawable drawable2 = TextDrawable.builder().buildRound(shortName, color);
        verticalImageView.setImageDrawable(drawable2);

        int dimensions = (int) getResources().getDimension(R.dimen.small_profile_image);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(dimensions, dimensions);
        verticalImageView.setLayoutParams(layoutParams);

        verticalTextView.setText(verticalName);
        verticalTextView.setTextColor(color);
        verticalTextView.setTypeface(Functions.getTypeFace(getActivity()), Typeface.BOLD);
    }

    private class OnVerticalClickListener implements RippleView.OnRippleCompleteListener {
        private int verticalType, verticalColorIndex;


        OnVerticalClickListener(int verticaltype, int index) {
            verticalType = verticaltype;
            verticalColorIndex = index;
        }

        @Override
        public void onComplete(RippleView rippleView) {
            Toast.makeText(getActivity(), Constants.VERTICAL_NAMES.get(verticalType), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getActivity(), PostLeadActivity.class);
            intent.putExtra("vertical_color_index", verticalColorIndex);
            intent.putExtra("selected_vertical", Constants.VERTICAL_NAMES.get(verticalType));
            startActivity(intent);
            getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
    }
}
