package com.example.covidtracker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

public class MarkFragment extends Fragment {

    TextView textView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mark, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        textView = getView().findViewById(R.id.text_view_mark);

        String message = "COVID-19\n" +
                "Health Check\n" +
                "Survey";

        textView.setText(message);

        // Identify Mark button
        Button button = getView().findViewById(R.id.buttonMark);

        // When Mark button is clicked
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Test Message
                // Toast.makeText(getContext(), "Button Clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(view.getContext(), QuizActivity.class);
                startActivity(intent);

            }
        });
    }
}
