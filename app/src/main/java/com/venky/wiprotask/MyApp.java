package com.venky.wiprotask;

import android.app.Application;

import com.venky.wiprotask.data.DataManager;
import com.venky.wiprotask.di.component.AppComponent;
import com.venky.wiprotask.di.component.DaggerAppComponent;
import com.venky.wiprotask.di.module.AppModule;
import com.venky.wiprotask.utils.AppLogger;

import javax.inject.Inject;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Venkatesh on 03,December,2019
 */
public class MyApp extends Application {

    @Inject
    DataManager mDataManager;

    @Inject
    CalligraphyConfig mCalligraphyConfig;

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();

        mAppComponent.inject(this);

        AppLogger.init();

        CalligraphyConfig.initDefault(mCalligraphyConfig);
    }


    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(AppComponent applicationComponent) {
        mAppComponent = applicationComponent;
    }
}
