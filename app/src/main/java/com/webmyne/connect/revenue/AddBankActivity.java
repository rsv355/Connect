package com.webmyne.connect.revenue;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.webmyne.connect.R;

/**
 * Created by priyasindkar on 17-02-2016.
 */
public class AddBankActivity extends AppCompatActivity {
    private Toolbar toolbar;

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
    }
}
