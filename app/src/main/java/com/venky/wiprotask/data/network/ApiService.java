package com.venky.wiprotask.data.network;

import com.venky.wiprotask.data.network.model.TitleResponseModel;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Venkatesh on 03,December,2019
 */
public interface ApiService {

    @GET("2iodh4vg0eortkl/facts.json")
    Single<TitleResponseModel> getTitleDetails();

}
