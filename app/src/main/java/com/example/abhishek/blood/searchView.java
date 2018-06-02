package com.example.abhishek.blood;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Dell on 30/05/2018.
 */

public class searchView extends AppCompatActivity {

    DatabaseHelper myDB;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewsearchlist);

        Bundle extras = getIntent().getExtras();
        String b = extras.getString("blood_search");
        String c = extras.getString("city_search");



        ListView listView = (ListView) findViewById(R.id.listview);
        myDB = new DatabaseHelper(this);


        //populate an ArrayList<String> from the database and then view it
        ArrayList<String> thesearchList = new ArrayList<>();
        Cursor data = myDB.getListContents();

        if (data.getCount() == 0) {
            Toast.makeText(this, "There are no contents in this list!", Toast.LENGTH_LONG).show();
        } else {
            while(data.moveToNext()) {
                Log.d("mytext",data.getString(3));
                Log.d("mytext1",b);
                Log.d("mytext2",data.getString(6));
                Log.d("mytext3",c);
                if(data.getString(3).equals(b) &&  data.getString(6).equals(c)){

                    thesearchList.add(data.getString(1));
                    thesearchList.add(data.getString(3));
                    thesearchList.add(data.getString(4));
                    thesearchList.add(data.getString(5));
                    thesearchList.add(data.getString(6));
                    thesearchList.add("---------------------     ");


                    ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, thesearchList);
                    listView.setAdapter(listAdapter);
                    Toast.makeText(getApplicationContext(), "Match Found..!!", Toast.LENGTH_SHORT).show();
                }


            }


        }

    }
}
