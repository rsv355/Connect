package com.webmyne.connect.postLead;

import java.util.List;

/**
 * Created by priyasindkar on 08-03-2016.
 */
public interface LeadsView {
    void setInitUI(VerticalDataObject verticalDataObject, List<VerticalDataObject> secondaryVerticalsList);
    void onSelectVertical(List<Integer> selectedVerticals);
    void setNameError(String error);
    void setEmailError(String error);
    void setContactNoError(String error);
    void onValidationSuccess();
    void onTermsAccepted();
    void onTermsDeclined();
    void showProgressDialog();
    void hideProgressDialog();
    void onLeadPostSuccess(String success);
    void onLeadPostFail(String error);
}
