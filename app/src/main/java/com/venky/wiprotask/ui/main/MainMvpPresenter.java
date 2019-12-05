package com.venky.wiprotask.ui.main;

import com.venky.wiprotask.di.PerActivity;
import com.venky.wiprotask.ui.base.MvpPresenter;

/**
 * Created by Venkatesh on 12,November,2019
 */

@PerActivity
public interface MainMvpPresenter<V extends MainMvpView> extends MvpPresenter<V> {

    void onNavMenuCreated();
}
