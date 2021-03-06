package com.example.corona.view.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.corona.R;
import com.example.corona.view.Fragments.ChartsFragment;
import com.example.corona.view.Fragments.HomeFragment;
import com.example.corona.view.Fragments.TipsFragment;
import com.example.corona.viewModel.TipsViewModel;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CountryActivity extends AppCompatActivity {
    private String countryName;
    private int countryConfirmed;
    private int countryRecovered;
    private int countryDeaths;
    private Fragment homeFragment;
    private Fragment chartsFragment;
    private Fragment tipsFragment;
    private TipsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);
        viewModel= new ViewModelProvider(this).get(TipsViewModel.class);

        Bundle bundle = getIntent().getExtras();
        countryName = bundle.getString("name");
        countryConfirmed = bundle.getInt("confirmed");
        countryRecovered = bundle.getInt("recovered");
        countryDeaths = bundle.getInt("deaths");
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);

        homeFragment = new HomeFragment(countryName,countryConfirmed,countryRecovered,countryDeaths);


        bundle.putString("name", countryName);
        bundle.putInt("confirmed", countryConfirmed);
        bundle.putInt("recovered", countryRecovered);
        bundle.putInt("deaths", countryDeaths);
        homeFragment.setArguments(bundle);


        setFragment(homeFragment);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        Toolbar toolbar = findViewById(R.id.tool);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.logout) {
            AuthUI.getInstance().signOut(this).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    goToLoginActivity();
                }
            });

        }
        return true;

    }

    private void goToLoginActivity() {
        Intent intent = new Intent(this, Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment fragment = null;
                    switch (menuItem.getItemId()) {
                        case R.id.navigation_home:
                            fragment = new HomeFragment(countryName,countryConfirmed,countryRecovered,countryDeaths);
                            break;
                        case R.id.navigation_charts:
                            fragment = new ChartsFragment(countryName);
                            break;
                        case R.id.navigation_tips:
                            fragment = new TipsFragment(CountryActivity.this, viewModel);
                            break;
                    }
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container,
                            fragment).commit();
                    return true;
                }
            };

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }
}
