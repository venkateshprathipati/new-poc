package com.venky.wiprotask.di.module;

import android.app.Application;
import android.content.Context;

import com.venky.wiprotask.BuildConfig;
import com.venky.wiprotask.R;
import com.venky.wiprotask.data.BaseDataManager;
import com.venky.wiprotask.data.DataManager;
import com.venky.wiprotask.data.network.ApiService;
import com.venky.wiprotask.data.network.RestApiHelper;
import com.venky.wiprotask.data.network.RestApiManager;
import com.venky.wiprotask.data.prefe.PreferenceHelper;
import com.venky.wiprotask.data.prefe.PreferencesManager;
import com.venky.wiprotask.di.ApiInfo;
import com.venky.wiprotask.di.AppContext;
import com.venky.wiprotask.di.DatabaseInfo;
import com.venky.wiprotask.di.PreferenceInfo;
import com.venky.wiprotask.utils.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Venkatesh on 03,December,2019
 */
@Module
public class AppModule {

    private Application mApplication;

    public AppModule(Application application) {
        this.mApplication = application;
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
    @PreferenceInfo
    String providePreferenceName() {
        return Constants.PREF_NAME;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(BaseDataManager mDataManager) {
        return mDataManager;
    }

    @Provides
    @Singleton
    PreferenceHelper providePreferencesHelper(PreferencesManager preferencesManager) {
        return preferencesManager;
    }

    @Provides
    @Singleton
    RestApiHelper provideRestApiHelper(RestApiManager restApiManager) {
        return restApiManager;
    }


    /**
     * @return HTTTP Client
     */
    @Provides
    @Singleton
    public OkHttpClient provideClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        return new OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor(chain -> {
            Request request = chain.request();
            return chain.proceed(request);
        }).build();
    }


    /**
     * provide Retrofit instances
     *
     * @param baseURL base url for api calling
     * @param client  OkHttp client
     * @return Retrofit instances
     */

    @Provides
    @Singleton
    public Retrofit provideRetrofit(String baseURL, OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(baseURL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    /**
     * Provide Api service
     *
     * @return ApiService instances
     */
    @Provides
    @Singleton
    public ApiService provideApiService() {
        return provideRetrofit(BuildConfig.BASE_URL, provideClient()).create(ApiService.class);
    }


    @Provides
    @Singleton
    CalligraphyConfig provideCalligraphyDefaultConfig(){
        return new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/source-sans-pro/SourceSansPro-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build();
    }

}
