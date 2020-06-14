package com.example.corona.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Summary {

    @SerializedName("Countries")
    @Expose
    private ArrayList<Country> countries;

    public ArrayList<Country> getCountries() {
        return countries;
    }

    public Summary( ArrayList<Country> countries) {
        countries = countries;
    }
}
