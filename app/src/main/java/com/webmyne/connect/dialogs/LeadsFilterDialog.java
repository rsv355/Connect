package com.webmyne.connect.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toolbar;

import com.webmyne.connect.R;

/**
 * Created by priyasindkar on 16-02-2016.
 */
public class LeadsFilterDialog extends Dialog implements View.OnClickListener{
    private ImageView imgClose;

    public LeadsFilterDialog(Context context) {
        super(context);
    }

    public LeadsFilterDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.dialog_filter_leads);
        imgClose = (ImageView) findViewById(R.id.imgClose);
        imgClose.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgClose:
                dismiss();
                break;
        }
    }
}
