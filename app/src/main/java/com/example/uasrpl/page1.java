package com.example.uasrpl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class page1 extends AppCompatActivity {

    private Button btnDaftar;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page1);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnDaftar = (Button) findViewById(R.id.btnDaftar);
        btnLogin.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            page1();
        }
    });

        btnDaftar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            daftar();
        }
    });
}
    public void page1() {
        Intent intent = new Intent(page1.this, Login.class);
        startActivity(intent);
    }

    public void daftar() {
        Intent intent = new Intent(page1.this, register.class);
        startActivity(intent);
    }
}