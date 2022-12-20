package com.example.finalprojectodc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ElectrictyScreen extends AppCompatActivity {
    ImageView searchPhoto ,home;
    RecyclerView recyclerView;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electricty_screen);
        home=findViewById(R.id.imageView14);
        home.setOnClickListener(v -> {
            Intent ih = new Intent(ElectrictyScreen.this,Screen7.class);
            startActivity(ih);
        });
        searchPhoto=findViewById(R.id.search);
        recyclerView = findViewById(R.id.recyclerView);
        dialog = myProgressDialog();
        dialog.show();
        getData();
     searchPhoto.setOnClickListener(v -> {
    Intent i = new Intent(ElectrictyScreen.this,AddRecomenddProduct.class);
    startActivity(i);
});
    }


    private void getData() {
        Call<List<Root2>> call = RetrofitClient4.getInstance().getApi().getProducts();
        call.enqueue(new Callback<List<Root2>>() {
            @Override
            public void onResponse(Call<List<Root2>> call, Response<List<Root2>> response) {
                if (!response.isSuccessful()) {
                    Log.d("response", String.valueOf(response.code()));


                } else {

                    List<Root2> products = response.body();
                    MyCustomAdapter5 myCustomAdapter2 = new MyCustomAdapter5(ElectrictyScreen.this, products);
                    recyclerView.setAdapter(myCustomAdapter2);
                    recyclerView.setLayoutManager(new LinearLayoutManager(ElectrictyScreen.this, LinearLayoutManager.VERTICAL, false));
                    recyclerView.setLayoutManager(new GridLayoutManager(ElectrictyScreen.this,2));
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


    }

    private ProgressDialog myProgressDialog(){
        ProgressDialog dialog = new ProgressDialog(this);

        dialog.setTitle("Fetching API");
        dialog.setMessage("Loading Data");
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setCancelable(false);

        return dialog;
    }


}