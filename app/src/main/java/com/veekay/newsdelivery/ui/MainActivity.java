package com.veekay.newsdelivery.ui;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.veekay.newsdelivery.MenuItemClickListener;
import com.veekay.newsdelivery.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.container) ViewPager container;
    @BindView(R.id.sourcesTabLayout) TabLayout sourcesTabLayout;

    private MenuItem loginMenuText;
    private MenuItem logoutMenuText;
    private MenuItem createAccount;
    private MenuItem feedBackMenuText;
    private MenuItem aboutMenuText;

    public Context mContext = this;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);


        mAuth = FirebaseAuth.getInstance();




        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        container.setOffscreenPageLimit(2);
        container.setCurrentItem(1);
        container.setAdapter(mSectionsPagerAdapter);

        container.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(sourcesTabLayout));
        sourcesTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(container));


        createAuthStateListener();


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.drawer_menu_layout, menu);
        MenuItemClickListener menuItemClickListener = new MenuItemClickListener(getApplicationContext(), this);

        loginMenuText = menu.findItem(R.id.loginMenuText);
        createAccount = menu.findItem(R.id.createAccount);
        logoutMenuText = menu.findItem(R.id.logoutMenuText);
        feedBackMenuText = menu.findItem(R.id.feedBackMenuText);
        aboutMenuText = menu.findItem(R.id.aboutMenuText);

        if(mAuth.getCurrentUser()==null){
            loginMenuText.setVisible(true);
            createAccount.setVisible(true);
            logoutMenuText.setVisible(false);
        }else{
            loginMenuText.setVisible(false);
            createAccount.setVisible(false);
            logoutMenuText.setVisible(true);
        }
        loginMenuText.setOnMenuItemClickListener(menuItemClickListener);
        logoutMenuText.setOnMenuItemClickListener(menuItemClickListener);
        aboutMenuText.setOnMenuItemClickListener(menuItemClickListener);
        feedBackMenuText.setOnMenuItemClickListener(menuItemClickListener);

        return true;
    }
    public void createAuthStateListener(){
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user!=null){
                    loginMenuText.setVisible(false);
                    createAccount.setVisible(false);
                    logoutMenuText.setVisible(true);
                }else{
                    loginMenuText.setVisible(true);
                    createAccount.setVisible(true);
                    logoutMenuText.setVisible(false);
                }
            }
        };
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
            return null;
        }
        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }
    }
}