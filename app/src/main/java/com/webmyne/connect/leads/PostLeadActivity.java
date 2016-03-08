package com.webmyne.connect.leads;

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
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.andexert.library.RippleView;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.webmyne.connect.R;
import com.webmyne.connect.Utils.Functions;
import com.webmyne.connect.base.DrawerActivity;
import com.webmyne.connect.customUI.CustomProgressDialog;
import com.webmyne.connect.customUI.textDrawableIcons.ColorGenerator;
import com.webmyne.connect.customUI.textDrawableIcons.TextDrawable;
import com.webmyne.connect.listeners.OnAlertButtonClicked;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by priyasindkar on 15-02-2016.
 */
public class PostLeadActivity extends AppCompatActivity implements View.OnClickListener, RippleView.OnRippleCompleteListener,
        LeadsView {
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbar;
    private ImageView imgVertical;
    private FloatingActionButton fab;
    private RippleView txtPostLead;
    private MaterialEditText editName, editRefNo, editEmail, editDescription;
    private LinearLayout linearVerticalsList;
    private CheckBox checkboxAI, checkboxAF, checkboxHI, checkboxLI, checkboxHO, checkboxNC;

    private ImageView imgSearchContacts;
    private static final int RESULT_PICK_CONTACT = 1;

    private LeadsPresenter leadsPresenter;
    private LeadsInteractor leadsInteractor;
    public List<Integer> selectedVerticals = new ArrayList<>();
    private CustomProgressDialog progressDialog;

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

        progressDialog = new CustomProgressDialog(PostLeadActivity.this);
        progressDialog.setCancelable(false);

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

        linearVerticalsList = (LinearLayout) findViewById(R.id.linearVerticalsList);
        checkboxAI = (CheckBox) findViewById(R.id.checkboxAI);
        checkboxAF = (CheckBox) findViewById(R.id.checkboxAF);
        checkboxHI = (CheckBox) findViewById(R.id.checkboxHI);
        checkboxLI = (CheckBox) findViewById(R.id.checkboxLI);
        checkboxHO = (CheckBox) findViewById(R.id.checkboxHO);
        checkboxNC = (CheckBox) findViewById(R.id.checkboxNC);

        leadsPresenter = new LeadsPresenterImpl(this);
        leadsInteractor = new LeadsInteractorImpl(this);
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
                leadsPresenter.validateFormFields(PostLeadActivity.this, editName.getText().toString().trim(),
                        editEmail.getText().toString(), editRefNo.getText().toString());
                /*final AlertDialog.Builder builder = Functions.showAlterDialog(PostLeadActivity.this, "Lead Posted Successfully", "An Email and SMS sent to reference");
                Functions.setOnAlertButtonClicked(new OnAlertButtonClicked() {
                    @Override
                    public void onAlertButtonClicked() {
                        SharedPreferences preferences = getSharedPreferences("is_lead_posted", 0);
                        preferences.edit().putBoolean("isLeadPosted", true).commit();
                        Intent intent = new Intent(PostLeadActivity.this, DrawerActivity.class);
                        startActivity(intent);
                    }
                });
                builder.show();*/
                break;
        }
    }

    @Override
    public void setInitUI(VerticalDataObject verticalDataObject, List<VerticalDataObject> secondaryVerticalsList) {
        collapsingToolbar.setTitle(verticalDataObject.verticalName);
        collapsingToolbar.setContentScrimColor(ColorGenerator.MATERIAL.getColorAtIndex(verticalDataObject.verticalColorIndex));
        TextDrawable drawable2 = TextDrawable.builder().buildRound(verticalDataObject.verticalShortName, ColorGenerator.MATERIAL.getColorAtIndex(verticalDataObject.verticalColorIndex));
        imgVertical.setImageDrawable(drawable2);

        selectedVerticals.add(verticalDataObject.getVerticalId());

        checkboxAI.setOnCheckedChangeListener(new OnVerticalChecked(secondaryVerticalsList.get(0)));
        checkboxAF.setOnCheckedChangeListener(new OnVerticalChecked(secondaryVerticalsList.get(1)));
        checkboxHI.setOnCheckedChangeListener(new OnVerticalChecked(secondaryVerticalsList.get(2)));
        checkboxLI.setOnCheckedChangeListener(new OnVerticalChecked(secondaryVerticalsList.get(3)));
        checkboxHO.setOnCheckedChangeListener(new OnVerticalChecked(secondaryVerticalsList.get(4)));
        checkboxNC.setOnCheckedChangeListener(new OnVerticalChecked(secondaryVerticalsList.get(5)));

        if (verticalDataObject.getVerticalName().equals(getString(R.string.auto_insurance))) {
            checkboxAI.setVisibility(View.GONE);
        } else if (verticalDataObject.getVerticalName().equals(getString(R.string.auto_finance))) {
            checkboxAF.setVisibility(View.GONE);
        } else if (verticalDataObject.getVerticalName().equals(getString(R.string.health_insurance))) {
            checkboxHI.setVisibility(View.GONE);
        } else if (verticalDataObject.getVerticalName().equals(getString(R.string.life_insurance))) {
            checkboxLI.setVisibility(View.GONE);
        } else if (verticalDataObject.getVerticalName().equals(getString(R.string.home_insurance))) {
            checkboxHO.setVisibility(View.GONE);
        } else if (verticalDataObject.getVerticalName().equals(getString(R.string.new_car))) {
            checkboxNC.setVisibility(View.GONE);
        }
    }

    @Override
    public void onSelectVertical(List<Integer> selectedVerticals) {
        this.selectedVerticals = selectedVerticals;
    }

    @Override
    public void setNameError(String error) {
        editName.setError(error);
    }

    @Override
    public void setEmailError(String error) {
        editEmail.setError(error);
    }

    @Override
    public void setContactNoError(String error) {
        editRefNo.setError(error);
    }

    @Override
    public void onValidationSuccess() {
        leadsPresenter.showPostTermsConditionsDialog(PostLeadActivity.this);
    }

    @Override
    public void onTermsAccepted() {
        leadsInteractor.doPostLead(PostLeadActivity.this, editName.getText().toString().trim(),
                editEmail.getText().toString(),editRefNo.getText().toString(), editDescription.getText().toString(),selectedVerticals);
    }

    @Override
    public void onTermsDeclined() {
        leadsPresenter.showDeclineMessageDialog(PostLeadActivity.this);

    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        progressDialog.hide();
    }

    @Override
    public void onLeadPostSuccess(String success) {
        AlertDialog.Builder builder = Functions.showAlterDialog(this, success, "Ok");
        Functions.setOnAlertButtonClicked(new OnAlertButtonClicked() {
            @Override
            public void onAlertButtonClicked() {
                SharedPreferences preferences = getSharedPreferences("is_lead_posted", 0);
                preferences.edit().putBoolean("isLeadPosted", true).commit();
                Intent intent = new Intent(PostLeadActivity.this, DrawerActivity.class);
                startActivity(intent);
            }
        });
        builder.show();
    }

    @Override
    public void onLeadPostFail(String error) {

    }

    private class OnVerticalChecked implements CompoundButton.OnCheckedChangeListener {
        VerticalDataObject verticalDataObject;

        public OnVerticalChecked(VerticalDataObject verticalDataObject) {
            this.verticalDataObject = verticalDataObject;
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            leadsPresenter.onVerticalSelect(verticalDataObject.getVerticalId(), isChecked, selectedVerticals);
        }
    }
}
