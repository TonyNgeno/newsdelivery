package com.veekay.newsdelivery.ui;


import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.container) ViewPager container;
    @BindView(R.id.sourcesTabLayout) TabLayout sourcesTabLayout;
    public Context mContext = this;
    private SectionsPagerAdapter mSectionsPagerAdapter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
//        allSourcesTab.
        ButterKnife.bind(this);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        container.setAdapter(mSectionsPagerAdapter);

        container.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(sourcesTabLayout));
        sourcesTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(container));


    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter{
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            if (position==0){
                return new AllSourcesFragment();
            }else if (position==1){
                return new SavedSourcesFragment();
            }
            return new AllSourcesFragment();
        }
        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
    }
}