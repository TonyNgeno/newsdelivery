package com.veekay.newsdelivery.ui;


import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.veekay.newsdelivery.R;
import com.veekay.newsdelivery.services.NewsSourcesService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setIcon(R.drawable.news_icon_std);
        getSources();
    }
    public void getSources(){
        NewsSourcesService newsSourcesService = new NewsSourcesService();

    }
}