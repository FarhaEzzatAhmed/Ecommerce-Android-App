package com.example.finalprojectodc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class CartScreen extends AppCompatActivity {
TextView receiver_msg ,reciver_price, number;
ImageView imageView,plus,minus,personicon,backicon;
    int imagevalue;
    private int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_screen);
        personicon = findViewById(R.id.person);

        personicon.setOnClickListener(v -> {
            Intent intentt = new Intent(CartScreen.this,PersonProfile.class);
            startActivity(intentt);
        });
        backicon =findViewById(R.id.backcart);
        backicon.setOnClickListener(v -> {
            Intent intent3 = new Intent (CartScreen.this,Screen7.class);
            startActivity(intent3);
        });
        plus =findViewById(R.id.plusCart2) ;
        minus =findViewById(R.id.minuscart2) ;
        number =findViewById(R.id.numberCart2) ;
imageView =findViewById(R.id.imageView21);
receiver_msg = findViewById(R.id.txtTitleCart);
reciver_price= findViewById(R.id.txtPriceCart);
Intent i = getIntent();
String str = i.getStringExtra("title");
receiver_msg.setText(str);
String str2 = i.getStringExtra("price");
reciver_price.setText(str2);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            imagevalue = bundle.getInt("image");
        }
        imageView.setImageResource(imagevalue);



    initCounter();




        plus.setOnClickListener(v -> {
            plusCounter();
        });

        minus.setOnClickListener(v -> {
            minusCounter();
        });
    }

    private void initCounter(){
        counter =1;
        number.setText(counter+"");

    }
    private  void plusCounter(){
        counter++;
        number.setText(counter+"");
    }
    private  void minusCounter(){
        counter--;
        number.setText(counter+"");
    }

}