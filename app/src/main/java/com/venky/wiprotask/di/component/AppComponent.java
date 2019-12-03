package com.venky.wiprotask.di.component;

import android.app.Application;
import android.content.Context;

import com.venky.wiprotask.MyApp;
import com.venky.wiprotask.data.DataManager;
import com.venky.wiprotask.di.AppContext;
import com.venky.wiprotask.di.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Venkatesh on 03,December,2019
 */

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    void inject(MyApp app);

    @AppContext
    Context context();

    Application application();

    DataManager getDataManager();

}
