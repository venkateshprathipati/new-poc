package com.venky.wiprotask.data.network;

import com.venky.wiprotask.data.network.model.TitleResponseModel;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by Venkatesh on 03,December,2019
 */
public interface RestApiHelper {

    Single<TitleResponseModel> getTitleDetails();
}
