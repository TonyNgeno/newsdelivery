package com.veekay.newsdelivery.ui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.veekay.newsdelivery.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateAccountActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.alreadySignedUpText) TextView alreadySignedUpText;
    @BindView(R.id.signUpButton) Button signUpButton;
    @BindView(R.id.signUpEmail) EditText signUpEmail;
    @BindView(R.id.signUpPassword) EditText signUpPassword;
    @BindView(R.id.signUpConfirmPassword) EditText signUpConfirmPassword;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        ButterKnife.bind(this);

        alreadySignedUpText.setOnClickListener(this);
        signUpButton.setOnClickListener(this);

        createAuthStateListener();
        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View view){
        if(view == alreadySignedUpText){
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        }else if(view == signUpButton){
            createNewUser();
        }
    }
    public void createAuthStateListener(){
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user!=null){
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            }
        };
    }
    public void createNewUser(){
        final String signUpEmailText = signUpEmail.getText().toString().trim();
        final String signUpPasswordText = signUpPassword.getText().toString().trim();
        final String signUpConfirmPasswordText = signUpConfirmPassword.getText().toString().trim();

        boolean validEmail = isValidEmail(signUpEmailText);
        boolean validPassword = isValidPassword(signUpPasswordText, signUpConfirmPasswordText);
        if(!validEmail || !validPassword) return;

        mAuth.createUserWithEmailAndPassword(signUpEmailText,signUpPasswordText)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(CreateAccountActivity.this, "Account Created Successfully", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(CreateAccountActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
    public boolean isValidEmail(String emailAddress){
        boolean isGoodEmail = (emailAddress!=null && Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches());
        if(!isGoodEmail){
            signUpEmail.setError("Please enter a valid Email Address");
        }
        return isGoodEmail;
    }
    public boolean isValidPassword(String password, String confirmPassword){
        if(password.length()<6){
            signUpPassword.setError("Password has to be more than 6 characters long.");
            return false;
        }else if(!password.equals(confirmPassword)){
            signUpConfirmPassword.setError("Passwords do not match");
            return false;
        }
        return true;
    }
}
