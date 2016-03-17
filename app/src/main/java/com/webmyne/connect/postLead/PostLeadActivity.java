package com.webmyne.connect.postLead;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.andexert.library.RippleView;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.webmyne.connect.R;
import com.webmyne.connect.Utils.Functions;
import com.webmyne.connect.Utils.PrefUtils;
import com.webmyne.connect.base.DrawerActivity;
import com.webmyne.connect.customUI.CustomProgressDialog;
import com.webmyne.connect.customUI.FlowLayout;
import com.webmyne.connect.customUI.textDrawableIcons.ColorGenerator;
import com.webmyne.connect.customUI.textDrawableIcons.TextDrawable;
import com.webmyne.connect.listeners.OnAlertButtonClicked;
import com.webmyne.connect.postLead.model.VerticalDataObject;
import com.webmyne.connect.postLead.presenter.LeadsInteractor;
import com.webmyne.connect.postLead.presenter.LeadsInteractorImpl;
import com.webmyne.connect.postLead.presenter.LeadsPresenter;
import com.webmyne.connect.postLead.presenter.LeadsPresenterImpl;
import com.webmyne.connect.postLead.presenter.LeadsView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by priyasindkar on 15-02-2016.
 */
public class PostLeadActivity extends AppCompatActivity implements View.OnClickListener, RippleView.OnRippleCompleteListener,
        LeadsView, AppBarLayout.OnOffsetChangedListener {
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbar;
    private AppBarLayout appbar;
    private ImageView imgVertical;
    private FloatingActionButton fab;
    private RippleView txtPostLead;
    private MaterialEditText editName, editRefNo, editEmail, editDescription;
    private FlowLayout linearVerticalsList;
    private ImageView imgSearchContacts;


    private LeadsPresenter leadsPresenter;
    private LeadsInteractor leadsInteractor;
    public List<Integer> selectedVerticals = new ArrayList<>();

    private CustomProgressDialog progressDialog;
    private static final int RESULT_PICK_CONTACT = 1;

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
        appbar = (AppBarLayout) findViewById(R.id.appbar);
        appbar.addOnOffsetChangedListener(this);

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

        linearVerticalsList = (FlowLayout) findViewById(R.id.linearVerticalsList);

        leadsPresenter = new LeadsPresenterImpl(PostLeadActivity.this, this);
        leadsInteractor = new LeadsInteractorImpl(this);
        if (getIntent() != null) {
            leadsPresenter.initUIData(PostLeadActivity.this, getIntent());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        leadsPresenter.onDestroy();
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
                leadsPresenter.pickContact(v, PostLeadActivity.this);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case RESULT_PICK_CONTACT:
                    leadsPresenter.contactPicked(data);
                    break;
            }
        }
    }

    @Override
    public void onComplete(RippleView rippleView) {
        switch (rippleView.getId()) {
            case R.id.txtPostLead:
                leadsPresenter.validateFormFields(PostLeadActivity.this, editName.getText().toString().trim(),
                        editEmail.getText().toString(), editRefNo.getText().toString());
                break;
        }
    }

    @Override
    public void setInitUI(VerticalDataObject verticalDataObject, List<VerticalDataObject> secondaryVerticalsList) {
        collapsingToolbar.setTitle(verticalDataObject.verticalName);
        collapsingToolbar.setContentScrimColor(ColorGenerator.MATERIAL.getColorAtIndex(verticalDataObject.verticalColorIndex));
        TextDrawable drawable2 = TextDrawable.builder().buildRound(verticalDataObject.verticalShortName, ColorGenerator.MATERIAL.getColorAtIndex(verticalDataObject.verticalColorIndex));
        imgVertical.setImageDrawable(drawable2);

        // add verticals checkboxes
        selectedVerticals.add(verticalDataObject.getVerticalId());
        linearVerticalsList.removeAllViews();
        for (int i = 0; i < secondaryVerticalsList.size(); i++) {
            View view = getLayoutInflater().inflate(R.layout.vertical_check_box_item, null);
            CheckBox checkBox = (CheckBox) view;
            checkBox.setText(secondaryVerticalsList.get(i).getVerticalName());
            checkBox.setOnCheckedChangeListener(new OnVerticalChecked(secondaryVerticalsList.get(i)));
            linearVerticalsList.addView(checkBox);
        }

        leadsPresenter.startAlphaAnimation(toolbar, 0, View.VISIBLE);
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
    public void setContactPickedDetails(String name, String mobile) {
        editName.setText(name);
        editRefNo.setText(mobile);
    }

    @Override
    public void setContactCannotPickedError(String msg) {
        leadsPresenter.showAlertMessageDialog(PostLeadActivity.this, msg);
    }

    @Override
    public void onValidationSuccess() {
        leadsPresenter.showPostTermsConditionsDialog(PostLeadActivity.this);
    }

    @Override
    public void onTermsAccepted() {
        leadsInteractor.doPostLead(PostLeadActivity.this, editName.getText().toString().trim(),
                editEmail.getText().toString(), editRefNo.getText().toString(), editDescription.getText().toString(), selectedVerticals);
    }

    @Override
    public void onTermsDeclined() {
        leadsPresenter.showAlertMessageDialog(PostLeadActivity.this, getString(R.string.post_lead_terms_decline));

    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        progressDialog.dismiss();
    }

    @Override
    public void onLeadPostSuccess(String success,String responseCode) {

        if (responseCode.equalsIgnoreCase(getString(R.string.success_response_code))) {
            PrefUtils.setActiveLead(PostLeadActivity.this, true);
        }



        Functions.showAlterDialog(this, success, "Ok").show();
        Functions.setOnAlertButtonClicked(new OnAlertButtonClicked() {
            @Override
            public void onAlertButtonClicked() {
                Intent intent = new Intent(PostLeadActivity.this, DrawerActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onLeadPostFail(String error) {
        Functions.showAlterDialog(this, error, "Ok").show();
        Functions.setOnAlertButtonClicked(new OnAlertButtonClicked() {
            @Override
            public void onAlertButtonClicked() {
                Intent intent = new Intent(PostLeadActivity.this, DrawerActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void showNoInternetDialog() {
        leadsPresenter.showNoInternetDialog(PostLeadActivity.this);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int offset) {
        Functions.setToolBarOffset(PostLeadActivity.this, appBarLayout, offset, toolbar);
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
