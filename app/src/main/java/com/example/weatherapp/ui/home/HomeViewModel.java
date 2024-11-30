package com.example.weatherapp.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class HomeViewModel extends ViewModel {
    private final SavedStateHandle savedStateHandle;

    public HomeViewModel(SavedStateHandle savedStateHandle) {
        this.savedStateHandle = savedStateHandle;
    }

    public void setTemperature(String temp) {
        savedStateHandle.set("temperature", temp);
    }

    public LiveData<String> getTemperature() {
        return savedStateHandle.getLiveData("temperature");
    }

    public void setWeatherReports(ArrayList<String> reports) {
        savedStateHandle.set("weatherReports", reports);
    }

    public LiveData<ArrayList<String>> getWeatherReports() {
        return savedStateHandle.getLiveData("weatherReports");
    }

    public void setLocation(String loc) {
        savedStateHandle.set("location", loc);
    }

    public LiveData<String> getLocation() {
        return savedStateHandle.getLiveData("location");
    }

    public void setWeatherDescription(String description) {
        savedStateHandle.set("weatherDescription", description);
    }

    public LiveData<String> getWeatherDescription() {
        return savedStateHandle.getLiveData("weatherDescription");
    }

    public void setWeatherIconUrl(String url) {
        savedStateHandle.set("weatherIconUrl", url);
    }

    public LiveData<String> getWeatherIconUrl() {
        return savedStateHandle.getLiveData("weatherIconUrl");
    }
}