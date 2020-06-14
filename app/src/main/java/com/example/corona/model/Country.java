package com.example.corona.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Country {
    @SerializedName("Country")
    @Expose
    private String country;


    @SerializedName("TotalConfirmed")
    @Expose
    private int totalConfirmed;

    @SerializedName("TotalDeaths")
    @Expose
    private int totalDeaths;


    @SerializedName("TotalRecovered")
    @Expose
    private int totalRecovered;


    public Country(String country, int totalConfirmed, int totalDeaths, int totalRecovered) {

        this.country = country;
        this.totalConfirmed = totalConfirmed;
        this.totalDeaths = totalDeaths;
        this.totalRecovered = totalRecovered;
    }


    public String getCountry() {
        return country;
    }

    public int getTotalConfirmed() {
        return totalConfirmed;
    }

    public int getTotalDeaths() {
        return totalDeaths;
    }

    public int getTotalRecovered() {
        return totalRecovered;
    }

}
