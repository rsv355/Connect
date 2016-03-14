package com.webmyne.connect.commissionHistory.ui;

import com.webmyne.connect.leadHistory.model.LeadStatusObject;
import com.webmyne.connect.postLead.model.VerticalDataObject;

import java.util.HashMap;

/**
 * Created by priyasindkar on 14-03-2016.
 */
public interface CommissionHistoryFilterView {
    void setUI(HashMap<Integer, VerticalDataObject> verticals);
    void onVerticalSelected(HashMap<Integer, Integer> selectedVerticals);
}
