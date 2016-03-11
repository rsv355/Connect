package com.webmyne.connect.leadHistory.presenter;

import com.webmyne.connect.leadHistory.adapter.LeadsListAdapter;
import com.webmyne.connect.leadHistory.model.LeadDataObject;

import java.util.ArrayList;

/**
 * Created by krishnakumar on 10-03-2016.
 */
public interface LeadsHistoryView {

    void showProgressDialog();
    void hideProgressDialog();
    void showFooter();
    void hideFooter();
    void showToast(String msg);
    void addEmptyView(String msg);
    void showNoInternetAlert();
    void setData(ArrayList<LeadDataObject> listData,LeadsListAdapter mLeadsAdapter);

}


