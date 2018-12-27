package com.example.shafi.criclive;

import android.content.Context;
import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;

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

public class ApiReadScorecard extends AsyncTask<String, Void, String> {

    ArrayList<BattingCardModel> battingCardList;
    ArrayList<BowlingModelClass> bowlingCardList;
    ArrayList<Scorecard> scorecardList;

    private ViewPager viewPager;
    private TabLayout mTabLayout;
    private FragmentManager scorecardFragmentManager;
    Context context;

    private String pid;
    private String batsman;
    private String dismissal_info;
    private int runs;
    private int balls;
    private int fours;
    private int sixes;
    private int sR;
    private String dismissal;
    private JSONObject dismissal_by;
    private String detail;


    private String pidBowling;
    private String bowler;
    private String o;
    private String m;
    private String r;
    private String w;
    private String econ;
    private int zeros;
    private int foursBowler;
    private int sixesBowler;
    SwipeRefreshLayout swipeRefreshLayoutScorecard;

    public void setViews(ViewPager viewPager, TabLayout mTabLayout, FragmentManager scorecardFragmentManager, Context context, SwipeRefreshLayout swipeRefreshLayoutScorecard) {
        this.viewPager = viewPager;
        this.mTabLayout = mTabLayout;
        this.scorecardFragmentManager=scorecardFragmentManager;
        this.context = context;
        this.swipeRefreshLayoutScorecard = swipeRefreshLayoutScorecard;
    }


    @Override
    protected void onPostExecute(String result) {

        super.onPostExecute(result);
        try {
            scorecardList = new ArrayList<>();
            JSONObject jsonObject = new JSONObject(result);
            JSONObject data = jsonObject.getJSONObject("data");

            JSONArray batting = data.getJSONArray("batting");
            JSONArray bowling = data.getJSONArray("bowling");


            int numOftabs = batting.length();

            for (int j = 0 ; j<batting.length();j++){

                battingCardList = new ArrayList<>();
                bowlingCardList = new ArrayList<>();

                JSONArray score = batting.getJSONObject(j).getJSONArray("scores");

                String titleBatting = batting.getJSONObject(j).getString("title");
                String titleBowling = bowling.getJSONObject(j).getString("title");


                JSONArray scoreBowling = bowling.getJSONObject(j).getJSONArray("scores");

                for (int i = 0; i < scoreBowling.length(); i++){
                    pidBowling = scoreBowling.getJSONObject(i).getString("pid");
                    bowler = scoreBowling.getJSONObject(i).getString("bowler");
                    o = scoreBowling.getJSONObject(i).getString("O");
                    m = scoreBowling.getJSONObject(i).getString("M");
                    r = scoreBowling.getJSONObject(i).getString("R");
                    w = scoreBowling.getJSONObject(i).getString("W");
                    econ = scoreBowling.getJSONObject(i).getString("Econ");


                    zeros = scoreBowling.getJSONObject(i).getInt("0s");
                    foursBowler = scoreBowling.getJSONObject(i).getInt("4s");
                    sixesBowler = scoreBowling.getJSONObject(i).getInt("6s");

                    bowlingCardList.add(new BowlingModelClass(pidBowling,bowler,o,m,r,w,econ,zeros,foursBowler,sixesBowler));


                }



                for (int i = 0; i < score.length(); i++) {

                    pid = score.getJSONObject(i).getString("pid");
                    batsman = score.getJSONObject(i).getString("batsman");

                    if(!(batsman.equals("Extras"))) {
                        dismissal_info = score.getJSONObject(i).getString("dismissal-info");
                        runs = score.getJSONObject(i).getInt("R");
                        balls = score.getJSONObject(i).getInt("B");
                        fours = score.getJSONObject(i).getInt("4s");
                        sixes = score.getJSONObject(i).getInt("6s");
                        sR = score.getJSONObject(i).getInt("SR");

                        dismissal = score.getJSONObject(i).getString("dismissal");
//                    dismissal_by =  score.getJSONObject(i).getJSONObject("dismissal-by");


                        battingCardList.add(new BattingCardModel(pid, batsman, dismissal_info, runs, balls, fours, sixes, sR, dismissal));


                    }else if (batsman.equals("Extras")){

                        detail = score.getJSONObject(i).getString("detail");
                    }




                }


                scorecardList.add(new Scorecard(titleBatting,titleBowling,battingCardList,bowlingCardList));

            }







//            textView.setText((battingCardList.size()+""));
//                myAdapter.notifyDataSetChanged();
//                bowlingCardAdapter.notifyDataSetChanged();

            ScorecardSetViews scorecardSetViews = new ScorecardSetViews(viewPager,mTabLayout,scorecardFragmentManager,context);
            scorecardSetViews.initViews(numOftabs,scorecardList);

//
        } catch (JSONException e) {
            e.printStackTrace();
        }

        swipeRefreshLayoutScorecard.setRefreshing(false);



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
