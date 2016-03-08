package com.webmyne.connect.leads;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.widget.CheckBox;

import com.webmyne.connect.R;
import com.webmyne.connect.Utils.Constants;
import com.webmyne.connect.Utils.Functions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by priyasindkar on 08-03-2016.
 */
public class LeadsPresenterImpl implements LeadsPresenter {
    private LeadsView leadsView;

    public LeadsPresenterImpl(LeadsView leadsView) {
        this.leadsView = leadsView;
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
            VerticalDataObject secondaryVerticalObj = new VerticalDataObject();
            secondaryVerticalObj.setVerticalName(verticals.get(i));
            secondaryVerticalObj.setVerticalShortName(verticalShortNames.get(i));
            secondaryVerticalObj.setVerticalId(verticalIds.get(i));

            secondaryVerticals.add(secondaryVerticalObj);
        }

        if (leadsView != null) {
            leadsView.setInitUI(verticalDataObject, secondaryVerticals);
        }
    }

    @Override
    public void onVerticalSelect(int verticalId, boolean isChecked, List<Integer> selectedVerticals) {
        if(isChecked) {
            if( !selectedVerticals.contains(verticalId))
                selectedVerticals.add(verticalId);
        } else {
            if( selectedVerticals.contains(verticalId))
                selectedVerticals.remove(new Integer(verticalId));
        }
        if(leadsView != null) {
            leadsView.onSelectVertical(selectedVerticals);
        }
    }

    @Override
    public void validateFormFields(Activity activity, String name, String emailId, String contactNo) {
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
            if(leadsView != null) {
                leadsView.onValidationSuccess();
            }
        }
    }

    @Override
    public void showPostTermsConditionsDialog(Activity activity) {
        PostLeadTermsConditionsDialog filterDialog = new PostLeadTermsConditionsDialog(activity, R.style.CustomAlertDialogStyle, leadsView);
        filterDialog.getWindow().getAttributes().width = (int) (Functions.getDeviceMetrics(activity).widthPixels*0.8);
        filterDialog.show();
    }

    @Override
    public void showDeclineMessageDialog(Activity activity) {
        Functions.getSimpleOkAlterDialog(activity, activity.getString(R.string.post_lead_terms_decline), "Ok" ).show();
    }

}
