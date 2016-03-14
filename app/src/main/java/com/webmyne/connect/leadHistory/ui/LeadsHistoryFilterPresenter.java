package com.webmyne.connect.leadHistory.ui;

import java.util.HashMap;

/**
 * Created by priyasindkar on 14-03-2016.
 */
public interface LeadsHistoryFilterPresenter {
    void setUI();
    void onVerticalSelected(int verticalId, boolean isChecked, HashMap<Integer, Integer> selectedVerticals);
    void onLeadStatusSelected(int leadStatusId, boolean isChecked, HashMap<Integer, Integer> selectedLeadStatuses);
    void onDestroy();
}
