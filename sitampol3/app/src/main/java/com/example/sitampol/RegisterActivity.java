package com.example.sitampol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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
    String url_register = "http://192.168.43.252/sitampol/api/register";
    private static final String KEY_NAMA_USER = "nama_pelanggan";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_DESA = "desa";
    private static final String KEY_KECAMATAN = "kecamatan";
    private static final String KEY_KOTA = "kota";
    private static final String KEY_RT = "rt";
    private static final String KEY_RW = "rw";


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

                if (validateInputs()) {
                    registerUser();
                }
            }
        });
    }

    private boolean validateInputs() {
        //first we will do the validations
        if (TextUtils.isEmpty(username)) {
            userName.setError("Masukkan username Anda !");
            userName.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(password)) {
            Password.setError("Masukkan password Anda !");
            Password.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(nama_pelanggan)) {
            Nama_pelanggan.setError("Masukkan nama Anda !");
            Nama_pelanggan.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(desa)) {
            Desa.setError("Masukkan desa Anda !");
            Desa.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(kecamatan)) {
            Kecamatan.setError("Masukkan kecamatan Anda !");
            Kecamatan.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(kota)) {
            Kota.setError("Masukkan kota Anda !");
            Kota.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(rt)) {
            Rt.setError("Masukkan rt Anda !");
            Rt.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(rw)) {
            Rw.setError("Masukkan rw Anda !");
            Rw.requestFocus();
            return false;
        } else {
            return true;
        }
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
                            String response_login = jsonObject.getString("kode");


                            if (response_login.equals("1")) {
                                Toast.makeText(RegisterActivity.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                            }
                            if (response_login.equals("2")) {
                                Toast.makeText(RegisterActivity.this, "Password Anda Salah!", Toast.LENGTH_SHORT).show();
                            }
                            if (response_login.equals("3")) {
                                Toast.makeText(RegisterActivity.this, "Anda Belum Mendaftar, Silahkan Daftar dulu!", Toast.LENGTH_SHORT).show();
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

