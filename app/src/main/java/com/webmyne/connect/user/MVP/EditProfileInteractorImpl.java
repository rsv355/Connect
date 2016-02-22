package com.webmyne.connect.user.MVP;

import android.os.Handler;

/**
 * Created by priyasindkar on 22-02-2016.
 */
public class EditProfileInteractorImpl implements EditProfileInteractor {

    @Override
    public void callEditProfile(final String emailId, final OnEditProfileFinishListener onEditProfileFinishListener) {
       // onEditProfileFinishListener.onSuccess();
        //onEditProfileFinishListener.onError();

        new Handler().postDelayed(new Runnable() { //just to show progresss
            @Override
            public void run() {
                if(emailId.length() == 0) {
                    onEditProfileFinishListener.onError("EmailId cannot be empty");
                } else {
                    onEditProfileFinishListener.onSuccess("Profile Updated Successfully");
                }
            }
        }, 5000);
    }
}
