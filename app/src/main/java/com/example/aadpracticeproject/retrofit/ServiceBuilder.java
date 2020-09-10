package com.example.aadpracticeproject.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceBuilder {

    private static final String URL_STRING = "https://gadsapi.herokuapp.com/api/";
    private static Retrofit.Builder builder = new Retrofit.Builder().baseUrl(URL_STRING)
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = builder.build();

    public static <s> s buildService(Class<s> serviceType){
        return retrofit.create(serviceType);

    }
}
