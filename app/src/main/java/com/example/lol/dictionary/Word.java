package com.example.lol.dictionary;

/**
 * Created by Windows on 01.02.2017.
 */
public class Word {

    Word(){}

    private String languageOne;
    private String languageTwo;
    private String languageThree;

    public Word (String languageOne, String languageTwo, String languageThree){
        this.languageOne = languageOne;
        this.languageTwo = languageTwo;
        this.languageThree = languageThree;
    }

    public String getLanguageOne() {
        return languageOne;
    }

    public void setLanguageOne(String languageOne) {
        this.languageOne = languageOne;
    }

    public String getLanguageTwo() {
        return languageTwo;
    }

    public void setLanguageTwo(String languageTwo) {
        this.languageTwo = languageTwo;
    }

    public String getLanguageThree() {
        return languageThree;
    }

    public void setLanguageThree(String languageThree) {
        this.languageThree = languageThree;
    }


}
