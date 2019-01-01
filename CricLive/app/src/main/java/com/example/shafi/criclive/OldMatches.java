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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shafi.criclive.database.OldMatchesDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class OldMatches extends Fragment implements MyAdapter.shaficlass{

    TextView textView;
    Button button;
    View view;
    Context context;
    private RecyclerView recyclerView;
    private OldMatchAdapter oldMatchAdapter;
    private RecyclerView.LayoutManager layoutManager;

    ArrayList<UpcomingMatchesModelClass> listUpcomingMatches;
    private MyAdapter myAdapter;
    private UpcomingMatchesAdapter upcomingMatchesAdapter;
    ArrayList<LiveMatchesDescriptiveModelClass> listLive;
    ArrayList<OldMatchesDescriptiveModelClass> listOld;
    SwipeRefreshLayout swipeRefreshLayoutOldMatches;

    private OldMatchesDatabase mOldMatchesDatabase;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.results_matches,container,false);


        listLive=new ArrayList<>();
        listOld=new ArrayList<>();
        listUpcomingMatches=new ArrayList<>();
//        listLiveMatches.add(new LiveMatchesModelClass(1,"2","3","","","",true,"",true));
//        listOldMatches.add(new OldMatchesModelClass(11,"2","3","","","",true,"","",true));
//        listLiveMatches.add(new LiveMatchesModelClass(1,"2","3","","","",true,"",true));
        context = getActivity();

        recyclerView = view.findViewById(R.id.recyclerView2);

        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        // define an adapter

        oldMatchAdapter = new OldMatchAdapter(context,listOld);

        myAdapter = new MyAdapter(context,listLive);
        upcomingMatchesAdapter = new UpcomingMatchesAdapter(context,listUpcomingMatches);

//        mAdapter.shaficonfirm(OldMatches.this);

        mOldMatchesDatabase = new OldMatchesDatabase(getActivity());
        Cursor cursor = mOldMatchesDatabase.retrieveData();


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
//            Toast.makeText(context, score+" kk", Toast.LENGTH_SHORT).show();
            Toast.makeText(context, "ooo "+i, Toast.LENGTH_SHORT).show();

        }



        recyclerView.setAdapter(oldMatchAdapter);

        swipeRefreshLayoutOldMatches = view.findViewById(R.id.swipeRefreshLayoutOldMatches);

        swipeRefreshLayoutOldMatches.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestTOOldMatches(swipeRefreshLayoutOldMatches);
            }
        });






        return view;
    }

    private void requestTOOldMatches(SwipeRefreshLayout swipeRefreshLayoutOldMatches) {

        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("score","r a vs b");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        listOld.add(new OldMatchesDescriptiveModelClass(new OldMatchesModelClass(11,"2","3","","","",true,"","",true),jsonObject1));
        oldMatchAdapter.notifyDataSetChanged();
        swipeRefreshLayoutOldMatches.setRefreshing(false);


//        String apiUrl = "https://cricapi.com/api/matches?apikey=tcS2HOv2g6bRglcrHf1pXPoOOIn1";
//        ApiRead apiRead = new ApiRead(myAdapter,oldMatchAdapter,upcomingMatchesAdapter, listUpcomingMatches);
//        apiRead.setListOld(listOld,swipeRefreshLayoutOldMatches);
//        apiRead.execute(apiUrl);
        Toast.makeText(getActivity(), "Button Tab 2 ", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void shafimethod(String s) {

        Toast.makeText(getContext(),s,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onStop() {
        super.onStop();
        mOldMatchesDatabase = new OldMatchesDatabase(getActivity());
        mOldMatchesDatabase.deleteDB();
        mOldMatchesDatabase = new OldMatchesDatabase(getActivity());
        mOldMatchesDatabase.insertData(listOld);
    }
}
