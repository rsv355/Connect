package com.webmyne.connect.leads;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.webmyne.connect.R;
import com.webmyne.connect.base.DrawerActivity;
import com.webmyne.connect.customUI.textDrawableIcons.ColorGenerator;
import com.webmyne.connect.customUI.textDrawableIcons.TextDrawable;


/**
 * Created by priyasindkar on 15-02-2016.
 */
public class PostLeadActivity extends AppCompatActivity implements View.OnClickListener{
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbar;
    private int verticalColorIndex;
    private String selectedVertical;
    private ImageView imgVertical;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_lead);
        verticalColorIndex = getIntent().getIntExtra("vertical_color_index", 0);
        selectedVertical = getIntent().getStringExtra("selected_vertical");

        toolbar = (Toolbar) findViewById(R.id.anim_toolbar);
        setSupportActionBar(toolbar);
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
        imgVertical = (ImageView) findViewById(R.id.imgVertical);

        collapsingToolbar.setTitle("Auto Insurance");
        collapsingToolbar.setExpandedTitleTextAppearance(R.style.ExpandedAppBarTitleStyle);
        collapsingToolbar.setCollapsedTitleTextAppearance(R.style.CollapsedAppBarTitleStyle);
        toolbar.setNavigationIcon(R.drawable.ic_navigation_close);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        collapsingToolbar.setContentScrimColor(ColorGenerator.MATERIAL.getColorAtIndex(verticalColorIndex));
        fab.setBackgroundTintList(ColorStateList.valueOf(ColorGenerator.MATERIAL.getColorAtIndex(verticalColorIndex)));

        TextDrawable drawable2 = TextDrawable.builder().buildRound("AI", ColorGenerator.MATERIAL.getColorAtIndex(verticalColorIndex));
        imgVertical.setImageDrawable(drawable2);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                /*RotateAnimation rotate = new RotateAnimation(360, 0, Animation.RELATIVE_TO_SELF,
                        0.5f,  Animation.RELATIVE_TO_SELF, 0.5f);
                rotate.setDuration(500);
                fab.startAnimation(rotate);
                rotate.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {*/
                        Intent intent = new Intent(PostLeadActivity.this, DrawerActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                   /* }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });*/
                break;
        }
    }
}
