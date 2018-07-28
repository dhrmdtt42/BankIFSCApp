package com.gstedge.dharam.gstedge;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class IFSCDetailActivity extends AppCompatActivity {

    TextView ifscdetails;
    Button btn_back;
    private String Jsonresponse;
    private String url = "https://ifsc.razorpay.com/";
    private int MY_SOCKET_TIMEOUT_MS = 500;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ifscdetail);

        ifscdetails = findViewById(R.id.ifscdetail);
        btn_back = findViewById(R.id.btn_back);

        SharedPreferences prefs = getSharedPreferences("signup", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

      final   String duser = getIntent().getStringExtra("user");


        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back_in = new Intent(IFSCDetailActivity.this, DashboardActivity.class);
                back_in.putExtra("user",duser);
                startActivity(back_in);
                finish();
            }
        });

        loadIFSCData();
    }

    private void loadIFSCData() {


        String ifsc_code = getIntent().getStringExtra("code");
//        String ifsc_code = intent.getStringExtra("code");
        String json_url = url.concat(ifsc_code);
//                Uri.parse(url).buildUpon().appendQueryParameter("IFSC",ifsc_code+"").toString();

        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Loading data....");
        dialog.show();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,json_url, null,  new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                dialog.dismiss();

                try {
                    String branch = response.getString("BRANCH").toString();
                    String address = response.getString("ADDRESS").toString();
                    String contact = response.getString("CONTACT").toString();
                    String city = response.getString("CITY").toString();
                    String district = response.getString("DISTRICT").toString();
                    String state = response.getString("STATE").toString();
                    String bank = response.getString("BANK").toString();
                    String bankcode = response.getString("BANKCODE").toString();
                    String bank_ifsc = response.getString("IFSC").toString();

                    Jsonresponse = "";
                    Jsonresponse += "Branch : "+ branch +"\n\n";
                    Jsonresponse += "Address : "+ address +"\n\n";
                    Jsonresponse += "Contact : "+ contact +"\n\n";
                    Jsonresponse += "City : "+ city +"\n\n";
                    Jsonresponse += "District : "+ district +"\n\n";
                    Jsonresponse += "State : "+ state +"\n\n";
                    Jsonresponse += "Bank : "+ bank +"\n\n";
                    Jsonresponse += "BankCode : "+ bankcode +"\n\n";
                    Jsonresponse += "IFSC Code : "+ bank_ifsc +"\n\n";

                    ifscdetails.setText(Jsonresponse);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                dialog.dismiss();
                Toast.makeText(IFSCDetailActivity.this, "Error while Loading...", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });


        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy( MY_SOCKET_TIMEOUT_MS, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);
    }
}
