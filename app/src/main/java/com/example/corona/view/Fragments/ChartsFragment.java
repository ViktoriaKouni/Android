package com.example.corona.view.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.corona.R;
import com.example.corona.model.Statistics;
import com.example.corona.viewModel.CoronaViewModel;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class ChartsFragment extends Fragment {


    public ChartsFragment(String countryName) {
        this.countryName = countryName;
    }

    String countryName;
    CoronaViewModel viewModel;
    List<Statistics> statisticsList = new ArrayList<>();
    com.github.mikephil.charting.charts.BarChart barChart;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_chart, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        barChart = view.findViewById(R.id.barChart);
        viewModel = new ViewModelProvider(getActivity()).get(CoronaViewModel.class);
        setObserver();
    }

    private void setObserver() {
        viewModel.getCountryStatistics(countryName);
        viewModel.getStatistics().observe(getViewLifecycleOwner(), new Observer<List<Statistics>>() {

            @Override
            public void onChanged(List<Statistics> statistics) {
                statisticsList.clear();
                statisticsList.addAll(statistics);
                makeChartData();
            }
        });
    }

    private void makeChartData() {
        HashMap<String, Integer> hashMap = new LinkedHashMap<>();
        hashMap.put("Jan", 0);
        hashMap.put("Feb", 0);
        hashMap.put("Mar", 0);
        hashMap.put("Apr", 0);
        hashMap.put("May", 0);
        hashMap.put("Jun", 0);
        hashMap.put("Jul", 0);
        hashMap.put("Aug", 0);
        hashMap.put("Sep", 0);
        hashMap.put("Oct", 0);
        hashMap.put("Nov", 0);
        hashMap.put("Dec", 0);

        for (Statistics stats : statisticsList) {
            String month = getMonth(stats.getDate());
            int newCases = stats.getTotalActive()-hashMap.get(month);
            hashMap.put(month, hashMap.get(month) + newCases);
        }


        final List<String> xLabels = new ArrayList<>();
        List<Integer> yLabel = new ArrayList<>();

        for (HashMap.Entry<String, Integer> entry : hashMap.entrySet()) {
            xLabels.add(entry.getKey());
            yLabel.add(entry.getValue());
        }
        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(true);

        barChart.getDescription().setEnabled(false);
        barChart.getLegend().setEnabled(false);


        barChart.setMaxVisibleValueCount(60);

        barChart.setPinchZoom(false);

        barChart.setDrawGridBackground(false);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setGranularity(1f);


        xAxis.setLabelCount(4);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                if (value < xLabels.size()) {
                    return xLabels.get((int) value);
                } else {
                    return "";
                }
            }
        });

        setMonthBarChartData(yLabel);
    }

    public String getMonth(String date) {
        String format = "yyyy-MM-dd'T'HH:mm:ss";
        DateFormat dateFormat = new SimpleDateFormat(format);
        Date mDate = null;
        try {
            mDate = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DateFormat monthFormat = new SimpleDateFormat("MMM");
        return monthFormat.format(mDate);
    }

    private void setMonthBarChartData(
             List<Integer> yLabels
    ) {

        List<BarEntry> values =new ArrayList<BarEntry>();

        for (int i=0;i<yLabels.size();i++) {
            Float value= (float)yLabels.get(i);
            values.add(new BarEntry((float)i, value));
        }

        BarDataSet set1;

        if (barChart.getData()!= null &&
                barChart.getData().getDataSetCount() > 0
        ) {
            set1 =(BarDataSet) barChart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            barChart.invalidate();
            barChart.getData().notifyDataChanged();
            barChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(values, "The year 2020");
            set1.setDrawIcons(false);

            List<Integer> colorsList = new ArrayList<>();
            for(int color:ColorTemplate.MATERIAL_COLORS){
                colorsList.add(color);
            }

            set1.setColors(colorsList);

            List<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);
            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
            data.setDrawValues(false);
            data.setBarWidth(0.5f);
            barChart.setData(data);
            barChart.setVisibleXRangeMaximum(10f) ;// allow 20 values to be displayed at once on the x-axis, not more
            barChart.moveViewToX(10f);
            barChart.notifyDataSetChanged();
            barChart.invalidate();
        }
    }

}