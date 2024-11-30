package com.example.weatherapp.ui.about;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AboutInfoViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public AboutInfoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("WeatherNOW V1.0\n" +
                "Last update: November 2024\n\n" +
                "WeatherNOW is open to everyone to use, modify, and distribute the code in accordance with the terms of the license.\n\n" +
                "Made by: Oleh Zubariev, Texas Christian University class of 2027");
    }

    public LiveData<String> getText() {
        return mText;
    }
}