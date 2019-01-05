package com.example.shafi.criclive.database;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import com.example.shafi.criclive.models.LiveMatchesDescriptiveModelClass;
import com.example.shafi.criclive.models.OldMatchesDescriptiveModelClass;
import com.example.shafi.criclive.models.UpcomingMatchesModelClass;

import java.util.ArrayList;

public class DatabasesInsertData {

    LiveMatchesDatabase mliveMatchesDatabase;
    OldMatchesDatabase mOldMatchesDatabase;
    UpComingMatchesDatabase mUpcomingMatchesDatabase;
    
    Context context;

    public DatabasesInsertData(Context context) {
        this.context = context;

    }

    public void insertData(ArrayList<LiveMatchesDescriptiveModelClass> listLive, ArrayList<OldMatchesDescriptiveModelClass> listOld, ArrayList<UpcomingMatchesModelClass> listUpcomingMatches){
        if (listLive !=null){

            mliveMatchesDatabase = new LiveMatchesDatabase(context);
            mliveMatchesDatabase.deleteDB(context);
            mliveMatchesDatabase = new LiveMatchesDatabase(context);


            if (mliveMatchesDatabase.insertData(listLive)){
                Log.d("insertdata","Data Inserted....");

            }
        }
        if(listOld!=null){
            mOldMatchesDatabase = new OldMatchesDatabase(context);
            mOldMatchesDatabase.deleteDB();
            mOldMatchesDatabase = new OldMatchesDatabase(context);
            if (mOldMatchesDatabase.insertData(listOld)){
                Toast.makeText(context, "Data Inserted old....", Toast.LENGTH_SHORT).show();
                Log.d("insertdata","Data Inserted old...." + listOld.size()+"");


            }
        }


        if(listUpcomingMatches!=null){
            mUpcomingMatchesDatabase = new UpComingMatchesDatabase(context);
            mUpcomingMatchesDatabase.deleteDB();
            mUpcomingMatchesDatabase = new UpComingMatchesDatabase(context);

            if (mUpcomingMatchesDatabase.insertData(listUpcomingMatches)){
                Log.d("insertdata","Data Inserted....");

            }
        }


    }
    
    
    
}
