package com.veekay.newsdelivery.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.veekay.newsdelivery.R;
import com.veekay.newsdelivery.adapters.SourcesListAdapter;
import com.veekay.newsdelivery.model.Source;
import com.veekay.newsdelivery.services.NewsSourcesService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllSourcesFragment extends Fragment {
    public ArrayList<Source> sources;
    private SourcesListAdapter sourcesListAdapter;
    @BindView(R.id.newsSourceRecyclerView) RecyclerView newsSourceRecyclerView;


    public AllSourcesFragment (){

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        getSources();
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_all_sources, container, false);
        ButterKnife.bind(this, view);
        return view;
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

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        sourcesListAdapter = new SourcesListAdapter(getContext(), sources);
                        newsSourceRecyclerView.setAdapter(sourcesListAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                        newsSourceRecyclerView.setLayoutManager(layoutManager);
                        newsSourceRecyclerView.setHasFixedSize(false);
                    }
                });
            }
        });

    }

}
