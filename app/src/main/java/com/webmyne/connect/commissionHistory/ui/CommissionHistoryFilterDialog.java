package com.webmyne.connect.commissionHistory.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.webmyne.connect.R;
import com.webmyne.connect.leadHistory.model.LeadStatusObject;
import com.webmyne.connect.leadHistory.ui.LeadsHistoryFilterPresenter;
import com.webmyne.connect.leadHistory.ui.LeadsHistoryFilterPresenterImpl;
import com.webmyne.connect.leadHistory.ui.LeadsHistoryFilterView;
import com.webmyne.connect.postLead.model.VerticalDataObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by priyasindkar on 16-02-2016.
 */
public class CommissionHistoryFilterDialog extends AppCompatDialog implements View.OnClickListener, CommissionHistoryFilterView{
    private AppCompatButton btnCancel, btnFilter;
    private View verticalList;
    private Context mContext;
    private CommissionHistoryFilterPresenterImpl presenter;
    private HashMap<Integer, Integer> selectedVerticals = new HashMap<>();
    private CheckBox checkboxAI, checkboxAF, checkboxHI,checkboxLI, checkboxHO, checkboxNC;


    public CommissionHistoryFilterDialog(Context context) {
        super(context);
        mContext = context;
    }

    public CommissionHistoryFilterDialog(Context context, int themeResId) {
        super(context, themeResId);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_filter_commission);
        init();
    }

    private void init() {
        presenter = new CommissionHistoryFilterPresenterImpl(mContext, this);

        btnCancel = (AppCompatButton) findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(this);
        btnFilter = (AppCompatButton) findViewById(R.id.btnFilter);
        btnFilter.setOnClickListener(this);

        verticalList = findViewById(R.id.verticalList);

        checkboxAI = (CheckBox) verticalList.findViewById(R.id.checkboxAI);
        checkboxAF = (CheckBox) verticalList.findViewById(R.id.checkboxAF);
        checkboxHI = (CheckBox) verticalList.findViewById(R.id.checkboxHI);
        checkboxLI = (CheckBox) verticalList.findViewById(R.id.checkboxLI);
        checkboxHO = (CheckBox) verticalList.findViewById(R.id.checkboxHO);
        checkboxNC = (CheckBox) verticalList.findViewById(R.id.checkboxNC);
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
    public void setUI(HashMap<Integer, VerticalDataObject> verticals) {
        Iterator it = verticals.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();

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
    }

    @Override
    public void onVerticalSelected(HashMap<Integer, Integer> selectedVerticals) {
        this.selectedVerticals = selectedVerticals;
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
}
