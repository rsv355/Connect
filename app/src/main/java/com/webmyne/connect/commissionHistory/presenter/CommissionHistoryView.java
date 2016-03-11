package com.webmyne.connect.commissionHistory.presenter;

import com.webmyne.connect.commissionHistory.adapter.CommissionListAdapter;
import com.webmyne.connect.commissionHistory.model.CommissionDataObject;

import java.util.ArrayList;

/**
 * Created by krishnakumar on 10-03-2016.
 */
public interface CommissionHistoryView {
    void showProgressDialog();
    void hideProgressDialog();
    void showFooter();
    void hideFooter();
    void showToast(String msg);
    void addEmptyView(String msg);
    void showNoInternetDialog();

    void setData(ArrayList<CommissionDataObject> listData, CommissionListAdapter mCommissionAdapter);

}


