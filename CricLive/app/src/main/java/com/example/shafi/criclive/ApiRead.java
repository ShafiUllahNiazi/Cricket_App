package com.example.shafi.criclive;

import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
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

public class ApiRead extends AsyncTask<String, Void, String> {



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
//    SwipeRefreshLayout swipeRefreshLayoutOldMatches;

    public void setListLive(ArrayList<LiveMatchesDescriptiveModelClass> listLive, SwipeRefreshLayout swipeRefreshLayoutLiveMatches) {
        this.listLive = listLive;
        this.swipeRefreshLayout = swipeRefreshLayoutLiveMatches;
        this.listOld = new ArrayList<>();

    }



    TextView textView;


    public ApiRead (MyAdapter mAdapter,OldMatchAdapter oldMatchAdapter,UpcomingMatchesAdapter upcomingMatchesAdapter,ArrayList<UpcomingMatchesModelClass> listUpcomingMatches) {

        this.mAdapter=mAdapter;
        this.oldMatchAdapter= oldMatchAdapter;
        this.upcomingMatchesAdapter= upcomingMatchesAdapter;
        this.listUpcomingMatches = listUpcomingMatches;


    }


    private void populateLists(JSONObject jsonObject){


        JSONArray jsonArray = null;
        try {
            jsonArray = jsonObject.getJSONArray("matches");
            Log.d("jsonArraya",jsonArray.toString());

//                Log.d("SSSJson",jsonArray.g);
//                textView.setText(jsonArray.toString());

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


//                        OldMatchesModelClass itemOldMatches = new OldMatchesModelClass(unique_id,date,dateTimeGMT,team_1,team_2,type,squad,toss_winner_team,winner_team,matchStarted);
//                        String scoreUrlOldMatch = "https://cricapi.com/api/cricketScore?apikey=tcS2HOv2g6bRglcrHf1pXPoOOIn1&unique_id="+unique_id+"";
//                        ApiReadCricketScoreOldMatches readCricketScore = new ApiReadCricketScoreOldMatches(oldMatchAdapter,listOld,itemOldMatches);
//                        readCricketScore.execute(scoreUrlOldMatch);

                    }else{
                        toss_winner_team = jsonArray.getJSONObject(i).getString("toss_winner_team");


//                        winner_team = "N?A";
//                        listliveMatches.add(new LiveMatchesModelClass(unique_id,date,dateTimeGMT,team_1,team_2,type,squad,toss_winner_team,matchStarted));
                        LiveMatchesModelClass item = new LiveMatchesModelClass(unique_id,date,dateTimeGMT,team_1,team_2,type,squad,toss_winner_team,matchStarted);

                        String scoreUrl = "https://cricapi.com/api/cricketScore?apikey=tcS2HOv2g6bRglcrHf1pXPoOOIn1&unique_id="+Long.toString(unique_id);
                        ApiReadCricketScore apiReadCricketScore = new ApiReadCricketScore(mAdapter,listLive,item);
                        apiReadCricketScore.execute(scoreUrl);


                    }

                }else {

                    listUpcomingMatches.add(new UpcomingMatchesModelClass(unique_id,date,dateTimeGMT,team_1,team_2,type,squad,matchStarted));

                }





            }





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
            JSONObject jsonObject = new JSONObject(result);
            populateLists(jsonObject);


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

    public void setListOld(ArrayList<OldMatchesDescriptiveModelClass> listOld, SwipeRefreshLayout swipeRefreshLayoutOldMatches) {

        this.listOld = listOld;
        this.swipeRefreshLayout = swipeRefreshLayoutOldMatches;
        this.listLive = new ArrayList<>();

    }

    public void setListUpcoming(SwipeRefreshLayout swipeRefreshLayoutUpcomingMatches) {
        swipeRefreshLayout = swipeRefreshLayoutUpcomingMatches;
        this.listLive = new ArrayList<>();
        this.listOld = new ArrayList<>();
    }
}