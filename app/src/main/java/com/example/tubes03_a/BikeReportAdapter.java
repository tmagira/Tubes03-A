package com.example.tubes03_a;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class BikeReportAdapter extends BaseAdapter {
    private ArrayList<BikeReport> reports;
    private Activity activity;
    private MainActivity mainActivity;
    private FragmentListener listener;

    public BikeReportAdapter(Activity activity, ArrayList<BikeReport> reports, FragmentListener listener) {
        this.reports = reports;
        this.activity = activity;
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return this.reports.size();
    }

    @Override
    public Object getItem(int position) {
            return reports.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        View convertView = LayoutInflater.from(this.activity).inflate(R.layout.list_layout, parent, false);
        BikeReport currentReport = (BikeReport) this.getItem(i);
        TextView title = convertView.findViewById(R.id.list_title);
        title.setText(currentReport.getTitle());

        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("lizt", "onClick: "+currentReport.getTitle());
                listener.createDetails(currentReport);
            }
        });
        return convertView;
    }
}
