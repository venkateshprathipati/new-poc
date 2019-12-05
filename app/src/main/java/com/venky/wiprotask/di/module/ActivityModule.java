package com.venky.wiprotask.di.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.venky.wiprotask.di.ActivityContext;
import com.venky.wiprotask.ui.itemdetails.ItemDetailsAdapter;
import com.venky.wiprotask.ui.itemdetails.ItemDetailsFragment;
import com.venky.wiprotask.ui.itemdetails.ItemDetailsMvpPresenter;
import com.venky.wiprotask.ui.itemdetails.ItemDetailsMvpView;
import com.venky.wiprotask.ui.itemdetails.ItemDetailsPresenter;
import com.venky.wiprotask.ui.main.MainMvpPresenter;
import com.venky.wiprotask.ui.main.MainMvpView;
import com.venky.wiprotask.ui.main.MainPresenter;
import com.venky.wiprotask.utils.rx.AppSchedulerProvider;
import com.venky.wiprotask.utils.rx.SchedulerProvider;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Venkatesh on 03,December,2019
 */
@Module
public class ActivityModule {

    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }


    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }


    @Provides
    LinearLayoutManager provideLinearLayoutManager(AppCompatActivity activity) {
        return new LinearLayoutManager(activity);
    }

    @Provides
    ItemDetailsAdapter providesAdapter(){
        return new ItemDetailsAdapter(new ArrayList<>());
    }

    @Provides
    MainMvpPresenter<MainMvpView> provideTitles(MainPresenter<MainMvpView> presenter){
    return presenter;
    }

    @Provides
    ItemDetailsMvpPresenter<ItemDetailsMvpView> providesItem(ItemDetailsPresenter<ItemDetailsMvpView> presenter){
        return presenter;
    }

}
