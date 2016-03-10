package com.webmyne.connect.customUI;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.tt.whorlviewlibrary.WhorlView;
import com.webmyne.connect.R;

/**
 * Created by priyasindkar on 22-02-2016.
 */
public class CustomProgressDialog extends ProgressDialog {
    private WhorlView progressBar;

    public CustomProgressDialog(Context context) {
        super(context);

    }


    public static ProgressDialog ctor(Context context) {
        CustomProgressDialog dialog = new CustomProgressDialog(context);
        dialog.setIndeterminate(true);
        //dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setCancelable(false);
        return dialog;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);// must call before super.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_progress_dialog);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        progressBar = (WhorlView) findViewById(R.id.progressBar);
    }

    @Override
    public void show() {
        super.show();
        progressBar.setVisibility(View.VISIBLE);
        progressBar.start();
    }

    @Override
    public void dismiss() {
        super.dismiss();
        progressBar.setVisibility(View.GONE);
        progressBar.stop();
    }
}
