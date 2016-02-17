package com.webmyne.connect.revenue;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.webmyne.connect.R;

/**
 * Created by priyasindkar on 17-02-2016.
 */
public class RedeemMoneyActivity extends AppCompatActivity implements View.OnClickListener{
    private CollapsingToolbarLayout collapsingToolbar;
    private Toolbar toolbar;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redeem_money);
        toolbar = (Toolbar) findViewById(R.id.anim_toolbar);
        setSupportActionBar(toolbar);
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("$100.00");
        toolbar.setNavigationIcon(R.drawable.ic_navigation_close);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                Intent intent = new Intent(RedeemMoneyActivity.this, AddBankActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.push_down_in, R.anim.push_up_out);
            break;
        }
    }
}
