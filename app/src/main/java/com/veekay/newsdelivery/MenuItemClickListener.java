package com.veekay.newsdelivery;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.veekay.newsdelivery.ui.AboutActivity;
import com.veekay.newsdelivery.ui.CreateAccountActivity;
import com.veekay.newsdelivery.ui.FeedbackActivity;
import com.veekay.newsdelivery.ui.LoginActivity;

/**
 * Created by kingkong on 9/25/17.
 */

public class MenuItemClickListener implements MenuItem.OnMenuItemClickListener {
    private Context mContext;
    private Activity mActivity;


    public MenuItemClickListener(Context context, Activity activity){
        mContext = context;
        mActivity = activity;
    }


    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        int id = menuItem.getItemId();
        if(id == R.id.loginMenuText){
            Intent intent = new Intent(mContext, LoginActivity.class);
            mContext.startActivity(intent);
        }else if(id == R.id.logoutMenuText){
            logout();
        }else if(id == R.id.createAccount){
            createAccount();
        }else if(id == R.id.aboutMenuText){
            openAboutPage();
        }else if(id == R.id.feedBackMenuText){
            giveFeedBack();
        }
        return true;
    }
    public void logout(){

        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(mContext, mActivity.getClass());
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        mContext.startActivity(intent);
    }
    public void createAccount(){
        Intent intent = new Intent(mContext, CreateAccountActivity.class);
        mContext.startActivity(intent);
    }
    public void openAboutPage(){
        Intent intent = new Intent(mContext, AboutActivity.class);
        mContext.startActivity(intent);
    }
    public void giveFeedBack(){
        Intent intent = new Intent(mContext, FeedbackActivity.class);
        mContext.startActivity(intent);
    }
}
