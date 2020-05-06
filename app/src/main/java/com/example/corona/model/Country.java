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


    public Country(String country,int totalConfirmed){

        this.country=country;
        this.totalConfirmed=totalConfirmed;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getTotalConfirmed() {
  return totalConfirmed;
    }

//  public void setTotalConfirmed(int totalConfirmed) {
 //       this.totalConfirmed = totalConfirmed;
 //   }
}
