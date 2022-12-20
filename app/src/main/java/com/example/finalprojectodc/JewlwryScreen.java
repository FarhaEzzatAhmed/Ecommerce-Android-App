package com.example.finalprojectodc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JewlwryScreen extends AppCompatActivity {
ImageButton ss;
    RecyclerView recyclerView;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jewlwry_screen);
        ss=findViewById(R.id.searchphoto);

        recyclerView = findViewById(R.id.recyclerView);
        dialog = myProgressDialog();
        dialog.show();
        getData();
        ss.setOnClickListener(v -> {
            Intent intent = new Intent(JewlwryScreen.this,AddRecomenddProduct.class);
            startActivity(intent);
        });



    }
    private void getData() {
        Call<List<Root2>> call = RetrofitClient3.getInstance().getApi().getProducts();
        call.enqueue(new Callback<List<Root2>>() {
            @Override
            public void onResponse(Call<List<Root2>> call, Response<List<Root2>> response) {
                if (!response.isSuccessful()) {
                    Log.d("response", String.valueOf(response.code()));


                } else {

                    List<Root2> products = response.body();
                    MyCustomAdapter4 myCustomAdapter2 = new MyCustomAdapter4(JewlwryScreen.this, products);
                    recyclerView.setAdapter(myCustomAdapter2);
                    recyclerView.setLayoutManager(new LinearLayoutManager(JewlwryScreen.this, LinearLayoutManager.VERTICAL, false));
                    recyclerView.setLayoutManager(new GridLayoutManager(JewlwryScreen.this,2));
                    //recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
                }
                dialog.dismiss();
            }


            @Override
            public void onFailure(Call<List<Root2>> call, Throwable t) {
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