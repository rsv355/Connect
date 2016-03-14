package com.webmyne.connect.user.ui;

/**
 * Created by priyasindkar on 14-03-2016.
 */
public class AddReferCodePresenterImpl implements AddReferCodePresenter {

    @Override
    public boolean validatePassword(String password) {

        if(password.length() == 0 || password.length() != 6) {
            return false;
        } else {
            return true;
        }
    }
}
