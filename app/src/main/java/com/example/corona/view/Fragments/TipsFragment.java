package com.example.corona.view.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.corona.R;
import com.example.corona.adapter.TipsAdapter;
import com.example.corona.model.Tips;
import com.example.corona.view.Activities.CountryActivity;
import com.example.corona.viewModel.TipsViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class TipsFragment extends Fragment implements TipsAdapter.OnListItemClickListener {
    private TipsViewModel tipsViewModel;
    private RecyclerView tipsRecyclerView;
    private EditText description;
    private FloatingActionButton addNewItem;
    private TipsAdapter tipsAdapter;

    public TipsFragment(CountryActivity countryActivity, TipsViewModel tipsViewModel) {
        this.tipsViewModel = tipsViewModel;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tips, container, false);
        tipsRecyclerView = rootView.findViewById(R.id.tips_list);
        tipsRecyclerView.hasFixedSize();
        tipsRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        setHasOptionsMenu(true);


        defaultData();
        tipsAdapter = new TipsAdapter(this);
        tipsRecyclerView.setAdapter(tipsAdapter);

        addNewItem = rootView.findViewById(R.id.add);
        addNewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTip();
            }
        });

        tipsViewModel.getTips().observe(this.getActivity(), new Observer<List<Tips>>() {
            @Override
            public void onChanged(List<Tips> tips) {
                tipsAdapter.setTips(tips);

            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                tipsViewModel.delete(tipsAdapter.getTipAt(viewHolder.getAdapterPosition()));
                Toast.makeText(getActivity(), "Tip deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(tipsRecyclerView);


        return rootView;
    }

    private void defaultData() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (!sharedPreferences.contains("data")) {

            tipsViewModel.insert(new Tips("Wash Your Hands"));
            tipsViewModel.insert(new Tips("Dont use public transportation"));
            tipsViewModel.insert(new Tips("STAY HOME"));
            editor.putBoolean("data", true).apply();
        }
    }

    private void saveTip() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getLayoutInflater().inflate(R.layout.add_tip, null);
        description = view.findViewById(R.id.tip_description_dialog);
        builder.setView(view);

        builder.setPositiveButton("Add New Tip", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String txt = description.getText().toString();
                System.out.println("added");
                tipsViewModel.insert(new Tips(txt));

            }
        });

        builder.setNegativeButton("Never mind, I forgot it", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        final AlertDialog ad = builder.create();
        ad.show();
    }

    @Override
    public void onListItemClick(Tips tips) {

    }
}

