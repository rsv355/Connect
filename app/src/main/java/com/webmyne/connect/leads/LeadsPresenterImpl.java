package com.webmyne.connect.leads;

import android.app.Activity;
import android.content.Intent;
import android.widget.CheckBox;

import com.webmyne.connect.Utils.Constants;

/**
 * Created by priyasindkar on 08-03-2016.
 */
public class LeadsPresenterImpl implements LeadsPresenter {
    private LeadsView leadsView;

    public LeadsPresenterImpl(LeadsView leadsView) {
        this.leadsView = leadsView;
    }

    @Override
    public void initUIData(Activity activity, Intent data) {
        int verticalColorIndex = data.getIntExtra("vertical_color_index", 0);
        String selectedVertical = data.getStringExtra("selected_vertical");
        int selectedVerticalNo = data.getIntExtra("selected_vertical_no", 0);
        String verticalShortName = Constants.VERTICAL_SHORT_NAMES.get(selectedVerticalNo);
        int verticalId = Constants.VERTICAL_IDS[selectedVerticalNo];

        VerticalDataObject verticalDataObject = new VerticalDataObject(selectedVertical, verticalShortName, selectedVerticalNo, verticalId, verticalColorIndex);

        if(leadsView != null) {
            leadsView.setInitUI(verticalDataObject);
        }
    }

    @Override
    public void onVerticalSelect(String vertical, boolean isChecked, int[] selectedVerticals) {

    }
}
