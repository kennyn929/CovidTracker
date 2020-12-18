package com.example.covidtracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class EditDataActivity extends AppCompatActivity {

    private static final String TAG = "EditDataActivity";

    private Button btnDelete;
    private EditText editable_item;

    DatabaseHelper myDBHelper;

    private String selectedName;
    private int selectedID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_data_layout);

        btnDelete = findViewById(R.id.btnDelete);
        editable_item = findViewById(R.id.editable_item);
        myDBHelper = new DatabaseHelper(this);

        // get intent extra from ViewListContents
        Intent receivedIntent = getIntent();

        selectedID = receivedIntent.getIntExtra("id", -1);
        selectedName = receivedIntent.getStringExtra("name");

        editable_item.setText(selectedName);

        /*
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = editable_item.getText().toString();
                if(!item.equals("")) {
                    myDBHelper.updateName(item, selectedID, selectedName);
                } else {
                    Toast.makeText(EditDataActivity.this, "Cannot be empty", Toast.LENGTH_LONG).show();
                }
                finish();
            }
        }); */

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDBHelper.deleteName(selectedID, selectedName);
                editable_item.setText("");
                Toast.makeText(EditDataActivity.this, "Deleted item.", Toast.LENGTH_LONG).show();
                finish();
            }
        });

    }
}
