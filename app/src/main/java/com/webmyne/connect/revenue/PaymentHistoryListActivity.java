package com.webmyne.connect.revenue;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.webmyne.connect.R;
import com.webmyne.connect.customUI.textDrawableIcons.ColorGenerator;

import java.util.ArrayList;

/**
 * Created by priyasindkar on 16-02-2016.
 */
public class PaymentHistoryListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PaymentHistoryListAdapter mPaymentHistoryListAdapter;
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbar;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_history_list);

        toolbar = (Toolbar) findViewById(R.id.anim_toolbar);
        setSupportActionBar(toolbar);
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("Payment History");
        collapsingToolbar.setExpandedTitleTextAppearance(R.style.ExpandedAppBarTitleStyle);
        collapsingToolbar.setCollapsedTitleTextAppearance(R.style.CollapsedAppBarTitleStyle);
        toolbar.setNavigationIcon(R.drawable.ic_navigation_close);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        initRevenueHistoryList();
        fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PaymentHistoryFilterDialog filterDialog = new PaymentHistoryFilterDialog(PaymentHistoryListActivity.this, R.style.CustomAlertDialogStyle);
                filterDialog.show();

            }
        });
    }

    private void initRevenueHistoryList() {
        recyclerView.setLayoutManager(new LinearLayoutManager(PaymentHistoryListActivity.this));
        ArrayList<PaymentHistoryDataObject> list = new ArrayList<>();
        list.add(new PaymentHistoryDataObject("QW123", 100,"2016-02-03 08:00AM GMT", "95", "Funds Pending Clearance", ColorGenerator.MATERIAL.getARandomColor()));
        list.add(new PaymentHistoryDataObject("UY123", 200,"2016-02-03 08:00AM GMT", "175.50", "Funds Pending Clearance", ColorGenerator.MATERIAL.getARandomColor()));
        list.add(new PaymentHistoryDataObject("EW786", 100,"2016-01-21 08:00AM GMT", "95", "Withdrawal Initiated", ColorGenerator.MATERIAL.getARandomColor()));
        list.add(new PaymentHistoryDataObject("IN386", 70,"2016-01-20 08:00AM GMT", "65", "Funds Pending Clearance", ColorGenerator.MATERIAL.getARandomColor()));
        list.add(new PaymentHistoryDataObject("BJ096", 50, "2015-12-10 08:00AM GMT", "45", "Withdrawal Completed", ColorGenerator.MATERIAL.getARandomColor()));
        list.add(new PaymentHistoryDataObject("XS956", 100, "2015-11-16 08:00AM GMT", "95", "Withdrawal Completed", ColorGenerator.MATERIAL.getARandomColor()));
        list.add(new PaymentHistoryDataObject("OP123", 20,"2015-10-13 08:00AM GMT", "17.50", "Withdrawal Completed", ColorGenerator.MATERIAL.getARandomColor()));

        mPaymentHistoryListAdapter = new PaymentHistoryListAdapter(PaymentHistoryListActivity.this, list);
        recyclerView.setAdapter(mPaymentHistoryListAdapter);

        mPaymentHistoryListAdapter.setOnItemClickListener(new PaymentHistoryListAdapter.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {

            }
        });
    }
}
