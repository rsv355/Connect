package com.webmyne.connect.user.MVP;

/**
 * Created by priyasindkar on 22-02-2016.
 */
public interface EditProfilePresenter {
    public void validateFormFields(String emailId);
    public void showEnterReferCodeDialog();
    public void onDestroy();
}
