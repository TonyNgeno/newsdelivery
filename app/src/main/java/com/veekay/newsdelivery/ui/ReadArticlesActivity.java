package com.veekay.newsdelivery.ui;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;
import com.veekay.newsdelivery.MenuItemClickListener;
import com.veekay.newsdelivery.R;
import com.veekay.newsdelivery.adapters.ArticlePagerAdapter;
import com.veekay.newsdelivery.model.Article;
import com.veekay.newsdelivery.model.Source;
import com.veekay.newsdelivery.services.NewsArticlesService;

import org.parceler.Parcels;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ReadArticlesActivity extends AppCompatActivity{
    @BindView(R.id.articleViewPager) ViewPager articleViewPager;
    private MenuItem loginMenuText;
    private MenuItem logoutMenuText;
    private MenuItem createAccount;
    private MenuItem feedBackMenuText;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    public ArrayList<Article> mArticles = new ArrayList<>();
    private ArticlePagerAdapter articlePagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_article);

        Source source = Parcels.unwrap(getIntent().getParcelableExtra("source"));
        String title = source.getName();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(title);

        ButterKnife.bind(this);


        String sourceId = source.getId();
        getArticles(sourceId);

        mAuth = FirebaseAuth.getInstance();
        createAuthStateListener();
    }

    public void getArticles(String sourceId){
        final NewsArticlesService newsArticlesService = new NewsArticlesService();
        newsArticlesService.findArticles(sourceId, new Callback(){

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                mArticles = newsArticlesService.processResults(response);
                ReadArticlesActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        articlePagerAdapter = new ArticlePagerAdapter(getSupportFragmentManager(), mArticles);
                        articleViewPager.setAdapter(articlePagerAdapter);
                        articleViewPager.setCurrentItem(0);
                    }
                });
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.drawer_menu_layout, menu);

        loginMenuText = menu.findItem(R.id.loginMenuText);
        createAccount = menu.findItem(R.id.createAccount);
        logoutMenuText = menu.findItem(R.id.logoutMenuText);
        feedBackMenuText = menu.findItem(R.id.feedBackMenuText);

        if(mAuth.getCurrentUser()==null){
            loginMenuText.setVisible(true);
            createAccount.setVisible(true);
            logoutMenuText.setVisible(false);
        }else{
            loginMenuText.setVisible(false);
            createAccount.setVisible(false);
            logoutMenuText.setVisible(true);
        }
        MenuItemClickListener menuItemClickListener = new MenuItemClickListener(getApplicationContext(), this);
        loginMenuText.setOnMenuItemClickListener(menuItemClickListener);
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
}
