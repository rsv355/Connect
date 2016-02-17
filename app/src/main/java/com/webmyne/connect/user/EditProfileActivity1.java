package com.webmyne.connect.user;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rengwuxian.materialedittext.MaterialAutoCompleteTextView;
import com.webmyne.connect.R;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by priyasindkar on 11-02-2016.
 */
public class EditProfileActivity1 extends AppCompatActivity  {

    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbar;
    MaterialAutoCompleteTextView editLocation;
    List<String> dataset = new LinkedList<>(Arrays.asList("One", "Two", "Three", "Four", "Five"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile1);

        toolbar = (Toolbar) findViewById(R.id.anim_toolbar);
        setSupportActionBar(toolbar);
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("My Profile");
        collapsingToolbar.setExpandedTitleTextAppearance(R.style.ExpandedAppBarTitleStyle);
        collapsingToolbar.setCollapsedTitleTextAppearance(R.style.CollapsedAppBarTitleStyle);
        toolbar.setNavigationIcon(R.drawable.ic_navigation_close);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dataset);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        editLocation = (MaterialAutoCompleteTextView) findViewById(R.id.editLocation);
        editLocation.setAdapter(adapter);
    }
}
