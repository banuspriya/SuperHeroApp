package com.superhero.myapplication.restapi;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public static final String API_BASE_URL = "https://superheroapi.com/api/298977098320698/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient(){

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        if(retrofit==null)
        {
            retrofit = new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .client(httpClient.build())
                    .addConverterFactory( GsonConverterFactory.create())
                    .build();

        }
        return  retrofit;

    }

    public static Api getInterface(){
        Api userservice = getClient().create(Api.class);
        return userservice;

    }
}
