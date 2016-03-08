package com.webmyne.connect.leads;

import android.app.Activity;
import android.content.Intent;
import android.widget.CheckBox;

/**
 * Created by priyasindkar on 08-03-2016.
 */
public interface LeadsPresenter {
    void initUIData(Activity activity, Intent data);
    void onVerticalSelect(String vertical, boolean isChecked, int[] selectedVerticals);
}
