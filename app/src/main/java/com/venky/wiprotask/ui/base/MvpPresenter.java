package com.venky.wiprotask.ui.base;

/**
 * Created by Venkatesh on 03,December,2019
 */
public interface MvpPresenter<V extends MvpView> {

    void onAttach(V mvpView);

    void onDetach();

    void handleApiError(Throwable error);

    void setUserAsLoggedOut();
}
