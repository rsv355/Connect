package com.webmyne.connect.user.MVP;

/**
 * Created by priyasindkar on 22-02-2016.
 */
public class EditProfilePresenterImpl implements EditProfilePresenter, OnEditProfileFinishListener {

    private EditProfileView editProfileView;
    private EditProfileInteractor editProfileInteractor;

    public EditProfilePresenterImpl(EditProfileView editProfileView) {
        this.editProfileView = editProfileView;
        this.editProfileInteractor = new EditProfileInteractorImpl();
    }

    @Override
    public void validateFormFields(String emailId) {
        if(editProfileView !=null) {
            editProfileView.showProgress();
        }
        this.editProfileInteractor.callEditProfile(emailId, this);
    }

    @Override
    public void onDestroy() {
        editProfileView = null;
    }

    @Override
    public void onError(String errorString) {
        if(editProfileView!=null) {
            editProfileView.hideProgress();
            editProfileView.setError(errorString);
        }
    }

    @Override
    public void onSuccess(String successString) {
        if(editProfileView!=null) {
            editProfileView.hideProgress();
            editProfileView.onSuccess(successString);
        }
    }

    @Override
    public void showEnterReferCodeDialog() {
        if(editProfileView!=null) {
            editProfileView.hideProgress();
            editProfileView.showReferCodeAlert();
        }
    }
}
