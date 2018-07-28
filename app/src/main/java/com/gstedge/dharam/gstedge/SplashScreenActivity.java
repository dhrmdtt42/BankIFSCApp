package com.gstedge.dharam.gstedge;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreenActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        sharedPreferences = getSharedPreferences("signup", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();


        Intent in = null;
        if (sharedPreferences.getBoolean("logged",false)) {
            in = new Intent(SplashScreenActivity.this, DashboardActivity.class);
//            startActivity(in);
//            super.onBackPressed();
        }else if (sharedPreferences.getBoolean("logged",true)){
            in = new Intent(SplashScreenActivity.this, SignUpActivity.class);
//            startActivity(in);
//            super.onBackPressed();

        }

        startActivity(in);
        finish();

    }
}
