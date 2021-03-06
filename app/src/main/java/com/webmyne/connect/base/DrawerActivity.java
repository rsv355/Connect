package com.webmyne.connect.base;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.util.DrawerImageLoader;
import com.webmyne.connect.R;
import com.webmyne.connect.Utils.ComplexPreferences;
import com.webmyne.connect.Utils.Functions;
import com.webmyne.connect.Utils.PrefUtils;
import com.webmyne.connect.commissionHistory.CommissionHistoryListActivity;
import com.webmyne.connect.dashboard.DashboardFragment;
import com.webmyne.connect.leadHistory.LeadsHistoryListActivity;
import com.webmyne.connect.user.model.UserLoginOutput;
import com.webmyne.connect.revenuePayment.PaymentHistoryListActivity;
import com.webmyne.connect.revenuePayment.RedeemMoneyActivity;
import com.webmyne.connect.shareAndEarn.SharAndEarnActivity;
import com.webmyne.connect.user.EditProfileActivity;

/**
 * Created by priyasindkar on 12-02-2016.
 */
public class DrawerActivity extends AppCompatActivity implements DrawerActivityView {
    private Toolbar toolbar;
    private AccountHeader headerResult = null;
    private Drawer result = null;
    private UserLoginOutput currentUser;
    private DrawerActivityPresenter drawerActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.e("IsActiveLeadStatus", ""+PrefUtils.isActiveLead(DrawerActivity.this));

        init();

        drawerActivityPresenter = new DrawerActivityPresenterImpl(this);

        //Dashboard Fragment
        FragmentManager manager = getFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(R.id.frame_container, DashboardFragment.newInstance(), "DASHBOARD");
        ft.commit();
    }

    private void init() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ComplexPreferences complexPreferences = new ComplexPreferences(this, "login-user", MODE_PRIVATE);
        currentUser = complexPreferences.getObject("loggedInUser", UserLoginOutput.class);

        /*if(currentUser!= null) {
            if(currentUser.isActiveLead()) {
                isLeadPosted = true;
            } else {
                SharedPreferences preferences = getSharedPreferences("user_lead_post", MODE_PRIVATE);
                isLeadPosted = preferences.getBoolean("isLeadPosted", false);
            }
        } else {
            isLeadPosted = false;
        }*/

        toolbar.setTitle("Hi, " + currentUser.Name);
        toolbar.setSubtitle("$ " + String.valueOf(currentUser.WalletBalance));

        final IProfile profile = new ProfileDrawerItem()
                .withTextColorRes(R.color.colorAccent)
                .withName(currentUser.Name)
                .withEmail(currentUser.Email).withIdentifier(100);


       // DrawerImageLoader.init(IDrawerImageLoader);
        if(currentUser.getProfilePic() != null || currentUser.getProfilePic().length() > 0) {
            Log.e("### PIC",currentUser.getProfilePic());
            profile.withIcon(currentUser.getProfilePic());
        } else {
            if (currentUser.Gender != null) {
                if (currentUser.Gender.equalsIgnoreCase("Female")) {
                    profile.withIcon(getResources().getDrawable(R.drawable.dummy_girl));
                } else if (currentUser.Gender.equalsIgnoreCase("Male")) {
                    profile.withIcon(getResources().getDrawable(R.drawable.dummy_boy));
                } else {
                    profile.withIcon(getResources().getDrawable(R.drawable.dummy_boy));
                }
            }
        }

        // Create the AccountHeader
        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withNameTypeface(Functions.getTypeFace(DrawerActivity.this))
                .withEmailTypeface(Functions.getTypeFace(DrawerActivity.this))
                .withTextColorRes(R.color.white)
                .withHeaderBackground(R.drawable.kk)
                .withAlternativeProfileHeaderSwitching(false)
                .withSelectionListEnabled(false)
                .addProfiles(
                        profile
                )
                //.withSavedInstance(savedInstanceState)
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
                                .withTextColor(getResources().getColor(R.color.colorPrimary))
                                .withSelectedTextColor(getResources().getColor(R.color.drawer_items_color))
                                .withName(R.string.home)
                                .withTypeface(Functions.getTypeFace(DrawerActivity.this))
                                //.withIcon(getResources().getDrawable(R.drawable.ic_profile_name))
                                //.withSelectedIconColor(getResources().getColor(R.color.colorPrimary))
                                .withIdentifier(1),
                   /*     new DividerDrawerItem(),*/
                        new PrimaryDrawerItem()
                                .withSelectedColorRes(R.color.white)
                                .withTextColor(getResources().getColor(R.color.colorPrimary))
                                .withSelectedTextColor(getResources().getColor(R.color.drawer_items_color))
                                .withName(R.string.lead_history)
                                .withTypeface(Functions.getTypeFace(DrawerActivity.this))
                                //.withIcon(getResources().getDrawable(R.drawable.ic_profile_name))
                                //.withSelectedIconColor(getResources().getColor(R.color.colorPrimary))
                                .withIdentifier(9),
                        new PrimaryDrawerItem()
                                .withSelectedColorRes(R.color.white)
                                .withTextColor(getResources().getColor(R.color.colorPrimary))
                                .withSelectedTextColor(getResources().getColor(R.color.drawer_items_color))
                                .withName(R.string.my_profile)
                                .withTypeface(Functions.getTypeFace(DrawerActivity.this))
