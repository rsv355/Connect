package com.webmyne.connect.postLead.presenter;

import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;

import com.webmyne.connect.R;
import com.webmyne.connect.Utils.ComplexPreferences;
import com.webmyne.connect.base.MyApplication;
import com.webmyne.connect.postLead.api.PostLeadService;
import com.webmyne.connect.postLead.model.MainPostLeadOutput;
import com.webmyne.connect.postLead.model.PostLeadInput;
import com.webmyne.connect.user.model.UserLoginOutput;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by priyasindkar on 08-03-2016.
 */
public class LeadsInteractorImpl implements LeadsInteractor {
    private LeadsView leadsView;

    public LeadsInteractorImpl(LeadsView leadsView) {
        this.leadsView = leadsView;
    }

    @Override
    public void doPostLead(final Activity activity, String name, String emailId, String contactNo, String description, List<Integer> selectedVerticals) {
        if (leadsView != null) {
            leadsView.showProgressDialog();
        }

        ComplexPreferences complexPreferences = new ComplexPreferences(activity, "login-user", activity.MODE_PRIVATE);
        UserLoginOutput currentUser = complexPreferences.getObject("loggedInUser", UserLoginOutput.class);

        if (currentUser != null) {
            int[] arr = new int[selectedVerticals.size()];

            for (int i = 0; i < selectedVerticals.size(); i++) {
                arr[i] = selectedVerticals.get(i);
            }
            PostLeadInput postLeadInput = new PostLeadInput(description, emailId, contactNo, name, currentUser.UserID, arr);
            PostLeadService postLeadService = MyApplication.retrofit.create(PostLeadService.class);

            Call<MainPostLeadOutput> call = postLeadService.doUserProfileUpdate(postLeadInput);
            call.enqueue(new Callback<MainPostLeadOutput>() {
                @Override
                public void onResponse(Call<MainPostLeadOutput> call, Response<MainPostLeadOutput> response) {
                    if(response.body() != null) {
                        if(response.body().PostLeadOutput != null) {
                            if (response.body().PostLeadOutput.ResponseCode.equalsIgnoreCase(activity.getString(R.string.success_response_code))) {
                                SharedPreferences preferences = activity.getSharedPreferences("user_lead_post", activity.MODE_PRIVATE);
                                preferences.edit().putBoolean("isLeadPosted", true).commit();
                                onLeadPost(true, activity.getString(R.string.post_lead_successful), "");
                            } else {
                                onLeadPost(false, "",response.body().PostLeadOutput.ResponseMessage);
                            }
                        } else {
                            onLeadPost(false, "", activity.getString(R.string.post_lead_failed));
                        }
                    } else {
                        onLeadPost(false, "", activity.getString(R.string.post_lead_failed));
                    }
                }

                @Override
                public void onFailure(Call<MainPostLeadOutput> call, Throwable t) {
                    onLeadPost(false, "", activity.getString(R.string.post_lead_failed));
                }
            });
        }
    }

    private void onLeadPost(boolean isSuccess, String success, String error) {
        if( leadsView != null) {
            leadsView.hideProgressDialog();
            if(isSuccess)
                leadsView.onLeadPostSuccess(success);
            else
                leadsView.onLeadPostSuccess(error);
        }
    }
}
