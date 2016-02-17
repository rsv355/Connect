package com.webmyne.connect.revenue;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.Toast;

import com.andexert.library.RippleView;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.webmyne.connect.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by priyasindkar on 16-02-2016.
 */
public class RevenueHistoryFilterDialog extends AppCompatDialog implements View.OnClickListener{
    private AppCompatButton btnCancel, btnFilter;
    private Context mContext;
    private MaterialEditText editStartDate, editEndDate;

    public RevenueHistoryFilterDialog(Context context) {
        super(context);
        mContext = context;
    }

    public RevenueHistoryFilterDialog(Context context, int themeResId) {
        super(context, themeResId);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_filter_revenues);
        //setTitle(mContext.getResources().getString(R.string.filter_revenue_history));
        btnCancel = (AppCompatButton) findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(this);
        btnFilter = (AppCompatButton) findViewById(R.id.btnFilter);
        btnFilter.setOnClickListener(this);
        editStartDate = (MaterialEditText) findViewById(R.id.editStartDate);
        editStartDate.setOnClickListener(this);
        editEndDate = (MaterialEditText) findViewById(R.id.editEndDate);
        editEndDate.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.editStartDate:
                Calendar newCalendar = Calendar.getInstance();
                DatePickerDialog fromDatePickerDialog = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year, monthOfYear, dayOfMonth);
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        editStartDate.setText(sdf.format(newDate.getTime()));
                    }
                }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

                fromDatePickerDialog.getDatePicker().setMinDate(newCalendar.getTimeInMillis());
                fromDatePickerDialog.show();
                break;

            case R.id.editEndDate:
                newCalendar = Calendar.getInstance();
                DatePickerDialog toDatePickerDialog = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year, monthOfYear, dayOfMonth);
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        editEndDate.setText(sdf.format(newDate.getTime()));
                    }
                }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

                toDatePickerDialog.getDatePicker().setMinDate(newCalendar.getTimeInMillis());
                toDatePickerDialog.show();
                break;
            case R.id.btnFilter:
                break;
            case R.id.btnCancel:
                dismiss();
                break;
        }
    }
}
