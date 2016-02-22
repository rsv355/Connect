package com.webmyne.connect.revenue;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.andexert.library.RippleView;
import com.webmyne.connect.R;
import com.webmyne.connect.Utils.Functions;

/**
 * Created by priyasindkar on 17-02-2016.
 */
public class AddBankActivity extends AppCompatActivity implements RippleView.OnRippleCompleteListener{
    private Toolbar toolbar;
    private RippleView txtSave;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bank);

        toolbar = (Toolbar) findViewById(R.id.anim_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Add Bank");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setNavigationIcon(R.drawable.ic_navigation_close);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        txtSave = (RippleView) findViewById(R.id.txtSave);
        txtSave.setOnRippleCompleteListener(this);
    }


    @Override
    public void onComplete(RippleView rippleView) {
        switch (rippleView.getId()) {
            case R.id.txtSave:
                AlertDialog.Builder dialog = Functions.getSimpleOkAlterDialog(AddBankActivity.this, "Bank Details Saved Successfully", "Ok");
                dialog.show();
                break;
        }
    }
}
