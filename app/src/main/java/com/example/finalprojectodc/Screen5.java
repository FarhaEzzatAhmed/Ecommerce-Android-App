package com.example.finalprojectodc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Screen5 extends AppCompatActivity {
Button btn;
EditText  email,password;
boolean isAllFieldsChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen5);
        email = findViewById(R.id.editTextTextEmailAddress3);
        password =findViewById(R.id.editTextTextPassword2);
        btn = findViewById(R.id.btnLogin3);
        btn.setOnClickListener(v -> {
            isAllFieldsChecked = CheckAllFields();
            if (isAllFieldsChecked) {
                Intent intent = new Intent(this, Screen6.class);
                startActivity(intent);
            }
        });
    }
    private boolean CheckAllFields() {
        if(email.length()==0){
            email.setError("this field is required");
            return false;
        }else if(password.length()==0){
            password.setError("password is require");
            return false;
        }
        return true;
    }



}