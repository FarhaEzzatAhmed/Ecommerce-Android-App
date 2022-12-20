package com.example.finalprojectodc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Screen8 extends AppCompatActivity {
    ImageView iconBack;
    RecyclerView recyclerView;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen8);
        recyclerView = findViewById(R.id.recyclerView);
        iconBack = findViewById(R.id.imageView12);
        iconBack.setOnClickListener(v -> {
            Intent ii = new Intent(Screen8.this,Screen7.class);
            startActivity(ii);
        });
        dialog = myProgressDialog();
        dialog.show();
        getData();
    }


    private void getData() {
        Call<List<Product>> call = RetrofitClient.getInstance().getApi().getProducts();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (!response.isSuccessful()) {
                    Log.d("response", String.valueOf(response.code()));


                } else {

                    List<Product> products = response.body();
                    MyCustomAdapter2 myCustomAdapter2 = new MyCustomAdapter2(Screen8.this, products);
                    recyclerView.setAdapter(myCustomAdapter2);
                    recyclerView.setLayoutManager(new LinearLayoutManager(Screen8.this, LinearLayoutManager.VERTICAL, false));
                    //recyclerView.setLayoutManager(new GridLayoutManager(Screen8.this,2));
                    //recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
                }
                dialog.dismiss();
            }


            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.d("response", t.getMessage());
                dialog.dismiss();

            }
        });


    }private ProgressDialog myProgressDialog(){
        ProgressDialog dialog = new ProgressDialog(this);

        dialog.setTitle("Fetching API");
        dialog.setMessage("Loading Data");
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setCancelable(false);

        return dialog;
    }



}