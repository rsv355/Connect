package com.webmyne.connect.leads;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
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
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.andexert.library.RippleView;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.webmyne.connect.R;
import com.webmyne.connect.Utils.Constants;
import com.webmyne.connect.base.DrawerActivity;
import com.webmyne.connect.customUI.textDrawableIcons.ColorGenerator;
import com.webmyne.connect.customUI.textDrawableIcons.TextDrawable;


/**
 * Created by priyasindkar on 15-02-2016.
 */
public class PostLeadActivity extends AppCompatActivity implements View.OnClickListener, RippleView.OnRippleCompleteListener{
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbar;
    private int verticalColorIndex, selectedVerticalNo;
    private String selectedVertical;
    private ImageView imgVertical;
    private FloatingActionButton fab;
    private RippleView txtPostLead;
    private MaterialEditText editName, editRefNo;
    private String verticalName="", verticalShortName="";

    private ImageView imgSearchContacts;
    private static final int RESULT_PICK_CONTACT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_lead);
        init();
    }

    private void init() {
        if(getIntent()!=null) {
            verticalColorIndex = getIntent().getIntExtra("vertical_color_index", 0);
            selectedVertical = getIntent().getStringExtra("selected_vertical");
            selectedVerticalNo = getIntent().getIntExtra("selected_vertical_no", 0);
            verticalName = Constants.VERTICAL_NAMES.get(selectedVerticalNo);
            verticalShortName = Constants.VERTICAL_SHORT_NAMES.get(selectedVerticalNo);
        }

        toolbar = (Toolbar) findViewById(R.id.anim_toolbar);
        setSupportActionBar(toolbar);
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(verticalName);
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

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
        imgVertical = (ImageView) findViewById(R.id.imgVertical);
        TextDrawable drawable2 = TextDrawable.builder().buildRound(verticalShortName, ColorGenerator.MATERIAL.getColorAtIndex(verticalColorIndex));
        imgVertical.setImageDrawable(drawable2);

        txtPostLead = (RippleView) findViewById(R.id.txtPostLead);
        txtPostLead.setOnRippleCompleteListener(this);
        editName = (MaterialEditText) findViewById(R.id.editName);
        editRefNo = (MaterialEditText) findViewById(R.id.editRefNo);

        imgSearchContacts = (ImageView) findViewById(R.id.imgSearchContacts);
        imgSearchContacts.setOnClickListener(this);
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
            case R.id.imgSearchContacts:
                pickContact(v);
            break;
        }
    }

    public void pickContact(View v)
    {
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
            String phoneNo = null ;
            String name = null;
            Uri uri = data.getData();
            cursor = getContentResolver().query(uri, null, null, null, null);
            cursor.moveToFirst();
            int  phoneIndex =cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            int  nameIndex =cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
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
}
