package com.example.finalprojectodc;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient3 {

    private static RetrofitClient3 instance=null;
    private API3 myApi;
    private  RetrofitClient3(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API3.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        myApi = retrofit.create(API3.class);
    }

    public static synchronized RetrofitClient3 getInstance(){
        if(instance == null){

            //ana hana 3amlt object wahd 5las msh hynf3 a3ml tany
            instance = new RetrofitClient3();

        }
        return instance;
    }

    public API3 getApi(){
        return myApi;
    }
}
