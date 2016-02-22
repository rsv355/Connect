package com.webmyne.connect.user.MVP;

/**
 * Created by priyasindkar on 22-02-2016.
 */
public interface EditProfileView {
    public void setError(String errorString);
    public void onSuccess(String successString);
    public void showReferCodeAlert();
    public void showProgress();
    public void hideProgress();
}
