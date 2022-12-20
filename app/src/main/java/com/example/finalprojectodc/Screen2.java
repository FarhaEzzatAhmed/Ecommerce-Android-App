package com.example.finalprojectodc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Screen2 extends AppCompatActivity {
Button btnLogin;
Button btnSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen2);
        btnLogin = findViewById(R.id.login1);
        btnSignUp = findViewById(R.id.signup1);

        btnLogin.setOnClickListener(v -> {

            Intent intent = new Intent(this,Screen4.class);
            startActivity(intent);
        });

        btnSignUp.setOnClickListener(v -> {

            Intent intent = new Intent(this,Screen3.class);
            startActivity(intent);
        });

    }
}