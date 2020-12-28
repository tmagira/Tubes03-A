package com.example.tubes03_a;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FilterFragment extends Fragment implements View.OnClickListener{
    private FragmentListener listener;

    private Button btnFind;
    private EditText etLocation;
    private RequestThread requestThread;
    private UIThreadWrapper uiThreadWrapper;
    private MainActivity mainActivity;
    private ArrayList<BikeReport> reports = new ArrayList<>();
    private ListView listView;

    public String proximity;

    public FilterFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.filter_fragment,container, false);

        //Assign view
        this.uiThreadWrapper = new UIThreadWrapper(this);
        this.btnFind = view.findViewById(R.id.btn_find);
        this.etLocation = view.findViewById(R.id.et_location);

        this.btnFind.setOnClickListener(this);

        this.reports.add(new BikeReport("ok"));
        //BikeReportAdapter adapter= new BikeReportAdapter(getContext(), R.layout.list_layout, reports);
        this.listView = view.findViewById(R.id.list_report);
        //listView.setAdapter(adapter);

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

    public static FilterFragment newInstance(){
        FilterFragment fragment = new FilterFragment();
        return fragment;
    }

    @Override
    public void onClick(View v) {
        this.proximity = this.etLocation.getText().toString();
        this.requestThread = new RequestThread(this.mainActivity,this.uiThreadWrapper, this.proximity);
        this.requestThread.startThread();
    }

    public void loadData(ArrayList<BikeReport> reports) {
        BikeReportAdapter adapter= new BikeReportAdapter(getActivity(), reports, this.listener);
        listView.setAdapter(adapter);
        Log.d("maknyus", "loadData: berhasil bosqu"+ reports.toString());
    }
}
