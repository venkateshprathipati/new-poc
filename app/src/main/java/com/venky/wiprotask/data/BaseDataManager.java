package com.venky.wiprotask.data;

import android.content.Context;


import com.venky.wiprotask.data.db.AppDatabase;
import com.venky.wiprotask.data.network.RestApiHelper;
import com.venky.wiprotask.data.prefe.PreferenceHelper;
import com.venky.wiprotask.di.AppContext;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

/**
 * Created by Venkatesh on 03,December,2019
 */

@Singleton
public class BaseDataManager implements DataManager {

    private static final String TAG = "BaseDataManager";


    private final Context mContext;
    private final AppDatabase mDatabase;
    private final PreferenceHelper mPreferencesHelper;
    private final RestApiHelper mApiHelper;

    @Inject
    public BaseDataManager(@AppContext Context context,
                           AppDatabase database,
                           PreferenceHelper preferencesHelper,
                           RestApiHelper apiHelper) {
        mContext = context;
        mDatabase = database;
        mPreferencesHelper = preferencesHelper;
        mApiHelper = apiHelper;
    }

    @Override
    public void updateApiHeader(Long userId, String accessToken) {

    }

}
