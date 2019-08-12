package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.job.JobInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.weatherapp.pojoClasses.Example;

import com.example.weatherapp.pojoClasses.Weather;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

public class MainActivity extends AppCompatActivity {
    TextView humidity, visibility, latitude, longitude, wind, pressure, temperature, descriptionText;
    Button getThat;
    EditText cityName;
    JsonObjectRequest jsonObjectRequest;
    RequestQueue queue;
    Example example;
    ImageView weatherImage;
    Weather weather;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        humidity = findViewById(R.id.humidity_text);
        visibility = findViewById(R.id.visibility_text);
        latitude = findViewById(R.id.latitude_text);
        longitude = findViewById(R.id.longitude_text);
        wind = findViewById(R.id.wind_text);
        pressure = findViewById(R.id.pressure_text);
        weatherImage = findViewById(R.id.weatherImage);
        descriptionText = findViewById(R.id.weatherDescription);


        cityName = findViewById(R.id.getCity);
       getThat = findViewById(R.id.getData);

       temperature = findViewById(R.id.temperature);

        queue = Volley.newRequestQueue(MainActivity.this);

            }
    private void setData() {

        int temp = (int) (example.getMain().getTemp()-273);
        Double latitudedata =   example.getCoord().getLat();
        Double    longitudedata =  example.getCoord().getLon();


        List<Weather> samp = example.getWeather();
        Log.d(Constants.MY_TAG,samp.get(0).getDescription());

        descriptionText.setText(samp.get(0).getMain());

        humidity.setText(example.getMain().getHumidity().toString());
        visibility.setText(example.getVisibility()/1000 + " KM");
        wind.setText(example.getWind().getSpeed().toString());
        pressure.setText(example.getMain().getPressure().toString());
        temperature.setText(Integer.toString(temp));
        latitude.setText(latitudedata.toString());
        longitude.setText(longitudedata.toString());
        cityName.setText("");

//Image Selection

        switch (samp.get(0).getMain()){
            case "Haze":
                weatherImage.setImageResource(R.drawable.haze);
                break;
            case "Rain":
                weatherImage.setImageResource(R.drawable.rain);
                break;

            case "Clear":
                weatherImage.setImageResource(R.drawable.clear);
                break;
            case "Clouds":
                weatherImage.setImageResource(R.drawable.clouds);
                break;

            case "Mist":
                weatherImage.setImageResource(R.drawable.mist);
                break;

            default:
                    break;
        }





    }

    public void getThat(View v){
        String url = Constants.baseUrl + cityName.getText().toString() + Constants.api_key;
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
           @Override
           public void onResponse(JSONObject response) {
                try{
                    Gson gson = new Gson();
                    example = gson.fromJson(response.toString(),Example.class);
                    Log.d(Constants.MY_TAG, response.toString());
                    setData();
                }
                catch (Exception e){
                    Log.d(Constants.MY_TAG, "Exception" + e);
                }
           }
       }, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {

           }
       });
       queue.add(jsonObjectRequest);
                }

}
