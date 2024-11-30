package com.example.weatherapp;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WeatherDataService {

    public static final String URL = "https://api.weatherstack.com/current?access_key=86ddb57868c2fb7ee19f2f623fe4936c&query=";

    Context context;
    String temperature;


    public WeatherDataService(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener {
        void onError(String message);

        void onResponse(String temperature);
    }

    public void getTemperature(String cityName, VolleyResponseListener volleyResponseListener){
        String url = URL+cityName;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                temperature = "";
                try {
                    JSONObject currentData = response.getJSONObject("current");
                    temperature = currentData.getString("temperature");

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                //Toast.makeText(context, "The temperature is: "+temperature+" degrees.", Toast.LENGTH_SHORT).show();
                volleyResponseListener.onResponse(temperature);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                volleyResponseListener.onError("Error");
            }
        });


        // Add the request to the RequestQueue.
        MySingleton.getInstance(context).addToRequestQueue(request);

       // return temperature;
    }

    public interface WeatherResponseListener {
        void onError(String message);

        void onResponse(List<WeatherReportModel> report);
    }

    public void weatherReport (String cityName, WeatherResponseListener weatherResponseListener) {
        List<WeatherReportModel> report = new ArrayList<>();
        String url = URL + cityName;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();

                try {
                    JSONObject current = response.getJSONObject("current");

                    WeatherReportModel data = new WeatherReportModel();

                    data.setObservation_time(current.getString("observation_time"));
                    data.setWeather_icons(current.getString("weather_icons"));
                    data.setWeather_descriptions(current.getString("weather_descriptions"));
                    data.setWind_speed(current.getInt("wind_speed"));
                    data.setWind_degree(current.getInt("wind_degree"));
                    data.setWind_dir(current.getString("wind_dir"));
                    data.setPressure(current.getInt("pressure"));
                    data.setPrecip(current.getInt("precip"));
                    data.setHumidity(current.getInt("humidity"));
                    data.setCloudcover(current.getInt("cloudcover"));
                    data.setFeelslike(current.getInt("feelslike"));
                    data.setUv_index(current.getInt("uv_index"));
                    data.setVisibility(current.getInt("visibility"));
                    data.setIs_day(current.getString("is_day"));
                    report.add(data);

                    weatherResponseListener.onResponse(report);

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        },    new Response.ErrorListener()    {
        @Override
        public void onErrorResponse (VolleyError error){

        }
        });

        MySingleton.getInstance(context).addToRequestQueue(request);
    }


}