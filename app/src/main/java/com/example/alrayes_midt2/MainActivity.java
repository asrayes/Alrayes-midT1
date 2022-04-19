package com.example.alrayes_midt2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.Instant;

public class MainActivity extends AppCompatActivity {
    TextView humidity, temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CalendarView cal = (CalendarView) findViewById(R.id.calendarView);
        TextView date = (TextView) findViewById(R.id.textView2);
        humidity = (TextView) findViewById(R.id.textView3);
        temp = (TextView) findViewById(R.id.textView5);
        Button button = (Button) findViewById(R.id.button2);

        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                String s = "Date: ";
                s += "Year: " + i;
                s += "Month: " + i1 + 1;
                s += "Day: " + i2;
                date.setText(s);
            }
        });

        String weatherWebserviceURL = "https://api.openweathermap.org/data/2.5/weather?q=london&appid=5d56b24d1108b763e9381132bc443b43&units=metric";
        weather(weatherWebserviceURL);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,MainActivity2.class));
            }
        });
    }

    public void weather(String url) {
        JsonObjectRequest jsonObj = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("Rayes","Response Received.");
                Log.d("Rayes",response.toString());

                try {
                    JSONObject jsonMain = response.getJSONObject("main");
                    temp.setText("Temperature: " + String.valueOf(jsonMain.getDouble("temp")));
                    humidity.setText("Humidity: " + String.valueOf(jsonMain.getDouble("humidity")));

                    } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {@Override public void onErrorResponse(VolleyError error) {
            Log.d("Rayes","Error retrieving URL.");
            Log.d("Rayes",error.toString());
        }});
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonObj);

    }
}