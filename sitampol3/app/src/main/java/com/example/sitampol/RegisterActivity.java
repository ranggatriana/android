package com.example.sitampol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class RegisterActivity extends AppCompatActivity {
    private EditText userName, Password, Nama_pelanggan, Desa, Kecamatan, Kota, Rt, Rw;
    private Button btnRegister;
    private String username, password, nama_pelanggan, desa, kecamatan, kota, rt, rw;
    String url_register = "http://192.168.43.93/sitampol/api/register";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userName = findViewById(R.id.editTextEmail);
        Password = findViewById(R.id.editTextPassword);
        Nama_pelanggan = findViewById(R.id.editTextNamaPelanggan);
        Desa = findViewById(R.id.editTextDesa);
        Kecamatan = findViewById(R.id.editTextKecamatan);
        Kota = findViewById(R.id.editTextKota);
        Rt = findViewById(R.id.editTextrt);
        Rw = findViewById(R.id.editTextrw);
        btnRegister = findViewById(R.id.buttonRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                username = userName.getText().toString().trim();
                password = Password.getText().toString().trim();
                nama_pelanggan = Nama_pelanggan.getText().toString().trim();
                desa = Desa.getText().toString().trim();
                kecamatan = Kecamatan.getText().toString().trim();
                kota = Kota.getText().toString().trim();
                rt = Rt.getText().toString().trim();
                rw = Rw.getText().toString().trim();

                registerUser();

            }
        });
    }


    //membuat string request

    private void registerUser() {
        StringRequest request = new StringRequest(Request.Method.POST, url_register,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            System.out.println(jsonObject);
                            String response_login = jsonObject.getString("status");


                            if (response_login.equals("0")) {
                                Toast.makeText(RegisterActivity.this, "username sudah pernah dipakai", Toast.LENGTH_SHORT).show();
                            }
                            if (response_login.equals("1")) {
                                Toast.makeText(RegisterActivity.this, "registrasi gagal!", Toast.LENGTH_SHORT).show();
                            }
                            if (response_login.equals("2")) {
                                Toast.makeText(RegisterActivity.this, "Registrasi berhasil", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("nama", nama_pelanggan);
                params.put("username", username );
                params.put("password", password);
                params.put("desa", desa);
                params.put("kecamatan", kecamatan);
                params.put("kota", kota);
                params.put("rt", rt);
                params.put("rw", rw);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}

