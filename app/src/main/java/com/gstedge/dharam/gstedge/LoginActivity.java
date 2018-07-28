package com.gstedge.dharam.gstedge;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText login, password;
    Button btn_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.login);
        password = findViewById(R.id.password);

        btn_login = findViewById(R.id.btn_login);

       final SharedPreferences sharedPreferences = getSharedPreferences("signup",Context.MODE_PRIVATE);

        final String Username = getIntent().getStringExtra("user");
        final String Paswrd = getIntent().getStringExtra("pass");
//        if (sharedPreferences.getBoolean("logged",false)) {
//            Intent in_login = new Intent(LoginActivity.this, DashboardActivity.class);
//            startActivity(in_login);
//            finish();
//        }

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String strlog = login.getText().toString();
                String strpass = password.getText().toString();

                if(strlog.equals(Username)&& strpass.equals(Paswrd)
                && strlog.matches("((?=.*\\d)(?=.*[a-zA-Z]).{8,20})") && strpass.matches("((?=.*\\d)(?=.*[a-zA-Z]).{8,15})")){
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("logged",true);
                    editor.commit();
                    Toast.makeText(LoginActivity.this, "Redirecting...", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                    intent.putExtra("user",Username);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Wrong Credentials", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }
}
