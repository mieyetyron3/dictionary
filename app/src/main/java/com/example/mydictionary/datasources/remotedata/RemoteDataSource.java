package com.example.mydictionary.datasources.remotedata;

import android.util.Log;

import com.example.mydictionary.datasources.remotedata.retrofit.RetroInstance;
import com.example.mydictionary.datasources.remotedata.retrofit.WordService;
import com.example.mydictionary.models.WordFullModel;

import java.util.List;
import java.util.function.Consumer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RemoteDataSource {
    private static final String TAG = RemoteDataSource.class.getSimpleName();
    private static RemoteDataSource remoteDataSource;
    private Retrofit retrofit;
    private WordService wordService;

    private RemoteDataSource() { //TODO: Pass dependencies for dependency injection
        retrofit = RetroInstance.getRetroClient();
        wordService = retrofit.create(WordService.class);
    }

    public static RemoteDataSource getRemoteDataSource() {
        if(remoteDataSource == null) {
            remoteDataSource = new RemoteDataSource();
        }

        return remoteDataSource;
    }

    public void getWord(String word, Consumer<List<WordFullModel>> callback) {
        Call<List<WordFullModel>> wordFullModelListCall = wordService.getWord(word);
        wordFullModelListCall.enqueue(new Callback<List<WordFullModel>>() {
            @Override
            public void onResponse(Call<List<WordFullModel>> call, Response<List<WordFullModel>> response) {
                Log.d(TAG, "onResponse: called. Word received successfully from Retrofit");
                List<WordFullModel> wordFullModelList = response.body();
                callback.accept(wordFullModelList);
            }

            @Override
            public void onFailure(Call<List<WordFullModel>> call, Throwable t) {
                Log.d(TAG, "onFailure: called. Failure occurred receiving word from Retrofit");
                callback.accept(null);
            }
        });
    }
}
