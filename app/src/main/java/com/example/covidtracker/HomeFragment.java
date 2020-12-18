package com.example.covidtracker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    TextView textview;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        textview = getView().findViewById(R.id.text_view_home);
        String instructions = "Welcome to COVID Tracker\n\n" +
                "To see where local outbreaks are located, tap on the 'Map' tab\n\n" +
                "To access latest statistics of COVID cases, tap on the 'Stats' tab\n\n" +
                "Visit the 'Survey' tab if you believe you are infected\n\n" +
                "For common questions on COVID, tap on the 'Help' tab";
        textview.setText(instructions);
    }
}
