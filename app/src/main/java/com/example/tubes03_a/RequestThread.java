package com.example.tubes03_a;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import timber.log.Timber;
import java.util.List;

public class RequestThread implements Runnable {
    protected MainActivity mainActivity;
    protected Thread thread;
    protected UIThreadWrapper uiThreadWrapper;
    protected final String BASE_URL = "https://bikewise.org/api/v2/incidents?proximity=", PROXIMITY = "proximity", INCIDENT = "incident_type";

    protected String proximity;
    protected String type;


    public RequestThread(MainActivity mainActivity, UIThreadWrapper uiThreadWrapper, String proximity, String type) {
        this.thread = new Thread(this);
        this.mainActivity = mainActivity;
        this.uiThreadWrapper = uiThreadWrapper;
        this.proximity = proximity;
        this.type = type;
    }

    public void startThread() {
        this.thread.start();
    }

    public String inputStreamToString(InputStream inputStream) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        StringBuffer stringBuffer = new StringBuffer();
        String s;
        while ((s = bufferedReader.readLine()) != null) {
            stringBuffer.append(s);
        }
        return stringBuffer.toString();
    }

    @Override
    public void run() {
        Uri builtURI;
        if(this.type != ""){
            builtURI = Uri.parse(BASE_URL).buildUpon().appendQueryParameter(INCIDENT, this.type).appendQueryParameter(PROXIMITY, this.proximity).build();
        }else{
            builtURI = Uri.parse(BASE_URL).buildUpon().appendQueryParameter(PROXIMITY, this.proximity).build();
        }
        HttpURLConnection conn = null;
        InputStream inputStream = null;

        try {
            URL requestURL = new URL(builtURI.toString());
            conn = (HttpURLConnection) requestURL.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);

            conn.connect();

            inputStream = conn.getInputStream();
            String result = inputStreamToString(inputStream);
            try {
                JSONObject obj = new JSONObject(result);

                ArrayList<BikeReport> resultList = new ArrayList<>();

                for(int i=0;i<obj.getJSONArray("incidents").length();i++){
                    //Mengambil value dari JSON
                    String title = (String) obj.getJSONArray("incidents").getJSONObject(i).get("title");
                    String type = (String) obj.getJSONArray("incidents").getJSONObject(i).get("type");
                    int occuredAt = (int) obj.getJSONArray("incidents").getJSONObject(i).get("occurred_at");
                    String address = (String) obj.getJSONArray("incidents").getJSONObject(i).get("address");
                    String linkImage = "No Image";
                    try {
                        //jika kecelakaan ada incident maka no image akan terganti menjadi link kecelakaan
                        linkImage = (String) obj.getJSONArray("incidents").getJSONObject(i).getJSONObject("media").get("image_url");
                    }
                    catch(Exception e) {
                        Log.d("app", "run: Image null");
                    }

                    String desc = "No Description";
                    try {
                        desc = (String) obj.getJSONArray("incidents").getJSONObject(i).get("description");
                    }
                    catch(Exception e) {
                        Timber.d(e, e.getMessage());
                      //  Log.d("app", "run: Description null");
                    }
                    //buat objek bike new report
                    BikeReport newReport = new BikeReport(0,title, type, occuredAt, address,linkImage, desc);
                    resultList.add(newReport);
                }

                this.uiThreadWrapper.setResult(resultList);

            } catch (Throwable t) {
                Timber.d(t, t.getMessage());
                //Log.e("My App", "Could not parse malformed JSON: ");
            }

        } catch (MalformedURLException e) {
            Timber.d(e, e.getMessage());
           // e.printStackTrace();
        } catch (IOException e) {
            Timber.d(e, e.getMessage());
            //e.printStackTrace();
        } finally {
            conn.disconnect();
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    Timber.d(e, e.getMessage());
                   // e.printStackTrace();
                }
            }

        }
    }
}
