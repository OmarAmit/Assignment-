package com.immortal.assignment.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.immortal.assignment.constants.AppConstant.BASE_URL;

public class RetrofitRequest {

    private  Retrofit retrofit;
    private static RetrofitRequest mInstance;

    private RetrofitRequest(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static synchronized RetrofitRequest getInsance(){
        if(mInstance == null){
            mInstance = new RetrofitRequest();
        }
        return mInstance;
    }

    public ApiRequest getApi(){
        return retrofit.create(ApiRequest.class);
    }
}
