package com.webmyne.connect.base;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.appeaser.sublimenavigationviewlibrary.SublimeNavigationView;
import com.webmyne.connect.R;

import com.webmyne.connect.dashboard.DashboardFragment;

/**
 * Created by priyasindkar on 15-02-2016.
 */
public class DrawerActivityNew extends AppCompatActivity {
    SublimeNavigationView snv;
    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_drawer);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            toolbar.setTitle("Hi, John Doe");
            toolbar.setSubtitle("$100.00");
        }

        snv = (SublimeNavigationView) findViewById(R.id.navigation_view);
        //Dashboard Fragment
        FragmentManager manager = getFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(R.id.frame_container, DashboardFragment.newInstance(), "DASHBOARD");
        ft.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

}
