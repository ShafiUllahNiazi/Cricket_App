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
import android.widget.Toast;

import com.example.shafi.criclive.controllers.ApiReading.ApiRead;
import com.example.shafi.criclive.controllers.ApiReading.ApiReadOldMatches;
import com.example.shafi.criclive.database.DatabasesInsertData;
import com.example.shafi.criclive.database.DatabasesRetrieveData;
import com.example.shafi.criclive.controllers.adapters.MyAdapter;
import com.example.shafi.criclive.controllers.adapters.OldMatchAdapter;
import com.example.shafi.criclive.R;
import com.example.shafi.criclive.controllers.adapters.UpcomingMatchesAdapter;
import com.example.shafi.criclive.database.OldMatchesDatabase;
import com.example.shafi.criclive.models.LiveMatchesDescriptiveModelClass;
import com.example.shafi.criclive.models.OldMatchesDescriptiveModelClass;
import com.example.shafi.criclive.models.UpcomingMatchesModelClass;

import java.util.ArrayList;

public class OldMatches extends Fragment {


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
    DatabasesRetrieveData databasesRetrieveData;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.results_matches,container,false);


        listLive=new ArrayList<>();
        listOld=new ArrayList<>();
        listUpcomingMatches=new ArrayList<>();
        context = getActivity();
        databasesRetrieveData = new DatabasesRetrieveData(getActivity());

        listLive = databasesRetrieveData.retriveLiveMatchesList();
        listOld = databasesRetrieveData.retriveOldMatchesList();
        listUpcomingMatches = databasesRetrieveData.retriveUpcomingMatchesList();
        recyclerView = view.findViewById(R.id.recyclerView2);
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        oldMatchAdapter = new OldMatchAdapter(context,listOld);
        myAdapter = new MyAdapter(context,listLive);
        upcomingMatchesAdapter = new UpcomingMatchesAdapter(context,listUpcomingMatches);







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

//        swipeRefreshLayoutOldMatches.setRefreshing(false);

        String apiUrl = "https://cricapi.com/api/matches?apikey=tcS2HOv2g6bRglcrHf1pXPoOOIn1";
        ApiRead apiRead = new ApiRead(context, swipeRefreshLayoutOldMatches, myAdapter,oldMatchAdapter,upcomingMatchesAdapter, listLive, listOld, listUpcomingMatches);
        apiRead.execute(apiUrl);


    }



    @Override
    public void onResume() {
        super.onResume();
        listOld = databasesRetrieveData.retriveOldMatchesList();
    }

    @Override
    public void onStop() {
        super.onStop();
        DatabasesInsertData databasesInsertData = new DatabasesInsertData(getActivity());
        databasesInsertData.insertData(listLive,listOld,listUpcomingMatches);
    }
}
