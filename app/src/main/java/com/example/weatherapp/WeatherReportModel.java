package com.example.weatherapp;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class WeatherReportModel {

        private String observation_time;
        private String weather_icons;
        private String weather_descriptions;
        private int wind_speed;
        private int wind_degree;
        private String wind_dir;
        private int pressure;
        private int precip;
        private int humidity;
        private int cloudcover;
        private int feelslike;
        private int uv_index;
        private int visibility;
        private String is_day;


    public WeatherReportModel(String observation_time, String weather_icons, String weather_descriptions, int wind_speed, int wind_degree, String wind_dir, int pressure, int precip, int humidity, int feelslike, int cloudcover, int uv_index, int visibility, String is_day) {
        this.observation_time = observation_time;
        this.weather_icons = weather_icons;
        this.weather_descriptions = weather_descriptions;
        this.wind_speed = wind_speed;
        this.wind_degree = wind_degree;
        this.wind_dir = wind_dir;
        this.pressure = pressure;
        this.precip = precip;
        this.humidity = humidity;
        this.feelslike = feelslike;
        this.cloudcover = cloudcover;
        this.uv_index = uv_index;
        this.visibility = visibility;
        this.is_day = is_day;
    }

    @Override
    public String toString() {
        return
                weather_descriptions.substring(2, weather_descriptions.length()-2) + '\n' +
                "Wind Speed: " + wind_speed +"m/s"+ '\n' +
                "Wind Dirrection: " + wind_dir + '\n' +
                "Pressure: " + pressure + "mbar"+ '\n' +
                "Precipitation: " + precip + '\n' +
                "Humidity: " + humidity + "%"+'\n' +
                "Feels Like: " + feelslike + '\n' +
                "UV Index: " + uv_index + '\n' +
                "Visibility: " + visibility + '\n';
    }

    public ArrayList<String> toStringArray(){
        ArrayList<String> str = new ArrayList<String>();
        str.add(Integer.toString(wind_speed)+"m/s");
        str.add(wind_dir);
        str.add(Integer.toString(pressure)+"mbar");
        str.add(Integer.toString(precip));
        str.add(Integer.toString(humidity)+"%");
        str.add(Integer.toString(feelslike)+"\u2103");
        str.add(Integer.toString(uv_index));
        str.add(Integer.toString(visibility));

        return str;

    }

    public WeatherReportModel() {
    }

    public String getObservation_time() {
        return observation_time;
    }

    public void setObservation_time(String observation_time) {
        this.observation_time = observation_time;
    }

    public String getWeather_icons() {
        return weather_icons.substring(1, weather_icons.length()-1);
    }

    public void setWeather_icons(String weather_icons) {
        this.weather_icons = weather_icons;
    }

    public String getWeather_descriptions() {
        return weather_descriptions.substring(2, weather_descriptions.length()-2);
    }

    public void setWeather_descriptions(String weather_descriptions) {
        this.weather_descriptions = weather_descriptions;
    }

    public int getWind_speed() {
        return wind_speed;
    }

    public void setWind_speed(int wind_speed) {
        this.wind_speed = wind_speed;
    }

    public int getWind_degree() {
        return wind_degree;
    }

    public void setWind_degree(int wind_degree) {
        this.wind_degree = wind_degree;
    }

    public String getWind_dir() {
        return wind_dir;
    }

    public void setWind_dir(String wind_dir) {
        this.wind_dir = wind_dir;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getPrecip() {
        return precip;
    }

    public void setPrecip(int precip) {
        this.precip = precip;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getCloudcover() {
        return cloudcover;
    }

    public void setCloudcover(int cloudcover) {
        this.cloudcover = cloudcover;
    }

    public int getFeelslike() {
        return feelslike;
    }

    public void setFeelslike(int feelslike) {
        this.feelslike = feelslike;
    }

    public int getUv_index() {
        return uv_index;
    }

    public void setUv_index(int uv_index) {
        this.uv_index = uv_index;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public String getIs_day() {
        return is_day;
    }

    public void setIs_day(String is_day) {
        this.is_day = is_day;
    }
}
