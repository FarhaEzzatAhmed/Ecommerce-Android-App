package com.example.finalprojectodc;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API2 {

    String BASE_URL = "https://api.escuelajs.co/api/v1/categories/";

    @GET("1/products/")
    Call<List<Root>> getProducts();
}

