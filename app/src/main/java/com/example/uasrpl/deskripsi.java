package com.example.uasrpl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.uasrpl.Config.Link;
import com.example.uasrpl.Dto.PesertaDto;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class deskripsi extends AppCompatActivity {
    private String idPeserta,idEvent,gambar,judul,deskripsi,pembicara,jammulai,selesai;
    ImageView foto;
    TextView jdl,dskrps,pmbcr,jmml,jmsls;
    private  Button buttondaftar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deskripsi);
        buttondaftar = findViewById(R.id.buttondaftar);
        buttondaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             button();
            }
        });


        if(

                getIntent().hasExtra("id_peserta")&&
                getIntent().hasExtra("id_event") &&
                        getIntent().hasExtra("gambar")&&
                        getIntent().hasExtra("judul")&&
                        getIntent().hasExtra("Deskripsi")&&
                        getIntent().hasExtra("pembicara")&&
                        getIntent().hasExtra("jammulai")&&
                        getIntent().hasExtra("selesai")

        ){
            this.idPeserta = getIntent().getStringExtra("id_peserta");
            this.idEvent = getIntent().getStringExtra("id_event");
            this.gambar = getIntent().getStringExtra("gambar");
            this.judul = getIntent().getStringExtra("judul");
            this.deskripsi = getIntent().getStringExtra("Deskripsi");
            this.pembicara = getIntent().getStringExtra("Pembicara");
            this.jammulai = getIntent().getStringExtra("jammulai");
            this.selesai = getIntent().getStringExtra("selesai");
        }

        foto = findViewById(R.id.imageEvent);
        Glide.with(this).load(gambar).into(foto);
        jdl = findViewById(R.id.judul);
        dskrps = findViewById(R.id.deskripsi);
        pmbcr = findViewById(R.id.pembicara);
        jmml = findViewById(R.id.jammulai);
        jmsls = findViewById(R.id.jamselesai);
        jdl.setText(judul);
        dskrps.setText(deskripsi);
        pmbcr.setText(pembicara);
        jmml.setText(jammulai);
        jmsls.setText(selesai);


    }
    public void button() {
        Link link = new Link();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, link.getEvent(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                            Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();
                        }
                    }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        })  {
            @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            // post data
            Map<String, String> params = new HashMap<String, String>();
            params.put("id_peserta",idPeserta);
            params.put("id_event",idEvent);
            return params;
        }
    };
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }
}