package com.venky.wiprotask.data.network;

import com.venky.wiprotask.data.network.model.WrapperResponse;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Venkatesh on 03,December,2019
 */
public class WrapperConverterFactory extends Converter.Factory  {

    private GsonConverterFactory factory;

    public WrapperConverterFactory(GsonConverterFactory factory) {
        this.factory = factory;
    }


    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        // e.g. WrappedResponse<Person>
        Type wrappedType = new ParameterizedType() {
            @Override
            public Type[] getActualTypeArguments() {
                // -> WrappedResponse<type>
                return new Type[]{type};
            }

            @Override
            public Type getOwnerType() {
                return null;
            }

            @Override
            public Type getRawType() {
                return WrapperResponse.class;
            }
        };
        Converter<ResponseBody, ?> gsonConverter = factory
                .responseBodyConverter(wrappedType, annotations, retrofit);
        return new WrapperResponseConverter(gsonConverter);
    }
}
