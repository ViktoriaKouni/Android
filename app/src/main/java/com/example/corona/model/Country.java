package com.example.corona.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Country implements Parcelable {
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
    private int totalRecovered ;




    public Country(String country,int totalConfirmed,int totalDeaths,int totalRecovered){

        this.country=country;
        this.totalConfirmed=totalConfirmed;
        this.totalDeaths=totalDeaths;
        this.totalRecovered=totalRecovered;
    }

    protected Country(Parcel in) {
        country = in.readString();
        totalConfirmed = in.readInt();
        totalDeaths = in.readInt();
        totalRecovered = in.readInt();
    }

    public static final Creator<Country> CREATOR = new Creator<Country>() {
        @Override
        public Country createFromParcel(Parcel in) {
            return new Country(in);
        }

        @Override
        public Country [] newArray(int size) {
            return new Country[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(country);
        dest.writeInt(totalConfirmed);
        dest.writeInt(totalDeaths);
        dest.writeInt(totalRecovered);
    }
}
