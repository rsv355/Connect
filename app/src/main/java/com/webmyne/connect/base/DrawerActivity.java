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

import com.webmyne.connect.commission.CommissionHistoryListActivity;
import com.webmyne.connect.dashboard.DashboardFragmentWithLeads;
import com.webmyne.connect.revenue.RedeemMoneyActivity;
import com.webmyne.connect.revenue.RevenueHistoryListActivity;
import com.webmyne.connect.user.EditProfileActivity;

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
                /*.withSelectionListEnabledForSingleProfile(true)
                .withSelectionListEnabled(false)
                .withOnAccountHeaderSelectionViewClickListener(null)
                .withAlternativeProfileHeaderSwitching(false)
                .withCurrentProfileHiddenInList(true)
                .withSelectionListEnabled(false)*/
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
                                .withName(R.string.home)
                                .withIcon(getResources().getDrawable(R.drawable.ic_profile_name))
                                .withSelectedIconColor(getResources().getColor(R.color.colorPrimary))
                                .withIdentifier(1),
                        new DividerDrawerItem(),
                        new PrimaryDrawerItem()
                                .withSelectedColorRes(R.color.white)
                                .withTextColor(getResources().getColor(R.color.primaryBackground))
                                .withSelectedTextColor(getResources().getColor(R.color.colorPrimary))
                                .withName(R.string.my_profile)
                                .withIcon(getResources().getDrawable(R.drawable.ic_profile_dob))
                                .withSelectedIconColor(getResources().getColor(R.color.colorPrimary))
                                .withIdentifier(2),
                        new DividerDrawerItem(),
                        new PrimaryDrawerItem()
                                .withSelectedColorRes(R.color.white)
                                .withTextColor(getResources().getColor(R.color.primaryBackground))
                                .withSelectedTextColor(getResources().getColor(R.color.colorPrimary))
                                .withName(R.string.redeem_revenue)
                                .withIcon(getResources().getDrawable(R.drawable.ic_profile_name))
                                .withSelectedIconColor(getResources().getColor(R.color.colorPrimary))
                                .withIdentifier(3),
                        new DividerDrawerItem(),
                        new PrimaryDrawerItem()
                                .withSelectedColorRes(R.color.white)
                                .withTextColor(getResources().getColor(R.color.primaryBackground))
                                .withSelectedTextColor(getResources().getColor(R.color.colorPrimary))
                                .withName(R.string.revenue_history)
                                .withIcon(getResources().getDrawable(R.drawable.ic_profile_name))
                                .withSelectedIconColor(getResources().getColor(R.color.colorPrimary))
                                .withIdentifier(4),
                        new DividerDrawerItem(),
                        new PrimaryDrawerItem()
                                .withSelectedColorRes(R.color.white)
                                .withTextColor(getResources().getColor(R.color.primaryBackground))
                                .withSelectedTextColor(getResources().getColor(R.color.colorPrimary))
                                .withName(R.string.my_commission)
                                .withIcon(getResources().getDrawable(R.drawable.ic_profile_name))
                                .withSelectedIconColor(getResources().getColor(R.color.colorPrimary))
                                .withIdentifier(5),
                        new DividerDrawerItem(),
                        new PrimaryDrawerItem()
                                .withSelectedColorRes(R.color.white)
                                .withTextColor(getResources().getColor(R.color.primaryBackground))
                                .withSelectedTextColor(getResources().getColor(R.color.colorPrimary))
                                .withName(R.string.share_and_earn)
                                .withIcon(getResources().getDrawable(R.drawable.ic_profile_name))
                                .withSelectedIconColor(getResources().getColor(R.color.colorPrimary))
                                .withIdentifier(6),
                        new DividerDrawerItem(),
                        new PrimaryDrawerItem()
                                .withSelectedColorRes(R.color.white)
                                .withTextColor(getResources().getColor(R.color.primaryBackground))
                                .withSelectedTextColor(getResources().getColor(R.color.colorPrimary))
                                .withName(R.string.help)
                                .withIcon(getResources().getDrawable(R.drawable.ic_profile_name))
                                .withSelectedIconColor(getResources().getColor(R.color.colorPrimary))
                                .withIdentifier(7))
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem != null) {
                            Intent intent = null;
                            if (drawerItem.getIdentifier() == 1) {
                                drawerItem.withSetSelected(true);
                                //Dashboard Fragment
                                FragmentManager manager = getFragmentManager();
                                FragmentTransaction ft = manager.beginTransaction();
                                ft.replace(R.id.frame_container, DashboardFragmentWithLeads.newInstance(), "DASHBOARD");
                                ft.commit();

                            } else if (drawerItem.getIdentifier() == 2) {
                                drawerItem.withSetSelected(true);
                                intent = new Intent(DrawerActivity.this, EditProfileActivity.class);
                                startActivity(intent);
                                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                            } else if (drawerItem.getIdentifier() == 3) {
                                drawerItem.withSetSelected(true);
                                intent = new Intent(DrawerActivity.this, RedeemMoneyActivity.class);
                                startActivity(intent);
                                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                            } else if (drawerItem.getIdentifier() == 4) {
                                drawerItem.withSetSelected(true);
                                intent = new Intent(DrawerActivity.this, RevenueHistoryListActivity.class);
                                startActivity(intent);
                                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                            } else if (drawerItem.getIdentifier() == 5) {
                                drawerItem.withSetSelected(true);
                                intent = new Intent(DrawerActivity.this, CommissionHistoryListActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
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