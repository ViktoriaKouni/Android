package com.example.corona;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.corona.adapter.CountriesAdapter;
import com.example.corona.model.Country;
import com.example.corona.viewModel.CoronaViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private CountriesAdapter adapter;
    private CoronaViewModel viewModel;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setViewModel();

        recyclerView = findViewById(R.id.countries);
        adapter = new CountriesAdapter(viewModel.getAllCountries().getValue());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        System.out.println(viewModel.getAllCountries());



    }

    public void onResume(){
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    public void setViewModel() {
        viewModel = new ViewModelProvider(this).get(CoronaViewModel.class);
        viewModel.init();

        viewModel.getAllCountries().observe(this, new Observer<List<Country>>() {

            @Override
            public void onChanged(List<Country> countries) {
                adapter.notifyDataSetChanged();
            }
        });
    }
}
