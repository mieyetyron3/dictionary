package com.example.mydictionary.models;

public class DefinitionModel {
    private String definition;
    private transient String[] synonyms;
    private transient String[] antonyms;

    public DefinitionModel() {
    }

    public String getDefinition() {
        return definition;
    }

    public String[] getSynonyms() {
        return synonyms;
    }

    public String[] getAntonyms() {
        return antonyms;
    }
}
