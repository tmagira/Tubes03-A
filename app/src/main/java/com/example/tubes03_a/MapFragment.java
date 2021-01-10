package com.example.tubes03_a;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapFragment extends Fragment implements View.OnClickListener {
    private FragmentListener listener;
    private Button btnSearchMarker;
    public String proximity;
    private LatLng pos;
    private String location;
    private UIThreadWrapper uiThreadWrapper;
    private MainActivity mainActivity;
    private RequestThread requestThread;

    public MapFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.map_fragment, container, false);
        this.btnSearchMarker = view.findViewById(R.id.btn_search_marker);
        this.uiThreadWrapper = new UIThreadWrapper(this);
        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.google_map);
        supportMapFragment.getMapAsync(new onMapReadyCallBack() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {
                        MarkerOptions markerOptions = new MarkerOptions();
                        markerOptions.position(latLng);
                        pos = latLng;
                        markerOptions.title(latLng.latitude + ":" + latLng.longitude);
                        googleMap.clear();
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                                latLng, 10
                        ));
                        googleMap.addMarker(markerOptions);
                        Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
                        try {
                            List<Address> list = geocoder.getFromLocation(pos.latitude, pos.longitude, 1);
                            location = list.get(0).getAddressLine(0) + ", " + list.get(0).getCountryName();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                });
            }
        });
        btnSearchMarker.setOnClickListener(this);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentListener) {
            this.listener = (FragmentListener) context;
        } else {
            throw new ClassCastException(context.toString() + " Must Implement Fragment Listener");
        }
    }

    public static MapFragment newInstance() {
        MapFragment fragment = new MapFragment();
        return fragment;
    }

    @Override
    public void onClick(View v) {
        if (v == this.btnSearchMarker) {
            this.proximity = location;
            this.proximity = "Jakarta";

            //Send Proximity to FilterFragment
            this.listener.sendProximity(this.proximity);
        }

    }
}
