package com.veekay.newsdelivery.ui;


import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.veekay.newsdelivery.R;
import com.veekay.newsdelivery.adapters.SourcesListAdapter;
import com.veekay.newsdelivery.model.Source;
import com.veekay.newsdelivery.services.NewsSourcesService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    public ArrayList<Source> sources;
    private SourcesListAdapter sourcesListAdapter;
    @Bind(R.id.newsSourceRecyclerView) RecyclerView newsSourceRecyclerView;
    public Context mContext = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getSources();
    }
    public void getSources(){
        final NewsSourcesService newsSourcesService = new NewsSourcesService();
        newsSourcesService.findSources(new Callback(){

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                sources = newsSourcesService.processResults(response);

                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        sourcesListAdapter = new SourcesListAdapter(mContext, sources);
                        newsSourceRecyclerView.setAdapter(sourcesListAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
                        newsSourceRecyclerView.setLayoutManager(layoutManager);
                        newsSourceRecyclerView.setHasFixedSize(false);
                    }
                });
            }
        });

    }
}