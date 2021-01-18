package com.example.tubes03_a;

import com.orm.SugarRecord;
import com.orm.dsl.Column;
import com.orm.dsl.Unique;

import org.parceler.Parcel;

//Kelas Model Bike Report
@Parcel
public class BikeReport extends SugarRecord {

    @Unique
    @Column(name = "occurredAt")
    private Integer occurredAt;
    @Column(name = "id")
    private Integer id;
    @Column(name = "title")
    private String title;
    @Column(name = "type")
    private String type;
    @Column(name = "address")
    private String address;
    @Column(name = "linkImage")
    private String linkImage;
    @Column(name = "desc")
    private String desc;

    public BikeReport(){}

    //Constructor
    public BikeReport(int id,String title, String type, int occurredAt,
                      String address, String link, String desc) {
        this.id =id;
        this.title = title;
        this.type = type;
        this.occurredAt = occurredAt;
        this.address = address;
        this.linkImage = link;
        this.desc = desc;
    }

    public Long getId() {
        return Long.valueOf(id);
    }

    public void setId(int id) {
        this.id = id;
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
