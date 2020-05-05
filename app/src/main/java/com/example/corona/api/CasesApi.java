package com.example.corona.api;


import com.example.corona.model.Summary;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CasesApi {

    @GET("summary")
    Call<Summary> getCountries();



}
