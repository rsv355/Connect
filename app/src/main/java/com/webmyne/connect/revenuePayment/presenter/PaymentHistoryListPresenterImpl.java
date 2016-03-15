package com.webmyne.connect.revenuePayment.presenter;

import android.content.Context;

import com.webmyne.connect.customUI.textDrawableIcons.ColorGenerator;
import com.webmyne.connect.revenuePayment.model.PaymentHistoryDataObject;

import java.util.ArrayList;

/**
 * Created by priyasindkar on 15-03-2016.
 */
public class PaymentHistoryListPresenterImpl implements PaymentHistoryListPresenter {
    private PaymentHistoryListView paymentHistoryListView;
    private Context mContext;

    public PaymentHistoryListPresenterImpl(PaymentHistoryListView paymentHistoryListView, Context mContext) {
        this.paymentHistoryListView = paymentHistoryListView;
        this.mContext = mContext;
    }

    @Override
    public void initUI() {
        ArrayList<PaymentHistoryDataObject> list = new ArrayList<>();
        list.add(new PaymentHistoryDataObject("QW123", 100,"2016-02-03 08:00AM GMT", 95, "Funds Pending Clearance", ColorGenerator.MATERIAL.getARandomColor()));
        list.add(new PaymentHistoryDataObject("UY123", 200,"2016-02-03 08:00AM GMT", 175.50, "Funds Pending Clearance", ColorGenerator.MATERIAL.getARandomColor()));
        list.add(new PaymentHistoryDataObject("EW786", 100,"2016-01-21 08:00AM GMT", 95, "Withdrawal Initiated", ColorGenerator.MATERIAL.getARandomColor()));
        list.add(new PaymentHistoryDataObject("IN386", 70,"2016-01-20 08:00AM GMT", 65, "Funds Pending Clearance", ColorGenerator.MATERIAL.getARandomColor()));
        list.add(new PaymentHistoryDataObject("BJ096", 50, "2015-12-10 08:00AM GMT", 45, "Withdrawal Completed", ColorGenerator.MATERIAL.getARandomColor()));
        list.add(new PaymentHistoryDataObject("XS956", 100, "2015-11-16 08:00AM GMT", 95, "Withdrawal Completed", ColorGenerator.MATERIAL.getARandomColor()));
        list.add(new PaymentHistoryDataObject("OP123", 20,"2015-10-13 08:00AM GMT", 7.50, "Withdrawal Completed", ColorGenerator.MATERIAL.getARandomColor()));

        paymentHistoryListView.initUI(list);
    }
}
