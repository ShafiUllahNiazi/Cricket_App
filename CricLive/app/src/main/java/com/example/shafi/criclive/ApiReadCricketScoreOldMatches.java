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

public class ApiReadCricketScoreOldMatches extends AsyncTask<String, Void, String> {


    public ApiReadCricketScoreOldMatches(OldMatchAdapter oldMatchAdapter, ArrayList<OldMatchesDescriptiveModelClass> listOld, OldMatchesModelClass itemOldMatches) {
        this.oldMatchAdapter = oldMatchAdapter;
        this.listOld = listOld;
        this.itemOldMatches = itemOldMatches;
    }

    OldMatchAdapter oldMatchAdapter;
    ArrayList<OldMatchesDescriptiveModelClass> listOld;
    OldMatchesModelClass itemOldMatches;



    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);


        try {
            JSONObject jsonObject = new JSONObject(result);
            listOld.add(new OldMatchesDescriptiveModelClass(itemOldMatches,jsonObject));
            oldMatchAdapter.notifyItemInserted(listOld.size()-1);


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
