package com.webmyne.connect.user.presenter;

import android.app.Activity;
import android.location.Address;
import android.location.Geocoder;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Toast;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.webmyne.connect.R;
import com.webmyne.connect.Utils.ComplexPreferences;
import com.webmyne.connect.Utils.Functions;
import com.webmyne.connect.user.model.IndustryModel;
import com.webmyne.connect.user.model.UserLoginOutput;
import com.webmyne.connect.user.model.UserUpdateInput;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public void getIndustryList(Activity activity, String searchString) {
        if (editProfileView != null) {
            editProfileView.showProgress();
        }
        editProfileInteractor.getIndustryList(activity, searchString);

    }

    @Override
    public void onIndustryListFetch(boolean isSuccess, List<IndustryModel> industryList) {
        if (editProfileView != null) {
            editProfileView.hideProgress();

            if(isSuccess) {
                if( !industryList.isEmpty()) {
                    String[] industries = new String[industryList.size()];
                    int i = 0;
                    for (IndustryModel industryModel : industryList) {
                        industries[i] = industryModel.IndustryName;
                        i++;
                    }
                    editProfileView.onIndustryListFetch(true, industries);
                } else {
                    editProfileView.onIndustryListFetch(false, null);
                }
            } else {
                editProfileView.onIndustryListFetch(false, null);
            }
        }
    }

    @Override
    public void validateFormFields(Activity activity, String name, String emailId, String DOB, String mobile,
                                   String zipcode, String location, String streetNumber, String indutry, String gender, int userId) {

        if (Functions.checkInternet(activity)) {
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
            } else if (DOB.trim().length() == 0) {
                if (editProfileView != null) {
                    editProfileView.setDOBError(activity.getString(R.string.dob_empty_validation));
                }
            } else if (mobile.trim().length() != 0 && mobile.trim().length() != 10) {
                if (editProfileView != null) {
                    editProfileView.setMobileError(activity.getString(R.string.mobile_invalid_validation));
                }
            } else if (zipcode.trim().length() == 0) {
                if (editProfileView != null) {
                    editProfileView.setZipcodError(activity.getString(R.string.zipcode_empty_validation));
                }
            } else if (indutry.trim().length() == 0 || indutry.trim().length() < 3 ) {
                if (editProfileView != null) {
                    editProfileView.setIndustryError(activity.getString(R.string.industry_empty_validation));
                }
            } else if (location.trim().length() == 0) {
                if (editProfileView != null) {
                    editProfileView.setAddressError(activity.getString(R.string.address_empty_validation));
                }
            } else if (streetNumber.trim().length() == 0) {
                if (editProfileView != null) {
                    editProfileView.setAddressError(activity.getString(R.string.street_no_empty_validation));
                }
            } else {
                if (editProfileView != null) {
                    UserUpdateInput userUpdateInput = new UserUpdateInput();
                    userUpdateInput.setName(name);
                    userUpdateInput.setEmail(emailId);
                    userUpdateInput.setDOB(DOB);
                    userUpdateInput.setMobile(mobile);
                    userUpdateInput.setZipCode(zipcode);
                    userUpdateInput.setAddress(streetNumber.replace(",", "") + ", " + location);
                    userUpdateInput.setGender(gender);
                    userUpdateInput.setIndustry(indutry);
                    userUpdateInput.setUserID(userId);
                    editProfileView.onValidationSuccess(true, userUpdateInput);
                }
            }
        } else {
            if (editProfileView != null) {
                editProfileView.showNoInternetDialog(activity);
            }
        }
    }

    @Override
    public void getAddressesFromZipcode(Activity activity, String zipcode) {
        final Geocoder geocoder = new Geocoder(activity);
        try {
            List<Address> addresses = geocoder.getFromLocationName(zipcode, 10);
            if (addresses != null && !addresses.isEmpty()) {
                Address address = addresses.get(0);
                /*String message = String.format("Latitude: %f, Longitude: %f",
                        address.getLatitude(), address.getLongitude());*/

                String[] addressList = new String[addresses.size()];
                if (addresses != null) {

                    for (int j = 0; j < addresses.size(); j++) {
                        Address returnedAddress = addresses.get(j);
                        StringBuilder strReturnedAddress = new StringBuilder();
                        for (int i = 0; i < returnedAddress.getMaxAddressLineIndex(); i++) {
                            strReturnedAddress.append(returnedAddress.getAddressLine(i).replace(",", "")).append("\n");
                        }
                        addressList[j] = strReturnedAddress.toString();
                    }
                }
                if (editProfileView != null) {
                    editProfileView.setAddressList(addressList);
                }
            }
        } catch (IOException e) {
        }
    }

    @Override
    public void doUpdateUser(Activity activity, UserUpdateInput userLoginOutput) {
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
