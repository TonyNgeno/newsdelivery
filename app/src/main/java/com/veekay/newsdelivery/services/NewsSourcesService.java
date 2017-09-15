package com.veekay.newsdelivery.services;

import com.veekay.newsdelivery.Constants;
import com.veekay.newsdelivery.model.Source;

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

/**
 * Created by kingkong on 9/15/17.
 */

public class NewsSourcesService {
    public void findSources(Callback callback){
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.NEWSAPI_SOURCES_BASE_URL).newBuilder();

        String url = urlBuilder.build().toString();

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = client.newCall(request);
        call.enqueue(callback);
    }
    public ArrayList<Source> processResults(Response response){
        ArrayList<Source> sources = new ArrayList<>();

        try{
            String jsonData = response.body().string();
            if(response.isSuccessful()){
                JSONObject sourcesJsonObject = new JSONObject(jsonData);
                JSONArray sourcesJson = sourcesJsonObject.getJSONArray("sources");
                for (int i = 0; i < sourcesJson.length(); i++){
                    JSONObject sourceJson = sourcesJson.getJSONObject(i);
                    String id = sourceJson.getString("id");
                    String name = sourceJson.getString("name");
                    String description = sourceJson.getString("description");
                    String url = sourceJson.getString("url");
                    String category = sourceJson.getString("category");//{}
                    String language = sourceJson.getString("language");
                    String country = sourceJson.getString("country");
                    ArrayList<String> sortsByAvailable = new ArrayList<>();

                    JSONArray sortsJsonArray = sourceJson.getJSONArray("sortBysAvailable");
                    if (sortsJsonArray!= null) {
                        for(int z =0; z < sortsJsonArray.length(); z++){
                            sortsByAvailable.add(sortsJsonArray.getString(z));
                        }
                    }
                    Source source = new Source(id,name,description,url,category,language,country,sortsByAvailable);
                    sources.add(source);
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sources;
    }
}
