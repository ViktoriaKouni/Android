package com.example.corona.view;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.corona.R;
import com.example.corona.view.Fragments.ChartsFragment;
import com.example.corona.view.Fragments.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CountryActivity extends AppCompatActivity {
    private String countryName;
    private Fragment homeFragment;
    private Fragment chartsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country);

        Bundle bundle = getIntent().getExtras();
        countryName = bundle.getString("name");
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_nav);

        homeFragment = new HomeFragment();
        chartsFragment = new ChartsFragment();

        bundle.putString("name", countryName);
        homeFragment.setArguments(bundle);
        chartsFragment.setArguments(bundle);

        setFragment(homeFragment);

        bottomNavigationView.setOnNavigationItemReselectedListener(navListener);

    }

    private BottomNavigationView.OnNavigationItemReselectedListener navListener = new BottomNavigationView.OnNavigationItemReselectedListener() {
        @Override
        public void onNavigationItemReselected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.navigation_home:
                    setFragment(homeFragment);
                    break;
                case R.id.navigation_charts:
                    setFragment(chartsFragment);
                    break;
            }
        }
    };

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }
}
