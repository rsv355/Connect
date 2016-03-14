package com.webmyne.connect.commissionHistory.ui;

import java.util.HashMap;

/**
 * Created by priyasindkar on 14-03-2016.
 */
public interface CommissionHistoryFilterPresenter {
    void setUI();
    void onVerticalSelected(int verticalId, boolean isChecked, HashMap<Integer, Integer> selectedVerticals);
    void onDestroy();
}
