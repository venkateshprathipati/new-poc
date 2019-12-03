package com.venky.wiprotask.data;

import com.venky.wiprotask.data.network.RestApiHelper;
import com.venky.wiprotask.data.prefe.PreferenceHelper;

/**
 * Created by Venkatesh on 03,December,2019
 */
public interface DataManager extends PreferenceHelper, RestApiHelper {

    void updateApiHeader(Long userId, String accessToken);



}
