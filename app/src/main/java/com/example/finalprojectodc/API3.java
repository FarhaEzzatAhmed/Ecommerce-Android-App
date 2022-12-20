package com.example.finalprojectodc;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API3 {


    String BASE_URL = "https://fakestoreapi.com/products/category/";

    @GET("jewelery")
    Call<List<Root2>> getProducts();
}
