package com.example.shafi.criclive.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.shafi.criclive.models.UpcomingMatchesModelClass;

import java.util.ArrayList;

public class UpComingMatchesDatabase extends SQLiteOpenHelper {

    Context context;

    public static final String DATABASE_NAME = "upcomingMatchesDB.db";
    public static final String TABLE_NAME = "upcomingMatches";

    public static final String COL_1 = "unique_id";
    public static final String COL_2 = "date";
    public static final String COL_3 = "dateTimeGMT";
    public static final String COL_4 = "team_1";
    public static final String COL_5 = "team_2";
    public static final String COL_6 = "type";
    public static final String COL_7 = "squad";
    public static final String COL_8 = "matchStarted";


    private static final String[] COLUMNS = { COL_1, COL_2, COL_3,
            COL_4, COL_5, COL_6, COL_7,COL_8};





    public UpComingMatchesDatabase(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_1 +" TEXT, " +
                COL_2 +" TEXT, " +
                COL_3 +" TEXT, " +
                COL_4 +" TEXT, " +
                COL_5 +" TEXT, " +
                COL_6 +" TEXT, " +
                COL_7 +" TEXT, " +
                COL_8 +" TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(ArrayList<UpcomingMatchesModelClass> upcomingList){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        long result = 0;
        for (int i = 0;i<upcomingList.size();i++){

            contentValues.put(COL_1,upcomingList.get(i).getUnique_id()+"");
            contentValues.put(COL_2,upcomingList.get(i).getDate());
            contentValues.put(COL_3,upcomingList.get(i).getDateTimeGMT());
            contentValues.put(COL_4,upcomingList.get(i).getTeam_1());
            contentValues.put(COL_5,upcomingList.get(i).getTeam_2());
            contentValues.put(COL_6,upcomingList.get(i).getType());
            contentValues.put(COL_7,String.valueOf(upcomingList.get(i).isSquad()));
            contentValues.put(COL_8,String.valueOf(upcomingList.get(i).isMatchStarted()));



            result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
            Log.d("LiveMatchesInserted","LiveMatchesInserted");




        }


        if (result == -1)
            return false;
        return true;
    }

    public Cursor retrieveData(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from " + TABLE_NAME, null);
    }

    public void deleteDB(){
        context.deleteDatabase(DATABASE_NAME);
    }
}
