package com.example.finalprojectodc;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory;

public class DetailsScreen extends AppCompatActivity {
    TextView title,price,description,number;
    ImageView img,plus,minus;
    Button cartButton;
    private int counter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_screen);
        cartButton= findViewById(R.id.button4);

        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = title.getText().toString();
                String str2 = price.getText().toString();
                Intent i = new Intent(DetailsScreen.this,CartScreen.class);
                i.putExtra("title",str);
                i.putExtra("price",str2);
                i.putExtra("image",R.drawable.accessories);
                startActivity(i);
            }
        });

        plus =findViewById(R.id.plusCart) ;
        minus =findViewById(R.id.minuscart) ;
        number =findViewById(R.id.number) ;
        price =findViewById(R.id.price) ;
        description =findViewById(R.id.description) ;
        title =findViewById(R.id.title) ;
        img= findViewById(R.id.img);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
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