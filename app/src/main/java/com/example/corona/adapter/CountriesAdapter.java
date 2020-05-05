package com.example.corona.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.corona.R;
import com.example.corona.model.Country;

import java.util.ArrayList;

public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.ViewHolder> {
    private ArrayList<Country> countries;

    public CountriesAdapter(ArrayList<Country> countries) {
        this.countries = countries;
    }

    @NonNull
    @Override
    public CountriesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.country_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountriesAdapter.ViewHolder holder, int position) {
        Country countryPosition=countries.get(position);
        holder.Country.setText(countryPosition.getCountry());
        holder.TotalConfirmed.setText(String.valueOf(countryPosition.getTotalConfirmed()));


    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView Country;
        TextView TotalConfirmed;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            Country = itemView.findViewById(R.id.countryName);
            TotalConfirmed=itemView.findViewById(R.id.totalConfirmed);
            parentLayout = itemView.findViewById(R.id.parent_layout);

        }
    }
}