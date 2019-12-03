package com.venky.wiprotask.di.component;

import com.venky.wiprotask.di.PerActivity;
import com.venky.wiprotask.di.module.ActivityModule;
import com.venky.wiprotask.ui.main.MainActivity;

import dagger.Component;

/**
 * Created by Venkatesh on 03,December,2019
 */

@PerActivity
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);



}
