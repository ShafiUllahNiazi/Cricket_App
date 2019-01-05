package com.example.shafi.criclive.controllers.ApiReading;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.shafi.criclive.controllers.adapters.OldMatchAdapter;
import com.example.shafi.criclive.database.DatabasesInsertData;
import com.example.shafi.criclive.models.OldMatchesDescriptiveModelClass;
import com.example.shafi.criclive.models.OldMatchesModelClass;

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

    Context context;
    OldMatchAdapter oldMatchAdapter;
    ArrayList<OldMatchesDescriptiveModelClass> listOld;
    OldMatchesModelClass itemOldMatches;

    public ApiReadCricketScoreOldMatches(Context context, OldMatchAdapter oldMatchAdapter, ArrayList<OldMatchesDescriptiveModelClass> listOld, OldMatchesModelClass itemOldMatches) {
        this.oldMatchAdapter = oldMatchAdapter;
        this.listOld = listOld;
        this.itemOldMatches = itemOldMatches;
        this.context = context;

    }





    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);


        try {
            JSONObject jsonObject = new JSONObject(result);
            listOld.add(new OldMatchesDescriptiveModelClass(itemOldMatches,jsonObject));
            oldMatchAdapter.notifyItemInserted(listOld.size()-1);
            DatabasesInsertData databasesInsertData = new DatabasesInsertData(context);
            databasesInsertData.insertData(null,listOld,null);
            Log.d("DBBBBB",listOld.size()+"");


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
