package com.webmyne.connect.commissionHistory;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.webmyne.connect.R;
import com.webmyne.connect.Utils.Functions;
import com.webmyne.connect.commissionHistory.adapter.CommissionListAdapter;
import com.webmyne.connect.commissionHistory.model.CommissionDataObject;
import com.webmyne.connect.commissionHistory.presenter.CommissionHistoryPresenter;
import com.webmyne.connect.commissionHistory.presenter.CommissionHistoryPresenterImpl;
import com.webmyne.connect.commissionHistory.presenter.CommissionHistoryView;
import com.webmyne.connect.customUI.CustomProgressDialog;
import com.webmyne.connect.customUI.FamiliarRecylerView.FamiliarRecyclerView;
import com.webmyne.connect.customUI.FamiliarRecylerView.FamiliarRecyclerViewOnScrollListener;
import com.webmyne.connect.leadHistory.adapter.LeadsListAdapter;
import com.webmyne.connect.leadHistory.model.LeadDataObject;

import java.util.ArrayList;

/**
 * Created by priyasindkar on 16-02-2016.
 */
public class CommissionHistoryListActivity extends AppCompatActivity implements CommissionHistoryView, SwipeRefreshLayout.OnRefreshListener {
    private Toolbar toolbar;
    private CustomProgressDialog progressDialog;
    private FamiliarRecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;
    private View footerView, emptyLayout;
    private int USER_ID = 1;
    private CommissionHistoryPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commission_history_list);
        init();
    }

    private void init() {
        toolbar = (Toolbar) findViewById(R.id.anim_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.commission_history_title));
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setNavigationIcon(R.drawable.ic_navigation_close);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        progressDialog = new CustomProgressDialog(CommissionHistoryListActivity.this);
        progressDialog.setCancelable(false);

        initRecyclerView();
        presenter = new CommissionHistoryPresenterImpl(CommissionHistoryListActivity.this, this);
        presenter.fetchLeadData(false, USER_ID);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    private void initRecyclerView() {
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        refreshLayout.setOnRefreshListener(this);

        recyclerView = (FamiliarRecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(CommissionHistoryListActivity.this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        footerView = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.recylerfooter, recyclerView, false);
        emptyLayout = (View) findViewById(R.id.emptyLayout);
        emptyLayout.setVisibility(View.GONE);
    }

    private void setEmptyView(String msg) {
        TextView txtMsg = (TextView) emptyLayout.findViewById(R.id.txtMsg);
        ImageView imgEmptyIcon = (ImageView) emptyLayout.findViewById(R.id.imgEmptyIcon);
        imgEmptyIcon.setImageResource(R.drawable.leadhistory_emptyimage);
        imgEmptyIcon.setColorFilter(R.color.accent_A400, PorterDuff.Mode.SRC_ATOP);
        txtMsg.setTypeface(Functions.getTypeFace(CommissionHistoryListActivity.this), Typeface.BOLD);
        txtMsg.setText(msg);
    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        progressDialog.hide();
    }

    @Override
    public void showFooter() {
        recyclerView.addFooterView(footerView);
    }

    @Override
    public void hideFooter() {
        recyclerView.removeFooterView(footerView);
    }

    @Override
    public void showToast(String msg) {
        Functions.showSnackMessage(CommissionHistoryListActivity.this, findViewById(android.R.id.content), getString(R.string.no_more_data));
    }

    @Override
    public void addEmptyView(String msg) {
        setEmptyView(msg);
        emptyLayout.setVisibility(View.VISIBLE);
        recyclerView.setEmptyView(emptyLayout);
    }

    @Override
    public void showNoInternetDialog() {
        presenter.showNoInternetDialog(CommissionHistoryListActivity.this);
    }

    @Override
    public void onRefresh() {
        presenter.fetchLeadData(true, USER_ID);
    }

    @Override
    public void setData(final ArrayList<CommissionDataObject> listData, CommissionListAdapter mCommissionAdapter) {
        if (refreshLayout.isRefreshing())
            refreshLayout.setRefreshing(false);

        recyclerView.setAdapter(mCommissionAdapter);
        mCommissionAdapter.notifyDataSetChanged();


        recyclerView.addOnScrollListener(new FamiliarRecyclerViewOnScrollListener(recyclerView.getLayoutManager()) {
            @Override
            public void onScrolledToTop() {
                Log.e("wg", "onScrolledToTop ...");
            }

            @Override
            public void onScrolledToBottom() {
                int lastPos = recyclerView.getLastVisiblePosition();
                ///new countDown(4000, 1000,lastPos).start();
                showFooter();
                Log.e("#### Last Item", "" + lastPos);
                presenter.loadMoreData(USER_ID, Long.parseLong(listData.get(lastPos).getLeadID()));
            }
        });
    }


}
