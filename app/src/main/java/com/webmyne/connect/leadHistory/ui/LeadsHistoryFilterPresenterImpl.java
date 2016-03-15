package com.webmyne.connect.leadHistory.ui;

import android.app.Activity;
import android.content.Context;

import com.rengwuxian.materialedittext.MaterialEditText;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.webmyne.connect.R;
import com.webmyne.connect.Utils.ComplexPreferences;
import com.webmyne.connect.Utils.Constants;
import com.webmyne.connect.Utils.Functions;
import com.webmyne.connect.leadHistory.model.LeadHistoryRequest;
import com.webmyne.connect.leadHistory.model.LeadStatusObject;
import com.webmyne.connect.leadHistory.model.LeadStatuses;
import com.webmyne.connect.postLead.model.VerticalDataObject;
import com.webmyne.connect.postLead.model.Verticals;
import com.webmyne.connect.user.model.UserLoginOutput;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by priyasindkar on 14-03-2016.
 */
public class LeadsHistoryFilterPresenterImpl implements LeadsHistoryFilterPresenter {
    private Context mContext;
    private LeadsHistoryFilterView leadsHistoryFilterView;

    public LeadsHistoryFilterPresenterImpl(Context mContext, LeadsHistoryFilterView leadsHistoryFilterView) {
        this.mContext = mContext;
        this.leadsHistoryFilterView = leadsHistoryFilterView;
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


        HashMap<Integer, LeadStatusObject> leadStatuses = new HashMap<>();

        for (int i = 0; i < Constants.TOTAL_LEAD_STATUSES; i++) {
            int statusCode = LeadStatuses.getStatusCode(i);
            String name = LeadStatuses.getStatusMSG(i);
            String description = LeadStatuses.getStatusDesc(i);

            LeadStatusObject leadStatusObject = new LeadStatusObject();
            leadStatusObject.setLeadStatusId(statusCode);
            leadStatusObject.setLeadStatusName(name);
            leadStatusObject.setLeadStatusDescription(description);

            leadStatuses.put(statusCode, leadStatusObject);
        }

        leadsHistoryFilterView.setUI(verticals, leadStatuses);


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
                                    if (leadsHistoryFilterView != null) {
                                        leadsHistoryFilterView.setDate(date, editText);
                                    }
                                } else {
                                    if (leadsHistoryFilterView != null) {
                                        leadsHistoryFilterView.setDateError(editText, mContext.getString(R.string.error_start_date));
                                    }
                                }
                            } else {
                                if (Functions.validateStartEndDates(otherDate, date)) {
                                    if (leadsHistoryFilterView != null) {
                                        leadsHistoryFilterView.setDate(date, editText);
                                    }
                                } else {
                                    if (leadsHistoryFilterView != null) {
                                        leadsHistoryFilterView.setDateError(editText, mContext.getString(R.string.error_end_date));
                                    }
                                }
                            }
                        } else {
                            if (leadsHistoryFilterView != null) {
                                leadsHistoryFilterView.setDate(date, editText);
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
    public void onFilterDataSet(String keyword, String startDate, String endDate, HashMap<Integer, Integer> verticals, HashMap<Integer, Integer> leadStatues) {

        ComplexPreferences complexPreferences = new ComplexPreferences(mContext, "login-user", mContext.MODE_PRIVATE);
        UserLoginOutput currentUser = complexPreferences.getObject("loggedInUser", UserLoginOutput.class);

        LeadHistoryRequest leadHistoryRequest = new LeadHistoryRequest();
        leadHistoryRequest.setUserID(currentUser.getUserID());
        leadHistoryRequest.setSearchInput(keyword);
        leadHistoryRequest.setStartDate(startDate);
        leadHistoryRequest.setEndDate(endDate);
        leadHistoryRequest.setLastLeadID(0);

        int[] verticalArray;
        int[] leadStatusesArray;
        verticalArray = Functions.convertHashMapToIntArray(verticals);
        leadStatusesArray = Functions.convertHashMapToIntArray(leadStatues);
        leadHistoryRequest.setLstLeadStatus(leadStatusesArray);
        leadHistoryRequest.setLstLeadTypeId(verticalArray);

        if(leadsHistoryFilterView != null) {
            leadsHistoryFilterView.setFilter(leadHistoryRequest);
        }
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
        if (leadsHistoryFilterView != null) {
            leadsHistoryFilterView.onVerticalSelected(selectedVerticals);
        }
    }

    @Override
    public void onLeadStatusSelected(int leadStatusId, boolean isChecked, HashMap<Integer, Integer> selectedLeadStatuses) {
        if (isChecked) {
            if (!selectedLeadStatuses.containsKey(leadStatusId))
                selectedLeadStatuses.put(leadStatusId, leadStatusId);
        } else {
            if (selectedLeadStatuses.containsKey(leadStatusId))
                selectedLeadStatuses.remove(new Integer(leadStatusId));
        }
        if (leadsHistoryFilterView != null) {
            leadsHistoryFilterView.onLeadStatusSelected(selectedLeadStatuses);
        }
    }

    @Override
    public void onDestroy() {
        leadsHistoryFilterView = null;
    }
}
