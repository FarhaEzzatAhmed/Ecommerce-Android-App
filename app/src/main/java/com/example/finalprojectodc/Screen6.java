package com.example.finalprojectodc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Screen6 extends AppCompatActivity {
Button btnSucess;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen6);

        btnSucess=findViewById(R.id.btnSucess);
        btnSucess.setOnClickListener(v -> {
            Intent intent = new Intent(this, Screen7.class);
            startActivity(intent);
        });

    }
}