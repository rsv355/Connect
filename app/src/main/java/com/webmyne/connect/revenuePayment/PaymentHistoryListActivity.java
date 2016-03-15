package com.webmyne.connect.revenuePayment;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.webmyne.connect.R;
import com.webmyne.connect.Utils.Functions;
import com.webmyne.connect.customUI.textDrawableIcons.ColorGenerator;
import com.webmyne.connect.revenuePayment.adapter.PaymentHistoryListAdapter;
import com.webmyne.connect.revenuePayment.model.PaymentHistoryDataObject;
import com.webmyne.connect.revenuePayment.presenter.PaymentHistoryListPresenter;
import com.webmyne.connect.revenuePayment.presenter.PaymentHistoryListPresenterImpl;
import com.webmyne.connect.revenuePayment.presenter.PaymentHistoryListView;
import com.webmyne.connect.revenuePayment.ui.PaymentHistoryFilterDialog;

import java.util.ArrayList;

/**
 * Created by priyasindkar on 16-02-2016.
 */
public class PaymentHistoryListActivity extends AppCompatActivity implements PaymentHistoryListView {
    private RecyclerView recyclerView;
    private PaymentHistoryListAdapter mPaymentHistoryListAdapter;
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbar;
    private FloatingActionButton fab;
    private PaymentHistoryListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_history_list);

    }

    @Override
    protected void onResume() {
        super.onResume();
        init();
    }

    private void init() {
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
        fab = (FloatingActionButton) findViewById(R.id.fab);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);


        presenter = new PaymentHistoryListPresenterImpl(this, PaymentHistoryListActivity.this);
        presenter.initUI();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PaymentHistoryFilterDialog filterDialog = new PaymentHistoryFilterDialog(PaymentHistoryListActivity.this, R.style.CustomAlertDialogStyle);
                filterDialog.getWindow().getAttributes().width = (int) (Functions.getDeviceMetrics(PaymentHistoryListActivity.this).widthPixels * 0.8);
                filterDialog.show();

            }
        });
    }

    @Override
    public void initUI(ArrayList<PaymentHistoryDataObject> list) {
        recyclerView.setLayoutManager(new LinearLayoutManager(PaymentHistoryListActivity.this));
        mPaymentHistoryListAdapter = new PaymentHistoryListAdapter(PaymentHistoryListActivity.this, list);
        recyclerView.setAdapter(mPaymentHistoryListAdapter);

        mPaymentHistoryListAdapter.setOnItemClickListener(new PaymentHistoryListAdapter.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {

            }
        });
    }
}
