package com.veekay.newsdelivery.model;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;


@Parcel
public class Source {
    private String id;
    private String name;
    private String description;
    private String url;
    private String category;
    private String language;
    private String country;
    private List<String> sortsByAvailable = new ArrayList<>();
    private String pushId;

    public Source(){}

    public Source(String id, String name, String description, String url, String category, String language, String country, ArrayList<String> sortsByAvailable){
        this.id = id;
        this.name = name;
        this.description = description;
        this.url = url;
        this.category = category;
        this.language = language;
        this.country = country;
        this.sortsByAvailable = sortsByAvailable;
    }

    public String getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getDescription(){
        return description;
    }
    public String getUrl(){
        return url;
    }
    public String getCategory(){
        return category;
    }
    public String getLanguage(){
        return language;
    }
    public String getCountry(){
        return country;
    }
    public List<String> getSortsByAvailable(){
        return sortsByAvailable;
    }
    public void setPushId(String pushId){
        this.pushId = pushId;
    }
    public String getPushId(){
        return pushId;
    }
}