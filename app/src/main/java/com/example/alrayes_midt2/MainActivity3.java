package com.example.alrayes_midt2;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        EditText id = (EditText) findViewById(R.id.editTextTextPersonName5);
        Button delete = (Button) findViewById(R.id.button);
        Button setTable = (Button) findViewById(R.id.button5);

        DataBaseHelper myDB = new DataBaseHelper(this);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDB.deleteSpecificProduct(id.getText().toString());
                Toast.makeText(MainActivity3.this, "Delete Completed", Toast.LENGTH_LONG).show();
            }
        });

        setTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor = myDB.getListContents();
                cursor.moveToFirst();
                TableLayout table = (TableLayout) findViewById(R.id.table);
                String s = "";
                while(cursor.moveToNext()) {
                    s += cursor.getString(0);
                    s += cursor.getString(1);
                    s += cursor.getString(2);
                    s += cursor.getString(3);
                    table.addView(new TableRow(new TextView(s)));
                }
            }
        });



    }
}