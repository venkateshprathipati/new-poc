package com.venky.wiprotask.ui.main;

import com.venky.wiprotask.data.DataManager;
import com.venky.wiprotask.ui.base.BasePresenter;
import com.venky.wiprotask.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Venkatesh on 12,November,2019
 */
public class MainPresenter<V extends MainMvpView> extends BasePresenter<V> implements MainMvpPresenter<V>{

    private static final String TAG = "MainPresenter";

    @Inject
    public MainPresenter(DataManager dataManager,
                         SchedulerProvider schedulerProvider,
                         CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }


    @Override
    public void onNavMenuCreated() {
        if (!isViewAttached()) {
            return;
        }

        final String currentUserName = getDataManager().getUserName();
//        Log.e(TAG,getDataManager().getUserName());
        if (currentUserName != null && !currentUserName.isEmpty()) {
            getMvpView().updateUserName(currentUserName);
        }

        final String currentUserEmail = getDataManager().getUserEmail();
       // Log.e(TAG,getDataManager().getUserEmail());
        if (currentUserEmail != null && !currentUserEmail.isEmpty()) {
            getMvpView().updateUserEmail(currentUserEmail);
        }

    }

    @Override
    public void openSynchronizationActivity() {
        getMvpView().navigateToSync();
    }

    @Override
    public void onDrawerOptionLogoutClick() {

    }
}
