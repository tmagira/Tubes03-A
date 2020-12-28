package com.example.tubes03_a;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class UIThreadWrapper extends Handler {
    protected MainActivity mainActivity;
    protected FilterFragment filterFragment;
    protected final int MSG_SEND_REQUEST = 0;
    protected String res = "";

    public UIThreadWrapper(FilterFragment filterFragment){
        this.filterFragment = filterFragment;
    }

    @Override
    public void handleMessage(Message msg){
        if(msg.what == this.MSG_SEND_REQUEST){
            ArrayList<BikeReport> reports = (ArrayList<BikeReport>) msg.obj;
            this.filterFragment.loadData(reports);
        }
    }

    public void setResult(ArrayList<BikeReport> result){
        Message msg = new Message();
        msg.what = MSG_SEND_REQUEST;
        msg.obj = result;
        this.sendMessage(msg);
        Log.d("maknyus", "setMsgShowResult: result "+result);
    }
}

