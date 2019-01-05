package com.example.shafi.criclive.controllers.fragments;

import android.content.Context;
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

import com.example.shafi.criclive.controllers.ApiReading.ApiRead;
import com.example.shafi.criclive.database.DatabasesInsertData;
import com.example.shafi.criclive.database.DatabasesRetrieveData;
import com.example.shafi.criclive.controllers.adapters.MyAdapter;
import com.example.shafi.criclive.controllers.adapters.OldMatchAdapter;
import com.example.shafi.criclive.R;
import com.example.shafi.criclive.controllers.adapters.UpcomingMatchesAdapter;
import com.example.shafi.criclive.database.UpComingMatchesDatabase;
import com.example.shafi.criclive.models.LiveMatchesDescriptiveModelClass;
import com.example.shafi.criclive.models.OldMatchesDescriptiveModelClass;
import com.example.shafi.criclive.models.UpcomingMatchesModelClass;

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



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.upcoming_matches,container,false);


        listLive=new ArrayList<>();
        listOld=new ArrayList<>();
        listUpcomingMatches=new ArrayList<>();


        context = getActivity();
        DatabasesRetrieveData databasesRetrieveData = new DatabasesRetrieveData(getActivity());

        listLive = databasesRetrieveData.retriveLiveMatchesList();
        listOld = databasesRetrieveData.retriveOldMatchesList();
        listUpcomingMatches = databasesRetrieveData.retriveUpcomingMatchesList();

        recyclerView = view.findViewById(R.id.recyclerView3);

        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        upcomingMatchesAdapter = new UpcomingMatchesAdapter(context,listUpcomingMatches);


        myAdapter= new MyAdapter(context,listLive);
        oldMatchAdapter = new OldMatchAdapter(context,  listOld);
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



        String apiUrl = "https://cricapi.com/api/matches?apikey=tcS2HOv2g6bRglcrHf1pXPoOOIn1";
        ApiRead apiRead = new ApiRead(context, swipeRefreshLayoutUpcomingMatches, myAdapter,oldMatchAdapter,upcomingMatchesAdapter, listLive, listOld, listUpcomingMatches);


        apiRead.execute(apiUrl);

    }

    @Override
    public void onStop() {
        super.onStop();
        DatabasesInsertData databasesInsertData = new DatabasesInsertData(getActivity());
        databasesInsertData.insertData(listLive,listOld,listUpcomingMatches);
    }
}
