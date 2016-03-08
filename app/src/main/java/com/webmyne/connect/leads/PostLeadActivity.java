package com.webmyne.connect.leads;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.andexert.library.RippleView;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.webmyne.connect.R;
import com.webmyne.connect.Utils.Constants;
import com.webmyne.connect.base.DrawerActivity;
import com.webmyne.connect.customUI.FlowLayout;
import com.webmyne.connect.customUI.textDrawableIcons.ColorGenerator;
import com.webmyne.connect.customUI.textDrawableIcons.TextDrawable;


/**
 * Created by priyasindkar on 15-02-2016.
 */
public class PostLeadActivity extends AppCompatActivity implements View.OnClickListener, RippleView.OnRippleCompleteListener,
        LeadsView, CompoundButton.OnCheckedChangeListener {
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbar;
    private ImageView imgVertical;
    private FloatingActionButton fab;
    private RippleView txtPostLead;
    private MaterialEditText editName, editRefNo, editEmail, editDescription;
    private FlowLayout linearVerticalsList;
    private CheckBox checkboxAI, checkboxAF, checkboxHI, checkboxLI, checkboxHO, checkboxNC;

    private ImageView imgSearchContacts;
    private static final int RESULT_PICK_CONTACT = 1;

    private LeadsPresenter leadsPresenter;
    private int[] selectedVerticals = new int[Constants.TOTAL_VERTICALS];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_lead);
    }

    @Override
    protected void onResume() {
        super.onResume();
        init();
    }

    private void init() {
        toolbar = (Toolbar) findViewById(R.id.anim_toolbar);
        setSupportActionBar(toolbar);
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
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
        imgVertical = (ImageView) findViewById(R.id.imgVertical);

        txtPostLead = (RippleView) findViewById(R.id.txtPostLead);
        txtPostLead.setOnRippleCompleteListener(this);
        editName = (MaterialEditText) findViewById(R.id.editName);
        editRefNo = (MaterialEditText) findViewById(R.id.editRefNo);
        editEmail = (MaterialEditText) findViewById(R.id.editEmail);
        editDescription = (MaterialEditText) findViewById(R.id.editDescription);
        imgSearchContacts = (ImageView) findViewById(R.id.imgSearchContacts);
        imgSearchContacts.setOnClickListener(this);

        linearVerticalsList = (FlowLayout) findViewById(R.id.linearVerticalsList);

        /*checkboxAI = (CheckBox) findViewById(R.id.checkboxAI);
        checkboxAI.setOnCheckedChangeListener(this);
        checkboxAF = (CheckBox) findViewById(R.id.checkboxAF);
        checkboxAF.setOnCheckedChangeListener(this);
        checkboxHI = (CheckBox) findViewById(R.id.checkboxHI);
        checkboxHI.setOnCheckedChangeListener(this);
        checkboxLI = (CheckBox) findViewById(R.id.checkboxLI);
        checkboxLI.setOnCheckedChangeListener(this);
        checkboxHO = (CheckBox) findViewById(R.id.checkboxHO);
        checkboxHO.setOnCheckedChangeListener(this);
        checkboxNC = (CheckBox) findViewById(R.id.checkboxNC);
        checkboxNC.setOnCheckedChangeListener(this);*/

        leadsPresenter = new LeadsPresenterImpl(this);
        if (getIntent() != null) {
            leadsPresenter.initUIData(PostLeadActivity.this, getIntent());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                Intent intent = new Intent(PostLeadActivity.this, DrawerActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                break;
            case R.id.imgSearchContacts:
                pickContact(v);
                break;
        }
    }

    public void pickContact(View v) {
        Intent contactPickerIntent = new Intent(Intent.ACTION_PICK,
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
        startActivityForResult(contactPickerIntent, RESULT_PICK_CONTACT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case RESULT_PICK_CONTACT:
                    contactPicked(data);
                    break;
            }
        }
    }

    private void contactPicked(Intent data) {
        Cursor cursor = null;
        try {
            String phoneNo = null;
            String name = null;
            Uri uri = data.getData();
            cursor = getContentResolver().query(uri, null, null, null, null);
            cursor.moveToFirst();
            int phoneIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            int nameIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
            phoneNo = cursor.getString(phoneIndex);
            name = cursor.getString(nameIndex);
            editRefNo.setText(phoneNo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onComplete(RippleView rippleView) {
        switch (rippleView.getId()) {
            case R.id.txtPostLead:
                AlertDialog.Builder builder = new AlertDialog.Builder(PostLeadActivity.this, R.style.MaterialBaseTheme_Light_AlertDialog);
                builder.setTitle("Lead Successfully Uploaded!");
                builder.setMessage("SMS & Email is sent to your contact with further details");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        SharedPreferences preferences = getSharedPreferences("is_lead_posted", 0);
                        preferences.edit().putBoolean("isLeadPosted", true).commit();
                        Intent intent = new Intent(PostLeadActivity.this, DrawerActivity.class);
                        startActivity(intent);
                    }
                });
                builder.show();
                break;
        }
    }

    @Override
    public void setInitUI(VerticalDataObject verticalDataObject) {
        collapsingToolbar.setTitle(verticalDataObject.verticalName);
        collapsingToolbar.setContentScrimColor(ColorGenerator.MATERIAL.getColorAtIndex(verticalDataObject.verticalColorIndex));
        TextDrawable drawable2 = TextDrawable.builder().buildRound(verticalDataObject.verticalShortName, ColorGenerator.MATERIAL.getColorAtIndex(verticalDataObject.verticalColorIndex));
        imgVertical.setImageDrawable(drawable2);

        linearVerticalsList.removeAllViews();
        for(String vertical : Constants.VERTICAL_NAMES) {
            if( !verticalDataObject.getVerticalName().equalsIgnoreCase(vertical)) {
                View view = getLayoutInflater().inflate(R.layout.vertical_check_box_item, null);
                CheckBox checkBox = (CheckBox) view;
                checkBox.setText(vertical);
                checkBox.setOnCheckedChangeListener(this);
                linearVerticalsList.addView(checkBox);
            }
        }

        /*if(verticalDataObject.getVerticalName().equals(getString(R.string.auto_insurance))) {
            checkboxAI.setVisibility(View.GONE);
        } else if(verticalDataObject.getVerticalName().equals(getString(R.string.auto_finance))) {
            checkboxAF.setVisibility(View.GONE);
        }  else if(verticalDataObject.getVerticalName().equals(getString(R.string.health_insurance))) {
            checkboxHI.setVisibility(View.GONE);
        } else if(verticalDataObject.getVerticalName().equals(getString(R.string.life_insurance))) {
            checkboxLI.setVisibility(View.GONE);
        } else if(verticalDataObject.getVerticalName().equals(getString(R.string.home_insurance))) {
            checkboxHO.setVisibility(View.GONE);
        } else if(verticalDataObject.getVerticalName().equals(getString(R.string.new_car))) {
            checkboxNC.setVisibility(View.GONE);
        }*/
    }

    @Override
    public void seCheckBoxCheck() {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Log.e("checked", buttonView.getText().toString().trim());
        Log.e("isChecked", isChecked+"");

    }
}
