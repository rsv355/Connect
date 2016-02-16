package com.webmyne.connect.leads;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.webmyne.connect.R;
import com.webmyne.connect.customUI.textDrawableIcons.ColorGenerator;
import com.webmyne.connect.customUI.textDrawableIcons.TextDrawable;


/**
 * Created by priyasindkar on 15-02-2016.
 */
public class PostLeadActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbar;
    private int verticalColorIndex;
    private ImageView imgVertical;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_lead);
        verticalColorIndex = getIntent().getIntExtra("vertical_color_index", 0);

        toolbar = (Toolbar) findViewById(R.id.anim_toolbar);
        setSupportActionBar(toolbar);
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        imgVertical = (ImageView) findViewById(R.id.imgVertical);

        collapsingToolbar.setTitle("Auto Insurance");
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
}
