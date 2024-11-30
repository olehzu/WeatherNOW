////package com.example.weatherapp.ui.home;
////
////import android.Manifest;
////import android.content.pm.PackageManager;
////import android.location.Address;
////import android.location.Geocoder;
////import android.location.Location;
////import android.os.Bundle;
////import android.view.LayoutInflater;
////import android.view.View;
////import android.view.ViewGroup;
////import android.widget.TextView;
////import android.widget.Toast;
////
////import androidx.annotation.NonNull;
////import androidx.core.app.ActivityCompat;
////import androidx.core.content.ContextCompat;
////import androidx.fragment.app.Fragment;
////import androidx.lifecycle.ViewModelProvider;
////
////import com.example.weatherapp.CircleTransform;
////import com.example.weatherapp.CustomBaseAdapter;
////import com.example.weatherapp.MainActivity;
////import com.example.weatherapp.R;
////import com.example.weatherapp.WeatherDataService;
////import com.example.weatherapp.WeatherReportModel;
////import com.example.weatherapp.databinding.FragmentHomeBinding;
////import com.google.android.gms.location.FusedLocationProviderClient;
////import com.google.android.gms.location.LocationServices;
////import com.google.android.gms.tasks.OnSuccessListener;
////import com.squareup.picasso.MemoryPolicy;
////import com.squareup.picasso.NetworkPolicy;
////import com.squareup.picasso.Picasso;
////
////import java.io.IOException;
////import java.util.ArrayList;
////import java.util.List;
////import java.util.Locale;
////
////public class HomeFragment extends Fragment {
////
////
////    private FragmentHomeBinding binding;
////    FusedLocationProviderClient fusedLocationProviderClient;
////    private final static int REQUEST_CODE = 180;
////
////    private HomeViewModel homeViewModel;
////    public ArrayList<String> weatherSections= new ArrayList<String>();
////
////    public View onCreateView(@NonNull LayoutInflater inflater,
////                             ViewGroup container, Bundle savedInstanceState) {
////        homeViewModel =
////                new ViewModelProvider(this).get(HomeViewModel.class);
////
////        binding = FragmentHomeBinding.inflate(inflater, container, false);
////        View root = binding.getRoot();
////
//////        final TextView temp_data = binding.twWeatherText;
//////        homeViewModel.getText().observe(getViewLifecycleOwner(), temp_data::setText);
////
////        weatherSections.add("Wind Speed");
////        weatherSections.add("Wind Direction");
////        weatherSections.add("Pressure");
////        weatherSections.add("Precipitation");
////        weatherSections.add("Humidity");
////        weatherSections.add("Feels Like");
////        weatherSections.add("UV");
////        weatherSections.add("Visibility");
//
////        showLocationWeather();
////
////
////        return root;
////    }
////
////    private void showLocationWeather() {
////
////        WeatherDataService wds = new WeatherDataService(requireContext());
////
////        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity());
////
////        if(ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
////
////            fusedLocationProviderClient.getLastLocation()
////                    .addOnSuccessListener(new OnSuccessListener<Location>() {
////                        @Override
////                        public void onSuccess(Location location) {
////                            if (location != null) {
////                                Geocoder geocoder = new Geocoder(requireContext(), Locale.getDefault());
////                                try {
////                                    List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
////
////                                    wds.getTemperature(addresses.get(0).getLocality(), new WeatherDataService.VolleyResponseListener() {
////                                        @Override
////                                        public void onError(String message) {
////                                            Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show();
////                                        }
////
////                                        @Override
////                                        public void onResponse(String temperature) {
////                                            MainActivity.temp_text.setText(temperature+"\u2103");
////                                        }
////                                    });
////
////                                    wds.weatherReport(addresses.get(0).getLocality(), new WeatherDataService.WeatherResponseListener() {
////                                        @Override
////                                        public void onError(String message) {
////                                            Toast.makeText(requireContext(), "error", Toast.LENGTH_SHORT).show();
////                                        }
////
////                                        @Override
////                                        public void onResponse(List<WeatherReportModel> report) {
////                                            CustomBaseAdapter cbs = new CustomBaseAdapter(requireActivity(), report.get(report.size()-1).toStringArray(), weatherSections, report.get(report.size()-1).getWind_degree());
////                                            //ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, R.layout.row, R.id.textViewRow, report);
////                                            MainActivity.lv_weatherReports.setAdapter(cbs);
////                                            Picasso.get()
////                                                    .load(report.get(report.size()-1).getWeather_icons().substring(1, report.get(report.size()-1).getWeather_icons().length()-1))
////                                                    .transform(new CircleTransform(20))
////                                                    .networkPolicy(NetworkPolicy.NO_CACHE)
////                                                    .memoryPolicy(MemoryPolicy.NO_CACHE)
////                                                    .placeholder(R.drawable.baseline_settings_24)
////                                                    .error(R.drawable.ic_dashboard_black_24dp)
////                                                    .into(MainActivity.imageView);
////                                            MainActivity.weather_tag.setText(report.get(report.size()-1).getWeather_descriptions());
////                                            //Picasso.get().invalidate(report.get(0).getWeather_icons().substring(1, report.get(0).getWeather_icons().length()-1));
////                                        }
////                                    });
////
////                                    MainActivity.location_tag.setText(addresses.get(0).getLocality().toString());
////
////
////
////                                } catch (IOException e) {
////                                    throw new RuntimeException(e);
////                                }
////                            }
////                        }
////                    });
////
////        }else {
////
////            askPermission();
////
////        }
////
////
////    }
////
////    public void askPermission() {
////
////        ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
////    }
////
////    @Override
////    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
////
////        if (requestCode == REQUEST_CODE) {
////
////            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
////
////                showLocationWeather();
////            }
////        }
////
////
////        else{
////            Toast.makeText(requireContext(), "error", Toast.LENGTH_SHORT).show();
////        }
////        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
////    }
////
////
////    @Override
////    public void onDestroyView() {
////        super.onDestroyView();
////        binding = null;
////    }
////}
//
//// Observe ViewModel LiveData
//package com.example.weatherapp.ui.home;
//
//import android.Manifest;
//import android.content.pm.PackageManager;
//import android.location.Address;
//import android.location.Geocoder;
//import android.location.Location;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.core.app.ActivityCompat;
//import androidx.core.content.ContextCompat;
//import androidx.fragment.app.Fragment;
//import androidx.lifecycle.SavedStateViewModelFactory;
//import androidx.lifecycle.ViewModelProvider;
//import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
//
//import com.example.weatherapp.CircleTransform;
//import com.example.weatherapp.CustomBaseAdapter;
//import com.example.weatherapp.MainActivity;
//import com.example.weatherapp.R;
//import com.example.weatherapp.WeatherDataService;
//import com.example.weatherapp.WeatherReportModel;
//import com.example.weatherapp.databinding.FragmentHomeBinding;
//import com.google.android.gms.location.FusedLocationProviderClient;
//import com.google.android.gms.location.LocationServices;
//import com.squareup.picasso.MemoryPolicy;
//import com.squareup.picasso.NetworkPolicy;
//import com.squareup.picasso.Picasso;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Locale;
//
//public class HomeFragment extends Fragment {
//
//    private FragmentHomeBinding binding;
//    private FusedLocationProviderClient fusedLocationProviderClient;
//    private static final int REQUEST_CODE = 180;
//    private SwipeRefreshLayout swipeRefreshLayout;
//
//    private HomeViewModel homeViewModel;
//    public ArrayList<String> weatherSections = new ArrayList<>();
//
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        // Use SavedStateViewModelFactory to provide the SavedStateHandle to the ViewModel
//        homeViewModel = new ViewModelProvider(this, new SavedStateViewModelFactory(requireActivity().getApplication(), requireActivity())).get(HomeViewModel.class);
//
//        binding = FragmentHomeBinding.inflate(inflater, container, false);
//        View root = binding.getRoot();
//
//
//
//        // Initialize weather sections
//        weatherSections.add("Wind Speed");
//        weatherSections.add("Wind Direction");
//        weatherSections.add("Pressure");
//        weatherSections.add("Precipitation");
//        weatherSections.add("Humidity");
//        weatherSections.add("Feels Like");
//        weatherSections.add("UV");
//        weatherSections.add("Visibility");
//
//        // Observe ViewModel LiveData
//        homeViewModel.getTemperature().observe(getViewLifecycleOwner(), temperature -> {
//            MainActivity.temp_text.setText(temperature + "\u2103");
//        });
//
//        homeViewModel.getWeatherReports().observe(getViewLifecycleOwner(), reports -> {
//            if (reports != null) {
//                CustomBaseAdapter adapter = new CustomBaseAdapter(requireActivity(), new ArrayList<>(reports), weatherSections, 0);
//                MainActivity.lv_weatherReports.setAdapter(adapter);
//            }
//        });
//
//        homeViewModel.getLocation().observe(getViewLifecycleOwner(), location -> {
//            MainActivity.location_tag.setText(location);
//        });
//
//        homeViewModel.getWeatherDescription().observe(getViewLifecycleOwner(), description -> {
//            MainActivity.weather_tag.setText(description);
//        });
//
//        homeViewModel.getWeatherIconUrl().observe(getViewLifecycleOwner(), url -> {
//            Picasso.get()
//                    .load(url)
//                    .transform(new CircleTransform(20))
//                    .networkPolicy(NetworkPolicy.NO_CACHE)
//                    .memoryPolicy(MemoryPolicy.NO_CACHE)
//                    .placeholder(R.drawable.baseline_settings_24)
//                    .error(R.drawable.ic_dashboard_black_24dp)
//                    .into(MainActivity.imageView);
//        });
//
//        // Load data if not already present
//        if (homeViewModel.getTemperature().getValue() == null) {
//            showLocationWeather();
//        }
//
//
//        return root;
//    }
//
//    private void refreshWeatherData() {
//        showLocationWeather();
//    }
//
//    private void showLocationWeather() {
//        WeatherDataService wds = new WeatherDataService(requireContext());
//        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity());
//
//        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
//            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(location -> {
//                if (location != null) {
//                    Geocoder geocoder = new Geocoder(requireContext(), Locale.getDefault());
//                    try {
//                        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
//
//                        String locality = addresses.get(0).getLocality();
//                        homeViewModel.setLocation(locality);
//
//                        wds.getTemperature(locality, new WeatherDataService.VolleyResponseListener() {
//                            @Override
//                            public void onError(String message) {
//                                Toast.makeText(requireContext(), "Error fetching temperature", Toast.LENGTH_SHORT).show();
//                            }
//
//                            @Override
//                            public void onResponse(String temperature) {
//                                homeViewModel.setTemperature(temperature);
//                            }
//                        });
//
//                        wds.weatherReport(locality, new WeatherDataService.WeatherResponseListener() {
//                            @Override
//                            public void onError(String message) {
//                                Toast.makeText(requireContext(), "Error fetching weather report", Toast.LENGTH_SHORT).show();
//                            }
//
//                            @Override
//                            public void onResponse(List<WeatherReportModel> report) {
//                                if (!report.isEmpty()) {
//                                    WeatherReportModel latestReport = report.get(report.size() - 1);
//                                    ArrayList<String> reportArray = new ArrayList<>(latestReport.toStringArray());
//                                    homeViewModel.setWeatherReports(reportArray);
//                                    homeViewModel.setWeatherDescription(latestReport.getWeather_descriptions());
//                                    homeViewModel.setWeatherIconUrl(latestReport.getWeather_icons()
//                                            .substring(1, latestReport.getWeather_icons().length() - 1));
//                                }
//                            }
//                        });
//                    } catch (IOException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//            });
//        } else {
//            askPermission();
//        }
//    }
//
//    private void askPermission() {
//        ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        if (requestCode == REQUEST_CODE) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                showLocationWeather();
//            } else {
//                Toast.makeText(requireContext(), "Permission denied", Toast.LENGTH_SHORT).show();
//            }
//        }
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        showLocationWeather();
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        binding = null;
//    }
//}
package com.example.weatherapp.ui.home;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.weatherapp.CircleTransform;
import com.example.weatherapp.CustomBaseAdapter;
import com.example.weatherapp.MainActivity;
import com.example.weatherapp.R;
import com.example.weatherapp.WeatherDataService;
import com.example.weatherapp.WeatherReportModel;
import com.example.weatherapp.databinding.FragmentHomeBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HomeFragment extends Fragment {


    private FragmentHomeBinding binding;
    FusedLocationProviderClient fusedLocationProviderClient;
    private final static int REQUEST_CODE = 180;

    public ArrayList<String> weatherSections= new ArrayList<String>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView temp_data = binding.twWeatherText;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), temp_data::setText);

        weatherSections.add("Wind Speed");
        weatherSections.add("Wind Direction");
        weatherSections.add("Pressure");
        weatherSections.add("Precipitation");
        weatherSections.add("Humidity");
        weatherSections.add("Feels Like");
        weatherSections.add("UV");
        weatherSections.add("Visibility");

        showLocationWeather();


        return root;
    }

    private void showLocationWeather() {

        WeatherDataService wds = new WeatherDataService(requireContext());

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity());

        if(ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            fusedLocationProviderClient.getLastLocation()
                    .addOnSuccessListener(new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (location != null) {
                                Geocoder geocoder = new Geocoder(requireContext(), Locale.getDefault());
                                try {
                                    List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

                                    wds.getTemperature(addresses.get(0).getLocality(), new WeatherDataService.VolleyResponseListener() {
                                        @Override
                                        public void onError(String message) {
                                            Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show();
                                        }

                                        @Override
                                        public void onResponse(String temperature) {
                                            MainActivity.temp_text.setText(temperature+"\u2103");
                                        }
                                    });

                                    wds.weatherReport(addresses.get(0).getLocality(), new WeatherDataService.WeatherResponseListener() {
                                        @Override
                                        public void onError(String message) {
                                            Toast.makeText(requireContext(), "error", Toast.LENGTH_SHORT).show();
                                        }

                                        @Override
                                        public void onResponse(List<WeatherReportModel> report) {
                                            CustomBaseAdapter cbs = new CustomBaseAdapter(requireActivity(), report.get(report.size()-1).toStringArray(), weatherSections, report.get(report.size()-1).getWind_degree());
                                            //ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, R.layout.row, R.id.textViewRow, report);
                                            MainActivity.lv_weatherReports.setAdapter(cbs);
                                            Picasso.get()
                                                    .load(report.get(report.size()-1).getWeather_icons().substring(1, report.get(report.size()-1).getWeather_icons().length()-1))
                                                    .transform(new CircleTransform(20))
                                                    .networkPolicy(NetworkPolicy.NO_CACHE)
                                                    .memoryPolicy(MemoryPolicy.NO_CACHE)
                                                    .placeholder(R.drawable.baseline_settings_24)
                                                    .error(R.drawable.ic_dashboard_black_24dp)
                                                    .into(MainActivity.imageView);
                                            MainActivity.weather_tag.setText(report.get(report.size()-1).getWeather_descriptions());
                                            //Picasso.get().invalidate(report.get(0).getWeather_icons().substring(1, report.get(0).getWeather_icons().length()-1));
                                        }
                                    });

                                    MainActivity.location_tag.setText(addresses.get(0).getLocality().toString());



                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }
                    });

        }else {

            askPermission();

        }


    }

    public void askPermission() {

        ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == REQUEST_CODE) {

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                showLocationWeather();
            }
        }


        else{
            Toast.makeText(requireContext(), "error", Toast.LENGTH_SHORT).show();
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}