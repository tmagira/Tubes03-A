package com.example.tubes03_a;

import java.util.ArrayList;
import java.util.List;

public class Presenter {

    public interface IMainActivity{
        void updateList(List<BikeReport>reportS);
    }
    protected DataBaseHandler sqlite;
    protected List<BikeReport>reports ;
    protected IMainActivity ui;

    public Presenter(IMainActivity ui,DataBaseHandler sqlite){
        this.reports = new ArrayList<>();
        this.ui = ui;
        this.sqlite=sqlite;
    }

    public void loadData(){
        this.reports=sqlite.getAllRecord();
        this.ui.updateList(this.reports);
    }

    public void loadSearchResult(String tag){
        this.reports=sqlite.getFilteredRecord(tag);
        this.ui.updateList(this.reports);
    }

    public int countItem(){
        return reports.size();
    }

}