//                                .withIcon(getResources().getDrawable(R.drawable.ic_profile_dob))
//                                .withSelectedIconColor(getResources().getColor(R.color.colorPrimary))
                                .withIdentifier(2),
                     /*   new DividerDrawerItem(),*/
                        new PrimaryDrawerItem()
                                .withSelectedColorRes(R.color.white)
                                .withTextColor(getResources().getColor(R.color.colorPrimary))
                                .withSelectedTextColor(getResources().getColor(R.color.drawer_items_color))
                                .withName(R.string.redeem_revenue)
                                .withTypeface(Functions.getTypeFace(DrawerActivity.this))
//                                .withIcon(getResources().getDrawable(R.drawable.ic_profile_name))
//                                .withSelectedIconColor(getResources().getColor(R.color.colorPrimary))
                                .withIdentifier(3),
                       /* new DividerDrawerItem(),*/
                        new PrimaryDrawerItem()
                                .withSelectedColorRes(R.color.white)
                                .withTextColor(getResources().getColor(R.color.colorPrimary))
                                .withSelectedTextColor(getResources().getColor(R.color.drawer_items_color))
                                .withName(R.string.transaction_history)
                                .withTypeface(Functions.getTypeFace(DrawerActivity.this))
//                                .withIcon(getResources().getDrawable(R.drawable.ic_profile_name))
//                                .withSelectedIconColor(getResources().getColor(R.color.colorPrimary))
                                .withIdentifier(4),
                       /* new DividerDrawerItem(),*/
                        new PrimaryDrawerItem()
                                .withSelectedColorRes(R.color.white)
                                .withTextColor(getResources().getColor(R.color.colorPrimary))
                                .withSelectedTextColor(getResources().getColor(R.color.drawer_items_color))
                                .withName(R.string.my_commission)
                                .withTypeface(Functions.getTypeFace(DrawerActivity.this))
//                                .withIcon(getResources().getDrawable(R.drawable.ic_profile_name))
//                                .withSelectedIconColor(getResources().getColor(R.color.colorPrimary))
                                .withIdentifier(5),
                     /*   new DividerDrawerItem(),*/
                        new PrimaryDrawerItem()
                                .withSelectedColorRes(R.color.white)
                                .withTextColor(getResources().getColor(R.color.colorPrimary))
                                .withSelectedTextColor(getResources().getColor(R.color.drawer_items_color))
                                .withName(R.string.share_and_earn)
                                .withTypeface(Functions.getTypeFace(DrawerActivity.this))
//                                .withIcon(getResources().getDrawable(R.drawable.ic_profile_name))
//                                .withSelectedIconColor(getResources().getColor(R.color.colorPrimary))
                                .withIdentifier(6),
                   /*     new DividerDrawerItem(),*/
                        new PrimaryDrawerItem()
                                .withSelectedColorRes(R.color.white)
                                .withTextColor(getResources().getColor(R.color.colorPrimary))
                                .withSelectedTextColor(getResources().getColor(R.color.drawer_items_color))
                                .withName(R.string.help)
                                .withTypeface(Functions.getTypeFace(DrawerActivity.this))
//                                .withIcon(getResources().getDrawable(R.drawable.ic_profile_name))
//                                .withSelectedIconColor(getResources().getColor(R.color.colorPrimary))
                                .withIdentifier(7),
                        new PrimaryDrawerItem()
                                .withSelectedColorRes(R.color.white)
                                .withTextColor(getResources().getColor(R.color.colorPrimary))
                                .withSelectedTextColor(getResources().getColor(R.color.drawer_items_color))
                                .withName(R.string.logout)
                                .withTypeface(Functions.getTypeFace(DrawerActivity.this))
                                //                                .withIcon(getResources().getDrawable(R.drawable.ic_profile_name))
                                //                                .withSelectedIconColor(getResources().getColor(R.color.colorPrimary))
                                .withIdentifier(8))
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

                                    ft.replace(R.id.frame_container, DashboardFragment.newInstance(), "DASHBOARD");

                                ft.commit();

                            } else if (drawerItem.getIdentifier() == 2) {
                                drawerItem.withSetSelected(true);
                                intent = new Intent(DrawerActivity.this, EditProfileActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                            } else if (drawerItem.getIdentifier() == 3) {
                                drawerItem.withSetSelected(true);
                                intent = new Intent(DrawerActivity.this, RedeemMoneyActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                            } else if (drawerItem.getIdentifier() == 4) {
                                drawerItem.withSetSelected(true);
                                intent = new Intent(DrawerActivity.this, PaymentHistoryListActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                            } else if (drawerItem.getIdentifier() == 5) {
                                drawerItem.withSetSelected(true);
                                intent = new Intent(DrawerActivity.this, CommissionHistoryListActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                            } else if (drawerItem.getIdentifier() == 6) {
                                drawerItem.withSetSelected(true);
                                intent = new Intent(DrawerActivity.this, SharAndEarnActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                            } else if (drawerItem.getIdentifier() == 8) {
                                drawerItem.withSetSelected(true);
                                drawerActivityPresenter.doLogout(DrawerActivity.this);
                            } else if (drawerItem.getIdentifier() == 9) {
                                drawerItem.withSetSelected(true);
                                intent = new Intent(DrawerActivity.this, LeadsHistoryListActivity.class);
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
                //.withSavedInstance(savedInstanceState)
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

    @Override
    public void onLogout() {
        finishAffinity();

    }
}
