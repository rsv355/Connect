package com.webmyne.connect.leadHistory;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import com.webmyne.connect.R;
import com.webmyne.connect.Utils.Functions;
import com.webmyne.connect.customUI.CustomProgressDialog;
import com.webmyne.connect.customUI.FamiliarRecylerView.FamiliarRecyclerView;
import com.webmyne.connect.customUI.FamiliarRecylerView.FamiliarRecyclerViewOnScrollListener;
import com.webmyne.connect.leadHistory.presenter.LeadsHistoryView;
import com.webmyne.connect.leadHistory.adapter.LeadsListAdapter;
import com.webmyne.connect.leadHistory.model.LeadDataObject;
import com.webmyne.connect.leadHistory.presenter.LeadsHistoryPresenter;
import com.webmyne.connect.leadHistory.presenter.LeadsHistoryPresenterImpl;
import com.webmyne.connect.leadHistory.ui.LeadsHistoryFilterDialog;

/**
 * Created by priyasindkar on 16-02-2016.
 */
public class LeadsHistoryListActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, LeadsHistoryView {
    private FamiliarRecyclerView recyclerView;
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbar;
    private FloatingActionButton fab;
    private SwipeRefreshLayout refreshLayout;
    private View footerView, emptyLayout;
    private int USER_ID = 1;
    private CustomProgressDialog progressDialog;
    private LeadsHistoryPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leads_list);
        toolbar = (Toolbar) findViewById(R.id.anim_toolbar);
        setSupportActionBar(toolbar);

        init();
        initRecylerView();

        presenter = new LeadsHistoryPresenterImpl(LeadsHistoryListActivity.this, this);
        presenter.fetchLeadData(false, USER_ID);

    }

    private void init() {
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(getString(R.string.lead_history_title));
        collapsingToolbar.setExpandedTitleTextAppearance(R.style.ExpandedAppBarTitleStyle);
        collapsingToolbar.setCollapsedTitleTextAppearance(R.style.CollapsedAppBarTitleStyle);
        toolbar.setNavigationIcon(R.drawable.ic_navigation_close);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        fab = (FloatingActionButton) findViewById(R.id.fab);
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        refreshLayout.setOnRefreshListener(this);

        progressDialog = new CustomProgressDialog(LeadsHistoryListActivity.this);
        progressDialog.setCancelable(false);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LeadsHistoryFilterDialog filterDialog = new LeadsHistoryFilterDialog(LeadsHistoryListActivity.this, R.style.CustomAlertDialogStyle);
                filterDialog.getWindow().getAttributes().width = (int) (Functions.getDeviceMetrics(LeadsHistoryListActivity.this).widthPixels * 0.8);
                filterDialog.show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    private void initRecylerView() {
        recyclerView = (FamiliarRecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(LeadsHistoryListActivity.this));
        // ItemAnimator
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
        txtMsg.setTypeface(Functions.getTypeFace(LeadsHistoryListActivity.this), Typeface.BOLD);
        txtMsg.setText(msg);
    }

    @Override
    public void onRefresh() {
        presenter.fetchLeadData(true, USER_ID);
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
    public void setData(final ArrayList<LeadDataObject> listData, LeadsListAdapter mLeadsAdapter) {
        emptyLayout.setVisibility(View.GONE);
        if (refreshLayout.isRefreshing())
            refreshLayout.setRefreshing(false);

        recyclerView.setAdapter(mLeadsAdapter);
        mLeadsAdapter.notifyDataSetChanged();


        recyclerView.addOnScrollListener(new FamiliarRecyclerViewOnScrollListener(recyclerView.getLayoutManager()) {
            @Override
            public void onScrolledToTop() {
            }

            @Override
            public void onScrolledToBottom() {
                int lastPos = recyclerView.getLastVisiblePosition();
                showFooter();
                presenter.loadMoreData(USER_ID, Long.parseLong(listData.get(lastPos).getLeadID()));
            }
        });
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
        Functions.showSnackMessage(LeadsHistoryListActivity.this, findViewById(android.R.id.content), getString(R.string.no_more_data));
    }

    @Override
    public void addEmptyView(String msg) {
        setEmptyView(msg);
        emptyLayout.setVisibility(View.VISIBLE);
        recyclerView.setEmptyView(emptyLayout);
        hideFabButton();
    }

    @Override
    public void showNoInternetAlert() {
        presenter.showNoInternetDialog(LeadsHistoryListActivity.this);
    }

    private void hideFabButton() {
        CoordinatorLayout.LayoutParams p = (CoordinatorLayout.LayoutParams) fab.getLayoutParams();
        p.setAnchorId(View.NO_ID);
        fab.setLayoutParams(p);
        fab.setVisibility(View.GONE);
    }

    private void showFabButton() {
        fab.setVisibility(View.VISIBLE);
    }

    //end of main class
}
