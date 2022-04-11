package com.example.mydictionary.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mydictionary.models.WordFullModel;
import com.example.mydictionary.repositories.WordRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class SharedViewModel extends AndroidViewModel {
    private static final String TAG = SharedViewModel.class.getSimpleName();
    private WordRepository wordRepository;
    private MutableLiveData<List<WordFullModel>> wordFullModelListMutableLiveData;
    private String currentWord;
    private boolean clicked;
    private MutableLiveData<String> deleteWordMutableLiveData;
    private List<WordFullModel> sharedWordList;

    public SharedViewModel(@NonNull Application application) {
        super(application);
        this.wordRepository = WordRepository.getWordRepository(getApplication());
        this.wordFullModelListMutableLiveData = new MutableLiveData<>();
        currentWord = "";
        this.clicked = false;
        this.deleteWordMutableLiveData = new MutableLiveData<>();
        this.sharedWordList = new ArrayList<>();
    }

    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    public void setCurrentWord(String currentWord) {
        this.currentWord = currentWord;
    }

    public List<WordFullModel> getSharedWordList() {
        return sharedWordList;
    }

    public void setSharedWordList(List<WordFullModel> sharedWordList) {
        this.sharedWordList = sharedWordList;
    }

    public String getCurrentWord() {
        return currentWord;
    }


    public LiveData<List<WordFullModel>> getWordFullModelListMutableLiveData() {
        return wordFullModelListMutableLiveData;
    }

    public MutableLiveData<String> getDeleteWordMutableLiveData() {
        return deleteWordMutableLiveData;
    }

    public void getWord(String word) {
        wordRepository.getWord(word, new Consumer<List<WordFullModel>>() {
            @Override
            public void accept(List<WordFullModel> wordFullModels) {
                if(wordFullModels != null) {
                    //Set live data
                    wordFullModelListMutableLiveData.postValue(wordFullModels);
                    Log.d(TAG, "accept: called. Word successfully received from WordRepository: "+ wordFullModels.toString());
                }
                else {
                    //Report error
                    Log.d(TAG, "accept: called. Failure occurred when receiving Word from WordRepository");
                    currentWord = word;
                    wordFullModelListMutableLiveData.postValue(null);//TODO: This line can be merged
                }
            }
        });
    }

    public void deleteWord(String word) {
        deleteWordMutableLiveData.postValue(word);
    }
}
