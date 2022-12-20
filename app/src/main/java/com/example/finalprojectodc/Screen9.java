package com.example.finalprojectodc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Screen9 extends AppCompatActivity {
    RecyclerView recyclerView;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen9);


        recyclerView = findViewById(R.id.recyclerView);
        dialog = myProgressDialog();
        dialog.show();
        getData();
    }
    private void getData() {
        Call<List<Root>> call = RetrofitClient2.getInstance().getApi().getProducts();
        call.enqueue(new Callback<List<Root>>() {
            @Override
            public void onResponse(Call<List<Root>> call, Response<List<Root>> response) {
                if (!response.isSuccessful()) {
                    Log.d("response", String.valueOf(response.code()));


                } else {

                    List<Root> products = response.body();
                    MyCustomAdapter3 myCustomAdapter2 = new MyCustomAdapter3(Screen9.this, products);
                    recyclerView.setAdapter(myCustomAdapter2);
                    //recyclerView.setLayoutManager(new LinearLayoutManager(Screen9.this, LinearLayoutManager.VERTICAL, false));
                    recyclerView.setLayoutManager(new GridLayoutManager(Screen9.this,2));
                    //recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
                }
                dialog.dismiss();
            }


            @Override
            public void onFailure(Call<List<Root>> call, Throwable t) {
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