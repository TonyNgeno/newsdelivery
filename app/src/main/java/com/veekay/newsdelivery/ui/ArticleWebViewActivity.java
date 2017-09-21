package com.veekay.newsdelivery.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.veekay.newsdelivery.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticleWebViewActivity extends AppCompatActivity {
    @BindView(R.id.articleWebView)
    WebView articleWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WebView webview = new WebView(this);
        setContentView(webview);

        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        webview.loadUrl(url);
        webview.setWebViewClient(new WebViewClient());
    }
}
