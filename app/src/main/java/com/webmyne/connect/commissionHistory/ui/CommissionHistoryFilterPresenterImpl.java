package com.webmyne.connect.commissionHistory.ui;

import android.content.Context;

import com.webmyne.connect.Utils.Constants;
import com.webmyne.connect.commissionHistory.presenter.CommissionHistoryPresenter;
import com.webmyne.connect.leadHistory.model.LeadStatusObject;
import com.webmyne.connect.leadHistory.model.LeadStatuses;
import com.webmyne.connect.leadHistory.ui.LeadsHistoryFilterPresenter;
import com.webmyne.connect.leadHistory.ui.LeadsHistoryFilterView;
import com.webmyne.connect.postLead.model.VerticalDataObject;
import com.webmyne.connect.postLead.model.Verticals;

import java.util.HashMap;

/**
 * Created by priyasindkar on 14-03-2016.
 */
public class CommissionHistoryFilterPresenterImpl implements CommissionHistoryFilterPresenter {
    private Context mContext;
    private CommissionHistoryFilterView commissionHistoryFilterView;

    public CommissionHistoryFilterPresenterImpl(Context mContext,CommissionHistoryFilterView commissionHistoryFilterView) {
        this.mContext = mContext;
        this.commissionHistoryFilterView = commissionHistoryFilterView;
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

        commissionHistoryFilterView.setUI(verticals);


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
        if (commissionHistoryFilterView != null) {
            commissionHistoryFilterView.onVerticalSelected(selectedVerticals);
        }
    }

    @Override
    public void onDestroy() {
        commissionHistoryFilterView = null;
    }
}
