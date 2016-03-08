package com.webmyne.connect.user;

import android.app.Activity;
import android.util.Log;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.webmyne.connect.R;
import com.webmyne.connect.Utils.ComplexPreferences;
import com.webmyne.connect.Utils.Functions;
import com.webmyne.connect.login.model.UserLoginOutput;

import java.util.Calendar;

/**
 * Created by priyasindkar on 22-02-2016.
 */
public class EditProfilePresenterImpl implements EditProfilePresenter {

    private EditProfileView editProfileView;
    private EditProfileInteractor editProfileInteractor;

    public EditProfilePresenterImpl(EditProfileView editProfileView) {
        this.editProfileView = editProfileView;
        this.editProfileInteractor = new EditProfileInteractorImpl(this);
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
    public void showDatePicker(Activity activity) {
        //DatePickerDialog dpd = (DatePickerDialog) activity.getFragmentManager().findFragmentByTag("Datepickerdialog");
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                        String date = year + "-" + String.format("%02d", ++monthOfYear) + "-" + String.format("%02d", dayOfMonth);
                        if (editProfileView != null) {
                            editProfileView.onDateSet(date);
                        }
                    }
                },
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.setMaxDate(now);
        dpd.show(activity.getFragmentManager(), "Datepickerdialog");
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
        } else if (!Functions.isEmailValid(emailId.trim())) {
            if (editProfileView != null) {
                editProfileView.setError(activity.getString(R.string.email_invalid_validation));
            }
        } else if (mobile.trim().length() != 0 && mobile.trim().length() != 10) {
            if (editProfileView != null) {
                editProfileView.setError(activity.getString(R.string.mobile_invalid_validation));
            }
        } else {
            if (editProfileView != null) {
                UserLoginOutput userLoginOutput = new UserLoginOutput();
                userLoginOutput.setName(name);
                userLoginOutput.setEmail(emailId);
                userLoginOutput.setDOB(DOB);
                userLoginOutput.setMobile(mobile);
                userLoginOutput.setZipCode(zipcode);
                userLoginOutput.setAddress(location);
                userLoginOutput.setGender(gender);
                userLoginOutput.setUserID(userId);
                editProfileView.onValidationSuccess(true, userLoginOutput);
            }
            //doUpdateUser(activity, name, emailId, DOB, mobile, zipcode, location, gender, userId);
        }
    }

    @Override
    public void doUpdateUser(Activity activity, UserLoginOutput userLoginOutput) {
        if (editProfileView != null) {
            editProfileView.showProgress();
        }
        editProfileInteractor.doCallUpdateUserService(activity, userLoginOutput);
    }

    @Override
    public void onUpdateUser(boolean isSuccess, String success, String error) {
        if (editProfileView != null) {
            editProfileView.hideProgress();
        }
        if (isSuccess) {
            editProfileView.onUpdateUserSuccess(success);
        } else {
            editProfileView.onUpdateUserFail(error);
        }
    }

    @Override
    public void onDestroy() {
        editProfileView = null;
    }

    @Override
    public void showEnterReferCodeDialog() {
        if (editProfileView != null) {
            editProfileView.hideProgress();
            editProfileView.showReferCodeAlert();
        }
    }
}
