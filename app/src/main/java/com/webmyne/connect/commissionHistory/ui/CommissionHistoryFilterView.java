package com.webmyne.connect.commissionHistory.ui;

import com.rengwuxian.materialedittext.MaterialEditText;
import com.webmyne.connect.commissionHistory.model.CommissionHistoryRequest;
import com.webmyne.connect.leadHistory.model.LeadHistoryRequest;
import com.webmyne.connect.leadHistory.model.LeadStatusObject;
import com.webmyne.connect.postLead.model.VerticalDataObject;

import java.util.HashMap;

/**
 * Created by priyasindkar on 14-03-2016.
 */
public interface CommissionHistoryFilterView {
    void setUI(HashMap<Integer, VerticalDataObject> verticals);
    void setDate(String date, MaterialEditText editText);
    void setDateError(MaterialEditText editText, String error);
    void onVerticalSelected(HashMap<Integer, Integer> selectedVerticals);
    void setFilter(CommissionHistoryRequest commissionHistoryRequest);
}
