package com.example.abhishek.blood;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {


    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;
    Cursor cursor;
    Button login_l;

    EditText name_l, password_l;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        name_l=(EditText)findViewById(R.id.name_l);
        password_l=(EditText)findViewById(R.id.password_l);
        login_l=(Button)findViewById(R.id.login_l);
        openHelper=new DatabaseHelper(this);
        db = openHelper.getReadableDatabase();



        login_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name2 = name_l.getText().toString();
                String password2 = password_l.getText().toString();


                cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME + " WHERE " + DatabaseHelper.COL_2 + "=? AND " + DatabaseHelper.COL_3 + "=?", new String[]{name2, password2});

                if (cursor != null) {
                    if (cursor.getCount() > 0) {
                        Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();
                        Log.d("myTag", DatabaseHelper.COL_2);
                        Log.d("myTag1", DatabaseHelper.COL_3);
                        Log.d("myTag2", name2);
                        Log.d("myTag3", password2);




                    } else {
                        Toast.makeText(getApplicationContext(), "Login error", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }


}
