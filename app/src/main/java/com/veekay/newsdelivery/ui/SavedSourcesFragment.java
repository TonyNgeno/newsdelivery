package com.veekay.newsdelivery.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.veekay.newsdelivery.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SavedSourcesFragment extends Fragment {


    public SavedSourcesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_saved_sources, container, false);
    }

}
