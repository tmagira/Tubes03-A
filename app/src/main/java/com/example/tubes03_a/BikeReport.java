package com.example.tubes03_a;

public class BikeReport {
    private String title;

    public BikeReport(String title) {
        this.title = title;
    }

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
}
