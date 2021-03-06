package com.example.tubes03_a;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;

import org.parceler.Parcels;

import java.util.List;

public class MainActivity extends AppCompatActivity implements FragmentListener{

    private DetailsFragment detailsFragment;
    protected FilterFragment filterFragment;
    private MapFragment mapFragment;
    private History history;
    private WelcomeFragment welcomeFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Assign variables
        this.detailsFragment = DetailsFragment.newInstance();
        this.filterFragment = FilterFragment.newInstance();
        this.mapFragment = MapFragment.newInstance();
        this.history = History.newInstance();
        this.welcomeFragment = WelcomeFragment.newInstance();
        this.fragmentManager = getSupportFragmentManager();

        changePage(1);
    }

    @Override
    public void changePage(int page) {
        this.ft = this.fragmentManager.beginTransaction();

        if (page == 1) {
            ft.replace(R.id.fragment_container, this.welcomeFragment);
        }else if (page == 2) {
            ft.replace(R.id.fragment_container, this.mapFragment).addToBackStack(null);
        }else if (page == 3) {
            ft.replace(R.id.fragment_container, this.filterFragment).addToBackStack(null);
        }else if (page == 4) {
            ft.replace(R.id.fragment_container, this.detailsFragment).addToBackStack(null);
        }else if (page == 5) {
            ft.replace(R.id.fragment_container, this.history).addToBackStack(null);
        }
        this.ft.commit();
    }

    @Override
    public void createDetails(BikeReport report) {
        //Mengirim bundle ke details fragment
        this.ft = this.fragmentManager.beginTransaction();

        Parcelable wrapped = Parcels.wrap(report);
        Bundle bundle = new Bundle();
        bundle.putParcelable("report", wrapped);
        this.detailsFragment.setArguments(bundle);
        changePage(4);
    }

    @Override
    public void sendProximity(String proximity) {
        this.filterFragment.setProximity(proximity);
//        this.filterFragment.setText(proximity);
        changePage(3);
    }

}