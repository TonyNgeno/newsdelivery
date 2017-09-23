package com.veekay.newsdelivery.ui;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.veekay.newsdelivery.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    @BindView(R.id.container) ViewPager container;
    @BindView(R.id.sourcesTabLayout) TabLayout sourcesTabLayout;
    @BindView(R.id.drawer_layout) DrawerLayout drawer_layout;
//    @BindView(R.id.loginMenuText) TextView loginMenuText;
//    @BindView(R.id.logoutMenuText) TextView logoutMenuText;
    public Context mContext = this;
    private SectionsPagerAdapter mSectionsPagerAdapter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

//        logoutMenuText.setOnClickListener(this);
//        loginMenuText.setOnClickListener(this);


        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        container.setAdapter(mSectionsPagerAdapter);

        container.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(sourcesTabLayout));
        sourcesTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(container));

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.loginMenuText){
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            return true;
        }else if(id == R.id.createAccount){
            Intent intent = new Intent(getApplicationContext(), CreateAccountActivity.class);
            startActivity(intent);
            return true;
        }
        drawer_layout.closeDrawer(GravityCompat.START);
        return true;
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