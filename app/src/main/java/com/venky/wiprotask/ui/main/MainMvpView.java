package com.venky.wiprotask.ui.main;

import com.venky.wiprotask.data.network.model.TitleResponseModel;
import com.venky.wiprotask.ui.base.MvpView;

import java.util.List;

/**
 * Created by Venkatesh on 12,November,2019
 */
public interface MainMvpView extends MvpView {

    void closeNavigationDrawer();

    void lockDrawer();

    void unlockDrawer();
}
