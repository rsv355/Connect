package com.webmyne.connect.leads;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;

import com.webmyne.connect.R;
import com.webmyne.connect.customUI.textDrawableIcons.ColorGenerator;

/**
 * Created by priyasindkar on 16-02-2016.
 */
public class LeadsListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private LeadsListAdapter mLeadsAdapter;
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbar;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leads_list);

        toolbar = (Toolbar) findViewById(R.id.anim_toolbar);
        setSupportActionBar(toolbar);
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("Leads");
        toolbar.setNavigationIcon(R.drawable.ic_navigation_close);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        fab = (FloatingActionButton) findViewById(R.id.fab);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        initLeadsList();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LeadsFilterDialog  filterDialog = new LeadsFilterDialog(LeadsListActivity.this);
                filterDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
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

    private void initLeadsList() {
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

      /*  mLeadsAdapter.addItem(new LeadDataObject(), 0);
        mLeadsAdapter.addItem(new LeadDataObject(), 1);
        mLeadsAdapter.addItem(new LeadDataObject(), 2);*/

        recyclerView.setItemAnimator(new SlideInLeftAnimator());
      /*  recyclerView.getItemAnimator().setAddDuration(1000);
        recyclerView.getItemAnimator().setRemoveDuration(1000);
        recyclerView.getItemAnimator().setMoveDuration(1000);
        recyclerView.getItemAnimator().setChangeDuration(1000);*/



        mLeadsAdapter.setOnItemClickListener(new LeadsListAdapter.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {

            }
        });
    }
}
