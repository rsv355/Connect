package com.webmyne.connect.leadHistory.presenter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;

import com.webmyne.connect.R;
import com.webmyne.connect.Utils.APIConstants;
import com.webmyne.connect.Utils.Constants;
import com.webmyne.connect.Utils.Functions;
import com.webmyne.connect.base.MyApplication;
import com.webmyne.connect.customUI.textDrawableIcons.ColorGenerator;
import com.webmyne.connect.leadHistory.adapter.LeadsListAdapter;
import com.webmyne.connect.leadHistory.api.LeadHistoryService;
import com.webmyne.connect.leadHistory.model.LeadDataObject;
import com.webmyne.connect.leadHistory.model.LeadHistoryData;
import com.webmyne.connect.leadHistory.model.LeadHistoryRequest;
import com.webmyne.connect.leadHistory.model.LeadHistoryResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by krishnakumar on 10-03-2016.
 */
public class LeadsHistoryPresenterImpl implements LeadsHistoryPresenter {
    private LeadsHistoryView leadsHistoryView;
    private LeadsListAdapter mLeadsAdapter;
    private boolean isLayoutRefreshed = false;
    private ArrayList<LeadDataObject> listData;
    private Context _ctx;

    public LeadsHistoryPresenterImpl(Context context, LeadsHistoryView leadsHistoryView) {
        this._ctx = context;
        this.leadsHistoryView = leadsHistoryView;
    }

    @Override
    public void fetchLeadData(boolean isRefreshed, long userID) {
        isLayoutRefreshed = isRefreshed;
        getList(false, userID, 0);
    }

    @Override
    public void loadMoreData(long userID, long lastLeadID) {
        getList(true, userID, lastLeadID);
    }

    @Override
    public void showNoInternetDialog(Activity activity) {
        Functions.getSimpleOkAlterDialog(activity, activity.getString(R.string.no_internet_connection), "Ok").show();
        if(leadsHistoryView != null) {
            leadsHistoryView.addEmptyView(_ctx.getString(R.string.no_commission_history_found));
        }
    }

    @Override
    public void onDestroy() {
        leadsHistoryView = null;
    }

    private void getList(final boolean isLoadMoreData, long userID, long lastLeadID) {
        if ( Functions.checkInternet(_ctx)) {
            if (isLoadMoreData) {
                leadsHistoryView.showFooter();
            } else {
                if (!isLayoutRefreshed)
                    leadsHistoryView.showProgressDialog();
            }

            Retrofit retrofit = MyApplication.retrofit;
            LeadHistoryService checkinApiService = retrofit.create(LeadHistoryService.class);
            LeadHistoryRequest requestObj = new LeadHistoryRequest();
            requestObj.setUserID(userID);

            if (isLoadMoreData)
                requestObj.setLastLeadID(lastLeadID);

            Call<LeadHistoryResponse> call = checkinApiService.getSearchResult(requestObj);

            call.enqueue(new Callback<LeadHistoryResponse>() {
                @Override
                public void onResponse(Call<LeadHistoryResponse> call, Response<LeadHistoryResponse> response) {
                    Log.e("onResponse", "Sucess");
                    if (isLoadMoreData) {
                        if (response.body().getLeadData().size() == 0) {
                            leadsHistoryView.showToast("No More Data Found");
                            leadsHistoryView.hideFooter();
                        } else {
                            processLoadNewData(response.body().getLeadData());
                        }
                    } else {
                        if (response.body().getLeadData().size() == 0) {
                            if (!isLayoutRefreshed)
                                leadsHistoryView.hideProgressDialog();
                            leadsHistoryView.addEmptyView(_ctx.getString(R.string.no_lead_history_found));
                        } else {
                            processInitData(response.body().getLeadData());
                        }
                    }
                }

                @Override
                public void onFailure(Call<LeadHistoryResponse> call, Throwable t) {
                    Log.e("onFailure", t.toString());
                    if (isLoadMoreData) {
                        leadsHistoryView.hideFooter();
                    } else {
                        if (!isLayoutRefreshed) {
                            leadsHistoryView.hideProgressDialog();
                            leadsHistoryView.addEmptyView(_ctx.getString(R.string.no_lead_history_found));
                        }
                    }
                }
            });
        } else {
            if (leadsHistoryView != null) {
                leadsHistoryView.showNoInternetAlert();
            }
        }
    }

    private void processLoadNewData(ArrayList<LeadHistoryData> data) {
        for (int i = 0; i < data.size(); i++) {
            listData.add(new LeadDataObject(Constants.getVerticalShortName(Integer.valueOf(data.get(i).LeadTypeID)) + "" + data.get(i).getLeadID(),
                    Constants.getVerticalShortName(Integer.valueOf(data.get(i).LeadTypeID)),
                    "" + data.get(i).getLeadDateTime(),
                    data.get(i).getStatus(),
                    "" + data.get(i).getRName(),
                    ColorGenerator.MATERIAL.getARandomColor()));
        }
        leadsHistoryView.hideFooter();
        mLeadsAdapter.notifyDataSetChanged();

    }

    private void processInitData(ArrayList<LeadHistoryData> data) {
        listData = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            listData.add(new LeadDataObject(Constants.getVerticalShortName(Integer.valueOf(data.get(i).LeadTypeID)) + "" + data.get(i).getLeadID(),
                    Constants.getVerticalShortName(Integer.valueOf(data.get(i).LeadTypeID)),
                    "" + data.get(i).getLeadDateTime(),
                    data.get(i).getStatus(),
                    "" + data.get(i).getRName(),
                    ColorGenerator.MATERIAL.getARandomColor()));
        }
        leadsHistoryView.hideProgressDialog();
        mLeadsAdapter = new LeadsListAdapter(_ctx, listData);
        leadsHistoryView.setData(listData, mLeadsAdapter);
    }

    //end of main class
}
