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
    private OnListItemClickedListener onListItemClickedListener;

    public CountriesAdapter(List<Country> countries, OnListItemClickedListener listener) {
        this.countries = countries;
        this.countriesAll = new ArrayList<>(countries);
        onListItemClickedListener = listener;
    }

    @NonNull
    @Override
    public CountriesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.country_item, parent, false);
        return new ViewHolder(view, onListItemClickedListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CountriesAdapter.ViewHolder holder, int position) {
        Country countryPosition = countries.get(position);
        holder.Country.setText(countryPosition.getCountry());
        holder.TotalConfirmed.setText(String.valueOf(countryPosition.getTotalConfirmed()));


    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView Country;
        TextView TotalConfirmed;
        MenuItem search;

        RelativeLayout parentLayout;
        OnListItemClickedListener onListItemClickedListener;

        public ViewHolder(@NonNull View itemView, OnListItemClickedListener listener) {
            super(itemView);

            Country = itemView.findViewById(R.id.countryName);
            TotalConfirmed = itemView.findViewById(R.id.totalConfirmed);
            search=itemView.findViewById(R.id.search);
            parentLayout = itemView.findViewById(R.id.parent_layout);

            onListItemClickedListener = listener;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onListItemClickedListener.onListItemClicked(getAdapterPosition());
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

            if (charSequence == null || charSequence.length() == 0) {
                filteredCountries.addAll(countriesAll);
            } else {
                String filterPattern = charSequence.toString().toLowerCase().trim();
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
        @SuppressWarnings("unchecked")
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            countries.clear();
            countries.addAll((List<Country>) filterResults.values);
            notifyDataSetChanged();

        }
    };

    public interface OnListItemClickedListener {
        void onListItemClicked(int clickedItemIndex);
    }
}
