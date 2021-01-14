package com.example.tubes03_a;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.beardedhen.androidbootstrap.BootstrapButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static androidx.core.content.ContextCompat.getSystemService;

public class FilterFragment extends Fragment implements View.OnClickListener{
    private FragmentListener listener;

    private BootstrapButton btnFind;
    private EditText etLocation;
    private RequestThread requestThread;
    private UIThreadWrapper uiThreadWrapper;
    private MainActivity mainActivity;
    private ArrayList<BikeReport> reports;
    private ListView listView;
    public String proximity, type;
    BikeReportAdapter adapter;
    DataBaseHandler dataBaseHandler;

    private Spinner spinnerType;
    public FilterFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.filter_fragment,container, false);

        //Assign view
        this.uiThreadWrapper = new UIThreadWrapper(this);
        this.btnFind = view.findViewById(R.id.btn_find);
        this.etLocation = view.findViewById(R.id.et_location);
        this.spinnerType = view.findViewById(R.id.spinner_type);

        this.btnFind.setOnClickListener(this);
        reports  = new ArrayList<>();
        this.listView = view.findViewById(R.id.list_report);
        this.dataBaseHandler = new DataBaseHandler(getActivity());
        this.adapter= new BikeReportAdapter(getActivity(), reports, this.listener,dataBaseHandler);

        //Adapter Dropdown
        String[] type = new String[]{"All", "Crash", "Theft", "Hazard", "Chop Shop", "Infrastructure"};
        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item,type);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spinnerType.setAdapter(arrayAdapter);

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
        if(v==this.btnFind){
            //Get Incident type
            this.type = this.spinnerType.getSelectedItem().toString().toLowerCase();
            if(type.equalsIgnoreCase("all")){
                this.type="";
            }

            //get proximity/location
            this.proximity = this.etLocation.getText().toString();

            //Membuat thread untuk request data ke BikeWise API
            this.requestThread = new RequestThread(this.mainActivity,this.uiThreadWrapper, this.proximity, this.type);
            this.requestThread.startThread();
        }
    }

    //Menampilkan data incident dari BikeWise API ke dalam list
    public void loadData(ArrayList<BikeReport> reports) {
        adapter= new BikeReportAdapter(getActivity(), reports, this.listener,dataBaseHandler);
        listView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        //etLocation.setText(proximity);
        this.requestThread = new RequestThread(this.mainActivity,this.uiThreadWrapper, this.proximity, this.type);
        this.requestThread.startThread();
    }

    public void setProximity(String proximity) {
        this.proximity = proximity;
    }
}
