package com.example.treateasy;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface hospitalInterface {

    @GET("/")
    Call<List<HospitalResponse>> getHospitalDetails();
}
