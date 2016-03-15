package com.webmyne.connect.leadHistory.ui;

import com.webmyne.connect.leadHistory.model.LeadHistoryRequest;

/**
 * Created by priyasindkar on 15-03-2016.
 */
public interface LeadHistoryListFilterCommunicatorView {
    void onLeadFilterSet(LeadHistoryRequest leadHistoryRequest);
    void onClearFilter();
}
