package com.example.covidtracker;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ViewListContents extends AppCompatActivity {

    DatabaseHelper myDB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewcontents_layout);

        ListView listView = (ListView) findViewById(R.id.ListView);
        myDB = new DatabaseHelper(this);

        ArrayList<String> list = new ArrayList<>();
        Cursor data = myDB.getListContents();

        if(data.getCount() == 0) {
            Toast.makeText(ViewListContents.this, "There's nothing in the database.", Toast.LENGTH_LONG).show();
        }
        else {
            while(data.moveToNext()) {
                list.add(data.getString(1));
                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
                listView.setAdapter(listAdapter);
            }

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String name = parent.getItemAtPosition(position).toString();
                    //Toast.makeText(ViewListContents.this, "You clicked on " + name + ".", Toast.LENGTH_LONG).show();

                    Cursor data = myDB.getItemID(name);

                    int itemID = -1;
                    while(data.moveToNext()) {
                        itemID = data.getInt(0);
                    }

                    if(itemID > -1) {
                        //Toast.makeText(ViewListContents.this, "ID is " + itemID, Toast.LENGTH_LONG).show();
                        Intent editScreenIntent = new Intent(ViewListContents.this, EditDataActivity.class);
                        editScreenIntent.putExtra("id", itemID);
                        editScreenIntent.putExtra("name", name);
                        startActivity(editScreenIntent);
                        finish();
                    }
                    else {
                        Toast.makeText(ViewListContents.this, "Found nothing.", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }


}
