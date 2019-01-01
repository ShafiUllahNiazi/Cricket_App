package com.example.shafi.criclive;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import com.example.shafi.criclive.database.LiveMatchesDatabase;

public class LiveMatches extends Fragment implements MyAdapter.shaficlass {

    private static final String TAG ="Tab1Fragment";

    private LiveMatchesDatabase mliveMatchesDatabase;

    private SwipeRefreshLayout swipeRefreshLayoutLiveMatches;

    TextView textView;
    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private RecyclerView.LayoutManager layoutManager;


    ArrayList<UpcomingMatchesModelClass> listUpcomingMatches;

    private OldMatchAdapter oldMatchAdapter;
    private UpcomingMatchesAdapter upcomingMatchesAdapter;
    RequestTOApi requestTOApi;
    JSONObject jsonObjectApi;

    ArrayList<JSONObject> jsonObjects;
    ArrayList<LiveMatchesDescriptiveModelClass> listLive;
    ArrayList<OldMatchesDescriptiveModelClass> listOld;




    Button button;
    View view;
    Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.live_matches,container,false);

        listLive=new ArrayList<>();
        listOld=new ArrayList<>();
        listUpcomingMatches=new ArrayList<>();
        listLive = new ArrayList<>();
//        listLiveMatches.add(new LiveMatchesModelClass(1,"2","3","","","",true,"",true));

//        JSONObject jsonObject1 = new JSONObject();
//        try {
//            jsonObject1.put("score","a vs b");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        listLive.add(new LiveMatchesDescriptiveModelClass(new LiveMatchesModelClass(1,"2","3","","","",true,"",true),jsonObject1));
//        listOldMatches.add(new OldMatchesModelClass(1,"2","3","","","",true,"","",true));
//        listLiveMatches.add(new LiveMatchesModelClass(1,"2","3","","","",true,"",true));
        context = getActivity();

        mliveMatchesDatabase = new LiveMatchesDatabase(getActivity());
        Cursor cursor = mliveMatchesDatabase.retrieveData();


        int i=0;
        while (cursor.moveToNext()){
            i++;

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
            Boolean isSquad ;
            Boolean isMatchStarted;

            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("score",score);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            LiveMatchesModelClass item = new LiveMatchesModelClass(unique_id,date,dateTimeGMT,team_1,team_2,type,squad,toss_winner_team,matchStarted);

            listLive.add(new LiveMatchesDescriptiveModelClass(item,jsonObject));
//            Toast.makeText(context, score+" kk", Toast.LENGTH_SHORT).show();
            Toast.makeText(context, "ggg "+i, Toast.LENGTH_SHORT).show();

        }



        recyclerView = view.findViewById(R.id.recyclerView1);

        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        // define an adapter


        myAdapter = new MyAdapter(context,listLive);

        myAdapter.shaficonfirm(LiveMatches.this);



        oldMatchAdapter = new OldMatchAdapter(context,listOld);
        upcomingMatchesAdapter = new UpcomingMatchesAdapter(context,listUpcomingMatches);




        recyclerView.setAdapter(myAdapter);


        swipeRefreshLayoutLiveMatches= view.findViewById(R.id.swipeRefreshLayoutLiveMatches);
        swipeRefreshLayoutLiveMatches.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                requestTOLiveMatches(swipeRefreshLayoutLiveMatches);
            }
        });


        return view;
    }

    private void requestTOLiveMatches(SwipeRefreshLayout swipeRefreshLayoutLiveMatches) {


        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("score","a vs b");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        listLive.add(new LiveMatchesDescriptiveModelClass(new LiveMatchesModelClass(1,"2","3","","","",true,"",true),jsonObject1));

        myAdapter.notifyDataSetChanged();
        swipeRefreshLayoutLiveMatches.setRefreshing(false);

//
//        String apiUrl = "https://cricapi.com/api/matches?apikey=tcS2HOv2g6bRglcrHf1pXPoOOIn1";
//        ApiRead apiRead = new ApiRead(myAdapter,oldMatchAdapter,upcomingMatchesAdapter,listUpcomingMatches);
//        apiRead.setListLive(listLive,swipeRefreshLayoutLiveMatches);
//        apiRead.execute(apiUrl);
        Toast.makeText(getActivity(), "swipe live matches ", Toast.LENGTH_SHORT).show();

    }
    @Override
    public void shafimethod(String s) {
        Toast.makeText(getContext(),s,Toast.LENGTH_LONG).show();
    }


    @Override
    public void onStop() {
        super.onStop();
        Toast.makeText(context, "onstop", Toast.LENGTH_SHORT).show();
        mliveMatchesDatabase = new LiveMatchesDatabase(getActivity());
        mliveMatchesDatabase.deleteDB(getActivity());
        mliveMatchesDatabase = new LiveMatchesDatabase(getActivity());

        if (mliveMatchesDatabase.insertData(listLive)){
            Toast.makeText(context, "Data Inserted....", Toast.LENGTH_SHORT).show();
            Log.d("insertdata","Data Inserted....");
        }
    }
}
