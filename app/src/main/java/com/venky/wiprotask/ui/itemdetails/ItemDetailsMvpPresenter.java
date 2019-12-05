package com.venky.wiprotask.ui.itemdetails;

import com.venky.wiprotask.di.PerActivity;
import com.venky.wiprotask.ui.base.MvpPresenter;

@PerActivity
public interface ItemDetailsMvpPresenter<V extends ItemDetailsMvpView> extends MvpPresenter<V> {

    void loadData();
}
