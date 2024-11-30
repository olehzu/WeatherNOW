//package com.example.weatherapp.ui;
//
//
//import android.Manifest;
//import android.app.Activity;
//import android.content.Context;
//import android.content.pm.PackageManager;
//import android.location.Address;
//import android.location.Geocoder;
//import android.location.Location;
//import android.widget.ArrayAdapter;
//import android.widget.ListView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.core.app.ActivityCompat;
//import androidx.core.content.ContextCompat;
//import androidx.core.content.PackageManagerCompat;
//
//import com.example.weatherapp.MainActivity;
//import com.example.weatherapp.WeatherDataService;
//import com.example.weatherapp.WeatherReportModel;
//import com.google.android.gms.location.FusedLocationProviderClient;
//import com.google.android.gms.location.LocationServices;
//import com.google.android.gms.tasks.OnSuccessListener;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.Locale;
//
//public class LocationWeather {
//
//    Context context;
//    Activity activity;
//    TextView temp_text;
//    ListView lv_weatherReports;
//    TextView location_tag;
//
//    FusedLocationProviderClient fusedLocationProviderClient;
//
//    private final static int REQUEST_CODE = 180;
//
//    public LocationWeather(Context co, Activity av, TextView tt, ListView ll, TextView lt
//                           ) {
//        context = co;
//        activity = av;
//        temp_text = tt;
//        lv_weatherReports = ll;
//        location_tag = lt;
//    }
//
//    public void showLocationWeather() {
//
//        WeatherDataService wds = new WeatherDataService(context);
//
//        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity);
//
//        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
//
//            fusedLocationProviderClient.getLastLocation()
//                    .addOnSuccessListener(new OnSuccessListener<Location>() {
//                        @Override
//                        public void onSuccess(Location location) {
//                            if (location != null) {
//                                Geocoder geocoder = new Geocoder(context, Locale.getDefault());
//                                try {
//                                    List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
//
//                                    wds.getTemperature(addresses.get(0).getLocality(), new WeatherDataService.VolleyResponseListener() {
//                                        @Override
//                                        public void onError(String message) {
//                                            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
//                                        }
//
//                                        @Override
//                                        public void onResponse(String temperature) {
//                                            temp_text.setText(temperature + "\u2103");
//                                        }
//                                    });
//
//                                    wds.weatherReport(addresses.get(0).getLocality(), new WeatherDataService.WeatherResponseListener() {
//                                        @Override
//                                        public void onError(String message) {
//                                            Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
//                                        }
//
//                                        @Override
//                                        public void onResponse(List<WeatherReportModel> report) {
//                                            ArrayAdapter arrayAdapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, report);
//                                            lv_weatherReports.setAdapter(arrayAdapter);
//                                        }
//                                    });
//
//                                    location_tag.setText(addresses.get(0).getLocality().toString());
//
//
//                                } catch (IOException e) {
//                                    throw new RuntimeException(e);
//                                }
//                            }
//                        }
//                    });
//
//        } else {
//            MainActivity mainActivity = new MainActivity();
//
//            mainActivity.askPermission();
//
//        }
//
//
//    }
//}
//
