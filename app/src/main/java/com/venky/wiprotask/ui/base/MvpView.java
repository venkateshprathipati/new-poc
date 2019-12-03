package com.venky.wiprotask.ui.base;

import android.support.annotation.StringRes;

/**
 * Created by Venkatesh on 03,December,2019
 */
public interface MvpView {

    void showLoading();

    void hideLoading();

    void openLoginActivity();

    void onError(@StringRes int resId);

    void onError(String message);

    void showMessage(String message);

    void showMessage(@StringRes int resId);

    boolean isNetworkConnected();

    void hideKeyboard();
}
