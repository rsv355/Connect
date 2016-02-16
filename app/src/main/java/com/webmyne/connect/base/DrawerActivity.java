package com.webmyne.connect.base;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.webmyne.connect.R;

import com.webmyne.connect.dashboard.DashboardFragmentWithLeads;

/**
 * Created by priyasindkar on 12-02-2016.
 */
public class DrawerActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private AccountHeader headerResult = null;
    private Drawer result = null;
    private static final int PROFILE_SETTING = 100000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        init(savedInstanceState);

        //Dashboard Fragment
        FragmentManager manager = getFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(R.id.frame_container, DashboardFragmentWithLeads.newInstance(), "DASHBOARD");
        ft.commit();
    }

    private void init(Bundle savedInstanceState) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Hi, John Doe");
        toolbar.setSubtitle("$100.00");

        final IProfile profile = new ProfileDrawerItem()
                .withTextColorRes(R.color.colorAccent)
                .withName("John Doe")
                .withEmail("johndoe@gmail.com")
                .withIcon(getResources().getDrawable(R.drawable.dummy_boy)).withIdentifier(100);

        // Create the AccountHeader
        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withTextColorRes(R.color.white)
                .withHeaderBackground(R.drawable.kk)
                .addProfiles(
                        profile
                )
                .withSavedInstance(savedInstanceState)
                .build();

        //Create the drawer
        result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withHasStableIds(true)
                .withSliderBackgroundColor(getResources().getColor(R.color.white))
                .withAccountHeader(headerResult)
                .addDrawerItems(
                        new PrimaryDrawerItem()
                                .withSelectedColorRes(R.color.white)
                                .withTextColor(getResources().getColor(R.color.primaryBackground))
                                .withSelectedTextColor(getResources().getColor(R.color.colorPrimary))
                                .withName("Edit Profile")
                                .withIcon(getResources().getDrawable(R.drawable.ic_profile_name))
                                .withSelectedIconColor(getResources().getColor(R.color.colorPrimary))
                                .withIdentifier(1),
                        new DividerDrawerItem(),
                        new PrimaryDrawerItem()
                                .withSelectedColorRes(R.color.white)
                                .withTextColor(getResources().getColor(R.color.primaryBackground))
                                .withSelectedTextColor(getResources().getColor(R.color.colorPrimary))
                                .withName("My Revenue")
                                .withIcon(getResources().getDrawable(R.drawable.ic_profile_dob))
                                .withSelectedIconColor(getResources().getColor(R.color.colorPrimary))
                                .withIdentifier(2),
                        new DividerDrawerItem())
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        //check if the drawerItem is set.
                        //there are different reasons for the drawerItem to be null
                        //--> click on the header
                        //--> click on the footer
                        //those items don't contain a drawerItem

                        if (drawerItem != null) {
                            Intent intent = null;
                            if (drawerItem.getIdentifier() == 1) {
                                drawerItem.withSetSelected(true);
                                Toast.makeText(DrawerActivity.this, "Edit Profile", Toast.LENGTH_SHORT).show();
                                //intent = new Intent(MainActivity.this, CrossfadeDrawerLayoutActivity.class);
                            } else if (drawerItem.getIdentifier() == 2) {
                                drawerItem.withSetSelected(true);
                                Toast.makeText(DrawerActivity.this, "My Revenue", Toast.LENGTH_SHORT).show();
                                // intent = new Intent(MainActivity.this, CrossfadeDrawerLayout.class);
                            }
                            if (intent != null) {
                                DrawerActivity.this.startActivity(intent);
                            }
                        }

                        return false;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //add the values which need to be saved from the drawer to the bundle
        outState = result.saveInstanceState(outState);
        //add the values which need to be saved from the accountHeader to the bundle
        outState = headerResult.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

}
