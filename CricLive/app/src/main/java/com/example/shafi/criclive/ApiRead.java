package com.example.shafi.criclive;

import android.os.AsyncTask;
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

    ArrayList<LiveMatchesModelClass> listliveMatches;
    ArrayList<OldMatchesModelClass> listOldMatches;
    ArrayList<UpcomingMatchesModelClass> listUpcomingMatches;
    MyAdapter mAdapter;
    OldMatchAdapter oldMatchAdapter;
    UpcomingMatchesAdapter upcomingMatchesAdapter;

    TextView textView;

//    public ApiRead(MyAdapter mAdapter, ArrayList<LiveMatchesModelClass> listliveMatches,ArrayList<OldMatchesModelClass> listOldMatches,ArrayList<UpcomingMatchesModelClass> listUpcomingMatches) {
//        this.listliveMatches =listliveMatches;
//
//        this.lisOldMatches=listOldMatches;
//        this.listUpcomingMatches = listUpcomingMatches;
//        this.mAdapter=mAdapter;
//
//    }
//    public ApiRead(OldMatchAdapter oldMatchAdapter, ArrayList<LiveMatchesModelClass> listliveMatches,ArrayList<OldMatchesModelClass> listOldMatches,ArrayList<UpcomingMatchesModelClass> listUpcomingMatches) {
//        this.listliveMatches =listliveMatches;
//
//        this.lisOldMatches=listOldMatches;
//        this.listUpcomingMatches = listUpcomingMatches;
//        this.oldMatchAdapter=oldMatchAdapter;
//
//    }
//
//    public ApiRead(UpcomingMatchesAdapter upcomingMatchesAdapter, ArrayList<LiveMatchesModelClass> listliveMatches,ArrayList<OldMatchesModelClass> listOldMatches,ArrayList<UpcomingMatchesModelClass> listUpcomingMatches) {
//        this.listliveMatches =listliveMatches;
//
//        this.lisOldMatches=listOldMatches;
//        this.listUpcomingMatches = listUpcomingMatches;
//        this.upcomingMatchesAdapter= upcomingMatchesAdapter;
//
//    }

    public ApiRead (MyAdapter mAdapter,OldMatchAdapter oldMatchAdapter,UpcomingMatchesAdapter upcomingMatchesAdapter,ArrayList<LiveMatchesModelClass> listliveMatches,ArrayList<OldMatchesModelClass> listOldMatches,ArrayList<UpcomingMatchesModelClass> listUpcomingMatches) {

        this.mAdapter=mAdapter;
        this.oldMatchAdapter= oldMatchAdapter;
        this.upcomingMatchesAdapter= upcomingMatchesAdapter;
        this.listliveMatches = listliveMatches;
        this.listOldMatches = listOldMatches;
        this.listUpcomingMatches = listUpcomingMatches;

    }




    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        try {
            JSONObject jsonObject = new JSONObject(result);
            String score =jsonObject.toString();
//                textView.setText(score);
            JSONArray jsonArray = jsonObject.getJSONArray("matches");
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
                        listOldMatches.add(new OldMatchesModelClass(unique_id,date,dateTimeGMT,team_1,team_2,type,squad,toss_winner_team,winner_team,matchStarted));


                    }else{
                        toss_winner_team = jsonArray.getJSONObject(i).getString("toss_winner_team");
//                        winner_team = "N?A";
                        listliveMatches.add(new LiveMatchesModelClass(unique_id,date,dateTimeGMT,team_1,team_2,type,squad,toss_winner_team,matchStarted));

                    }

                }else {
//                        toss_winner_team = null;
//                        winner_team = null;
//                    toss_winner_team = "N/A";
//                    winner_team = "N/A";
                    listUpcomingMatches.add(new UpcomingMatchesModelClass(unique_id,date,dateTimeGMT,team_1,team_2,type,squad,matchStarted));

                }





            }
//                for (int i = 0; i < 100; i++) {
//
//
//
//                    oldMatches.add(new OldMatches("abc","def","aaaa"));
//
//                }
//
//                String s = newMatches.get(1).getUnique_id().toString();
//                String score = jsonObject.getString("score");
//                JSONArray jsonArray = new JSONArray(score);
//                User.fromJson(jsonArray);


//                textView.setText(s);
//                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,User.fromJson(jsonArray));
//            textView.setText(s);
//                textView.setText(newMatches.toString());
//                textView.setText(newMatches.toString());
//                putRecyclerVIew();
            Log.d("sizeeeL",Integer.toString(listliveMatches.size()));

            Log.d("sizeeeO",Integer.toString(listOldMatches.size()));
            Log.d("sizeeeU",Integer.toString(listUpcomingMatches.size()));
            mAdapter.notifyDataSetChanged();
            oldMatchAdapter.notifyDataSetChanged();
            upcomingMatchesAdapter.notifyDataSetChanged();



            Log.d("Khann",listliveMatches.toString());
//                Log.i("Shafii",score);
        } catch (JSONException e) {
            e.printStackTrace();
        }














//        try {
//            JSONObject jsonObject = new JSONObject(result);
//            String score =jsonObject.toString();
//            JSONArray jsonArray = jsonObject.getJSONArray("data");
//
//            for (int i = 0; i < jsonArray.length(); i++) {
//
//                String title = jsonArray.getJSONObject(i).getString("title");
//                String description = jsonArray.getJSONObject(i).getString("description");
//                String unique_id = jsonArray.getJSONObject(i).getString("unique_id");
//
//                oldMatches.add(new OldMatches(title,description,unique_id));
////                myAdapter.notifyItemInserted(oldMatches.size()-1);
//                int g = myAdapter.getItemCount();
//                String ghhh= Integer.toString(g);
//                Log.d("mmadapter",ghhh);
//                g=oldMatches.size();
//                ghhh = Integer.toString(g);
//                Log.d("ssize",ghhh);
//
//            }
//            myAdapter.notifyDataSetChanged();
//
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }




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