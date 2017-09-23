package com.veekay.newsdelivery.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.veekay.newsdelivery.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.loginEmailEditText) EditText loginEmailEditText;
    @BindView(R.id.loginPassword) EditText loginPassword;
    @BindView(R.id.noAccountText) TextView noAccountText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        noAccountText.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        if(view == noAccountText){
            Intent intent = new Intent(getApplicationContext(), CreateAccountActivity.class);
            startActivity(intent);
        }
    }
}
