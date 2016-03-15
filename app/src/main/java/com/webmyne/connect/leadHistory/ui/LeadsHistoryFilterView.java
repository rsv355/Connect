package com.webmyne.connect.leadHistory.ui;

import com.rengwuxian.materialedittext.MaterialEditText;
import com.webmyne.connect.leadHistory.model.LeadHistoryRequest;
import com.webmyne.connect.leadHistory.model.LeadStatusObject;
import com.webmyne.connect.postLead.model.VerticalDataObject;

import java.util.HashMap;

/**
 * Created by priyasindkar on 14-03-2016.
 */
public interface LeadsHistoryFilterView {
    void setUI(HashMap<Integer, VerticalDataObject> verticals, HashMap<Integer, LeadStatusObject> leadStatuses);
    void setDate(String date, MaterialEditText editText);
    void setDateError(MaterialEditText editText, String error);
    void onVerticalSelected(HashMap<Integer, Integer> selectedVerticals);
    void onLeadStatusSelected(HashMap<Integer, Integer> selectedLeadStatuses);
    void setFilter(LeadHistoryRequest leadHistoryRequest);
}
