package com.example.finalprojectodc;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API {

    String BASE_URL = "https://api.escuelajs.co/api/v1/";

    @GET("categories")
    Call<List<Product>> getProducts();
}
