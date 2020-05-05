package com.example.corona.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Summary {
    @SerializedName("Global")
    @Expose
    private Global global;
    @SerializedName("Countries")
    @Expose
    private ArrayList<Country> countries;

    public ArrayList<Country> getCountries() {
        return countries;
    }

    public Summary(Global global, ArrayList<Country> countries) {
        global = global;
        countries = countries;
    }
}
