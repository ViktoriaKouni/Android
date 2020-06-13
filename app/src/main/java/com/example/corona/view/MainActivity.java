package com.example.corona.view;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.corona.R;
import com.example.corona.adapter.CountriesAdapter;
import com.example.corona.api.ResourceIdlng;
import com.example.corona.model.Country;
import com.example.corona.viewModel.CoronaViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements CountriesAdapter.OnListItemClickedListener {
    private CountriesAdapter adapter;
    private CoronaViewModel viewModel;
    private RecyclerView recyclerView;
    private String countryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ResourceIdlng.getInstance().increment();
        Toolbar toolbar = findViewById(R.id.tool);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        setViewModel();

        recyclerView = findViewById(R.id.countries);
        adapter = new CountriesAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        System.out.println(viewModel.getAllCountries());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.search);

        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {

                adapter.getFilter().filter(query);
                return true;
            }
        });
        return true;
    }


    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    public void setViewModel() {
        viewModel = new ViewModelProvider(this).get(CoronaViewModel.class);

        viewModel.getAllCountries().observe(this, new Observer<List<Country>>() {

            @Override
            public void onChanged(List<Country> countries) {
                adapter.setCountries(countries);
                adapter.notifyDataSetChanged();
                ResourceIdlng.getInstance().decrement();

            }
        });
    }

    @Override
    public void onListItemClicked(int clickedItemIndex) {
    }


}
