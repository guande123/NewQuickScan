package com.gdz.demo.gdnews.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.gdz.demo.gdnews.R;
import com.gdz.demo.gdnews.utils.SPUtils;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Log.i("WelcomeActivity", "onCreate: " );
        if(SPUtils.isFirstRun(WelcomeActivity.this)){
            startActivity(new Intent(WelcomeActivity.this,GuideActivity.class));
            finish();
        }else{
           new Handler().postDelayed(new Runnable() {
               @Override
               public void run() {
                   startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
                   finish();
               }
           },2000);
        }
    }
}
