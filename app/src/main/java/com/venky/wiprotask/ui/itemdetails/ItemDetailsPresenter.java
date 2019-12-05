package com.venky.wiprotask.ui.itemdetails;

import android.util.Log;

import com.venky.wiprotask.data.DataManager;
import com.venky.wiprotask.ui.base.BasePresenter;
import com.venky.wiprotask.ui.base.MvpView;
import com.venky.wiprotask.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class ItemDetailsPresenter<V extends ItemDetailsMvpView> extends BasePresenter<V>
            implements ItemDetailsMvpPresenter<V>{

    private static final String TAG = "ItemDetailsPresenter";
    @Inject
    public ItemDetailsPresenter(DataManager dataManager,
                                SchedulerProvider schedulerProvider,
                                CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void loadData() {
        getMvpView().showLoading();
        getCompositeDisposable().add(getDataManager()
                .getTitleDetails()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(titleResponseModel -> {
                    if (!isViewAttached()) {
                        return;
                    }

                    getMvpView().hideLoading();
                    getMvpView().swipeToRefresh();

                    if (titleResponseModel != null && titleResponseModel.getRows() != null){
                        getMvpView().updateRows(titleResponseModel.getRows());
                        getMvpView().updateTitle(titleResponseModel.getTitle());

                    }

                },throwable -> {
                    if (!isViewAttached()) {
                        return;
                    }
                    getMvpView().hideLoading();
                    handleApiError(throwable);
                    Log.e(TAG, throwable.toString());
                    getMvpView().showMessage(throwable.toString());
                }));
    }

}
