package com.example.tubes03_a;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class DetailsFragment extends Fragment implements  OnMapReadyCallback{
    private FragmentListener listener;
    private TextView tvTitle, tvType, tvDate, tvAddress, tvDesc;
    private ImageView ivPic;
    private BikeReport report;
    private GoogleMap googleMap;

    public DetailsFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.details_fragment,container, false);

        //Assign variables
        this.tvTitle = view.findViewById(R.id.tv_title);
        this.tvType = view.findViewById(R.id.tv_type);
        this.tvDate = view.findViewById(R.id.tv_date);
        this.tvAddress = view.findViewById(R.id.tv_address);
        this.tvDesc = view.findViewById(R.id.tv_desc);
        this.ivPic = view.findViewById(R.id.iv_picture);

        //Membaca Bundle
        Bundle b = getArguments();
        if (b != null) {
            this.report = b.getParcelable("report");

            //Reformat UNIX Timestamp
            long unixSeconds = this.report.getOccurredAt();
            Date date = new java.util.Date(unixSeconds*1000L);
            SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = simpleDateFormat.format(date);

            //Set texts
            this.tvTitle.setText(this.report.getTitle());
            this.tvType.setText(this.report.getType());
            this.tvDate.setText(String.valueOf(formattedDate));
            this.tvAddress.setText(this.report.getAddress());
            this.tvDesc.setText(this.report.getDesc());



            Picasso.get().load(this.report.getLink()).into(this.ivPic);
            Toast.makeText(getActivity(), getLocationFromAddress(getContext(),this.report.getAddress()).toString(),Toast.LENGTH_LONG).show();
          //  onMapReady(googleMap);
        } else {
            Toast.makeText(getActivity(), "Report Not Found",Toast.LENGTH_LONG).show();
        }

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

    public static DetailsFragment newInstance(){
        DetailsFragment fragment = new DetailsFragment();
        return fragment;
    }

    //ubah location name jadi lat long
    public LatLng getLocationFromAddress(Context context,String strAddress) {

        Geocoder coder = new Geocoder(context);
        List<Address> address;
        LatLng p1 = null;

        try {
            // May throw an IOException
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }

            Address location = address.get(0);
            p1 = new LatLng(location.getLatitude(), location.getLongitude() );

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return p1;
    }

    @Override
    public void onMapReady(GoogleMap googleMap){
        LatLng loc = getLocationFromAddress(getContext(),this.report.getAddress());
        googleMap.addMarker(new MarkerOptions()
                .position(loc)
                .title("Tempat Terjadi Insiden"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(loc));
    }

}
