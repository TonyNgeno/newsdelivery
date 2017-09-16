package com.veekay.newsdelivery.services;

import com.veekay.newsdelivery.Constants;
import com.veekay.newsdelivery.model.Article;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class NewsArticlesService {
    public void findArticles(String sourceId, Callback callback){
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.NEWSAPI_ARTICLES_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter("source",sourceId);
        urlBuilder.addQueryParameter("sortBy","top");
        urlBuilder.addQueryParameter("apiKey",Constants.NEWSAPI_API_KEY);

        String url = urlBuilder.build().toString();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = client.newCall(request);
        call.enqueue(callback);

    }
    public ArrayList<Article> processResults(Response response){
        ArrayList<Article> articles = new ArrayList<>();
        try{
            String jsonData = response.body().string();
            if(response.isSuccessful()){
                JSONObject responseJson = new JSONObject(jsonData);
                JSONArray articleJsonObject = responseJson.getJSONArray("articles");
                for (int i = 0; i < articleJsonObject.length();i++){
                    JSONObject articleJson = articleJsonObject.getJSONObject(i);
                    String author = articleJson.getString("author");
                    String title = articleJson.getString("title");
                    String description = articleJson.getString("description");
                    String url = articleJson.getString("url");
                    String urlToImage = articleJson.getString("urlToImage");
                    String publishedAt = articleJson.getString("publishedAt");

                    Article article = new Article(author,title,description,url,urlToImage,publishedAt);
                    articles.add(article);
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return articles;
    }
}
