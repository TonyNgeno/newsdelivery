package com.veekay.newsdelivery.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.veekay.newsdelivery.R;
import com.veekay.newsdelivery.model.Article;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArticleContentFragment extends FragmentPagerAdapter {
    public ArrayList<Article> articles = new ArrayList<>();


    public ArticleContentFragment() {
        // Required empty public constructor
    }

    @Override
    public int getCount() {
        return 0;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_article_content, container, false);
    }

    @Override
    public Fragment getItem(int position) {
        return null;
    }
}
