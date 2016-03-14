package com.webmyne.connect.user;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ListViewCompat;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.andexert.library.RippleView;
import com.rengwuxian.materialedittext.MaterialAutoCompleteTextView;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.webmyne.connect.R;
import com.webmyne.connect.Utils.Functions;
import com.webmyne.connect.customUI.CustomProgressDialog;
import com.webmyne.connect.user.model.UserLoginOutput;
import com.webmyne.connect.user.presenter.EditProfilePresenter;
import com.webmyne.connect.user.presenter.EditProfilePresenterImpl;
import com.webmyne.connect.user.presenter.EditProfileView;
import com.webmyne.connect.user.ui.AddReferCodeFilterDialog;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by priyasindkar on 11-02-2016.
 */
public class EditProfileActivity extends AppCompatActivity implements View.OnClickListener, EditProfileView, AppBarLayout.OnOffsetChangedListener {
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbar;
    private AppBarLayout appbar;
    private TextView txtMyReferCode;
    private MaterialAutoCompleteTextView editLocation;
    private RippleView txtUpdate;
    private MaterialEditText editName, editEmail, editDOB, editPhone, editZipcode, editIndustry;
    private List<String> locationList = new LinkedList<>(/*Arrays.asList("One", "Two", "Three", "Four", "Five")*/);
    private CustomProgressDialog progressDialog;
    private EditProfilePresenter editProfilePresenter;
    private UserLoginOutput currentUser;
    private UserLoginOutput updatedUserObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        init();
    }

    private void init() {
        toolbar = (Toolbar) findViewById(R.id.anim_toolbar);
        setSupportActionBar(toolbar);
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(getString(R.string.my_profile_title));
        collapsingToolbar.setExpandedTitleTextAppearance(R.style.ExpandedAppBarTitleStyle);
        collapsingToolbar.setCollapsedTitleTextAppearance(R.style.CollapsedAppBarTitleStyle);
        appbar = (AppBarLayout) findViewById(R.id.appbar);
        appbar.addOnOffsetChangedListener(this);
        toolbar.setNavigationIcon(R.drawable.ic_navigation_close);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        progressDialog = new CustomProgressDialog(EditProfileActivity.this);
        progressDialog.setCancelable(false);

        editProfilePresenter = new EditProfilePresenterImpl(this);

        editName = (MaterialEditText) findViewById(R.id.editName);
        editDOB = (MaterialEditText) findViewById(R.id.editDOB);
        editDOB.setOnClickListener(this);
        editPhone = (MaterialEditText) findViewById(R.id.editPhone);
        editEmail = (MaterialEditText) findViewById(R.id.editEmail);
        editZipcode = (MaterialEditText) findViewById(R.id.editZipcode);
        editIndustry = (MaterialEditText) findViewById(R.id.editIndustry);
        editLocation = (MaterialAutoCompleteTextView) findViewById(R.id.editLocation);
        txtMyReferCode = (TextView) findViewById(R.id.txtMyReferCode);
        txtMyReferCode.setTypeface(Functions.getTypeFace(this));
        txtUpdate = (RippleView) findViewById(R.id.txtUpdate);
        txtUpdate.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                editProfilePresenter.validateFormFields(EditProfileActivity.this, editName.getText().toString().trim(), editEmail.getText().toString().trim(),
                        editDOB.getText().toString().trim(), editPhone.getText().toString().trim(), editZipcode.getText().toString().trim(),
                        editLocation.getText().toString().trim(), editIndustry.getText().toString().trim(),currentUser.Gender, currentUser.UserID);
            }
        });

        editProfilePresenter.initUI(EditProfileActivity.this);


        editZipcode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 5) {
                    editProfilePresenter.getAddressesFromZipcode(EditProfileActivity.this, editZipcode.getText().toString());
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        editProfilePresenter.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.editDOB:
                editProfilePresenter.showDatePicker(EditProfileActivity.this);
                break;
        }
    }

    @Override
    public void onDateSet(String date) {
        editDOB.setText(date);
    }

    @Override
    public void setError(String errorString) {
        AlertDialog.Builder dialog = Functions.getSimpleOkAlterDialog(EditProfileActivity.this, errorString, "Ok");
        dialog.show();
    }

    @Override
    public void setNameError(String errorString) {
        editName.setError(errorString);
    }

    @Override
    public void setEmailError(String errorString) {
        editEmail.setError(errorString);
    }

    @Override
    public void setMobileError(String errorString) {
        editPhone.setError(errorString);
    }

    @Override
    public void setDOBError(String errorString) {
        editDOB.setError(errorString);
    }

    @Override
    public void setZipcodError(String errorString) {
        editZipcode.setError(errorString);
    }

    @Override
    public void setIndustryError(String errorString) {
        editIndustry.setError(errorString);
    }

    @Override
    public void setAddressError(String errorString) {
        editLocation.setError(errorString);
    }

    @Override
    public void setAddressList(String[] addresses) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, addresses);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        editLocation.setAdapter(adapter);
        editLocation.setDropDownAnchor(R.id.editLocation);
        editLocation.showDropDown();

        //dismiss softkeyboard
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

    }

    @Override
    public void onValidationSuccess(boolean isValid, UserLoginOutput userLoginOutput) {
        if (currentUser != null) {
            updatedUserObject = userLoginOutput;
            if (!currentUser.isNewAccount()) {
                editProfilePresenter.doUpdateUser(EditProfileActivity.this, updatedUserObject);
            } else {
                editProfilePresenter.showEnterReferCodeDialog();
            }
        }
    }

    @Override
    public void onReferCodeAdd(boolean isReferCodeAdded, String referCode) {
        if (isReferCodeAdded) {
            updatedUserObject.setReferCode(referCode);
        }
        editProfilePresenter.doUpdateUser(EditProfileActivity.this, updatedUserObject);
    }

    @Override
    public void onUpdateUserSuccess(String successString) {
        Functions.getSimpleOkAlterDialog(EditProfileActivity.this, successString, "Ok").show();
    }

    @Override
    public void onUpdateUserFail(String errorString) {
        Functions.getSimpleOkAlterDialog(EditProfileActivity.this, errorString, "Ok").show();
    }

    @Override
    public void initUI(UserLoginOutput currentUser) {
        this.currentUser = currentUser;

        editName.setText(currentUser.Name);
        editEmail.setText(currentUser.Email);

        if (!currentUser.Mobile.equals("")) {
            editPhone.setText(currentUser.Mobile);
        }
        if (!currentUser.DOB.equals("")) {
            editDOB.setText(currentUser.DOB);
        }
        if (!currentUser.ZipCode.equals("")) {
            editZipcode.setText(currentUser.ZipCode);
        }
        if (!currentUser.Address.equals("")) {
            editLocation.setText(currentUser.Address);
        }

        if (!currentUser.UserReferCode.equals("")) {
            txtMyReferCode.setText(currentUser.UserReferCode);
        }
        editProfilePresenter.startAlphaAnimation(toolbar, 0, View.VISIBLE);
    }

    @Override
    public void showReferCodeAlert() {
        AddReferCodeFilterDialog filterDialog = new AddReferCodeFilterDialog(EditProfileActivity.this, this, R.style.CustomAlertDialogStyle);
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

    @Override
    public void showNoInternetDialog(Activity activity) {
        editProfilePresenter.showNoInternetDialog(activity);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int offset) {
        editProfilePresenter.setToolBarOffset(EditProfileActivity.this, appBarLayout, offset, toolbar);
    }
}
