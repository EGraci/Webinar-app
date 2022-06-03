package com.example.uasrpl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.uasrpl.Config.Link;
import com.example.uasrpl.Dto.PesertaDto;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {
    private TextInputLayout user, pass;
    private Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user = (TextInputLayout) findViewById(R.id.txEmail);
        pass = (TextInputLayout) findViewById(R.id.txPassword);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button();
            }
        });
    }
        public void button() {
            Link link = new Link();
            StringRequest stringRequest = new StringRequest(Request.Method.GET, link.getPeserta()+"?username="+user.getEditText().getText().toString()+"&password="+pass.getEditText().getText().toString(),
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d("json", response);
                            if(response.equals("null") || response.equals(null)){
                                Toast.makeText(getApplicationContext(),"Username dan Password Salah",Toast.LENGTH_SHORT).show();
                            }else{
                                PesertaDto data = new PesertaDto();
                                try {
                                    JSONObject raw = new JSONObject(response);
                                    data.setNama(raw.getString("nama_lengkap"));
                                    data.setId(raw.getString("id_peserta"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                Intent intent = new Intent(Login.this, MainActivity.class);
                                intent.putExtra("id", data.getId());
                                intent.putExtra("nama", data.getNama());
                                startActivity(intent);
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                }
            });
            RequestQueue queue = Volley.newRequestQueue(this);
            queue.add(stringRequest);
        }
    }