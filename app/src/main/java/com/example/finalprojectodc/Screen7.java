package com.example.finalprojectodc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class Screen7 extends AppCompatActivity {
    RecyclerView myRecycler;
    ArrayList<ProductModel> data= new ArrayList<>();
    MyCustomAdapter adapter;

    String[] productTitle = {"New Arrivals","Clothes","Bags","Shoese","Electronics","Jewelry"};
    int[] productImag = {R.drawable.whitecart,R.drawable.shirt,R.drawable.bag,R.drawable.shose,R.drawable.electricty,R.drawable.jwlery};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen7);

                myRecycler = findViewById(R.id.mainRecyclerView);
        setupModel();

        MyCustomAdapter adapter = new MyCustomAdapter(this,data);

        myRecycler.setAdapter(adapter);
        myRecycler.setLayoutManager(new LinearLayoutManager(this));


        //


    }


//////





    private void setupModel(){
        for (int i = 0;i<productTitle.length;i++){
            data.add(new ProductModel(productTitle[i],productImag[i]));
        }
    }
}