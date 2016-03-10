package com.webmyne.connect.postLead.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.webmyne.connect.R;

/**
 * Created by priyasindkar on 16-02-2016.
 */
public class LeadsFilterDialog extends AppCompatDialog implements View.OnClickListener{
    private AppCompatButton btnCancel, btnFilter;
    private Context mContext;

    public LeadsFilterDialog(Context context) {
        super(context);
        mContext = context;
    }

    public LeadsFilterDialog(Context context, int themeResId) {
        super(context, themeResId);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_filter_leads);
        btnCancel = (AppCompatButton) findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(this);
        btnFilter = (AppCompatButton) findViewById(R.id.btnFilter);
        btnFilter.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnFilter:
                break;
            case R.id.btnCancel:
                dismiss();
                break;
        }
    }
}
