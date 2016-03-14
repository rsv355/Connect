package com.webmyne.connect.leadHistory.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.webmyne.connect.R;
import com.webmyne.connect.customUI.FlowLayout;
import com.webmyne.connect.leadHistory.model.LeadDataObject;
import com.webmyne.connect.leadHistory.model.LeadStatusObject;
import com.webmyne.connect.postLead.model.VerticalDataObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by priyasindkar on 16-02-2016.
 */
public class LeadsHistoryFilterDialog extends AppCompatDialog implements View.OnClickListener, LeadsHistoryFilterView{
    private AppCompatButton btnCancel, btnFilter;
    private View verticalList, leadStatusList;
    private Context mContext;
    private LeadsHistoryFilterPresenter presenter;
    private HashMap<Integer, Integer> selectedVerticals = new HashMap<>();
    private HashMap<Integer, Integer> selectedLeadStatuses = new HashMap<>();
    private CheckBox checkboxAI, checkboxAF, checkboxHI,checkboxLI, checkboxHO, checkboxNC, checkboxInProgress, checkboxVerificationDone
            , checkboxActive, checkboxSold,checkboxUserNotInterested, checkboxExpired;


    public LeadsHistoryFilterDialog(Context context) {
        super(context);
        mContext = context;
    }

    public LeadsHistoryFilterDialog(Context context, int themeResId) {
        super(context, themeResId);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_filter_leads);
        init();
    }

    private void init() {
        presenter = new LeadsHistoryFilterPresenterImpl(mContext, this);

        btnCancel = (AppCompatButton) findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(this);
        btnFilter = (AppCompatButton) findViewById(R.id.btnFilter);
        btnFilter.setOnClickListener(this);

      /*  linearVerticalsList = (FlowLayout) findViewById(R.id.linearVerticalsList);
        linearLeadStatusList = (FlowLayout) findViewById(R.id.linearLeadStatusList);*/
        verticalList = findViewById(R.id.verticalList);
        leadStatusList = findViewById(R.id.leadStatusList);

        checkboxAI = (CheckBox) verticalList.findViewById(R.id.checkboxAI);
        checkboxAF = (CheckBox) verticalList.findViewById(R.id.checkboxAF);
        checkboxHI = (CheckBox) verticalList.findViewById(R.id.checkboxHI);
        checkboxLI = (CheckBox) verticalList.findViewById(R.id.checkboxLI);
        checkboxHO = (CheckBox) verticalList.findViewById(R.id.checkboxHO);
        checkboxNC = (CheckBox) verticalList.findViewById(R.id.checkboxNC);

        checkboxInProgress = (CheckBox) leadStatusList.findViewById(R.id.checkboxInProgress);
        checkboxVerificationDone = (CheckBox) leadStatusList.findViewById(R.id.checkboxVerificationDone);
        checkboxActive = (CheckBox) leadStatusList.findViewById(R.id.checkboxActive);
        checkboxSold = (CheckBox) leadStatusList.findViewById(R.id.checkboxSold);
        checkboxExpired = (CheckBox) leadStatusList.findViewById(R.id.checkboxExpired);
        checkboxUserNotInterested = (CheckBox) leadStatusList.findViewById(R.id.checkboxUserNotInterested);
        presenter.setUI();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnFilter:
                presenter = null;
                dismiss();
                break;
            case R.id.btnCancel:
                presenter = null;
                dismiss();
                break;
        }
    }

    @Override
    public void setUI(HashMap<Integer, VerticalDataObject> verticals, HashMap<Integer, LeadStatusObject> leadStatuses) {
        //linearVerticalsList.removeAllViews();

        Iterator it = verticals.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            /*View view = getLayoutInflater().inflate(R.layout.vertical_check_box_item, null);
            CheckBox checkBox = (CheckBox) view;
            checkBox.setText( ((VerticalDataObject)pair.getValue()).getVerticalName());
            checkBox.setOnCheckedChangeListener(new OnVerticalChecked(((VerticalDataObject)pair.getValue())));
           // linearVerticalsList.addView(checkBox); */

            int verticalIndex = (int) pair.getKey();
            switch (verticalIndex) {
                case 0:
                    checkboxAI.setOnCheckedChangeListener(new OnVerticalChecked(((VerticalDataObject)pair.getValue())));
                    break;
                case 1:
                    checkboxAF.setOnCheckedChangeListener(new OnVerticalChecked(((VerticalDataObject)pair.getValue())));
                    break;
                case 2:
                    checkboxHI.setOnCheckedChangeListener(new OnVerticalChecked(((VerticalDataObject)pair.getValue())));
                    break;
                case 3:
                    checkboxLI.setOnCheckedChangeListener(new OnVerticalChecked(((VerticalDataObject)pair.getValue())));
                    break;
                case 4:
                    checkboxHO.setOnCheckedChangeListener(new OnVerticalChecked(((VerticalDataObject)pair.getValue())));
                    break;
                case 5:
                    checkboxNC.setOnCheckedChangeListener(new OnVerticalChecked(((VerticalDataObject)pair.getValue())));
                    break;
            }
        }

       // linearLeadStatusList.removeAllViews();
        Iterator leadStatusIterator = leadStatuses.entrySet().iterator();
        while (leadStatusIterator.hasNext()) {
            Map.Entry pair = (Map.Entry)leadStatusIterator.next();
          /*  View view = getLayoutInflater().inflate(R.layout.vertical_check_box_item, null);
            CheckBox checkBox = (CheckBox) view;
            checkBox.setText( ((LeadStatusObject)pair.getValue()).getLeadStatusName());
            checkBox.setOnCheckedChangeListener(new OnLeadStatusChecked(((LeadStatusObject)pair.getValue())));
            linearLeadStatusList.addView(checkBox);*/

            int leadStatusId = (int) pair.getKey();
            switch (leadStatusId) {
                case 0:
                    checkboxInProgress.setOnCheckedChangeListener(new OnLeadStatusChecked(((LeadStatusObject)pair.getValue())));
                    break;
                case 1:
                    checkboxVerificationDone.setOnCheckedChangeListener(new OnLeadStatusChecked(((LeadStatusObject)pair.getValue())));
                    break;
                case 2:
                    checkboxActive.setOnCheckedChangeListener(new OnLeadStatusChecked(((LeadStatusObject)pair.getValue())));
                    break;
                case 3:
                    checkboxSold.setOnCheckedChangeListener(new OnLeadStatusChecked(((LeadStatusObject)pair.getValue())));
                    break;
                case 4:
                    checkboxExpired.setOnCheckedChangeListener(new OnLeadStatusChecked(((LeadStatusObject)pair.getValue())));
                    break;
                case 5:
                    checkboxUserNotInterested.setOnCheckedChangeListener(new OnLeadStatusChecked(((LeadStatusObject)pair.getValue())));
                    break;
            }
        }
    }

    @Override
    public void onVerticalSelected(HashMap<Integer, Integer> selectedVerticals) {
        this.selectedVerticals = selectedVerticals;
        Log.e("selectedVerticals", selectedVerticals.toString());
    }

    @Override
    public void onLeadStatusSelected(HashMap<Integer, Integer> selectedLeadStatuses) {
        this.selectedLeadStatuses = selectedLeadStatuses;
    }

    private class OnVerticalChecked implements CompoundButton.OnCheckedChangeListener {
        VerticalDataObject verticalDataObject;

        public OnVerticalChecked(VerticalDataObject verticalDataObject) {
            this.verticalDataObject = verticalDataObject;
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            presenter.onVerticalSelected(verticalDataObject.getVerticalId(), isChecked, selectedVerticals);
        }
    }

    private class OnLeadStatusChecked implements CompoundButton.OnCheckedChangeListener {
        LeadStatusObject leadStatusObject;

        public OnLeadStatusChecked(LeadStatusObject leadStatusObject) {
            this.leadStatusObject = leadStatusObject;
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            presenter.onVerticalSelected(leadStatusObject.getLeadStatusId(), isChecked, selectedVerticals);
        }
    }
}
