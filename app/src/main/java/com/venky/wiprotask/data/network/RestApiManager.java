package com.venky.wiprotask.data.network;

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


}
