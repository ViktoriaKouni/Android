package com.example.corona.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Statistics {
    public String getCountry() {
        return country;
    }

    @SerializedName("Country")
    @Expose
    private String country;

    @SerializedName("Active")
    @Expose
    private int totalActive;

    @SerializedName("Date")
    @Expose
    private String date;

    public void setCountry(String country) {
        this.country = country;
    }

    public int getTotalActive() {
        return totalActive;
    }

    public void setTotalActive(int totalActive) {
        this.totalActive = totalActive;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}
