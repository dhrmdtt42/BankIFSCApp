package com.gstedge.dharam.gstedge;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Matcher;

public class SignUpActivity extends AppCompatActivity {

    EditText edit_username, edit_dob, edit_phone,edit_password,edit_cnfpassword;
    Button btn_submit;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Matcher matcher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        pref = getSharedPreferences("signup", Context.MODE_PRIVATE);
        editor = pref.edit();

        final Calendar mycalender = Calendar.getInstance();

      final   DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                mycalender.set(Calendar.YEAR, year);
                mycalender.set(Calendar.MONTH, month);
                mycalender.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel(mycalender);
            }
        };

        edit_username = findViewById(R.id.edit_username);
        edit_dob = findViewById(R.id.edit_dob);
        edit_phone = findViewById(R.id.edit_phone);
        edit_password = findViewById(R.id.edit_password);
        edit_cnfpassword = findViewById(R.id.edit_cnfpassword);

        edit_dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new DatePickerDialog(SignUpActivity.this, date, mycalender
                        .get(Calendar.YEAR), mycalender.get(Calendar.MONTH),
                        mycalender.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        btn_submit = findViewById(R.id.btn_submit);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String Username = edit_username.getText().toString();

//                ValidationUtils validation = new ValidationUtils();
//                 boolean validUser = validation.validate(Username);
                if (Username.equals("") || !Username.matches("((?=.*\\d)(?=.*[a-zA-Z]).{8,20})")){
                    Toast.makeText(SignUpActivity.this, "please enter username with atleast one character and one digit with minimum length 8 and maxlength 20 ", Toast.LENGTH_SHORT).show();
                    return;
                }


                String Dob = edit_dob.getText().toString();

                if (Dob.equals("")){
                    Toast.makeText(SignUpActivity.this, "please enter date of birth", Toast.LENGTH_SHORT).show();
                    return;
                }

                String Phone = edit_phone.getText().toString();

                if (Phone.equals("") || Phone.length()!=10){
                    Toast.makeText(SignUpActivity.this, "please enter valid 10 digit phone no", Toast.LENGTH_SHORT).show();
                    return;
                }

                String Passwrd = edit_password.getText().toString();

//                boolean validpswrd = validation.validate(Passwrd);
                if (Passwrd.equals("") ||!Passwrd.matches("((?=.*\\d)(?=.*[a-zA-Z]).{8,15})")){
                    Toast.makeText(SignUpActivity.this, "\"please enter Password with atleast one character and one digit with minimum length 8 and maxlength 15 \"", Toast.LENGTH_SHORT).show();
                    return;
                }

                String CnfPswrd = edit_cnfpassword.getText().toString();

//                boolean validCnfpswrd = validation.validate(Passwrd);
                if (CnfPswrd.equals("") || !CnfPswrd.equals(Passwrd) || !CnfPswrd.matches("((?=.*\\d)(?=.*[a-zA-Z]).{8,15})")) {
                    Toast.makeText(SignUpActivity.this, "\"please enter Confirm password with atleast one character one digit with minimum length 8 and maxlength 15 \"", Toast.LENGTH_SHORT).show();
                    return;
                }

                editor.putString("Username",null);
                editor.putString("Dob",null);
                editor.putString("Phone",null);
                editor.putString("Paswrd",null);
                editor.putString("CnfPswrd",null);

                editor.commit();
                Intent in_sgnup = new Intent(SignUpActivity.this, LoginActivity.class);
                in_sgnup.putExtra("user", Username);
                in_sgnup.putExtra("pass",Passwrd);
                startActivity(in_sgnup);
                finish();
            }


        });

//        editor.putString("Username",null);
//        editor.putString("Dob",null);
//        editor.putString("Phone",null);
//        editor.putString("Paswrd",null);
//        editor.putString("CnfPswrd",null);
//
//        editor.commit();


    }

    private void updateLabel(Calendar calendar) {
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        edit_dob.setText(sdf.format(calendar.getTime()));
    }
}
