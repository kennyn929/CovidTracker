package com.example.covidtracker;

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

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StatisticsFragment extends Fragment {

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
        textViewResult = getView().findViewById(R.id.text_view_result);

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
                for (Case cases : caseSet) {

                    //stateNames.add("" + cases.getState());

                    String content = "";

                    //content += cases.getState() + "\n";

                    content += "State: " + cases.getState() + "\n";
                    content += "Positive: " + cases.getPositive() + "\n";
                    content += "Positive Increase: " + cases.getPositiveIncrease() + "\n";
                    content += "Probable: " + cases.getProbableCases() + "\n";
                    content += "Confirmed Deaths: " + cases.getDeathConfirmed() + "\n\n";

                    textViewResult.append(content);


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


}
