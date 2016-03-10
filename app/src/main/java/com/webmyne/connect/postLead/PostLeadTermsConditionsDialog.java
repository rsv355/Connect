package com.webmyne.connect.postLead;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.webmyne.connect.R;

/**
 * Created by priyasindkar on 16-02-2016.
 */
public class PostLeadTermsConditionsDialog extends AppCompatDialog implements View.OnClickListener, LeadsDialogView{
    private AppCompatButton btnDecline, btnAccept;
    private Context mContext;
    private LeadsDialogPresenter leadsDialogPresenter;
    private LeadsView leadsView;

    public PostLeadTermsConditionsDialog(Context context) {
        super(context);
        mContext = context;
    }

    public PostLeadTermsConditionsDialog(Context context, int themeResId, LeadsView leadsView) {
        super(context, themeResId);
        mContext = context;
        this.leadsView = leadsView;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_post_lead_terms_conditions);

        leadsDialogPresenter = new LeadsDialogPresenterImpl(this);
        btnDecline = (AppCompatButton) findViewById(R.id.btnDecline);
        btnDecline.setOnClickListener(this);
        btnAccept = (AppCompatButton) findViewById(R.id.btnAccept);
        btnAccept.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAccept:
                leadsDialogPresenter.onAcceptDeclinePostLeadTerms(true);
                dismiss();
                break;
            case R.id.btnDecline:
                leadsDialogPresenter.onAcceptDeclinePostLeadTerms(false);
                dismiss();
                break;
        }
    }

    @Override
    public void onAcceptTerms() {
        leadsView.onTermsAccepted();
    }

    @Override
    public void onDeclineTerms() {
        leadsView.onTermsDeclined();
    }
}
