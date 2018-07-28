package com.gstedge.dharam.gstedge;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DashboardActivity extends AppCompatActivity {

    TextView text_tag;
    EditText ifsc;
    Button btn_ifsc,btn_logout;

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        pref = getSharedPreferences("signup",MODE_PRIVATE);
        editor = pref.edit();



      final String name = getIntent().getStringExtra("user");
//               pref.getString("Username",null);

        text_tag = findViewById(R.id.text_tag);

        text_tag.setText("Welcome "+name+"..!!");
        ifsc = findViewById(R.id.ifsc);
        btn_ifsc = findViewById(R.id.btn_ifsc);
        btn_logout = findViewById(R.id.btn_logout);

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                SharedPreferences sharedpreferences = getSharedPreferences("signup", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.commit();
                startActivity(new Intent(DashboardActivity.this,LoginActivity.class));
                finish();
            }
        });

        btn_ifsc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ifsccode = ifsc.getText().toString();
                if(ifsccode.equals("") || ifsccode.length()>11 || ifsccode.length()<11){
                    Toast.makeText(DashboardActivity.this, "please enter valid IFSC code", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    Intent in_ifsc = new Intent(DashboardActivity.this, IFSCDetailActivity.class);
                    in_ifsc.putExtra("code", ifsccode);
                    startActivity(in_ifsc);
                    finish();
                }

            }
        });
    }
}
