package com.venky.wiprotask.ui.base;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.venky.wiprotask.MyApp;
import com.venky.wiprotask.R;
import com.venky.wiprotask.di.component.ActivityComponent;
import com.venky.wiprotask.di.component.DaggerActivityComponent;
import com.venky.wiprotask.di.module.ActivityModule;
import com.venky.wiprotask.utils.CommonUtils;
import com.venky.wiprotask.utils.NetworkUtils;

import butterknife.Unbinder;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static android.content.pm.PackageManager.GET_META_DATA;

/**
 * Created by Venkatesh on 03,December,2019
 */
public abstract class BaseActivity extends AppCompatActivity implements MvpView,BaseFragment.Callback {

    private ProgressDialog mProgressDialog;

    private ActivityComponent mActivityComponent;

    private Unbinder mUnBinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        mActivityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .appComponent(((MyApp) getApplication()).getAppComponent())
                .build();
    }


    public ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void requestPermissionsSafely(String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    public boolean hasPermission(String permission) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }


    @Override
    public void showLoading() {
        hideLoading();
        mProgressDialog = CommonUtils.showLoadingDialog(this);
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }


    private void resetTitles() {
        try {
            ActivityInfo info = getPackageManager().getActivityInfo(getComponentName(), GET_META_DATA);
            if (info.labelRes != 0) {
                setTitle(info.labelRes);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void showSnackBar(String message) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),message, Snackbar.LENGTH_SHORT);
        View sbView = snackbar.getView();
        TextView textView = sbView.findViewById(R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(this, R.color.white));
        snackbar.show();
    }


    @Override
    public void openLoginActivity() {

    }

    @Override
    public void onError(int resId) {
        onError(getString(resId));
    }

    @Override
    public void onError(String message) {
        if (message != null) {
            showSnackBar(message);
        } else {
            showSnackBar(getString(R.string.some_error));
        }
    }

    @Override
    public void showMessage(String message) {
        if (message != null) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, getString(R.string.some_error), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showMessage(int resId) {
        showMessage(getString(resId));
    }

    @Override
    public boolean isNetworkConnected() {
        return NetworkUtils.isNetworkConnected(getApplicationContext());
    }

    @Override
    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    public void setUnBinder(Unbinder unBinder) {
        mUnBinder = unBinder;
    }


    @Override
    protected void onDestroy() {
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        super.onDestroy();
    }

    protected abstract void setUp();

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
