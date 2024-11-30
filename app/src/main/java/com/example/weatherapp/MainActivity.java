package com.example.weatherapp;

import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.Manifest;

import com.example.weatherapp.ui.home.HomeViewModel;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.weatherapp.databinding.ActivityMainBinding;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity{

    public static Button btn_show;
    public static EditText et_dataInput;
    public static ListView lv_weatherReports;
    public static TextView temp_text;
    public static TextView location_tag;
    public static TextView weather_tag;
    public static ImageView imageView;
    public static ListView lv_weatherReports_list;
    public static TextView temp_text_list;
    public static TextView location_tag_list;
    public static TextView weather_tag_list;
    public static ImageView imageView_list;


    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_about)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }


        //assign values to each control
        btn_show = findViewById(R.id.btn_show);
        et_dataInput = findViewById(R.id.et_dataInput);
        lv_weatherReports = findViewById(R.id.lv_weatherReport);
        temp_text = findViewById(R.id.tw_weatherText);
        location_tag = findViewById(R.id.location_tag);
        imageView = findViewById(R.id.weather_image);
        weather_tag = findViewById(R.id.weather_tag);
        lv_weatherReports_list = findViewById(R.id.lv_weatherReport_list);
        temp_text_list = findViewById(R.id.tw_weatherText_list);
        location_tag_list = findViewById(R.id.location_tag_list);
        weather_tag_list = findViewById(R.id.weather_tag_list);
        imageView_list = findViewById(R.id.weather_image_list);

    }
}

