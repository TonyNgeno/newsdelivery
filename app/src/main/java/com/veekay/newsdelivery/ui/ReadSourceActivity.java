package com.veekay.newsdelivery.ui;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.veekay.newsdelivery.R;
import com.veekay.newsdelivery.model.Source;

import org.parceler.Parcels;

public class ReadSourceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_source);

        Source source = Parcels.unwrap(getIntent().getParcelableExtra("source"));
        String title = source.getName();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(title);
    }
}
