package com.webmyne.connect.user;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.view.Window;
import android.widget.DatePicker;

import com.rengwuxian.materialedittext.MaterialEditText;
import com.webmyne.connect.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by priyasindkar on 16-02-2016.
 */
public class AddReferCodeFilterDialog extends AppCompatDialog implements View.OnClickListener{
    private AppCompatButton btnCancel, btnReferCode;
    private Context mContext;

    public AddReferCodeFilterDialog(Context context) {
        super(context);
        mContext = context;
    }

    public AddReferCodeFilterDialog(Context context, int themeResId) {
        super(context, themeResId);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_filter_add_refer_code);
        //setTitle(mContext.getResources().getString(R.string.filter_revenue_history));
        btnCancel = (AppCompatButton) findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(this);
        btnReferCode = (AppCompatButton) findViewById(R.id.btnReferCode);
        btnReferCode.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnReferCode:
                break;
            case R.id.btnCancel:
                dismiss();
                break;
        }
    }
}
