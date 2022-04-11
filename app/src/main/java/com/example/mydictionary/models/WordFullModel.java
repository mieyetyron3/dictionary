package com.example.mydictionary.models;

import java.io.Serializable;
import java.util.List;

public class WordFullModel  implements Serializable {
    private String word;
    private transient PhoneticsModel phonetics;
    private List<MeaningModel> meanings;
    private transient LicenseModel license;
    private transient String[] sourceUrls;

    public WordFullModel() {
    }

    public String getWord() {
        return word;
    }

    public PhoneticsModel getPhonetics() {
        return phonetics;
    }

    public List<MeaningModel> getMeanings() {
        return meanings;
    }

    public LicenseModel getLicense() {
        return license;
    }

    public String[] getSourceUrls() {
        return sourceUrls;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
