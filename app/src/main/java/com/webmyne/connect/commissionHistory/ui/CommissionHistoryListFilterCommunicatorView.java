package com.webmyne.connect.commissionHistory.ui;

import com.webmyne.connect.commissionHistory.model.CommissionHistoryRequest;

/**
 * Created by priyasindkar on 15-03-2016.
 */
public interface CommissionHistoryListFilterCommunicatorView {
    void onLeadFilterSet(CommissionHistoryRequest commissionHistoryRequest);
    void onClearFilter();
}
