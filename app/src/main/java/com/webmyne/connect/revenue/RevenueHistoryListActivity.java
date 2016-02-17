package com.webmyne.connect.revenue;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.andexert.library.RippleView;
import com.webmyne.connect.R;
import com.webmyne.connect.customUI.textDrawableIcons.ColorGenerator;

import java.util.ArrayList;

import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;

/**
 * Created by priyasindkar on 16-02-2016.
 */
public class RevenueHistoryListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RevenueHistoryListAdapter mRevenueHistoryListAdapter;
    private Toolbar toolbar;
    private RippleView viewMoreRipple;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revenue_history_list);

        toolbar = (Toolbar) findViewById(R.id.anim_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setNavigationIcon(R.drawable.ic_navigation_close);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        initRevenueHistoryList();
    }

    private void initRevenueHistoryList() {
        recyclerView.setLayoutManager(new LinearLayoutManager(RevenueHistoryListActivity.this));
        ArrayList<RevenueHistoryDataObject> list = new ArrayList<>();
        list.add(new RevenueHistoryDataObject("QW123", 100,"2016-02-03 08:00AM GMT", "ACTIVE", "John Doe", ColorGenerator.MATERIAL.getARandomColor()));
        list.add(new RevenueHistoryDataObject("UY123", 200,"2016-02-03 08:00AM GMT", "ACTIVE", "Jane Doe", ColorGenerator.MATERIAL.getARandomColor()));
        list.add(new RevenueHistoryDataObject("EW786", 100,"2016-01-21 08:00AM GMT", "ONGOING", "John Doe", ColorGenerator.MATERIAL.getARandomColor()));
        list.add(new RevenueHistoryDataObject("IN386", 1000,"2016-01-20 08:00AM GMT", "DEACTIVE", "Jane Doe", ColorGenerator.MATERIAL.getARandomColor()));
        list.add(new RevenueHistoryDataObject("BJ096", 50, "2015-12-10 08:00AM GMT", "DEACTIVE", "John Doe", ColorGenerator.MATERIAL.getARandomColor()));
        list.add(new RevenueHistoryDataObject("XS956", 100, "2015-11-16 08:00AM GMT", "DEACTIVE", "Jane Doe", ColorGenerator.MATERIAL.getARandomColor()));
        list.add(new RevenueHistoryDataObject("OP123", 5000,"2015-10-13 08:00AM GMT", "DEACTIVE", "Jane Doe", ColorGenerator.MATERIAL.getARandomColor()));

        mRevenueHistoryListAdapter = new RevenueHistoryListAdapter(RevenueHistoryListActivity.this, list);
        recyclerView.setAdapter(mRevenueHistoryListAdapter);

      /*  mLeadsAdapter.addItem(new LeadDataObject(), 0);
        mLeadsAdapter.addItem(new LeadDataObject(), 1);
        mLeadsAdapter.addItem(new LeadDataObject(), 2);*/

        recyclerView.setItemAnimator(new SlideInLeftAnimator());
      /*  recyclerView.getItemAnimator().setAddDuration(1000);
        recyclerView.getItemAnimator().setRemoveDuration(1000);
        recyclerView.getItemAnimator().setMoveDuration(1000);
        recyclerView.getItemAnimator().setChangeDuration(1000);*/



        mRevenueHistoryListAdapter.setOnItemClickListener(new RevenueHistoryListAdapter.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {

            }
        });
    }
}
