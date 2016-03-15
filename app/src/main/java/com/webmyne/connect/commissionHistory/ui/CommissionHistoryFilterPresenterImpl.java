package com.webmyne.connect.commissionHistory.ui;

import android.app.Activity;
import android.content.Context;

import com.rengwuxian.materialedittext.MaterialEditText;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.webmyne.connect.R;
import com.webmyne.connect.Utils.ComplexPreferences;
import com.webmyne.connect.Utils.Constants;
import com.webmyne.connect.Utils.Functions;
import com.webmyne.connect.commissionHistory.model.CommissionHistoryRequest;
import com.webmyne.connect.commissionHistory.presenter.CommissionHistoryPresenter;
import com.webmyne.connect.leadHistory.model.LeadHistoryRequest;
import com.webmyne.connect.leadHistory.model.LeadStatusObject;
import com.webmyne.connect.leadHistory.model.LeadStatuses;
import com.webmyne.connect.leadHistory.ui.LeadsHistoryFilterPresenter;
import com.webmyne.connect.leadHistory.ui.LeadsHistoryFilterView;
import com.webmyne.connect.postLead.model.VerticalDataObject;
import com.webmyne.connect.postLead.model.Verticals;
import com.webmyne.connect.user.model.UserLoginOutput;

import java.util.Calendar;
import java.util.HashMap;

/**
 * Created by priyasindkar on 14-03-2016.
 */
public class CommissionHistoryFilterPresenterImpl implements CommissionHistoryFilterPresenter {
    private Context mContext;
    private CommissionHistoryFilterView commissionHistoryFilterView;

    public CommissionHistoryFilterPresenterImpl(Context mContext,CommissionHistoryFilterView commissionHistoryFilterView) {
        this.mContext = mContext;
        this.commissionHistoryFilterView = commissionHistoryFilterView;
    }

    @Override
    public void setUI() {
        HashMap<Integer, VerticalDataObject> verticals = new HashMap<>();

        for (int i = 0; i < Constants.TOTAL_VERTICALS; i++) {
            int verticalIndex = Verticals.getVerticalCode(i);
            String verticalName = Verticals.getVerticalName(i);
            String verticalShortName = Verticals.getVerticalShortName(i);
            int verticalId = Verticals.getVerticalId(i);

            VerticalDataObject verticalDataObject = new VerticalDataObject();
            verticalDataObject.setVerticalId(verticalId);
            verticalDataObject.setVerticalName(verticalName);
            verticalDataObject.setVerticalShortName(verticalShortName);
            verticalDataObject.setVerticalIndexNo(verticalIndex);

            verticals.put(verticalIndex, verticalDataObject);
        }
        commissionHistoryFilterView.setUI(verticals);
    }

    @Override
    public void showDatePicker(final MaterialEditText editText, final String otherDate, final boolean isStartDate) {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                        String date = year + "-" + String.format("%02d", ++monthOfYear) + "-" + String.format("%02d", dayOfMonth);

                        if (otherDate.trim().length() > 0) {
                            if (isStartDate) {
                                if (Functions.validateStartEndDates(date, otherDate)) {
                                    if (commissionHistoryFilterView != null) {
                                        commissionHistoryFilterView.setDate(date, editText);
                                    }
                                } else {
                                    if (commissionHistoryFilterView != null) {
                                        commissionHistoryFilterView.setDateError(editText, mContext.getString(R.string.error_start_date));
                                    }
                                }
                            } else {
                                if (Functions.validateStartEndDates(otherDate, date)) {
                                    if (commissionHistoryFilterView != null) {
                                        commissionHistoryFilterView.setDate(date, editText);
                                    }
                                } else {
                                    if (commissionHistoryFilterView != null) {
                                        commissionHistoryFilterView.setDateError(editText, mContext.getString(R.string.error_end_date));
                                    }
                                }
                            }
                        } else {
                            if (commissionHistoryFilterView != null) {
                                commissionHistoryFilterView.setDate(date, editText);
                            }
                        }
                    }


                },
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        Activity activity = (Activity) mContext;
        dpd.show(activity.getFragmentManager(), "Datepickerdialog");
    }

    @Override
    public void onVerticalSelected(int verticalId, boolean isChecked, HashMap<Integer, Integer> selectedVerticals) {
        if (isChecked) {
            if (!selectedVerticals.containsKey(verticalId))
                selectedVerticals.put(verticalId, verticalId);
        } else {
            if (selectedVerticals.containsKey(verticalId))
                selectedVerticals.remove(new Integer(verticalId));
        }
        if (commissionHistoryFilterView != null) {
            commissionHistoryFilterView.onVerticalSelected(selectedVerticals);
        }
    }

    @Override
    public void onFilterDataSet(String keyword, String startDate, String endDate, HashMap<Integer, Integer> verticals) {
        ComplexPreferences complexPreferences = new ComplexPreferences(mContext, "login-user", mContext.MODE_PRIVATE);
        UserLoginOutput currentUser = complexPreferences.getObject("loggedInUser", UserLoginOutput.class);

        CommissionHistoryRequest commissionHistoryRequest = new CommissionHistoryRequest();
        commissionHistoryRequest.setUserID(currentUser.getUserID());
        commissionHistoryRequest.setSearchInput(keyword);
        commissionHistoryRequest.setStartDate(startDate);
        commissionHistoryRequest.setEndDate(endDate);
        commissionHistoryRequest.setLastLeadID(0);

        int[] verticalArray;
        verticalArray = Functions.convertHashMapToIntArray(verticals);
        commissionHistoryRequest.setLstLeadTypeId(verticalArray);

        if(commissionHistoryFilterView != null) {
            commissionHistoryFilterView.setFilter(commissionHistoryRequest);
        }
    }

    @Override
    public void onDestroy() {
        commissionHistoryFilterView = null;
    }
}
