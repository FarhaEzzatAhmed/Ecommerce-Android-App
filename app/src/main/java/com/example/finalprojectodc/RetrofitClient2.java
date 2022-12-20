package com.example.finalprojectodc;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient2 {

    private static RetrofitClient2 instance=null;
    private API2 myApi;
    private  RetrofitClient2(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API2.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        myApi = retrofit.create(API2.class);
    }
    public static synchronized RetrofitClient2 getInstance(){
        if(instance == null){

            //ana hana 3amlt object wahd 5las msh hynf3 a3ml tany
            instance = new RetrofitClient2();

        }
        return instance;
    }

    public API2 getApi(){
        return myApi;
    }
}

