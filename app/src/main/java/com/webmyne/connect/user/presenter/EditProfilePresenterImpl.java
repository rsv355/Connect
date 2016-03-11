package com.webmyne.connect.user.presenter;

import android.app.Activity;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AlphaAnimation;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.webmyne.connect.R;
import com.webmyne.connect.Utils.ComplexPreferences;
import com.webmyne.connect.Utils.Functions;
import com.webmyne.connect.user.model.UserLoginOutput;

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
    public void initUI(Activity activity) {
        ComplexPreferences complexPreferences = new ComplexPreferences(activity, "login-user", activity.MODE_PRIVATE);
        UserLoginOutput currentUser = complexPreferences.getObject("loggedInUser", UserLoginOutput.class);

        if (editProfileView != null) {
            editProfileView.initUI(currentUser);
        }
    }

    @Override
    public void showDatePicker(Activity activity) {
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
    public void setToolBarOffset(Activity activity, AppBarLayout appBarLayout, int offset, Toolbar toolbar) {
       Functions.setToolBarOffset(activity, appBarLayout, offset, toolbar);
    }

    @Override
    public void validateFormFields(Activity activity, String name, String emailId, String DOB, String mobile,
                                   String zipcode, String location, String gender, int userId) {

        if( Functions.checkInternet(activity)) {
            if (name.trim().length() == 0) {
                if (editProfileView != null) {
                    editProfileView.setNameError(activity.getString(R.string.name_empty_validation));
                }
            } else if (emailId.trim().length() == 0) {
                if (editProfileView != null) {
                    editProfileView.setEmailError(activity.getString(R.string.email_empty_validation));
                }
            } else if (!Functions.isEmailValid(emailId.trim())) {
                if (editProfileView != null) {
                    editProfileView.setEmailError(activity.getString(R.string.email_invalid_validation));
                }
            } else if (mobile.trim().length() != 0 && mobile.trim().length() != 10) {
                if (editProfileView != null) {
                    editProfileView.setMobileError(activity.getString(R.string.mobile_invalid_validation));
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
            }
        } else {
            if(editProfileView != null) {
                editProfileView.showNoInternetDialog(activity);
            }
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
    public void showNoInternetDialog(Activity activity) {
        Functions.getSimpleOkAlterDialog(activity, activity.getString(R.string.no_internet_connection), "Ok").show();
    }

    @Override
    public void showEnterReferCodeDialog() {
        if (editProfileView != null) {
            editProfileView.hideProgress();
            editProfileView.showReferCodeAlert();
        }
    }

    @Override
    public void startAlphaAnimation(View v, long duration, int visibility) {
       Functions.startAlphaAnimation(v, duration, visibility);
    }
}
