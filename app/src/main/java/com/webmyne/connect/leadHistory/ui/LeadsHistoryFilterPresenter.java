package com.webmyne.connect.leadHistory.ui;

import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.HashMap;

/**
 * Created by priyasindkar on 14-03-2016.
 */
public interface LeadsHistoryFilterPresenter {
    void setUI();
    void showDatePicker(MaterialEditText editText, String otherDate, boolean isStartDate);
    void onFilterDataSet(String keyword, String startDate, String endDate,HashMap<Integer, Integer> verticals, HashMap<Integer, Integer> leadStatues);
    void onVerticalSelected(int verticalId, boolean isChecked, HashMap<Integer, Integer> selectedVerticals);
    void onLeadStatusSelected(int leadStatusId, boolean isChecked, HashMap<Integer, Integer> selectedLeadStatuses);
    void onDestroy();
}
