package com.webmyne.connect.commissionHistory.presenter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.webmyne.connect.R;
import com.webmyne.connect.Utils.Constants;
import com.webmyne.connect.Utils.Functions;
import com.webmyne.connect.base.MyApplication;
import com.webmyne.connect.commissionHistory.adapter.CommissionListAdapter;
import com.webmyne.connect.commissionHistory.api.CommissionHistoryService;
import com.webmyne.connect.commissionHistory.model.CommissionDataObject;
import com.webmyne.connect.commissionHistory.model.CommissionHistoryData;
import com.webmyne.connect.commissionHistory.model.CommissionHistoryRequest;
import com.webmyne.connect.commissionHistory.model.CommissionHistoryResponse;
import com.webmyne.connect.customUI.textDrawableIcons.ColorGenerator;
import com.webmyne.connect.leadHistory.presenter.LeadsHistoryPresenter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by krishnakumar on 10-03-2016.
 */
public class CommissionHistoryPresenterImpl implements CommissionHistoryPresenter {
    private CommissionHistoryView commissionHistoryView;
    private CommissionListAdapter mCommissionListAdapter;
    private boolean isLayoutRefreshed = false;
    private ArrayList<CommissionDataObject> listData;
    private Context _ctx;

    public CommissionHistoryPresenterImpl(Context context, CommissionHistoryView commissionHistoryView) {
        this._ctx = context;
        this.commissionHistoryView = commissionHistoryView;
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
        if(commissionHistoryView != null) {
            commissionHistoryView.addEmptyView(_ctx.getString(R.string.no_commission_history_found));
        }
    }

    @Override
    public void onDestroy() {
        commissionHistoryView = null;
    }

    private void getList(final boolean isLoadMoreData, long userID, long lastLeadID) {
        if( Functions.checkInternet(_ctx)) {
            if (isLoadMoreData) {
                commissionHistoryView.showFooter();
            } else {
                if (!isLayoutRefreshed)
                    commissionHistoryView.showProgressDialog();
            }

            Retrofit retrofit = MyApplication.retrofit;
            CommissionHistoryService commissionHistoryService = retrofit.create(CommissionHistoryService.class);
            final CommissionHistoryRequest requestObj = new CommissionHistoryRequest();
            requestObj.setUserID(userID);

            if (isLoadMoreData)
                requestObj.setLastLeadID(lastLeadID);

            Call<CommissionHistoryResponse> call = commissionHistoryService.getSearchResult(requestObj);

            call.enqueue(new Callback<CommissionHistoryResponse>() {
                @Override
                public void onResponse(Call<CommissionHistoryResponse> call, Response<CommissionHistoryResponse> response) {
                    Log.e("onResponse", "Success");
                    if (isLoadMoreData) {
                        if (response.body() != null) {
                            if (response.body().getCommissionData().size() == 0) {
                                commissionHistoryView.showToast("No More Data Found");
                                commissionHistoryView.hideFooter();
                            } else {
                                processLoadNewData(response.body().getCommissionData());
                            }
                        }

                    } else {
                        if (response.body() != null) {
                            if (response.body().getCommissionData() != null) {
                                if (response.body().getCommissionData().size() == 0) {
                                    if (!isLayoutRefreshed)
                                        commissionHistoryView.hideProgressDialog();
                                    commissionHistoryView.addEmptyView(_ctx.getString(R.string.no_commission_history_found));
                                } else {
                                    processInitData(response.body().getCommissionData());
                                }
                            } else {
                                //error
                                commissionHistoryView.hideProgressDialog();
                            }
                        } else {
                            //error
                            commissionHistoryView.hideProgressDialog();
                            // commissionHistoryView.addEmptyView();
                        }
                    }

                }

                @Override
                public void onFailure(Call<CommissionHistoryResponse> call, Throwable t) {
                    Log.e("onFailure", t.toString());
                    if (isLoadMoreData) {
                        commissionHistoryView.hideFooter();
                    } else {
                        if (!isLayoutRefreshed) {
                            commissionHistoryView.hideProgressDialog();
                            commissionHistoryView.addEmptyView(_ctx.getString(R.string.no_commission_history_found));
                        }
                    }


                }
            });
        } else {
            if(commissionHistoryView != null) {
                commissionHistoryView.showNoInternetDialog();
            }
        }
    }

    private void processLoadNewData(ArrayList<CommissionHistoryData> data) {
        for (int i = 0; i < data.size(); i++) {
            listData.add(new CommissionDataObject(Constants.getVerticalShortName(Integer.valueOf(data.get(i).LeadTypeID)) + "" + data.get(i).getLeadID(),
                    Constants.getVerticalShortName(Integer.valueOf(data.get(i).LeadTypeID)),
                    "" + data.get(i).getLeadDateTime(),
                    data.get(i).getStatus(),
                    "" + data.get(i).getRName(), data.get(i).getSoldPrice(),
                      data.get(i).getCommissionEarned(),
                    ColorGenerator.MATERIAL.getARandomColor()));
        }
        commissionHistoryView.hideFooter();
        mCommissionListAdapter.notifyDataSetChanged();

    }

    private void processInitData(ArrayList<CommissionHistoryData> data) {
        listData = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            listData.add(new CommissionDataObject(Constants.getVerticalShortName(Integer.valueOf(data.get(i).LeadTypeID)) + "" + data.get(i).getLeadID(),
                    Constants.getVerticalShortName(Integer.valueOf(data.get(i).LeadTypeID)),
                    "" + data.get(i).getLeadDateTime(),
                    data.get(i).getStatus(),
                    "" + data.get(i).getRName(), data.get(i).getSoldPrice(),
                    data.get(i).getCommissionEarned(),
                    ColorGenerator.MATERIAL.getARandomColor()));
        }
        commissionHistoryView.hideProgressDialog();
        mCommissionListAdapter = new CommissionListAdapter(_ctx, listData);
        commissionHistoryView.setData(listData, mCommissionListAdapter);
    }


    //end of main class
}
