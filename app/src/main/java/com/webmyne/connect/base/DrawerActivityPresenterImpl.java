package com.webmyne.connect.base;

import android.app.Activity;
import android.content.SharedPreferences;

import com.webmyne.connect.Utils.ComplexPreferences;

/**
 * Created by priyasindkar on 08-03-2016.
 */
public class DrawerActivityPresenterImpl implements DrawerActivityPresenter {
    private DrawerActivityView drawerActivityView;

    public DrawerActivityPresenterImpl(DrawerActivityView drawerActivityView) {
        this.drawerActivityView = drawerActivityView;
    }

    @Override
    public void doLogout(Activity activity) {
        SharedPreferences preferences = activity.getSharedPreferences("login-user-prefs", 0);
        preferences.edit().clear().commit();

        ComplexPreferences complexPreferences = new ComplexPreferences(activity, "login-user", activity.MODE_PRIVATE);
        complexPreferences.removeObject("loggedInUser");
        complexPreferences.commit();

        if(drawerActivityView != null) {
            drawerActivityView.onLogout();
        }
    }
}
