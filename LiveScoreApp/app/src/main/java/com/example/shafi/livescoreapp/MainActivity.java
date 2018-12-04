package com.example.shafi.livescoreapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    ArrayList<OldMatches> oldMatches;
    Button button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        button=findViewById(R.id.button);
//        textView.setText("working in oncreate");

//        putRecyclerVIew();

    }

    public void displayScore(View view){


//        for (int i = 0; i < 100; i++) {
//
//
//
//            oldMatches.add(new OldMatches("abc","def","aaaa"));
//
//        }
////        String s = oldMatches.get(1).getTitle().toString();
////        textView.setText(s);
//        putRecyclerVIew();

//        String s = "https://cricapi.com/api/cricketScore?apikey=tcS2HOv2g6bRglcrHf1pXPoOOIn1&unique_id=1157378";
        String s = "https://cricapi.com/api/cricket?apikey=tcS2HOv2g6bRglcrHf1pXPoOOIn1";
        Myclass myclass = new Myclass();
        myclass.execute(s);
//        textView.setText("working button click");
//        Log.i("Khannn"," working button click");
    }

    private void putRecyclerVIew(){
        recyclerView = findViewById(R.id.recyclerView);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        // define an adapter
        mAdapter = new MyAdapter(getApplicationContext(),oldMatches);
        recyclerView.setAdapter(mAdapter);
    }


    public class Myclass extends AsyncTask<String, Void, String> {

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            try {
                JSONObject jsonObject = new JSONObject(result);
                String score =jsonObject.toString();
                JSONArray jsonArray = jsonObject.getJSONArray("data");
//
                oldMatches= new ArrayList<OldMatches>();
                for (int i = 0; i < jsonArray.length(); i++) {

                    String title = jsonArray.getJSONObject(i).getString("title");
                    String description = jsonArray.getJSONObject(i).getString("description");
                    String unique_id = jsonArray.getJSONObject(i).getString("unique_id");

                    oldMatches.add(new OldMatches(title,description,unique_id));

                }
//                for (int i = 0; i < 100; i++) {
//
//
//
//                    oldMatches.add(new OldMatches("abc","def","aaaa"));
//
//                }
//
                String s = oldMatches.get(1).getTitle().toString();
//                String score = jsonObject.getString("score");
//                JSONArray jsonArray = new JSONArray(score);
//                User.fromJson(jsonArray);


//                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,User.fromJson(jsonArray));
                textView.setText(s);
                putRecyclerVIew();
                Log.i("Khann"," workingg");
//                Log.i("Shafii",score);
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


}