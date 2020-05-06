package com.example.corona.adapter;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.corona.R;
import com.example.corona.model.Country;

import java.util.ArrayList;
import java.util.List;

public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.ViewHolder> implements Filterable {

    private List<Country> countries;
    private List<Country> countriesAll;

    public CountriesAdapter(List<Country> countries) {
        this.countries = countries;
        this.countriesAll = new ArrayList<>(countries);
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
        Country countryPosition = countries.get(position);
        holder.Country.setText(countryPosition.getCountry());
        holder.TotalConfirmed.setText(String.valueOf(countryPosition.getTotalConfirmed()));


    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView Country;
        TextView TotalConfirmed;
        MenuItem search;

        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            Country = itemView.findViewById(R.id.countryName);
            TotalConfirmed = itemView.findViewById(R.id.totalConfirmed);
            search=itemView.findViewById(R.id.search);
            parentLayout = itemView.findViewById(R.id.parent_layout);

        }



    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }
    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Country> filteredCountries = new ArrayList<>();

            if (charSequence.toString().isEmpty()) {
                filteredCountries.addAll(countriesAll);
            } else {
                String filterPattern=charSequence.toString().toLowerCase().trim();
                for (Country  country : countriesAll) {
                    if (country.getCountry().toLowerCase().contains(filterPattern)) {
                        filteredCountries.add(country);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredCountries;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            countries.clear();
            countries.addAll((List) filterResults.values);
            notifyDataSetChanged();

        }
    };


}
