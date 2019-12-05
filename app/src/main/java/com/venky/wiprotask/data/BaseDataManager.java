package com.venky.wiprotask.data;

import android.content.Context;
import com.venky.wiprotask.data.network.RestApiHelper;
import com.venky.wiprotask.data.network.model.TitleResponseModel;
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
    private final PreferenceHelper mPreferencesHelper;
    private final RestApiHelper mApiHelper;

    @Inject
    public BaseDataManager(@AppContext Context context,
                           PreferenceHelper preferencesHelper,
                           RestApiHelper apiHelper) {
        mContext = context;
        mPreferencesHelper = preferencesHelper;
        mApiHelper = apiHelper;
    }

    @Override
    public void updateApiHeader(Long userId, String accessToken) {

    }

    @Override
    public Single<TitleResponseModel> getTitleDetails() {
        return mApiHelper.getTitleDetails();
    }
}
