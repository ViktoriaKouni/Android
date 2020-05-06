package com.example.corona.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.corona.api.CasesApi;
import com.example.corona.model.Country;
import com.example.corona.model.Summary;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CoronaRepository {
    private ArrayList<Country> countries = new ArrayList<>();
    private static CoronaRepository instance;


    public MutableLiveData<ArrayList<Country>> getAllCountries() {
        getCountries();

        countries = getCountriesList();


        MutableLiveData<ArrayList<Country>> data = new MutableLiveData<>();
        System.out.println("MUTABLE DATA" + Arrays.asList(countries));
        data.setValue(countries);
        System.out.println("THIS IS MUTABLE LIVE DATA:" + data);
        return data;
    }



    public static CoronaRepository getInstance() {
        if (instance == null) {
            instance = new CoronaRepository();
        }
        return instance;
    }

    public void getCountries() {
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .baseUrl("https://api.covid19api.com/")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = retrofitBuilder.build();
        CasesApi endpoints = retrofit.create(CasesApi.class);

        Call<Summary> call = endpoints.getCountries();
        call.enqueue(new Callback<Summary>() {
            @Override
            public void onResponse(Call<Summary> call, Response<Summary> response) {
                if (response.code() == 200) {
                    Log.i("Retrofit", "Good response");
                    Summary apiCountries = response.body();
                    countries.addAll(apiCountries.getCountries());
                    System.out.println("API COUNTRIES ARE HERE:" + Arrays.asList(apiCountries.getCountries()));
                }
            }
            @Override
            public void onFailure(Call<Summary> call, Throwable t) {
                ;
                Log.i("RetrofitError", "" + t.getMessage());
                Log.i("Retrofit", "Something went wrong :(" + call.toString());
            }
        });
    }


    public ArrayList<Country> getCountriesList() {

        ArrayList<Country> countriesArray = new ArrayList<Country>();
        // write code to get a list of type Country from your endpoint


        return countriesArray;

    }






}