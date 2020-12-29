package com.example.tubes03_a;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;

import java.util.List;

public class MainActivity extends AppCompatActivity implements FragmentListener{

    private DetailsFragment detailsFragment;
    protected FilterFragment filterFragment;
    private MapFragment mapFragment;
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
        this.welcomeFragment = WelcomeFragment.newInstance();
        this.fragmentManager = getSupportFragmentManager();

        changePage(2);
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
        }
        this.ft.commit();
    }

    @Override
    public void createDetails(BikeReport report) {
        //Mengirim bundle ke details fragment
        this.ft = this.fragmentManager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putParcelable("report", report);
        this.detailsFragment.setArguments(bundle);
        changePage(4);
    }

}