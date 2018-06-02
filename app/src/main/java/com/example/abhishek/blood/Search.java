package com.example.abhishek.blood;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class Search extends AppCompatActivity {


    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;
    Cursor cursor;
    Spinner blood_search;
    EditText city_search;
    Button search_bt;
    Spinner simpleSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        simpleSpinner = (Spinner)findViewById(R.id.simple_spinner);
        final List<String> simplelist = new ArrayList<String>();
        simplelist.add("Select Blood Group");
        simplelist.add("A+");
        simplelist.add("A-");
        simplelist.add("B+");
        simplelist.add("B-");
        simplelist.add("O+");
        simplelist.add("O-");
        simplelist.add("AB+");
        simplelist.add("AB-");
        ArrayAdapter<String> simpleAdapter = new ArrayAdapter<>(Search.this,R.layout.support_simple_spinner_dropdown_item,simplelist);
        simpleSpinner.setAdapter(simpleAdapter);
        simpleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(Search.this,simplelist.get(position),Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                simpleSpinner.setSelection(0);
            }
        });



        blood_search=(Spinner)findViewById(R.id.simple_spinner);
        city_search=(EditText)findViewById(R.id.city_search);
        search_bt=(Button)findViewById(R.id.search_bt);
        openHelper=new DatabaseHelper(this);
        db = openHelper.getReadableDatabase();



        search_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bs = blood_search.getSelectedItem().toString();
                String cs = city_search.getText().toString();
                Intent i = new Intent(Search.this, searchView.class);
                i.putExtra("blood_search", bs);
                i.putExtra("city_search", cs);
                startActivity(i);


            }
        });
    }


}
