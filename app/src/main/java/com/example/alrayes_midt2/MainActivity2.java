package com.example.alrayes_midt2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        EditText ID = (EditText) findViewById(R.id.editTextTextPersonName);
        EditText fname = (EditText) findViewById(R.id.editTextTextPersonName2);
        EditText lname = (EditText) findViewById(R.id.editTextTextPersonName3);
        EditText NID = (EditText) findViewById(R.id.editTextTextPersonName4);
        Button insert = (Button) findViewById(R.id.insert);
        Button activ1 = (Button) findViewById(R.id.button3);
        Button activ3 = (Button) findViewById(R.id.button4);

        DataBaseHelper myDB = new DataBaseHelper(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ID.getText().toString().equals("") || fname.getText().toString().equals("") || lname.getText().toString().equals("") || NID.getText().toString().equals("")) {
                    Toast.makeText(MainActivity2.this, "Please fill all fields missing.", Toast.LENGTH_LONG).show();
                }
                else {
                    myDB.addData(ID.getText().toString(),fname.getText().toString(), lname.getText().toString(), NID.getText().toString());
                    Toast.makeText(MainActivity2.this, "Insertion Completed", Toast.LENGTH_LONG).show();
                }
            }
        });

        activ1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity2.this, MainActivity.class));
            }
        });

        activ3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity2.this, MainActivity3.class));
            }
        });
    }
}