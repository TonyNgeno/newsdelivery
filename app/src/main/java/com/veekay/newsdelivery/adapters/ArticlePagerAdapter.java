package com.veekay.newsdelivery.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.veekay.newsdelivery.model.Article;
import com.veekay.newsdelivery.ui.ArticleContentFragment;

import java.util.ArrayList;

/**
 * Created by kingkong on 9/15/17.
 */

public class ArticlePagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Article> mArticles;

    public ArticlePagerAdapter(FragmentManager fragmentManager, ArrayList<Article> articles){
        super(fragmentManager);
        mArticles = articles;
    }

    @Override
    public Fragment getItem(int position) {
        return ArticleContentFragment.newInstance(mArticles.get(position));
    }

    @Override
    public int getCount() {
        return mArticles.size();
    }
    @Override
    public CharSequence getPageTitle(int position){
        String title = mArticles.get(position).getAuthor();
        if(title.equals("null")| title.contains("http")|title ==""){
            return "by: Team Member";
        }else{
            return "by: "+ mArticles.get(position).getAuthor();
        }

    }
}
