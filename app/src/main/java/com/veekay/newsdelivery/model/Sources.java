package com.veekay.newsdelivery.model;
import java.util.List;

public class Sources{
    private String mId;
    private String mName;
    private String mDescription;
    private String mUrl;
    private String mCategory;
    private String mLanguage;
    private String mCountry;
    private List<String> mSortsByAvailable;

    public void Sources(String id, String name, String description, String url, String category, String language, String country, List<String> sortsByAvailable){
        mId = id;
        mName = name;
        mDescription = description;
        mUrl = url;
        mCategory = category;
        mLanguage = language;
        mCountry = country;
        mSortsByAvailable = sortsByAvailable;
    }
    public String getId(){
        return mId;
    }
    public String getName(){
        return mName;
    }
    public String getDescription(){
        return mDescription;
    }
    public String getUrl(){
        return mUrl;
    }
    public String getCategory(){
        return mCategory;
    }
    public String getLanguage(){
        return mLanguage;
    }
    public String getCountry(){
        return mCountry;
    }
    public List<String> getSortsByAvailable(){
        return mSortsByAvailable;
    }
}