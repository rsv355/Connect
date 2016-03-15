package com.webmyne.connect.postLead.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;

import com.webmyne.connect.R;
import com.webmyne.connect.Utils.APIConstants;
import com.webmyne.connect.Utils.Constants;
import com.webmyne.connect.Utils.Functions;
import com.webmyne.connect.postLead.PostLeadActivity;
import com.webmyne.connect.postLead.ui.PostLeadTermsConditionsDialog;
import com.webmyne.connect.postLead.model.VerticalDataObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by priyasindkar on 08-03-2016.
 */
public class LeadsPresenterImpl implements LeadsPresenter {
    private LeadsView leadsView;
    private static final int RESULT_PICK_CONTACT = 1;
    private Context mContext;

    public LeadsPresenterImpl(Context mContext, LeadsView leadsView) {
        this.leadsView = leadsView;
        this.mContext = mContext;
    }

    @Override
    public void initUIData(Activity activity, Intent data) {
        int verticalColorIndex = data.getIntExtra("vertical_color_index", 0);
        String selectedVertical = data.getStringExtra("selected_vertical");
        int selectedVerticalNo = data.getIntExtra("selected_vertical_no", 0);
        String verticalShortName = Constants.VERTICAL_SHORT_NAMES.get(selectedVerticalNo);
        int verticalId = Constants.VERTICAL_IDS.get(selectedVerticalNo);

        VerticalDataObject verticalDataObject = new VerticalDataObject(selectedVertical, verticalShortName, selectedVerticalNo, verticalId, verticalColorIndex);

        List<String> verticals = Constants.VERTICAL_NAMES;
        List<String> verticalShortNames = Constants.VERTICAL_SHORT_NAMES;
        List<Integer> verticalIds = Constants.VERTICAL_IDS;
        List<VerticalDataObject> secondaryVerticals = new ArrayList<>();

        for (int i = 0; i < Constants.TOTAL_VERTICALS; i++) {
            if (!verticals.get(i).equalsIgnoreCase(selectedVertical)) {
                VerticalDataObject secondaryVerticalObj = new VerticalDataObject();
                secondaryVerticalObj.setVerticalName(verticals.get(i));
                secondaryVerticalObj.setVerticalShortName(verticalShortNames.get(i));
                secondaryVerticalObj.setVerticalId(verticalIds.get(i));

                secondaryVerticals.add(secondaryVerticalObj);
            }
        }
        if (leadsView != null) {
            leadsView.setInitUI(verticalDataObject, secondaryVerticals);
        }
    }

    @Override
    public void setToolBarOffset(Activity activity, AppBarLayout appBarLayout, int offset, Toolbar toolbar) {
        Functions.setToolBarOffset(activity, appBarLayout, offset, toolbar);
    }

    @Override
    public void onVerticalSelect(int verticalId, boolean isChecked, List<Integer> selectedVerticals) {
        if (isChecked) {
            if (!selectedVerticals.contains(verticalId))
                selectedVerticals.add(verticalId);
        } else {
            if (selectedVerticals.contains(verticalId))
                selectedVerticals.remove(new Integer(verticalId));
        }
        if (leadsView != null) {
            leadsView.onSelectVertical(selectedVerticals);
        }
    }

    @Override
    public void pickContact(View v, Activity activity) {
        Intent contactPickerIntent = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
        activity.startActivityForResult(contactPickerIntent, RESULT_PICK_CONTACT);
    }

    @Override
    public void contactPicked(Intent data) {
        Cursor cursor = null;
        try {
            String phoneNo = null;
            String name = null;
            Uri uri = data.getData();
            cursor = mContext.getContentResolver().query(uri, null, null, null, null);
            cursor.moveToFirst();
            int phoneIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            int nameIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
            phoneNo = cursor.getString(phoneIndex);
            name = cursor.getString(nameIndex);
            cursor.close();
            if (name != null && phoneNo != null) {
                if (leadsView != null) {
                    leadsView.setContactPickedDetails(name, phoneNo);
                }
            } else {
                if (leadsView != null) {
                    leadsView.setContactCannotPickedError(mContext.getString(R.string.contact_not_picker));
                }
            }


        } catch (Exception e) {
            Log.e("exp", e.toString());
            cursor.close();
        }
    }


    @Override
    public void validateFormFields(Activity activity, String name, String emailId, String contactNo) {

        if (Functions.checkInternet(activity)) {
            if (name.trim().length() == 0) {
                if (leadsView != null) {
                    leadsView.setNameError(activity.getString(R.string.name_empty_validation));
                }
            } else if (emailId.trim().length() == 0) {
                if (leadsView != null) {
                    leadsView.setEmailError(activity.getString(R.string.email_empty_validation));
                }
            } else if (!Functions.isEmailValid(emailId.trim())) {
                if (leadsView != null) {
                    leadsView.setEmailError(activity.getString(R.string.email_invalid_validation));
                }
            } else if (contactNo.trim().length() == 0) {
                if (leadsView != null) {
                    leadsView.setContactNoError(activity.getString(R.string.mobile_mandatory_validation));
                }
            } else if (contactNo.trim().length() != 10) {
                if (leadsView != null) {
                    leadsView.setContactNoError(activity.getString(R.string.mobile_invalid_validation));
                }
            } else {
                if (leadsView != null) {
                    leadsView.onValidationSuccess();
                }
            }
        } else {
            if (leadsView != null) {
                leadsView.showNoInternetDialog();
            }
        }
    }

    @Override
    public void showPostTermsConditionsDialog(Activity activity) {
        PostLeadTermsConditionsDialog filterDialog = new PostLeadTermsConditionsDialog(activity, R.style.CustomAlertDialogStyle, leadsView);
        filterDialog.getWindow().getAttributes().width = (int) (Functions.getDeviceMetrics(activity).widthPixels * 0.8);
        filterDialog.show();
    }

    @Override
    public void showAlertMessageDialog(Activity activity, String msg) {
        Functions.getSimpleOkAlterDialog(activity, msg, "Ok").show();
    }

    @Override
    public void showNoInternetDialog(Activity activity) {
        Functions.getSimpleOkAlterDialog(activity, activity.getString(R.string.no_internet_connection), "Ok").show();
    }

    @Override
    public void onDestroy() {
        leadsView = null;
    }

    @Override
    public void startAlphaAnimation(View v, long duration, int visibility) {
        Functions.startAlphaAnimation(v, duration, visibility);
    }

}
