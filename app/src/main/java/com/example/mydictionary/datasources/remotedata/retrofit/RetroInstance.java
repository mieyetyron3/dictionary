package com.example.mydictionary.datasources.remotedata.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroInstance {
    public static String BASE_URL = "https://api.dictionaryapi.dev";
    public static Retrofit retrofit;

    public static Retrofit getRetroClient() {
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
