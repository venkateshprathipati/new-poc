package com.venky.wiprotask.ui.main;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.venky.wiprotask.R;
import com.venky.wiprotask.ui.account.AccountFragment;
import com.venky.wiprotask.ui.base.BaseActivity;
import com.venky.wiprotask.ui.custom.RoundedImageView;
import com.venky.wiprotask.ui.dashboard.DashboardFragment;
import com.venky.wiprotask.ui.quicklookup.QuickLookupFragment;
import com.venky.wiprotask.ui.reports.ReportsFragment;
import com.venky.wiprotask.ui.sychronization.SyncActivity;
import com.venky.wiprotask.ui.transaction.TransactionFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Venkatesh on 11,November,2019
 */
public class MainActivity extends BaseActivity implements MainMvpView,BottomNavigationView.OnNavigationItemSelectedListener{

    @Inject
    MainMvpPresenter<MainMvpView> mPresenter;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawer;
    @BindView(R.id.nav_view)
    NavigationView mNavigationView;
    private Fragment fragment = null;
    private ActionBarDrawerToggle mDrawerToggle;
    private TextView mNameTextView;
    private TextView mEmailTextView;
    private RoundedImageView mProfileImageView;
    BottomNavigationView bottomNavigationView;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(MainActivity.this);
        setUp();
    }

    @Override
    protected void setUp() {
        setSupportActionBar(mToolbar);
        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawer,
                mToolbar,
                R.string.open_drawer,
                R.string.close_drawer) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                hideKeyboard();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawer.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        loadFragment(new DashboardFragment());
        setupNavMenu();
        setBottomMenu();
        mPresenter.onNavMenuCreated();

    }

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        if (fragment != null) {
            fragmentManager
                    .beginTransaction()
                    .disallowAddToBackStack()
                    .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                    .remove(fragment)
                    .commitNow();
            unlockDrawer();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    @Override
    public void closeNavigationDrawer() {

    }

    @Override
    public void lockDrawer() {
        if (mDrawer != null)
            mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    @Override
    public void unlockDrawer() {
        if (mDrawer != null)
            mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }

    @Override
    public void updateUserName(String currentUserName) {
        mNameTextView.setText(currentUserName);
    }

    @Override
    public void updateUserEmail(String currentUserEmail) {
        mEmailTextView.setText(currentUserEmail);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Drawable drawable = item.getIcon();
        if (drawable instanceof Animatable) {
            ((Animatable) drawable).start();
        }
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            case R.id.action_notification:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void setupNavMenu(){
        View headerLayout = mNavigationView.getHeaderView(0);
        mProfileImageView = (RoundedImageView) headerLayout.findViewById(R.id.iv_profile_pic);
        mNameTextView = (TextView) headerLayout.findViewById(R.id.title);
        mEmailTextView = (TextView) headerLayout.findViewById(R.id.email);

        mNavigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        mDrawer.closeDrawer(GravityCompat.START);
                        switch (item.getItemId()) {
                            case R.id.nav_route_plan:
                             //   mPresenter.onDrawerOptionAboutClick();
                                return true;
                            case R.id.nav_start_trip:
                               // mPresenter.onDrawerRateUsClick();
                                return true;
                            case R.id.nav_meetings:
                               // mPresenter.onDrawerMyFeedClick();
                                return true;
                            case R.id.nav_sync:
                                mPresenter.openSynchronizationActivity();
                                return true;
                            case R.id.nav_logout:
                                mPresenter.onDrawerOptionLogoutClick();
                                return true;
                            default:
                                return false;
                        }
                    }
                });
    }

    public void setBottomMenu(){
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        }

    @Override
    public void navigateToSync() {
        Intent intent = SyncActivity.getStartIntent(MainActivity.this);
        startActivity(intent);
        finish();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.navigation_home:
                mToolbar.setTitle("Dashboard");
                fragment = new DashboardFragment();
                loadFragment(fragment);
                return true;
            case R.id.navigation_transaction:
                mToolbar.setTitle("Transaction");
                fragment = new TransactionFragment();
                loadFragment(fragment);
                return true;
            case R.id.navigation_quicklookup:
                mToolbar.setTitle("Quick Lookup");
                fragment = new QuickLookupFragment();
                loadFragment(fragment);
                return true;
            case R.id.navigation_account:
                mToolbar.setTitle("Account");
                fragment = new AccountFragment();
                loadFragment(fragment);
                return true;
            case R.id.navigation_reports:
                mToolbar.setTitle("Reports");
                fragment = new ReportsFragment();
                loadFragment(fragment);
                return true;
        }
        return false;
    }


    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, fragment,"Dashboard");
        transaction.addToBackStack("Dashboard");
        transaction.commit();
    }


    @Override
    public void logout() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mDrawer != null)
            mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }
}
