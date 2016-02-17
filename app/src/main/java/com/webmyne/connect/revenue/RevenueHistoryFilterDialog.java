package com.webmyne.connect.revenue;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.andexert.library.RippleView;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.webmyne.connect.R;

/**
 * Created by priyasindkar on 16-02-2016.
 */
public class RevenueHistoryFilterDialog extends Dialog implements View.OnClickListener{
    private RippleView txtCancel, txtFilter;
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
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.dialog_filter_revenues);
        txtCancel = (RippleView) findViewById(R.id.txtCancel);
        editStartDate = (MaterialEditText) findViewById(R.id.editStartDate);
        editStartDate.setOnClickListener(this);
        editEndDate = (MaterialEditText) findViewById(R.id.editEndDate);
        editEndDate.setOnClickListener(this);

        txtCancel.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
               dismiss();
            }
        });
        txtFilter = (RippleView) findViewById(R.id.txtFilter);
        txtFilter.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                Toast.makeText(mContext, "Filter", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.editStartDate:
                break;
            case R.id.editEndDate:
                break;
        }
    }
}
