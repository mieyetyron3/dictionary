package com.example.mydictionary.models;

import java.util.List;

public class MeaningModel {
    private String partOfSpeech;
    private List<DefinitionModel> definitions;
    private transient String[] synonyms;
    private transient String[] antonyms;

    public MeaningModel() {
    }

    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    public List<DefinitionModel> getDefinitions() {
        return definitions;
    }

    public String[] getSynonyms() {
        return synonyms;
    }

    public String[] getAntonyms() {
        return antonyms;
    }
}
