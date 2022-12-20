package com.example.finalprojectodc;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static RetrofitClient instance=null;
    private API myApi;
    private  RetrofitClient(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        myApi = retrofit.create(API.class);
    }
    public static synchronized RetrofitClient getInstance(){
        if(instance == null){

            //ana hana 3amlt object wahd 5las msh hynf3 a3ml tany
            instance = new RetrofitClient();

        }
        return instance;
    }

    public API getApi(){
        return myApi;
    }
}
