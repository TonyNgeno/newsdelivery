package com.veekay.newsdelivery.model;
import java.util.ArrayList;



public class Source {
    private String mId;
    private String mName;
    private String mDescription;
    private String mUrl;
    private String mCategory;
    private String mLanguage;
    private String mCountry;
    private ArrayList<String> mSortsByAvailable = new ArrayList<>();

    public Source(){}

    public Source(String id, String name, String description, String url, String category, String language, String country, ArrayList<String> sortsByAvailable){
        this.mId = id;
        this.mName = name;
        this.mDescription = description;
        this.mUrl = url;
        this.mCategory = category;
        this.mLanguage = language;
        this.mCountry = country;
        this.mSortsByAvailable = sortsByAvailable;
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
    public ArrayList<String> getSortsByAvailable(){
        return mSortsByAvailable;
    }
}