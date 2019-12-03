package com.venky.wiprotask.data.network;

import com.venky.wiprotask.data.network.model.WrapperResponse;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by Venkatesh on 03,December,2019
 */
public class WrapperResponseConverter<T> implements Converter<ResponseBody,T> {

    private Converter<ResponseBody, WrapperResponse<T>> converter;

    public WrapperResponseConverter(Converter<ResponseBody,
            WrapperResponse<T>> converter) {
        this.converter = converter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        WrapperResponse<T> response = converter.convert(value);

        if (!response.getError()) {
            return response.getData();
        }
        // RxJava will call onError with this exception
        throw new WrapperError(response.getStatus());
    }
}
