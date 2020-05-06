package com.example.corona.api;


import com.example.corona.model.Country;
import com.example.corona.model.Summary;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CasesApi {

    @GET("summary")
    Call<Summary> getCountries();

    @GET("country/{name}")
    Call<Country> getCountry(@Path("name") String name);



}
