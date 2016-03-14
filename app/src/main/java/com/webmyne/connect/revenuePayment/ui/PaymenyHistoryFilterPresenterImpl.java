package com.webmyne.connect.revenuePayment.ui;

import android.app.Activity;
import android.content.Context;

import com.rengwuxian.materialedittext.MaterialEditText;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

/**
 * Created by priyasindkar on 14-03-2016.
 */
public class PaymenyHistoryFilterPresenterImpl implements PaymentHistoryFilterPresenter {
    private Context mContext;
    private PaymentHistoryDialogView paymentHistoryDialogView;

    public PaymenyHistoryFilterPresenterImpl(Context mContext, PaymentHistoryDialogView paymentHistoryDialogView) {
        this.mContext = mContext;
        this.paymentHistoryDialogView = paymentHistoryDialogView;
    }

    @Override
    public void showDatePicker(final MaterialEditText editText) {
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                        String date = year + "-" + String.format("%02d", ++monthOfYear) + "-" + String.format("%02d", dayOfMonth);
                        if (paymentHistoryDialogView != null) {
                            paymentHistoryDialogView.setDate(date, editText);
                        }
                    }
                },
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.setMaxDate(now);
        Activity activity = (Activity) mContext;
        dpd.show(activity.getFragmentManager(), "Datepickerdialog");
    }
}

