package com.example.corona.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.corona.model.Country;
import com.example.corona.model.Statistics;
import com.example.corona.repository.CoronaRepository;

import java.util.ArrayList;
import java.util.List;

public class CoronaViewModel extends AndroidViewModel {
    private CoronaRepository repository;

    public CoronaViewModel(@NonNull Application application) {
        super(application);
        repository = CoronaRepository.getInstance(application);
    }

    public LiveData<ArrayList<Country>> getAllCountries() {
        return repository.getAllCountries();
    }

    public LiveData<List<Statistics>> getStatistics() {
        return repository.getStatistics();
    }

    public void getCountryStatistics(String name) {
        repository.getCountryStatistics(name);
    }


    public Country getCountry(String country) {
        return repository.getCountry(country);
    }
}