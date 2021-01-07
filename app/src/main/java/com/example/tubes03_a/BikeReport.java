package com.example.tubes03_a;

import android.os.Parcel;
import android.os.Parcelable;

//Kelas Model Bike Report
public class BikeReport implements Parcelable {
    private String title, type, address, link, desc;
    private int occurredAt;

    //Constructor
    public BikeReport(String title, String type, int occurredAt,
                      String address, String link, String desc) {
        this.title = title;
        this.type = type;
        this.occurredAt = occurredAt;
        this.address = address;
        this.link = link;
        this.desc = desc;
    }

    protected BikeReport(Parcel in) {
        title = in.readString();
        type = in.readString();
        occurredAt = in.readInt();
        address = in.readString();
        link = in.readString();
        desc = in.readString();
    }

    public static final Creator<BikeReport> CREATOR = new Creator<BikeReport>() {
        @Override
        public BikeReport createFromParcel(Parcel in) {
            return new BikeReport(in);
        }

        @Override
        public BikeReport[] newArray(int size) {
            return new BikeReport[size];
        }
    };

    @Override
    public String toString() {
        return "BikeReport{" +
                "title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", occurredAt='" + occurredAt + '\'' +
                ", address='" + address + '\'' +
                ", desc='" + desc + '\'' +
                '}';
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
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.type);
        dest.writeInt(this.occurredAt);
        dest.writeString(this.address);
        dest.writeString(this.desc);
    }
}
