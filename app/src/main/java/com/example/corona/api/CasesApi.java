package com.example.corona.api;


import com.example.corona.model.Country;
import com.example.corona.model.Statistics;
import com.example.corona.model.Summary;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CasesApi {

    @GET("summary")
    Call<Summary> getCountries();

    @GET("country/{name}")
    Call<Country> getCountry(@Path("name") String name);

    @GET("dayone/country/{name}")
    Call<List<Statistics>> getStatistics(@Path("name") String name);

}
