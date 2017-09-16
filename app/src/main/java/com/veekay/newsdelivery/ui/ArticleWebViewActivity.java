package com.veekay.newsdelivery.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.veekay.newsdelivery.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ArticleWebViewActivity extends AppCompatActivity {
    @Bind(R.id.articleWebView)
    WebView articleWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WebView webview = new WebView(this);
        setContentView(webview);

        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        webview.loadUrl(url);
    }
}
