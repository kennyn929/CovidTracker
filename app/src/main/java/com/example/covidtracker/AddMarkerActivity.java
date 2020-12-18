package com.example.covidtracker;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
    Button btnAdd, btnView, btnAddCurrentLocation, btnUseMarker;
    EditText editText;
    TextView instructions;

    FusedLocationProviderClient mFusedLocationProviderClient;

    String coordinates;
    String userCoord;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_marker);

        editText = (EditText) findViewById(R.id.editText);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnView = (Button) findViewById(R.id.btnView);
        btnAddCurrentLocation = (Button) findViewById(R.id.btnAddCurrentLocation);
        btnUseMarker = (Button) findViewById(R.id.btnUsePlacedMarker);
        myDB = new DatabaseHelper(this);
        instructions = findViewById(R.id.textAddMarker);
        instructions.setText("Add a location by selecting either your current location or your placed marker. Then press add.\n\nSelect \"View Contents\" to see your current list of marked areas");

        Intent receivedIntent = getIntent();
        if (receivedIntent.getStringExtra("userCoord") != null) {
            userCoord = receivedIntent.getStringExtra("userCoord");
        }
        else {
            btnUseMarker.setEnabled(false);
            Toast.makeText(AddMarkerActivity.this, "Place a marker on a map to use its location.", Toast.LENGTH_SHORT).show();
        }




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
                    Toast.makeText(AddMarkerActivity.this, "Put something in the text field!", Toast.LENGTH_SHORT).show();
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
                    editText.setText(newEntry);
                    //addData(newEntry);

                }
                else {
                    Toast.makeText(AddMarkerActivity.this, "Something went wrong.", Toast.LENGTH_SHORT).show();
                }
            }

        });

        btnUseMarker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEntry = userCoord;

                if (newEntry.length() != 0) {
                    editText.setText(newEntry);
                    //Toast.makeText(AddMarkerActivity.this, userCoord, Toast.LENGTH_LONG).show();

                }
                else {
                    Toast.makeText(AddMarkerActivity.this, "Need to place a marker on the map!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void addData(String newEntry) {
        boolean insertData = myDB.addData(newEntry);

        if (insertData) {
            Toast.makeText(AddMarkerActivity.this, "Successfully enter location.", Toast.LENGTH_SHORT).show();
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
                        //Toast.makeText(AddMarkerActivity.this, "location found.", Toast.LENGTH_SHORT).show();
                        Location currentLocation = (Location) task.getResult();

                        if (currentLocation != null){
                            coordinates = currentLocation.getLatitude() + " " + currentLocation.getLongitude();
                        }
                        else {
                            Toast.makeText(AddMarkerActivity.this, "Enable your location to be able to use your current location.", Toast.LENGTH_SHORT).show();
                            btnAddCurrentLocation.setEnabled(false);
                        }



                    } else {
                        //Toast.makeText(AddMarkerActivity.this, "unable to get location", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } catch (SecurityException e) {
            Toast.makeText(AddMarkerActivity.this, "getDeviceLocation: SecurityException: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}
