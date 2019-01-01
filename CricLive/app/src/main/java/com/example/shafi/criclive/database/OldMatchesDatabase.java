package com.example.shafi.criclive.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.shafi.criclive.LiveMatchesDescriptiveModelClass;
import com.example.shafi.criclive.OldMatchesDescriptiveModelClass;

import org.json.JSONException;

import java.util.ArrayList;

public class OldMatchesDatabase extends SQLiteOpenHelper {

    Context context;

    public static final String DATABASE_NAME = "oldMatchesDB.db";
    public static final String TABLE_NAME = "oldMatches";
    public static final String COL_1 = "score";
    public static final String COL_2 = "unique_id";
    public static final String COL_3 = "date";
    public static final String COL_4 = "dateTimeGMT";
    public static final String COL_5 = "team_1";
    public static final String COL_6 = "team_2";
    public static final String COL_7 = "type";
    public static final String COL_8 = "squad";
    public static final String COL_9 = "toss_winner_team";
    public static final String COL_10 = "winner_team";
    public static final String COL_11 = "matchStarted";


    private static final String[] COLUMNS = { COL_1, COL_2, COL_3,
            COL_4, COL_5, COL_6, COL_7,COL_8, COL_9, COL_10, COL_11};





    public OldMatchesDatabase(Context context) {
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
                COL_8 +" TEXT, " +
                COL_9 +" TEXT, " +
                COL_10 +" TEXT, " +
                COL_11 +" TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(ArrayList<OldMatchesDescriptiveModelClass> oldList){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        long result = 0;
        for (int i = 0;i<oldList.size();i++){
            try {
                contentValues.put(COL_1,oldList.get(i).getJsonObject().getString("score"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            contentValues.put(COL_2,oldList.get(i).getOldMatchesListsItem().getUnique_id()+"");
            contentValues.put(COL_3,oldList.get(i).getOldMatchesListsItem().getDate());
            contentValues.put(COL_4,oldList.get(i).getOldMatchesListsItem().getDateTimeGMT());
            contentValues.put(COL_5,oldList.get(i).getOldMatchesListsItem().getTeam_1());
            contentValues.put(COL_6,oldList.get(i).getOldMatchesListsItem().getTeam_2());
            contentValues.put(COL_7,oldList.get(i).getOldMatchesListsItem().getType());
            contentValues.put(COL_8,String.valueOf(oldList.get(i).getOldMatchesListsItem().isSquad()));
            contentValues.put(COL_9,oldList.get(i).getOldMatchesListsItem().getToss_winner_team());
            contentValues.put(COL_10,String.valueOf(oldList.get(i).getOldMatchesListsItem().isMatchStarted()));

            result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
            Log.d("LiveMatchesInserted","LiveMatchesInserted");




        }



//        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
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
