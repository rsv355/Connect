package com.webmyne.connect.leadHistory;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.webmyne.connect.R;
import com.webmyne.connect.Utils.Constants;
import com.webmyne.connect.customUI.FamiliarRecylerView.FamiliarRecyclerView;
import com.webmyne.connect.customUI.FamiliarRecylerView.FamiliarRecyclerViewOnScrollListener;
import com.webmyne.connect.customUI.textDrawableIcons.ColorGenerator;
import com.webmyne.connect.leadHistory.model.LeadDataObject;
import com.webmyne.connect.leadHistory.model.LeadHistoryData;
import com.webmyne.connect.leadHistory.model.LeadHistoryIntf;
import com.webmyne.connect.leadHistory.model.LeadHistoryRequest;
import com.webmyne.connect.leadHistory.model.LeadHistoryResponse;
import com.webmyne.connect.leads.LeadsFilterDialog;

/**
 * Created by priyasindkar on 16-02-2016.
 */
public class LeadsListActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{
    private FamiliarRecyclerView recyclerView;
    private LeadsListAdapter mLeadsAdapter;
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbar;
    private FloatingActionButton fab;
    private SwipeRefreshLayout refreshLayout;
    private View footerView;
    private int USER_ID = 1;
    ArrayList<LeadDataObject> listData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leads_list);
        toolbar = (Toolbar) findViewById(R.id.anim_toolbar);
        setSupportActionBar(toolbar);

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

        recyclerView = (FamiliarRecyclerView) findViewById(R.id.recyclerView);

        getList();
        //initLeadsList();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LeadsFilterDialog filterDialog = new LeadsFilterDialog(LeadsListActivity.this, R.style.CustomAlertDialogStyle);
                filterDialog.show();

            }
        });

        /*viewMoreRipple.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                LeadsFilterDialog filterDialog = new LeadsFilterDialog(LeadsListActivity.this);
                filterDialog.show();
            }
        });*/


    }

   /* private void initLeadsList() {
        recyclerView.setLayoutManager(new LinearLayoutManager(LeadsListActivity.this));
        ArrayList<LeadDataObject> list = new ArrayList<>();
        list.add(new LeadDataObject("QW123", "AI","2016-02-03 08:00AM GMT", "ACTIVE", "John Doe", ColorGenerator.MATERIAL.getARandomColor()));
        list.add(new LeadDataObject("UY123", "AF","2016-02-03 08:00AM GMT", "ACTIVE", "Jane Doe", ColorGenerator.MATERIAL.getARandomColor()));
        list.add(new LeadDataObject("EW786", "HO","2016-01-21 08:00AM GMT", "ONGOING", "John Doe", ColorGenerator.MATERIAL.getARandomColor()));
        list.add(new LeadDataObject("IN386", "HI","2016-01-20 08:00AM GMT", "DEACTIVE", "Jane Doe", ColorGenerator.MATERIAL.getARandomColor()));
        list.add(new LeadDataObject("BJ096", "LI", "2015-12-10 08:00AM GMT", "DEACTIVE", "John Doe", ColorGenerator.MATERIAL.getARandomColor()));
        list.add(new LeadDataObject("XS956", "AI", "2015-11-16 08:00AM GMT", "DEACTIVE", "Jane Doe", ColorGenerator.MATERIAL.getARandomColor()));
        list.add(new LeadDataObject("OP123", "LI","2015-10-13 08:00AM GMT", "DEACTIVE", "Jane Doe", ColorGenerator.MATERIAL.getARandomColor()));

        mLeadsAdapter = new LeadsListAdapter(LeadsListActivity.this, list);
        recyclerView.setAdapter(mLeadsAdapter);

      *//*  mLeadsAdapter.addItem(new LeadDataObject(), 0);
        mLeadsAdapter.addItem(new LeadDataObject(), 1);
        mLeadsAdapter.addItem(new LeadDataObject(), 2);*//*

        recyclerView.setItemAnimator(new SlideInLeftAnimator());
      *//*  recyclerView.getItemAnimator().setAddDuration(1000);
        recyclerView.getItemAnimator().setRemoveDuration(1000);
        recyclerView.getItemAnimator().setMoveDuration(1000);
        recyclerView.getItemAnimator().setChangeDuration(1000);*//*



        mLeadsAdapter.setOnItemClickListener(new LeadsListAdapter.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {

            }
        });
    }*/

    private void getList(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ws-srv-net/Applications/Androids/MapleAppServices/LeadsService.svc/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LeadHistoryIntf checkinApiService = retrofit.create(LeadHistoryIntf.class);
        LeadHistoryRequest requestObj = new LeadHistoryRequest();
        requestObj.setUserID(USER_ID);

        Call<LeadHistoryResponse> call = checkinApiService.getSearchResult(requestObj);

        call.enqueue(new Callback<LeadHistoryResponse>() {
            @Override
            public void onResponse(Call<LeadHistoryResponse> call, Response<LeadHistoryResponse> response) {
                Log.e("onResponse", "Sucess");
                if(refreshLayout.isRefreshing())
                    refreshLayout.setRefreshing(false);

                setData(response.body().getLeadData());

            }

            @Override
            public void onFailure(Call<LeadHistoryResponse> call, Throwable t) {
                Log.e("onFailure", t.toString());
            }
        });
    }

    private void setData(ArrayList<LeadHistoryData> data){
        listData = new ArrayList<>();

        for(int i=0;i<data.size();i++){
            listData.add(new LeadDataObject(Constants.getVerticalShortName(Integer.valueOf(data.get(i).LeadTypeID))+""+data.get(i).getLeadID(),
                    Constants.getVerticalShortName(Integer.valueOf(data.get(i).LeadTypeID)),
                    ""+data.get(i).getLeadDateTime(),
                    "ACTIVE",
                    ""+data.get(i).getRName(),
                    ColorGenerator.MATERIAL.getARandomColor()));
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(LeadsListActivity.this));
        // ItemAnimator
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        footerView = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.recylerfooter, recyclerView, false);

        // empty view
        //  mRecyclerView.setEmptyView(findViewById(R.id.tv_empty), true);


        mLeadsAdapter = new LeadsListAdapter(LeadsListActivity.this, listData);
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
                LoadMoreData(Long.parseLong(listData.get(lastPos).getLeadID()));
            }
        });
    }


    private void appendData(ArrayList<LeadHistoryData> data){
        for(int i=0;i<data.size();i++){
            listData.add(new LeadDataObject(Constants.getVerticalShortName(Integer.valueOf(data.get(i).LeadTypeID))+""+data.get(i).getLeadID(),
                    Constants.getVerticalShortName(Integer.valueOf(data.get(i).LeadTypeID)),
                    ""+data.get(i).getLeadDateTime(),
                    "ACTIVE",
                    ""+data.get(i).getRName(),
                    ColorGenerator.MATERIAL.getARandomColor()));
        }

        mLeadsAdapter.notifyDataSetChanged();
    }


    void showFooter(){
        recyclerView.addFooterView(footerView);
    }

    void hideFooter(){
        recyclerView.removeFooterView(footerView);
    }


    void LoadMoreData(long lastLeadID){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ws-srv-net/Applications/Androids/MapleAppServices/LeadsService.svc/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LeadHistoryIntf checkinApiService = retrofit.create(LeadHistoryIntf.class);
        LeadHistoryRequest requestObj = new LeadHistoryRequest();
        requestObj.setUserID(USER_ID);
        requestObj.setLastLeadID(lastLeadID);

        Call<LeadHistoryResponse> call = checkinApiService.getSearchResult(requestObj);

        call.enqueue(new Callback<LeadHistoryResponse>() {
            @Override
            public void onResponse(Call<LeadHistoryResponse> call, Response<LeadHistoryResponse> response) {
                hideFooter();
                Log.e("onResponse2", "Sucess");
                if (response.body().getLeadData().size() == 0) {
                    Toast.makeText(LeadsListActivity.this, "No More Data Found", Toast.LENGTH_SHORT).show();
                } else {
                    appendData(response.body().getLeadData());
                }

            }

            @Override
            public void onFailure(Call<LeadHistoryResponse> call, Throwable t) {
                Log.e("onFailure", t.toString());
            }
        });
    }

    @Override
    public void onRefresh() {

        getList();
    }

    //end of main class
}
