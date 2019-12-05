/*
 * Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://mindorks.com/license/apache-v2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.venky.wiprotask.di.module;

import android.app.Application;
import android.content.Context;

import com.venky.wiprotask.BuildConfig;
import com.venky.wiprotask.R;
import com.venky.wiprotask.data.BaseDataManager;
import com.venky.wiprotask.data.DataManager;
import com.venky.wiprotask.data.network.RestApiHelper;
import com.venky.wiprotask.data.prefe.PreferenceHelper;
import com.venky.wiprotask.di.ApiInfo;
import com.venky.wiprotask.di.AppContext;
import com.venky.wiprotask.di.DatabaseInfo;
import com.venky.wiprotask.di.PreferenceInfo;
import com.venky.wiprotask.utils.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


@Module
public class ApplicationTestModule {

    private final Application mApplication;

    public ApplicationTestModule(Application application) {
        mApplication = application;
    }

    @Provides
    @AppContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return Constants.DB_NAME;
    }

    @Provides
    @ApiInfo
    String provideApiKey() {
        return BuildConfig.BASE_URL;
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return Constants.PREF_NAME;
    }

    // TODO : Mock all below for UI testing

    @Provides
    @Singleton
    DataManager provideDataManager(BaseDataManager baseDataManager) {
        return baseDataManager;
    }

    @Provides
    @Singleton
    PreferenceHelper providePreferencesHelper(PreferenceHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

    @Provides
    @Singleton
    RestApiHelper provideRestApiHelper(RestApiHelper restApiHelper) {
        return restApiHelper;
    }

    @Provides
    @Singleton
    CalligraphyConfig provideCalligraphyDefaultConfig() {
        return new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/source-sans-pro/SourceSansPro-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build();
    }
}
