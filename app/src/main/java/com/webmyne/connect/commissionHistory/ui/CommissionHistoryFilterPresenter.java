package com.webmyne.connect.commissionHistory.ui;

import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.HashMap;

/**
 * Created by priyasindkar on 14-03-2016.
 */
public interface CommissionHistoryFilterPresenter {
    void setUI();
    void showDatePicker(MaterialEditText editText, String otherDate, boolean isStartDate);
    void onVerticalSelected(int verticalId, boolean isChecked, HashMap<Integer, Integer> selectedVerticals);
    void onFilterDataSet(String keyword, String startDate, String endDate,HashMap<Integer, Integer> verticals);
    void onDestroy();
}
