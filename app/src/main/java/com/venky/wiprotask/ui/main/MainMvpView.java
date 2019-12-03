package com.venky.wiprotask.ui.main;

import com.venky.wiprotask.ui.base.MvpView;

/**
 * Created by Venkatesh on 12,November,2019
 */
public interface MainMvpView extends MvpView {

    void openLoginActivity();

    void closeNavigationDrawer();

    void lockDrawer();

    void unlockDrawer();

    void updateUserName(String currentUserName);

    void updateUserEmail(String currentUserEmail);

    void navigateToSync();

    void logout();

}
