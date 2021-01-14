package com.example.tubes03_a;

import android.os.Parcelable;

import org.parceler.Parcel;

//Kelas Model Bike Report
@Parcel
public class BikeReport {
    private String title, type, address, linkImage, desc;
    private int occurredAt;

    public BikeReport(){}

    //Constructor
    public BikeReport(String title, String type, int occurredAt,
                      String address, String link, String desc) {
        this.title = title;
        this.type = type;
        this.occurredAt = occurredAt;
        this.address = address;
        this.linkImage = link;
        this.desc = desc;
    }

    public BikeReport(String string, String string1, int parseInt, String string2, String string3, String string4, String string5) {
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLink() {
        return linkImage;
    }

    public void setLink(String link) {
        this.linkImage = link;
    }

    public int getOccurredAt() {
        return occurredAt;
    }

    public void setOccurredAt(int occurredAt) {
        this.occurredAt = occurredAt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


}
