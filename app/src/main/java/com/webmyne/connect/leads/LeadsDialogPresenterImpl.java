package com.webmyne.connect.leads;

/**
 * Created by priyasindkar on 08-03-2016.
 */
public class LeadsDialogPresenterImpl implements  LeadsDialogPresenter {
    private LeadsDialogView leadsDialogView;

    public LeadsDialogPresenterImpl(LeadsDialogView leadsDialogView) {
        this.leadsDialogView = leadsDialogView;
    }

    @Override
    public void onAcceptDeclinePostLeadTerms(boolean isAccept) {
        if(isAccept) {
            if(leadsDialogView != null) {
                leadsDialogView.onAcceptTerms();
            }
        } else {
            if(leadsDialogView != null) {
                leadsDialogView.onDeclineTerms();
            }
        }

    }
}
