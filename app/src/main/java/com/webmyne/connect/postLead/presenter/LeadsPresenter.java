package com.webmyne.connect.postLead.presenter;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

/**
 * Created by priyasindkar on 08-03-2016.
 */
public interface LeadsPresenter {
    void initUIData(Activity activity, Intent data);
    void setToolBarOffset(Activity activity, AppBarLayout appBarLayout, int offset, Toolbar toolbar);
    void startAlphaAnimation (View v, long duration, int visibility);
    void onVerticalSelect(int verticalId, boolean isChecked, List<Integer> selectedVerticals);
    void pickContact(View v, Activity activity);
    void contactPicked(Intent data);
    void validateFormFields(Activity activity, String name, String emailId, String contactNo);
    void showPostTermsConditionsDialog(Activity activity);
    void showAlertMessageDialog(Activity activity, String msg);
    void showNoInternetDialog(Activity activity);
    void onDestroy();
}
