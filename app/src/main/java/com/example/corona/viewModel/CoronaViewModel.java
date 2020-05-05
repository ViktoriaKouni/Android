package com.example.corona.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.corona.model.Country;
import com.example.corona.repository.CoronaRepository;

import java.util.ArrayList;

public class CoronaViewModel extends ViewModel {

    private CoronaRepository repository;
    private MutableLiveData<ArrayList<Country>> countryList;

    public void init() {
        if(countryList != null) {
            return;
        }
        repository = CoronaRepository.getInstance();
        countryList = repository.getAllCountries();
    }

   public LiveData<ArrayList<Country>> getAllCountries(){
       System.out.println("THIS IS LIVE DATA FROM VIEWMODEL :" + countryList);
        return countryList;
    }
}