package com.example.sitampol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private EditText username,password;
    private Button btnLoggin;
    private TextView bikinakun;

    String url_login = "http://192.168.43.93/sitampol/api/login";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.editTextEmail);
        password = findViewById(R.id.editTextPassword);
        btnLoggin = findViewById(R.id.buttonLogin);
        bikinakun = findViewById(R.id.bikinakun);

        btnLoggin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });

        bikinakun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }



    private void Login(){
        final String username_nya = username.getText().toString().trim();
        final String password_nya = password.getText().toString().trim();
        //membuat string request

        StringRequest request = new StringRequest(Request.Method.POST, url_login,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            System.out.println(response);
                            String response_login = jsonObject.getString("kode");


                            if(response_login.equals("1")){
                                Toast.makeText(LoginActivity.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                            }
                            if(response_login.equals("2")){
                                Toast.makeText(LoginActivity.this,"Password Anda Salah!",Toast.LENGTH_SHORT).show();
                            }
                            if(response_login.equals("3")){
                                Toast.makeText(LoginActivity.this,"Anda Belum Mendaftar, Silahkan Daftar dulu!",Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })
        {
            @Override
            protected Map<String, String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("username",username_nya);
                params.put("password",password_nya);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}


