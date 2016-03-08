package com.webmyne.connect.leads;

import android.app.Activity;
import android.content.Intent;

import java.util.List;

/**
 * Created by priyasindkar on 08-03-2016.
 */
public interface LeadsPresenter {
    void initUIData(Activity activity, Intent data);
    void onVerticalSelect(int verticalId, boolean isChecked, List<Integer> selectedVerticals);
    void validateFormFields(Activity activity, String name, String emailId, String contactNo);
    void showPostTermsConditionsDialog(Activity activity);
    void showDeclineMessageDialog(Activity activity);
}
