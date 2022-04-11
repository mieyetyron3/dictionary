package com.example.mydictionary.repositories;

import android.app.Application;
import android.util.Log;

import com.example.mydictionary.datasources.remotedata.RemoteDataSource;
import com.example.mydictionary.models.WordFullModel;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class WordRepository {
    private static final String TAG = WordRepository.class.getSimpleName();
    private static WordRepository wordRepository;
    private RemoteDataSource remoteDataSource;
    private Executor executor;
    //TODO: private LocalDataSource localDataSource;

    private WordRepository(Application application) {
        this.remoteDataSource = RemoteDataSource.getRemoteDataSource();
        this.executor = Executors.newSingleThreadExecutor();
    }

    public static WordRepository getWordRepository(Application application) {
        if(wordRepository == null) {
            wordRepository = new WordRepository(application);
        }

        return wordRepository;
    }

    public void getWord(String word, Consumer<List<WordFullModel>> callback) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                remoteDataSource.getWord(word, new Consumer<List<WordFullModel>>() {
                    @Override
                    public void accept(List<WordFullModel> wordFullModels) {
                        Log.d(TAG, "accept: called. Word received from RemoteDataSource");
                        callback.accept(wordFullModels);
                        //TODO: Write to local database if non null
                        //addProducts(productFullModel.getProducts());
                    }
                });
            }
        });
    }
}
