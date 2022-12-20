package com.example.finalprojectodc;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory;

import java.util.concurrent.atomic.AtomicReference;

public class ElectronicDetails extends AppCompatActivity {

    TextView title,price,description,number;
    ImageView img,plus,minus;
    Button cartButton;
    private int counter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_screen);
        cartButton = findViewById(R.id.cartButton);
        plus = findViewById(R.id.plusCart);
        minus = findViewById(R.id.minuscart);
        number = findViewById(R.id.number);
        price = findViewById(R.id.price);
        description = findViewById(R.id.description);
        title = findViewById(R.id.title);
        img = findViewById(R.id.img);
        AtomicReference<Intent> intent = new AtomicReference<>(getIntent());
        Bundle bundle = intent.get().getExtras();
        title.setText(bundle.getString("title"));
        price.setText(bundle.getString("price"));
        description.setText(bundle.getString("description"));
        Glide.with(this)
                .load(bundle.getString("img"))
                .transition(withCrossFade(new DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()))
                .into(img);




        initCounter();

        plus.setOnClickListener(v -> {
            plusCounter();
        });

        minus.setOnClickListener(v -> {
            minusCounter();
        });
    }
    private void initCounter(){
        counter =0;
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

