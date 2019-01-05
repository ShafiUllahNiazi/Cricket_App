package com.example.shafi.criclive.controllers.ApiReading;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.widget.Toast;

import com.example.shafi.criclive.controllers.adapters.MyAdapter;
import com.example.shafi.criclive.controllers.adapters.OldMatchAdapter;
import com.example.shafi.criclive.controllers.adapters.UpcomingMatchesAdapter;
import com.example.shafi.criclive.database.DatabasesInsertData;
import com.example.shafi.criclive.models.LiveMatchesDescriptiveModelClass;
import com.example.shafi.criclive.models.LiveMatchesModelClass;
import com.example.shafi.criclive.models.OldMatchesDescriptiveModelClass;
import com.example.shafi.criclive.models.OldMatchesModelClass;
import com.example.shafi.criclive.models.UpcomingMatchesModelClass;

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

public class ApiReadOldMatches extends AsyncTask<String, Void, String> {
    private long unique_id;
    private String date;
    private String dateTimeGMT;
    private String team_1;
    private String team_2;
    private String type;
    private boolean squad;
    private String toss_winner_team;
    private String winner_team;
    private boolean matchStarted;



    ArrayList<UpcomingMatchesModelClass> listUpcomingMatches;
    MyAdapter mAdapter;
    OldMatchAdapter oldMatchAdapter;
    UpcomingMatchesAdapter upcomingMatchesAdapter;
    JSONObject jsonObject;
    ArrayList<LiveMatchesDescriptiveModelClass> listLive;
    ArrayList<OldMatchesDescriptiveModelClass> listOld;
    SwipeRefreshLayout swipeRefreshLayout;
    Context context;


    public ApiReadOldMatches(Context context, SwipeRefreshLayout swipeRefreshLayout, MyAdapter mAdapter, OldMatchAdapter oldMatchAdapter, UpcomingMatchesAdapter upcomingMatchesAdapter, ArrayList<LiveMatchesDescriptiveModelClass> listLive, ArrayList<OldMatchesDescriptiveModelClass> listOld, ArrayList<UpcomingMatchesModelClass> listUpcomingMatches) {

        this.mAdapter=mAdapter;
        this.oldMatchAdapter= oldMatchAdapter;
        this.upcomingMatchesAdapter= upcomingMatchesAdapter;
        this.listUpcomingMatches = listUpcomingMatches;
        this.context = context;
        this.listLive = listLive;
        this.listOld = listOld;
        this.swipeRefreshLayout = swipeRefreshLayout;


    }


    private void populateLists(JSONObject jsonObject){




        JSONArray jsonArray = null;
        try {
            jsonArray = jsonObject.getJSONArray("matches");
            Log.d("jsonArraya",jsonArray.toString());
            for (int i = 0; i < jsonArray.length(); i++) {

                unique_id = jsonArray.getJSONObject(i).getLong("unique_id");
                date = jsonArray.getJSONObject(i).getString("date");
                dateTimeGMT = jsonArray.getJSONObject(i).getString("dateTimeGMT");
                team_1 = jsonArray.getJSONObject(i).getString("team-1");
                team_2 = jsonArray.getJSONObject(i).getString("team-2");
                type = jsonArray.getJSONObject(i).getString("type");
                squad = jsonArray.getJSONObject(i).getBoolean("squad");

                matchStarted = jsonArray.getJSONObject(i).getBoolean("matchStarted");
                if (matchStarted){
                    if(jsonArray.getJSONObject(i).has("winner_team")){
                        toss_winner_team = jsonArray.getJSONObject(i).getString("toss_winner_team");
                        winner_team = jsonArray.getJSONObject(i).getString("winner_team");
                        OldMatchesModelClass itemOldMatches = new OldMatchesModelClass(unique_id,date,dateTimeGMT,team_1,team_2,type,squad,toss_winner_team,winner_team,matchStarted);
                        String scoreUrlOldMatch = "https://cricapi.com/api/cricketScore?apikey=tcS2HOv2g6bRglcrHf1pXPoOOIn1&unique_id="+unique_id+"";
                        ApiReadCricketScoreOldMatches readCricketScore = new ApiReadCricketScoreOldMatches(context,oldMatchAdapter,listOld,itemOldMatches);
                        readCricketScore.execute(scoreUrlOldMatch);

                    }else{
                        toss_winner_team = jsonArray.getJSONObject(i).getString("toss_winner_team");
//                        LiveMatchesModelClass item = new LiveMatchesModelClass(unique_id,date,dateTimeGMT,team_1,team_2,type,squad,toss_winner_team,matchStarted);
//                        String scoreUrl = "https://cricapi.com/api/cricketScore?apikey=tcS2HOv2g6bRglcrHf1pXPoOOIn1&unique_id="+Long.toString(unique_id);
//                        ApiReadCricketScore apiReadCricketScore = new ApiReadCricketScore(context,mAdapter,listLive,item);
//                        apiReadCricketScore.execute(scoreUrl);


                    }

                }else {

                    listUpcomingMatches.add(new UpcomingMatchesModelClass(unique_id,date,dateTimeGMT,team_1,team_2,type,squad,matchStarted));

                }





            }





            DatabasesInsertData databasesInsertData = new DatabasesInsertData(context);
            databasesInsertData.insertData(listLive,listOld,listUpcomingMatches);
            Log.d("sizeeeU",Integer.toString(listUpcomingMatches.size()));
            mAdapter.notifyDataSetChanged();
            oldMatchAdapter.notifyDataSetChanged();
            upcomingMatchesAdapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }

        swipeRefreshLayout.setRefreshing(false);



    }




    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        try {
            if (result!=null){
                JSONObject jsonObject = new JSONObject(result);
                populateLists(jsonObject);

            }

            Toast.makeText(context, "No internet Connection", Toast.LENGTH_SHORT).show();


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
