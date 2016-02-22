package com.webmyne.connect.user;

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
import com.rengwuxian.materialedittext.MaterialEditText;
import com.webmyne.connect.R;
import com.webmyne.connect.Utils.Functions;
import com.webmyne.connect.customUI.CustomProgressDialog;
import com.webmyne.connect.user.MVP.EditProfilePresenter;
import com.webmyne.connect.user.MVP.EditProfilePresenterImpl;
import com.webmyne.connect.user.MVP.EditProfileView;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by priyasindkar on 11-02-2016.
 */
public class EditProfileActivity1 extends AppCompatActivity implements View.OnClickListener, EditProfileView{

    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbar;
    private FloatingActionButton fab;
    private TextView txtMyReferCode;
    private MaterialAutoCompleteTextView editLocation;
    private RippleView txtUpdate;
    private MaterialEditText editEmail;
    private List<String> dataset = new LinkedList<>(Arrays.asList("One", "Two", "Three", "Four", "Five"));
    private CustomProgressDialog progressDialog;

    private EditProfilePresenter editProfilePresenter;

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

        progressDialog = new CustomProgressDialog(EditProfileActivity1.this);
        progressDialog.setCancelable(false);

        editProfilePresenter = new EditProfilePresenterImpl(this);

        editEmail = (MaterialEditText) findViewById(R.id.editEmail);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);

        txtMyReferCode = (TextView) findViewById(R.id.txtMyReferCode);
        txtMyReferCode.setTypeface(Functions.getTypeFace(this));
        txtUpdate = (RippleView) findViewById(R.id.txtUpdate);
        txtUpdate.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                editProfilePresenter.validateFormFields(editEmail.getText().toString().trim());
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dataset);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        editLocation = (MaterialAutoCompleteTextView) findViewById(R.id.editLocation);
        editLocation.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        editProfilePresenter.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                editProfilePresenter.showEnterReferCodeDialog();
                break;
        }
    }

    @Override
    public void setError(String errorString) {
        AlertDialog.Builder dialog = Functions.getSimpleOkAlterDialog(EditProfileActivity1.this, errorString, "Ok");
        dialog.show();
    }

    @Override
    public void onSuccess(String successString) {
        AlertDialog.Builder dialog = Functions.getSimpleOkAlterDialog(EditProfileActivity1.this, successString, "Ok");
        dialog.show();
    }

    @Override
    public void showReferCodeAlert() {
        AddReferCodeFilterDialog filterDialog = new AddReferCodeFilterDialog(EditProfileActivity1.this, R.style.CustomAlertDialogStyle);
        filterDialog.show();
    }

    @Override
    public void showProgress() {
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.hide();
    }
}
