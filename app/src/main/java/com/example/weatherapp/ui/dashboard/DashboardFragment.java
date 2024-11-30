package com.example.weatherapp.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.weatherapp.CircleTransform;
import com.example.weatherapp.CustomBaseAdapter;
import com.example.weatherapp.MainActivity;
import com.example.weatherapp.WeatherDataService;
import com.example.weatherapp.WeatherReportModel;
import com.example.weatherapp.databinding.FragmentDashboardBinding;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    public ArrayList<String> weatherSections= new ArrayList<String>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        weatherSections.add("Wind Speed");
        weatherSections.add("Wind Direction");
        weatherSections.add("Pressure");
        weatherSections.add("Precipitation");
        weatherSections.add("Humidity");
        weatherSections.add("Feels Like");
        weatherSections.add("UV");
        weatherSections.add("Visibility");

        final WeatherDataService wds = new WeatherDataService(requireContext());

//        final TextView textView = binding.textDashboard;
//        dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        try{
            MainActivity.temp_text_list.setText("AAAAAAAAAAAAAA");
        }catch (Exception E){
            Toast.makeText(requireContext(), "errorrrrrrrrrrr", Toast.LENGTH_SHORT).show();
        }

        try {
            MainActivity.btn_show.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String place = "";
                    place = MainActivity.et_dataInput.getText().toString();
                    if (place.isEmpty()) {
                        Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show();
                    } else {
                        wds.getTemperature(place, new WeatherDataService.VolleyResponseListener() {
                            @Override
                            public void onError(String message) {
                                Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onResponse(String temperature) {
                                MainActivity.temp_text_list.setText(temperature + "\u2103");
                            }
                        });

                        wds.weatherReport(place, new WeatherDataService.WeatherResponseListener() {
                            @Override
                            public void onError(String message) {
                                Toast.makeText(requireContext(), "error", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onResponse(List<WeatherReportModel> report) {
                                //ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, R.layout.row, R.id.textViewRow, report);
                                CustomBaseAdapter cbs = new CustomBaseAdapter(requireActivity(), report.get(0).toStringArray(), weatherSections, report.get(0).getWind_degree());
                                MainActivity.lv_weatherReports_list.setAdapter(cbs);
                                Picasso.get()
                                        .load(report.get(0).getWeather_icons().substring(1, report.get(0).getWeather_icons().length() - 1))
                                        .transform(new CircleTransform(20))
                                        .networkPolicy(NetworkPolicy.NO_CACHE)  // Bypass disk cache
                                        .memoryPolicy(MemoryPolicy.NO_CACHE)
                                        .into(MainActivity.imageView_list);
                                MainActivity.weather_tag_list.setText(report.get(0).getWeather_descriptions());
                            }
                        });
                    }

                }
            });

        }catch(Exception E){
            Toast.makeText(requireContext(), "error", Toast.LENGTH_SHORT).show();
        }


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}