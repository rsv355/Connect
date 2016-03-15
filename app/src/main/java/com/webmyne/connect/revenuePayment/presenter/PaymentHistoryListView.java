package com.webmyne.connect.revenuePayment.presenter;

import com.webmyne.connect.revenuePayment.model.PaymentHistoryDataObject;

import java.util.ArrayList;

/**
 * Created by priyasindkar on 15-03-2016.
 */
public interface PaymentHistoryListView {
    void initUI(ArrayList<PaymentHistoryDataObject> list);
}
