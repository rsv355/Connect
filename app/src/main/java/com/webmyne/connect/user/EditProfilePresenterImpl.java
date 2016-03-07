package com.webmyne.connect.user;

import android.app.Activity;

import com.webmyne.connect.R;
import com.webmyne.connect.Utils.ComplexPreferences;
import com.webmyne.connect.Utils.Functions;
import com.webmyne.connect.login.model.UserLoginOutput;

/**
 * Created by priyasindkar on 22-02-2016.
 */
public class EditProfilePresenterImpl implements EditProfilePresenter {

    private EditProfileView editProfileView;
    private EditProfileInteractor editProfileInteractor;

    public EditProfilePresenterImpl(EditProfileView editProfileView) {
        this.editProfileView = editProfileView;
        this.editProfileInteractor = new EditProfileInteractorImpl();
    }

    @Override
    public void initUserData(Activity activity) {
        ComplexPreferences complexPreferences = new ComplexPreferences(activity, "login-user", activity.MODE_PRIVATE);
        UserLoginOutput currentUser = complexPreferences.getObject("loggedInUser", UserLoginOutput.class);

        if (editProfileView != null) {
            editProfileView.initUserData(currentUser);
        }
    }

    @Override
    public void validateFormFields(Activity activity, String name, String emailId, String DOB, String mobile,
                                   String zipcode, String location, String gender, int userId) {
        if (name.trim().length() == 0) {
            if (editProfileView != null) {
                editProfileView.setError(activity.getString(R.string.name_empty_validation));
            }
        } else if (emailId.trim().length() == 0) {
            if (editProfileView != null) {
                editProfileView.setError(activity.getString(R.string.email_empty_validation));
            }
        } else if ( !Functions.isEmailValid(emailId.trim())) {
            if (editProfileView != null) {
                editProfileView.setError(activity.getString(R.string.email_invalid_validation));
            }
        } else if (mobile.trim().length() > 10) {
            if (editProfileView != null) {
                editProfileView.setError(activity.getString(R.string.mobile_invalid_validation));
            }
        } else {
            doUpdateUser(activity, name, emailId, DOB, mobile, zipcode, location, gender, userId);
        }
    }

    public void doUpdateUser(Activity activity, String name, String emailId, String DOB,
                             String mobile, String zipcode, String location, String gender, int userId) {
        if(editProfileView !=null) {
            editProfileView.showProgress();
        }
        UserLoginOutput userLoginOutput = new UserLoginOutput();
        userLoginOutput.UserID = userId;
        userLoginOutput.Name = name;
        userLoginOutput.Email = emailId;
        userLoginOutput.DOB = DOB;
        userLoginOutput.Mobile = mobile;
        userLoginOutput.Gender = gender;
        userLoginOutput.ZipCode = zipcode;
        userLoginOutput.Address = location;

        editProfileInteractor.doCallUpdateUserService(userLoginOutput);

    }

    @Override
    public void onDestroy() {
        editProfileView = null;
    }


    public void onError(String errorString) {
        if (editProfileView != null) {
            editProfileView.hideProgress();
            editProfileView.setError(errorString);
        }
    }

    public void onSuccess(String successString) {
        if (editProfileView != null) {
            editProfileView.hideProgress();
            editProfileView.onSuccess(successString);
        }
    }

    @Override
    public void showEnterReferCodeDialog() {
        if (editProfileView != null) {
            editProfileView.hideProgress();
            editProfileView.showReferCodeAlert();
        }
    }
}
