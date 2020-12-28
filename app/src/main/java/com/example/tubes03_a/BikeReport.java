package com.example.tubes03_a;

import android.os.Parcel;
import android.os.Parcelable;

public class BikeReport implements Parcelable {
    private String title;

    public BikeReport(String title) {
        this.title = title;
    }

    protected BikeReport(Parcel in) {
        title = in.readString();
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
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
    }
}
