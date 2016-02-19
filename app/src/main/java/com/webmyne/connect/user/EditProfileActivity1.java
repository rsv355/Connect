package com.webmyne.connect.user;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.andexert.library.RippleView;
import com.rengwuxian.materialedittext.MaterialAutoCompleteTextView;
import com.webmyne.connect.R;
import com.webmyne.connect.Utils.Functions;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by priyasindkar on 11-02-2016.
 */
public class EditProfileActivity1 extends AppCompatActivity implements View.OnClickListener{

    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbar;
    private FloatingActionButton fab;
    private TextView txtMyReferCode;
    private MaterialAutoCompleteTextView editLocation;
    private RippleView txtUpdate;
    private List<String> dataset = new LinkedList<>(Arrays.asList("One", "Two", "Three", "Four", "Five"));

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
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);

        txtMyReferCode = (TextView) findViewById(R.id.txtMyReferCode);
        txtMyReferCode.setTypeface(Functions.getTypeFace(this));
        txtUpdate = (RippleView) findViewById(R.id.txtUpdate);
        txtUpdate.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EditProfileActivity1.this, R.style.MaterialBaseTheme_Light_AlertDialog);
                builder.setMessage("Profile Updated Successfully!");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dataset);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        editLocation = (MaterialAutoCompleteTextView) findViewById(R.id.editLocation);
        editLocation.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                AddReferCodeFilterDialog filterDialog = new AddReferCodeFilterDialog(EditProfileActivity1.this, R.style.CustomDialogStyle);
                filterDialog.show();
                break;
        }
    }
}
