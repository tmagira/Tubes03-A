package com.example.tubes03_a;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class History extends Fragment implements View.OnClickListener{
    private ListView listView;
    private FragmentListener listener;
    private Presenter presenter;

    public History(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.history,container, false);

        //Assign view
        this.listView = view.findViewById(R.id.list_report_history);
        presenter.loadData();
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

    //Menampilkan data incident dari BikeWise API ke dalam list
    public void loadData(ArrayList<BikeReport> reports) {

    }

    @Override
    public void onClick(View v) {

    }
}