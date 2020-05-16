package com.example.corona.view.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.corona.R;

public class HomeFragment extends Fragment {

    TextView totalConfirmed;
    String countryName;
    public HomeFragment(){}


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = this.getArguments();

        countryName = bundle.getString("name");
        System.out.println(countryName);
        View view = inflater.inflate(R.layout.fragment_home, container, false);





        return view;
    }


}
