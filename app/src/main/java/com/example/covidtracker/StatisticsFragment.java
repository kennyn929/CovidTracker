package com.example.covidtracker;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StatisticsFragment extends Fragment{

    private TextView textViewResult;

    ArrayList<String> stateNames = new ArrayList<String>();
    List<Case> caseSet;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_statistics, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        // Gather data from API
        textViewResult = view.findViewById(R.id.text_view_result);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.covidtracking.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CovidApi covidApi = retrofit.create(CovidApi.class);

        Call<List<Case>> call = covidApi.getCase();

        call.enqueue(new Callback<List<Case>>() {
            @Override
            public void onResponse(Call<List<Case>> call, Response<List<Case>> response) {

                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                caseSet = response.body();



                ArrayList<String> stateNames = new ArrayList<>();
                for (Case cases : caseSet) {
                    stateNames.add(cases.getState());
                }

                Spinner spinner = (Spinner) getView().findViewById(R.id.state_spinner);
                // Create an ArrayAdapter using the string array and a default spinner layout
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, stateNames);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                // Apply the adapter to the spinner
                spinner.setAdapter(adapter);
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view,
                                               int position, long id) {

                        String item = adapterView.getItemAtPosition(position).toString();
                        if (item != null) {
                            Toast.makeText(getContext(), item,
                                    Toast.LENGTH_SHORT).show();

                            makeGraph(item, position);
                        }


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        // TODO Auto-generated method stub

                    }
                });





                int stateCount = 0;
                for (Case cases : caseSet) {

                    //visitors.add(new BarEntry(1, 2));

                    String content = "";

                    content += "State: " + cases.getState() + "\n";
                    content += "Positive: " + cases.getPositive() + "\n";
                    content += "Positive Increase: " + cases.getPositiveIncrease() + "\n";
                    content += "Probable: " + cases.getProbableCases() + "\n";
                    content += "Confirmed Deaths: " + cases.getDeathConfirmed() + "\n\n";

                    //textViewResult.append(content);
                    stateCount++;

                }







            }

            @Override
            public void onFailure(Call<List<Case>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });











        //Spinner spinner = (Spinner) view.findViewById(R.id.state_spinner);

        /*
        if (stateNames.size() < 1) {

            Toast.makeText(getContext(), "" + stateNames.size(), Toast.LENGTH_SHORT).show();
        } */

        /*
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, stateNames);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // put code which recognize a selected element
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

         */


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    void makeGraph(String name, int pos) {
        Case thing = caseSet.get(pos);
        int positive = thing.getPositive();
        int recovered = thing.getRecovered();
        int deaths = thing.getDeathConfirmed();

        BarChart barChart = getView().findViewById(R.id.bar_chart);

        ArrayList<String> categories = new ArrayList<String>();
        categories.add("Positive");
        categories.add("Recovered");
        categories.add("Deaths");

        ArrayList<BarEntry> stats = new ArrayList<>();
        stats.add(new BarEntry(0, positive));
        stats.add(new BarEntry(1, recovered));
        stats.add(new BarEntry(2, deaths));

        BarDataSet barDataSet = new BarDataSet(stats, "Statistics for " + name);
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        BarData barData = new BarData(barDataSet);

        if (barChart != null) {
            barChart.setFitBars(false);
            barChart.setData(barData);
            barChart.getDescription().setText("");
            barChart.animateY(2000);
            barChart.getLegend().setEnabled(false);
            barChart.getAxisRight().setEnabled(false);
            barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(categories));
            barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
            barChart.getXAxis().setGranularity(1f);
            barChart.getAxisLeft().setAxisMinimum(0f);
        }
        else {
            Toast.makeText(getContext(), "No reference to barChart.", Toast.LENGTH_LONG).show();

        }
    }


}
