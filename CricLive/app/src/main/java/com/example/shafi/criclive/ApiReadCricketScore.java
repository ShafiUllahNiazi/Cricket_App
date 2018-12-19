package com.example.shafi.criclive;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ApiReadCricketScore extends AsyncTask<String, Void, String> {


    ArrayList<LiveMatchesDescriptiveModelClass> listLive;
    LiveMatchesModelClass itemOfLiveMatches;
    MyAdapter myAdapter;



    public ApiReadCricketScore(MyAdapter myAdapter, ArrayList<LiveMatchesDescriptiveModelClass> listLive, LiveMatchesModelClass itemOfLiveMatches) {

        this.myAdapter = myAdapter;
        this.listLive= listLive;
        this.itemOfLiveMatches=itemOfLiveMatches;

    }


    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);


        try {
            JSONObject jsonObject = new JSONObject(result);
            listLive.add(new LiveMatchesDescriptiveModelClass(itemOfLiveMatches,jsonObject));
            myAdapter.notifyItemInserted(listLive.size()-1);


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected String doInBackground(String... urls) {
        String result= "";
        URL url;
        HttpURLConnection urlConnection = null;
        try {
            url = new URL(urls[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStreamReader = urlConnection.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStreamReader);
            int data = reader.read();
            while(data != -1){
                char current = (char) data;
                result += current;
                data = reader.read();
            }
            return result;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }
}
