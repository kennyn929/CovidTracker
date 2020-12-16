package com.example.covidtracker;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddMarkerActivity extends AppCompatActivity {

    DatabaseHelper myDB;
    Button btnAdd, btnView;
    EditText editText;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_marker);

        editText = (EditText) findViewById(R.id.editText);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnView = (Button) findViewById(R.id.btnView);
        myDB = new DatabaseHelper(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String newEntry = editText.getText().toString();
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

}
