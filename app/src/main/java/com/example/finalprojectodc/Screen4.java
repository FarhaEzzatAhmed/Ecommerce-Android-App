package com.example.finalprojectodc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Screen4 extends AppCompatActivity {
Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen4);
        login.findViewById(R.id.button2);
        login.setOnClickListener(v -> {
            Intent i = new Intent(Screen4.this,Screen7.class);
            startActivity(i);
        });

    }
}