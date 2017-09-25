package com.veekay.newsdelivery.model;

import org.parceler.Parcel;

import java.time.ZonedDateTime;

/**
 * Created by kingkong on 9/15/17.
 */

@Parcel
public class Article {
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;
    private String pushId;

    public Article(){}
    public Article(String author, String title, String description, String url, String urlToImage, String publishedAt){
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
    }
    public String getAuthor(){
        return author;
    }
    public String getTitle(){
        return title;
    }
    public String getDescription(){
        return description;
    }
    public String getUrl(){
        return url;
    }
    public String getUrlToImage(){
        return urlToImage;
    }
    public String getPublishedAT(){
        return publishedAt;
    }
    public void setPushId(String pushId){
        this.pushId = pushId;
    }
    public String getPushId(){
        return pushId;
    }
}
