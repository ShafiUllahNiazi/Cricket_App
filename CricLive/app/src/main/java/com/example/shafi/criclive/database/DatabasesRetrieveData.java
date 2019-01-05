package com.example.shafi.criclive.database;

import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;
import com.example.shafi.criclive.models.LiveMatchesDescriptiveModelClass;
import com.example.shafi.criclive.models.LiveMatchesModelClass;
import com.example.shafi.criclive.models.OldMatchesDescriptiveModelClass;
import com.example.shafi.criclive.models.OldMatchesModelClass;
import com.example.shafi.criclive.models.UpcomingMatchesModelClass;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DatabasesRetrieveData {
    LiveMatchesDatabase mliveMatchesDatabase;
    OldMatchesDatabase mOldMatchesDatabase;
    UpComingMatchesDatabase mUpcomingMatchesDatabase;

    Context context;

    public DatabasesRetrieveData(Context context) {
        this.context = context;

    }

    public ArrayList<LiveMatchesDescriptiveModelClass> retriveLiveMatchesList(){
        mliveMatchesDatabase = new LiveMatchesDatabase(context);
        Cursor cursor = mliveMatchesDatabase.retrieveData();
        ArrayList<LiveMatchesDescriptiveModelClass> listLive = new ArrayList<>();



        while (cursor.moveToNext()){


            String score = cursor.getString(1);
            long unique_id = Long.valueOf(cursor.getString(2));
            String date = cursor.getString(3);
            String dateTimeGMT = cursor.getString(4);
            String team_1 = cursor.getString(5);
            String team_2 = cursor.getString(6);
            String type = cursor.getString(7);
            boolean squad = Boolean.valueOf(cursor.getString(8));
            String toss_winner_team = cursor.getString(9);
            boolean matchStarted = Boolean.valueOf(cursor.getString(10));


            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("score",score);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            LiveMatchesModelClass item = new LiveMatchesModelClass(unique_id,date,dateTimeGMT,team_1,team_2,type,squad,toss_winner_team,matchStarted);

            listLive.add(new LiveMatchesDescriptiveModelClass(item,jsonObject));



        }

        return listLive;

    }

    public ArrayList<OldMatchesDescriptiveModelClass> retriveOldMatchesList(){

        mOldMatchesDatabase = new OldMatchesDatabase(context);
        Cursor cursor = mOldMatchesDatabase.retrieveData();
        ArrayList<OldMatchesDescriptiveModelClass> listOld = new ArrayList<>();

        while (cursor.moveToNext()){

            String score = cursor.getString(1);
            long unique_id = Long.valueOf(cursor.getString(2));
            String date = cursor.getString(3);
            String dateTimeGMT = cursor.getString(4);
            String team_1 = cursor.getString(5);
            String team_2 = cursor.getString(6);
            String type = cursor.getString(7);
            boolean squad = Boolean.valueOf(cursor.getString(8));
            String toss_winner_team = cursor.getString(9);
            String winner_team = cursor.getString(10);
            boolean matchStarted = Boolean.valueOf(cursor.getString(11));
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("score",score);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            OldMatchesModelClass item = new OldMatchesModelClass(unique_id,date,dateTimeGMT,team_1,team_2,type,squad,toss_winner_team,winner_team,matchStarted);

            listOld.add(new OldMatchesDescriptiveModelClass(item,jsonObject));

        }

        return listOld;
    }

    public ArrayList<UpcomingMatchesModelClass> retriveUpcomingMatchesList(){
        mUpcomingMatchesDatabase = new UpComingMatchesDatabase(context);
        Cursor cursor = mUpcomingMatchesDatabase.retrieveData();
        ArrayList<UpcomingMatchesModelClass> listUpcomingMatches = new ArrayList<>();


        int i=0;
        while (cursor.moveToNext()){
            i++;


            long unique_id = Long.valueOf(cursor.getString(1));
            String date = cursor.getString(2);
            String dateTimeGMT = cursor.getString(3);
            String team_1 = cursor.getString(4);
            String team_2 = cursor.getString(5);
            String type = cursor.getString(6);
            boolean squad = Boolean.valueOf(cursor.getString(7));
            boolean matchStarted = Boolean.valueOf(cursor.getString(8));



            UpcomingMatchesModelClass item = new UpcomingMatchesModelClass(unique_id,date,dateTimeGMT,team_1,team_2,type,squad,matchStarted);

            listUpcomingMatches.add(item);
        }
        return listUpcomingMatches;
    }
}
