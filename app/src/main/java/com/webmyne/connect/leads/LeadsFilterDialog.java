package com.webmyne.connect.leads;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.andexert.library.RippleView;
import com.webmyne.connect.R;

/**
 * Created by priyasindkar on 16-02-2016.
 */
public class LeadsFilterDialog extends Dialog {
    private RippleView txtCancel, txtFilter;
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
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.dialog_filter_leads);
        txtCancel = (RippleView) findViewById(R.id.txtCancel);
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
}
