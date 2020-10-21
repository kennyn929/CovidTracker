package com.example.covidtracker;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CovidApi {

    @GET("v1/states/current.json")
    Call<List<Case>> getCase();

}
