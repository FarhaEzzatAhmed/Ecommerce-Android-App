package com.example.finalprojectodc;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient4 {

    private static RetrofitClient4 instance=null;
    private API4 myApi;
    private  RetrofitClient4(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API3.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        myApi = retrofit.create(API4.class);
    }

    public static synchronized RetrofitClient4 getInstance(){
        if(instance == null){

            //ana hana 3amlt object wahd 5las msh hynf3 a3ml tany
            instance = new RetrofitClient4();

        }
        return instance;
    }

    public API4 getApi(){
        return myApi;
    }
}
