package com.example.abhishek.blood;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class signup extends AppCompatActivity {


    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Button submit, login;
    EditText name, password, gender, mobile, city;
    Spinner simpleSpinner;
    Spinner blood_grp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        simpleSpinner = (Spinner)findViewById(R.id.blood_grp);
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
        ArrayAdapter<String> simpleAdapter = new ArrayAdapter<>(signup.this,R.layout.support_simple_spinner_dropdown_item,simplelist);
        simpleSpinner.setAdapter(simpleAdapter);
        simpleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(signup.this,simplelist.get(position),Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                simpleSpinner.setSelection(0);
            }
        });


        openHelper = new DatabaseHelper(this);
        name = (EditText) findViewById(R.id.name);
        password = (EditText) findViewById(R.id.password);
        blood_grp = (Spinner) findViewById(R.id.blood_grp);
        gender = (EditText) findViewById(R.id.gender);
        mobile = (EditText) findViewById(R.id.mobile);
        city = (EditText) findViewById(R.id.city_search);
        submit = (Button) findViewById(R.id.submit);
        login = (Button) findViewById(R.id.login);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = openHelper.getWritableDatabase();
                String name1 = name.getText().toString();
                String password1 = password.getText().toString();
                String blood_grp1 = blood_grp.getSelectedItem().toString();
                String gender1 = gender.getText().toString();
                String mobile1 = mobile.getText().toString();
                String city1 = city.getText().toString();
                if(!(name1.contentEquals("")) && !(password1.contentEquals("")) && !(blood_grp1.contentEquals("")) &&!(mobile1.contentEquals("")) && !(city1.contentEquals(""))) {
                    insertdata(name1, password1, gender1, blood_grp1, mobile1, city1);
                    Toast.makeText(getApplicationContext(), "register successfully", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "register not successfull", Toast.LENGTH_LONG).show();
                }
            }

        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(signup.this, Login.class);
                startActivity(intent);
            }


        });
    }
    public void insertdata(String name1, String password1, String blood_grp1, String gender1, String mobile1, String city1 ){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COL_2, name1);
        contentValues.put(DatabaseHelper.COL_3, password1);
        contentValues.put(DatabaseHelper.COL_4, gender1);
        contentValues.put(DatabaseHelper.COL_5, blood_grp1);
        contentValues.put(DatabaseHelper.COL_6, mobile1);
        contentValues.put(DatabaseHelper.COL_7, city1);
        long id = db.insert(DatabaseHelper.TABLE_NAME, null, contentValues);
    }




}

