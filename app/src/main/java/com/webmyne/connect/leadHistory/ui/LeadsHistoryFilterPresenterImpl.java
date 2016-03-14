package com.webmyne.connect.leadHistory.ui;

import android.content.Context;

import com.webmyne.connect.Utils.Constants;
import com.webmyne.connect.leadHistory.model.LeadStatusObject;
import com.webmyne.connect.leadHistory.model.LeadStatuses;
import com.webmyne.connect.postLead.model.VerticalDataObject;
import com.webmyne.connect.postLead.model.Verticals;

import java.util.HashMap;

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

 /*   @Override
    public void onVerticalCheckedChange(String verticalName, boolean isChecked, HashMap<Integer, Integer> selectedVerticals) {
        if (isChecked) {
            if (!selectedVerticals.containsKey(verticalName))
                selectedVerticals.put(verticalName, verticalId);
        } else {
            if (selectedVerticals.containsKey(verticalId))
                selectedVerticals.remove(new Integer(verticalId));
        }
        if (leadsHistoryFilterView != null) {
            leadsHistoryFilterView.onVerticalSelected(selectedVerticals);
        }
    }*/

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
