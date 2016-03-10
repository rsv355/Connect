package com.webmyne.connect.postLead.presenter;

import android.app.Activity;

import java.util.List;

/**
 * Created by priyasindkar on 08-03-2016.
 */
public interface LeadsInteractor {
    void doPostLead(Activity activity, String name, String emailId,
                    String contactNo, String description, List<Integer> selectedVerticals );
}
