package com.example.weatherapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomBaseAdapter extends BaseAdapter {

    Context context;
    ArrayList<String> report;
    ArrayList<String> sections;
    LayoutInflater inflater;
    int angle;

    public CustomBaseAdapter(Context ctx, ArrayList<String> report, ArrayList<String> sect, int angle){
        this.context=ctx;
        this.report = report;
        this.sections = sect;
        this.inflater= LayoutInflater.from(ctx);
        this.angle=angle;
    }

    @Override
    public int getCount() {
        return report.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.row,null);
        TextView txtViewSect = (TextView) convertView.findViewById(R.id.textViewSect);
        TextView txtView = (TextView) convertView.findViewById(R.id.textViewRow);
        ImageView img = (ImageView) convertView.findViewById(R.id.comp_image);
        ImageView imgNed = (ImageView) convertView.findViewById(R.id.needle_image);
        txtView.setText(report.get(position));
        txtViewSect.setText(sections.get(position));
        if(position==1){
            img.setImageResource(R.drawable.compass);
            imgNed.setImageResource(R.drawable.needle);
            imgNed.setRotation(angle);
        }
        return convertView;
    }
}
