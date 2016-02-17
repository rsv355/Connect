package com.webmyne.connect.commission;

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
public class CommissionHistoryListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CommissionHistoryListAdapter mCommissionHistoryListAdapter;
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
        initCommissionHistoryList();


    }

    private void initCommissionHistoryList() {
        recyclerView.setLayoutManager(new LinearLayoutManager(CommissionHistoryListActivity.this));
        ArrayList<CommissionHistoryDataObject> list = new ArrayList<>();
        list.add(new CommissionHistoryDataObject("QW123", 100,10, "2016-02-03 08:00AM GMT", "John Doe", ColorGenerator.MATERIAL.getARandomColor()));
        list.add(new CommissionHistoryDataObject("UY123", 200,20,"2016-02-03 08:00AM GMT",  "Jane Doe", ColorGenerator.MATERIAL.getARandomColor()));
        list.add(new CommissionHistoryDataObject("EW786", 100,5,"2016-01-21 08:00AM GMT", "John Doe", ColorGenerator.MATERIAL.getARandomColor()));
        list.add(new CommissionHistoryDataObject("IN386", 1000, 35 ,"2016-01-20 08:00AM GMT", "Jane Doe", ColorGenerator.MATERIAL.getARandomColor()));
        list.add(new CommissionHistoryDataObject("BJ096", 50, 4,"2015-12-10 08:00AM GMT",  "John Doe", ColorGenerator.MATERIAL.getARandomColor()));
        list.add(new CommissionHistoryDataObject("XS956", 100, 15, "2015-11-16 08:00AM GMT",  "Jane Doe", ColorGenerator.MATERIAL.getARandomColor()));
        list.add(new CommissionHistoryDataObject("OP123", 5000, 50, "2015-10-13 08:00AM GMT",  "Jane Doe", ColorGenerator.MATERIAL.getARandomColor()));

        mCommissionHistoryListAdapter = new CommissionHistoryListAdapter(CommissionHistoryListActivity.this, list);
        recyclerView.setAdapter(mCommissionHistoryListAdapter);

        mCommissionHistoryListAdapter.setOnItemClickListener(new CommissionHistoryListAdapter.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {

            }
        });
    }
}
