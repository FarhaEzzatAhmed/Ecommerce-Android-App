package com.example.finalprojectodc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class Localization extends AppCompatActivity {
    CardView Arabic, English;
    TextView selectLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localization);
        Arabic = findViewById(R.id.arbtnnn);
        English = findViewById(R.id.enbtnnn);
        selectLanguage= findViewById(R.id.selectLanguage);

        Arabic.setOnClickListener(v -> {
            setLanguage("ar");
            Arabic.setBackgroundColor(Color.BLACK);

        });

        English.setOnClickListener(v -> {
            setLanguage("en");
            English.setBackgroundColor(Color.BLACK);

        });


    }


    private void setLanguage(String lang) {

        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);

        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
        Intent intent = getIntent();
        finish();

        startActivity(intent);


    }
}