package com.example.covidtracker;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class AddMarkerActivity extends AppCompatActivity {

    DatabaseHelper myDB;
    Button btnAdd, btnView, btnAddCurrentLocation;
    EditText editText;

    FusedLocationProviderClient mFusedLocationProviderClient;

    String coordinates;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_marker);

        editText = (EditText) findViewById(R.id.editText);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnView = (Button) findViewById(R.id.btnView);
        btnAddCurrentLocation = (Button) findViewById(R.id.btnAddCurrentLocation);
        myDB = new DatabaseHelper(this);

        getDeviceLocation();

        btnAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String newEntry = editText.getText().toString();
                //String newEntry = coordinates;
                if (editText.length() != 0) {
                    addData(newEntry);
                    editText.setText("");

                }
                else {
                    Toast.makeText(AddMarkerActivity.this, "Put something in the text field!", Toast.LENGTH_LONG).show();
                }
            }

        });

        btnView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddMarkerActivity.this, ViewListContents.class);
                startActivity(intent);
            }
        });

        btnAddCurrentLocation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String newEntry = coordinates;
                if (coordinates.length() != 0) {
                    addData(newEntry);

                }
                else {
                    Toast.makeText(AddMarkerActivity.this, "Something went wrong.", Toast.LENGTH_LONG).show();
                }
            }

        });

    }

    public void addData(String newEntry) {
        boolean insertData = myDB.addData(newEntry);

        if (insertData) {
            Toast.makeText(AddMarkerActivity.this, "Successfully enter data.", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(AddMarkerActivity.this, "Failed to enter data.", Toast.LENGTH_LONG).show();
        }
    }

    void getDeviceLocation() {
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(AddMarkerActivity.this);

        try {
            Task location = mFusedLocationProviderClient.getLastLocation();
            location.addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(AddMarkerActivity.this, "location found.", Toast.LENGTH_SHORT).show();
                        Location currentLocation = (Location) task.getResult();

                        coordinates = currentLocation.getLatitude() + " " + currentLocation.getLongitude();

                    } else {
                        Toast.makeText(AddMarkerActivity.this, "unable to get location", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } catch (SecurityException e) {
            Toast.makeText(AddMarkerActivity.this, "getDeviceLocation: SecurityException: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}
