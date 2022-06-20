package com.example.uasrpl;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class deskripsi extends AppCompatActivity {
    private String idPeserta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deskripsi);

        if(
                getIntent().hasExtra("id_peserta")/* &&*/
//                getIntent().hasExtra("nama")
        ){
            this.idPeserta = getIntent().getStringExtra("id_peserta");
        }
        System.out.println(idPeserta);

    }
}