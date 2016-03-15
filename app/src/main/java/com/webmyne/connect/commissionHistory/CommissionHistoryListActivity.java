package com.webmyne.connect.commissionHistory;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
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
import com.webmyne.connect.commissionHistory.model.CommissionHistoryRequest;
import com.webmyne.connect.commissionHistory.presenter.CommissionHistoryPresenter;
import com.webmyne.connect.commissionHistory.presenter.CommissionHistoryPresenterImpl;
import com.webmyne.connect.commissionHistory.presenter.CommissionHistoryView;
import com.webmyne.connect.commissionHistory.ui.CommissionHistoryFilterDialog;
import com.webmyne.connect.commissionHistory.ui.CommissionHistoryListFilterCommunicatorView;
import com.webmyne.connect.customUI.CustomProgressDialog;
import com.webmyne.connect.customUI.FamiliarRecylerView.FamiliarRecyclerView;
import com.webmyne.connect.customUI.FamiliarRecylerView.FamiliarRecyclerViewOnScrollListener;
import com.webmyne.connect.leadHistory.adapter.LeadsListAdapter;
import com.webmyne.connect.leadHistory.model.LeadDataObject;
import com.webmyne.connect.leadHistory.model.LeadHistoryRequest;
import com.webmyne.connect.leadHistory.ui.LeadsHistoryFilterDialog;

import java.util.ArrayList;

/**
 * Created by priyasindkar on 16-02-2016.
 */
public class CommissionHistoryListActivity extends AppCompatActivity implements CommissionHistoryView,
        SwipeRefreshLayout.OnRefreshListener, CommissionHistoryListFilterCommunicatorView {
    private CustomProgressDialog progressDialog;
    private FamiliarRecyclerView recyclerView;
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbar;
    private FloatingActionButton fab;
    private SwipeRefreshLayout refreshLayout;
    private View footerView, emptyLayout;
    //todo get userid from current user
    private int USER_ID = 1;
    private CommissionHistoryPresenter presenter;
    private boolean isFilterApplied = false, isHistoryListEmpty = false;
    private CommissionHistoryRequest commissionHistoryRequestFilterObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commission_history_list);
        init();
    }

    private void init() {
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(getString(R.string.commission_history_title));
        collapsingToolbar.setExpandedTitleTextAppearance(R.style.ExpandedAppBarTitleStyle);
        collapsingToolbar.setCollapsedTitleTextAppearance(R.style.CollapsedAppBarTitleStyle);
        toolbar = (Toolbar) findViewById(R.id.anim_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_navigation_close);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        fab = (FloatingActionButton) findViewById(R.id.fab);

        progressDialog = new CustomProgressDialog(CommissionHistoryListActivity.this);
        progressDialog.setCancelable(false);

        initRecyclerView();
        presenter = new CommissionHistoryPresenterImpl(CommissionHistoryListActivity.this, this);
        CommissionHistoryRequest commissionHistoryRequest = new CommissionHistoryRequest();
        commissionHistoryRequest.setUserID(USER_ID);
        presenter.fetchLeadData(false, commissionHistoryRequest);

        final CommissionHistoryFilterDialog filterDialog = new CommissionHistoryFilterDialog(CommissionHistoryListActivity.this, this, R.style.CustomAlertDialogStyle);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterDialog.getWindow().getAttributes().width = (int) (Functions.getDeviceMetrics(CommissionHistoryListActivity.this).widthPixels * 0.8);
                filterDialog.show();
            }
        });
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
        isHistoryListEmpty = true;
        CommissionListAdapter mCommissionAdapter = new CommissionListAdapter(CommissionHistoryListActivity.this);
        recyclerView.setAdapter(mCommissionAdapter);
        mCommissionAdapter.notifyDataSetChanged();

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
        progressDialog.dismiss();
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
        if (!isHistoryListEmpty) {
            CommissionHistoryRequest commissionHistoryRequest;
            if (!isFilterApplied) {
                commissionHistoryRequest = new CommissionHistoryRequest();
                commissionHistoryRequest.setUserID(USER_ID);
            } else {
                commissionHistoryRequest = commissionHistoryRequestFilterObject;
            }
            presenter.fetchLeadData(true, commissionHistoryRequest);
        } else {
            refreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void setData(final ArrayList<CommissionDataObject> listData, CommissionListAdapter mCommissionAdapter) {
        isHistoryListEmpty = false;
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
                showFooter();
                CommissionHistoryRequest commissionHistoryRequest;
                if (!isFilterApplied) {
                    commissionHistoryRequest = new CommissionHistoryRequest();
                    commissionHistoryRequest.setUserID(USER_ID);
                } else {
                    commissionHistoryRequest = commissionHistoryRequestFilterObject;
                }

                if (listData.size() > 0) {
                    commissionHistoryRequest.setLastLeadID(Long.parseLong(listData.get(lastPos).getLeadID()));
                    presenter.loadMoreData(commissionHistoryRequest);
                }
            }
        });
    }


    @Override
    public void onLeadFilterSet(CommissionHistoryRequest commissionHistoryRequest) {
        commissionHistoryRequestFilterObject = commissionHistoryRequest;
        isFilterApplied = true;
        presenter.fetchLeadData(false, commissionHistoryRequestFilterObject);
    }

    @Override
    public void onClearFilter() {
        CommissionHistoryRequest commissionHistoryRequest = new CommissionHistoryRequest();
        commissionHistoryRequest.setUserID(USER_ID);
        presenter.fetchLeadData(false, commissionHistoryRequest);
    }
}
