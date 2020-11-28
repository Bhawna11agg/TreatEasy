package com.example.treateasy;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HospitalRetrofitClient {

    private static String baseUrl = "https://hospital-apii.herokuapp.com/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
