package com.example.finalprojectodc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class PersonProfile extends AppCompatActivity {
TextView languageTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_profile);
        languageTxt= findViewById(R.id.languageTxt);
        languageTxt.setOnClickListener(v -> {
            Intent i = new Intent(PersonProfile.this,Localization.class);
            startActivity(i);
        });

    }
}