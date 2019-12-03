package com.venky.wiprotask.ui.base;

import com.venky.wiprotask.data.DataManager;
import com.venky.wiprotask.data.network.WrapperError;
import com.venky.wiprotask.utils.rx.SchedulerProvider;
import com.google.gson.JsonSyntaxException;

import javax.inject.Inject;
import javax.net.ssl.HttpsURLConnection;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.HttpException;

/**
 * Created by Venkatesh on 03,December,2019
 */
public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

    public static final int API_STATUS_CODE = 0;
    private final SchedulerProvider mSchedulerProvider;
    private final CompositeDisposable mCompositeDisposable;
    private final DataManager manager;
    private V mMvpView;

    @Inject
    public BasePresenter(DataManager dataManager,SchedulerProvider schedulerProvider,CompositeDisposable compositeDisposable){
        this.manager = dataManager;
        this.mSchedulerProvider = schedulerProvider;
        this.mCompositeDisposable = compositeDisposable;
    }


    @Override
    public void onAttach(V mvpView) {
        mMvpView = mvpView;
    }

    @Override
    public void onDetach() {
        mCompositeDisposable.dispose();
        mMvpView = null;
    }


    public boolean isViewAttached(){
        return mMvpView != null;
    }

    public V getMvpView(){
        return mMvpView;
    }


    public void checkViewAttached() {
        if (!isViewAttached()) throw new ViewNotAttachedException();
    }

    public DataManager getDataManager() {
        return manager;
    }

    public SchedulerProvider getSchedulerProvider() {
        return mSchedulerProvider;
    }

    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }


    @Override
    public void handleApiError(Throwable error) {
        if (error instanceof HttpException){
            switch (((HttpException)error).code()){
                case HttpsURLConnection.HTTP_UNAUTHORIZED:
                    mMvpView.onError("Unauthorised UserProfile ");
                    break;
                case HttpsURLConnection.HTTP_FORBIDDEN:
                    mMvpView.onError("Forbidden");
                    break;
                case HttpsURLConnection.HTTP_INTERNAL_ERROR:
                    mMvpView.onError("Internal Server Error");
                    break;
                case HttpsURLConnection.HTTP_BAD_REQUEST:
                    mMvpView.onError("Bad Request");
                    break;
                case API_STATUS_CODE:
                    mMvpView.onError("No Internet Connection");
                    break;
                default:
                    mMvpView.onError(error.getLocalizedMessage());
            }
        }else if (error instanceof WrapperError){
            mMvpView.onError(error.getMessage());
        } else if (error instanceof JsonSyntaxException) {
            mMvpView.onError("Something Went Wrong API is not responding properly!");
        } else {
            mMvpView.onError(error.getMessage());
        }
    }

    @Override
    public void setUserAsLoggedOut() {

    }


    public static class ViewNotAttachedException extends RuntimeException{
        public ViewNotAttachedException() {
            super("Please call Presenter.onAttach(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }
}
