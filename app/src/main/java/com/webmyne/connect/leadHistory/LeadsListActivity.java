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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import com.webmyne.connect.R;
import com.webmyne.connect.Utils.Functions;
import com.webmyne.connect.customUI.CustomProgressDialog;
import com.webmyne.connect.customUI.FamiliarRecylerView.FamiliarRecyclerView;
import com.webmyne.connect.customUI.FamiliarRecylerView.FamiliarRecyclerViewOnScrollListener;
import com.webmyne.connect.leadHistory.presenter.LeadsHistoryView;
import com.webmyne.connect.leadHistory.adapter.LeadsListAdapter;
import com.webmyne.connect.leadHistory.model.LeadDataObject;
import com.webmyne.connect.leadHistory.presenter.LeadsPresenter;
import com.webmyne.connect.leadHistory.presenter.LeadsPresenterImpl;
import com.webmyne.connect.postLead.LeadsFilterDialog;

/**
 * Created by priyasindkar on 16-02-2016.
 */
public class LeadsListActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener,LeadsHistoryView {
    private FamiliarRecyclerView recyclerView;
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbar;
    private FloatingActionButton fab;
    private SwipeRefreshLayout refreshLayout;
    private View footerView,emptyLayout;
    private int USER_ID = 1;
    private CustomProgressDialog progressDialog;
    private LeadsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leads_list);
        toolbar = (Toolbar) findViewById(R.id.anim_toolbar);
        setSupportActionBar(toolbar);

        init();
        initRecylerView();

        presenter = new LeadsPresenterImpl(LeadsListActivity.this,this);
        presenter.fetchLeadData(false,USER_ID);

    }

    private void init(){
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("Lead History");
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
        refreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_refresh_layout);
        refreshLayout.setOnRefreshListener(this);

        progressDialog = new CustomProgressDialog(LeadsListActivity.this);
        progressDialog.setCancelable(false);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LeadsFilterDialog filterDialog = new LeadsFilterDialog(LeadsListActivity.this, R.style.CustomAlertDialogStyle);
                filterDialog.show();

            }
        });
    }

    private void initRecylerView(){
        recyclerView = (FamiliarRecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(LeadsListActivity.this));
        // ItemAnimator
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        footerView = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.recylerfooter, recyclerView, false);
        emptyLayout = (View)findViewById(R.id.emptyLayout);

    }

    private void setEmptyView(){
        TextView txtMsg = (TextView)emptyLayout.findViewById(R.id.txtMsg);
        ImageView imgEmptyIcon = (ImageView)emptyLayout.findViewById(R.id.imgEmptyIcon);
        imgEmptyIcon.setImageResource(R.drawable.leadhistory_emptyimage);
        imgEmptyIcon.setColorFilter(R.color.accent_A400, PorterDuff.Mode.SRC_ATOP);
        txtMsg.setTypeface(Functions.getTypeFace(LeadsListActivity.this), Typeface.BOLD);
        txtMsg.setText("Oops! we couldn't find lead hsitory for you.");

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
    public void setData(final ArrayList<LeadDataObject> listData,LeadsListAdapter mLeadsAdapter) {

           if(refreshLayout.isRefreshing())
                    refreshLayout.setRefreshing(false);


        recyclerView.setAdapter(mLeadsAdapter);
        mLeadsAdapter.notifyDataSetChanged();


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
        Toast.makeText(LeadsListActivity.this,""+msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addEmptyView() {
        recyclerView.setVisibility(View.GONE);
        emptyLayout.setVisibility(View.VISIBLE);
        setEmptyView();
        hideFabButton();
    }

    private void hideFabButton(){
        CoordinatorLayout.LayoutParams p = (CoordinatorLayout.LayoutParams) fab.getLayoutParams();
        p.setAnchorId(View.NO_ID);
        fab.setLayoutParams(p);
        fab.setVisibility(View.GONE);

    }

    private void showFabButton(){
        fab.setVisibility(View.VISIBLE);
    }

    //end of main class
}
