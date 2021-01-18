package com.example.tubes03_a;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class History extends Fragment implements View.OnClickListener{
    private ListView listView;
    private FragmentListener listener;
    private ArrayList<BikeReport> reports = new ArrayList<>();
    private HistoryAdapter adapter;

    public History(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.history,container, false);

        //Assign view
        this.listView = view.findViewById(R.id.list_report_history);


        List<BikeReport> list = BikeReport.listAll(BikeReport.class);
        ArrayList<BikeReport> populationsArrayList = new ArrayList<>();
        BikeReport bikeReport = new BikeReport();

        for (int i = 0; i < list.size(); i++) {
            bikeReport = list.get(i);
            boolean isEquals = false;

            //Cek apakah judul yg sama sudah dimasukkan atau belum
            for(int j=0;j<=i-1;j++){
                if(bikeReport.getTitle().equalsIgnoreCase(list.get(j).getTitle())){
                isEquals = true;
                break;
                }
            }

            //Kalau belum dimasukkan, masukkan ke list
            if(!isEquals){
                populationsArrayList.add(bikeReport);
            }
        }

        this.adapter = new HistoryAdapter(getActivity(),populationsArrayList,listener);
        this.listView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof FragmentListener){
            this.listener = (FragmentListener) context;
        }else{
            throw new ClassCastException(context.toString()+ " Must Implement Fragment Listener");
        }
    }

    public static History newInstance(){
        History fragment = new History();
        return fragment;
    }


    @Override
    public void onClick(View v) {

    }
}
