package com.veekay.newsdelivery.model;

import org.parceler.Parcel;

/**
 * Created by kingkong on 9/15/17.
 */

@Parcel
public class Article {
    private String mAuthor;
    private String mTitle;
    private String mDescription;
    private String mUrl;
    private String mUrlToImage;
    private String mPublishedAt;

    public Article(){}
    public Article(String author, String title, String description, String url, String urlToImage, String publishedAt){
        mAuthor = author;
        mTitle = title;
        mDescription = description;
        mUrl = url;
        mUrlToImage = urlToImage;
        mPublishedAt = publishedAt;
    }
    public String getAuthor(){
        return mAuthor;
    }
    public String getTitle(){
        return mTitle;
    }
    public String getDescription(){
        return mDescription;
    }
    public String getUrl(){
        return mUrl;
    }
    public String getUrlToImage(){
        return mUrlToImage;
    }
    public String getPublishedAT(){
        return mPublishedAt;
    }
}
