package com.venky.wiprotask.data.network;

import com.venky.wiprotask.data.network.model.TitleResponseModel;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

/**
 * Created by Venkatesh on 03,December,2019
 */

@Singleton
public class RestApiManager implements RestApiHelper  {

    ApiService mService;

    @Inject
    RestApiManager(ApiService apiService){
        mService = apiService;
    }


    @Override
    public Single<TitleResponseModel> getTitleDetails() {
        return mService.getTitleDetails();
    }
}
