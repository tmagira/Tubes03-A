package com.example.tubes03_a;

import android.os.Parcelable;

import java.util.List;

public interface FragmentListener {
    void changePage(int page);
    void createDetails(BikeReport report);
    void sendProximity(String proximity);
}
