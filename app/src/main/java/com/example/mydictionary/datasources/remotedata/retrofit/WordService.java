package com.example.mydictionary.datasources.remotedata.retrofit;

import com.example.mydictionary.models.WordFullModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WordService {
    @GET("/api/v2/entries/en/{word}")
    Call<List<WordFullModel>> getWord(@Path("word") String word);
}
