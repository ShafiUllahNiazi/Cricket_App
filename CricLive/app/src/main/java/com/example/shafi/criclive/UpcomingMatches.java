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
import com.example.shafi.criclive.database.UpComingMatchesDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class UpcomingMatches extends Fragment {

    TextView textView;
    Button button;
    View view;
    Context context;
    private RecyclerView recyclerView;
    private UpcomingMatchesAdapter upcomingMatchesAdapter;
    private RecyclerView.LayoutManager layoutManager;

    ArrayList<OldMatchesDescriptiveModelClass> listOld;
    ArrayList<UpcomingMatchesModelClass> listUpcomingMatches;
    ArrayList<LiveMatchesDescriptiveModelClass> listLive;

    private MyAdapter myAdapter;
    private OldMatchAdapter oldMatchAdapter;
    SwipeRefreshLayout swipeRefreshLayoutUpcomingMatches;


    private static final String TAG ="Tab1Fragment";

    private UpComingMatchesDatabase mUpComingMatchesDatabase;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.upcoming_matches,container,false);


        listLive=new ArrayList<>();
        listOld=new ArrayList<>();
        listUpcomingMatches=new ArrayList<>();


        context = getActivity();

        mUpComingMatchesDatabase = new UpComingMatchesDatabase(getActivity());
        Cursor cursor = mUpComingMatchesDatabase.retrieveData();


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
//            Toast.makeText(context, score+" kk", Toast.LENGTH_SHORT).show();
            Toast.makeText(context, "ooo "+i, Toast.LENGTH_SHORT).show();

        }




        recyclerView = view.findViewById(R.id.recyclerView3);

        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        // define an adapter

        upcomingMatchesAdapter = new UpcomingMatchesAdapter(context,listUpcomingMatches);


        myAdapter= new MyAdapter(context,listLive);
        oldMatchAdapter = new OldMatchAdapter(context,  listOld);

//        mAdapter.shaficonfirm(OldMatches.this);



        recyclerView.setAdapter(upcomingMatchesAdapter);

        swipeRefreshLayoutUpcomingMatches = view.findViewById(R.id.swipeRefreshLayoutUpcomingMatches);
        swipeRefreshLayoutUpcomingMatches.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                requestToUpcomingMatches();

            }
        });



        return view;
    }

    private void requestToUpcomingMatches() {


        listUpcomingMatches.add(new UpcomingMatchesModelClass(111,"2","3","","","",true,false));

        upcomingMatchesAdapter.notifyDataSetChanged();
        swipeRefreshLayoutUpcomingMatches.setRefreshing(false);

        /*String apiUrl = "https://cricapi.com/api/matches?apikey=tcS2HOv2g6bRglcrHf1pXPoOOIn1";
//                ApiRead apiRead = new ApiRead(mAdapter,listLiveMatches,listOldMatches,listUpcomingMatches);
        ApiRead apiRead = new ApiRead(myAdapter,oldMatchAdapter,upcomingMatchesAdapter,listUpcomingMatches);

        apiRead.setListUpcoming(swipeRefreshLayoutUpcomingMatches);
        apiRead.execute(apiUrl);*/

    }

    @Override
    public void onStop() {
        super.onStop();
        mUpComingMatchesDatabase = new UpComingMatchesDatabase(getActivity());
        mUpComingMatchesDatabase.deleteDB();
        mUpComingMatchesDatabase = new UpComingMatchesDatabase(getActivity());
        mUpComingMatchesDatabase.insertData(listUpcomingMatches);
    }
}
