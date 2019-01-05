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
import java.util.ArrayList;

import com.example.shafi.criclive.controllers.ApiReading.ApiRead;
import com.example.shafi.criclive.database.DatabasesInsertData;
import com.example.shafi.criclive.database.DatabasesRetrieveData;
import com.example.shafi.criclive.controllers.adapters.MyAdapter;
import com.example.shafi.criclive.controllers.adapters.OldMatchAdapter;
import com.example.shafi.criclive.R;
import com.example.shafi.criclive.controllers.adapters.UpcomingMatchesAdapter;
import com.example.shafi.criclive.models.LiveMatchesDescriptiveModelClass;
import com.example.shafi.criclive.models.OldMatchesDescriptiveModelClass;
import com.example.shafi.criclive.models.UpcomingMatchesModelClass;

public class LiveMatches extends Fragment  {


    private SwipeRefreshLayout swipeRefreshLayoutLiveMatches;


    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private MyAdapter myAdapter;
    private OldMatchAdapter oldMatchAdapter;
    private UpcomingMatchesAdapter upcomingMatchesAdapter;
    ArrayList<LiveMatchesDescriptiveModelClass> listLive;
    ArrayList<OldMatchesDescriptiveModelClass> listOld;
    ArrayList<UpcomingMatchesModelClass> listUpcomingMatches;
    View view;
    Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.live_matches, container, false);

        listLive = new ArrayList<>();
        listOld = new ArrayList<>();
        listUpcomingMatches = new ArrayList<>();
        context = getActivity();

        DatabasesRetrieveData databasesRetrieveData = new DatabasesRetrieveData(getActivity());
        listLive = databasesRetrieveData.retriveLiveMatchesList();
        listOld = databasesRetrieveData.retriveOldMatchesList();
        listUpcomingMatches = databasesRetrieveData.retriveUpcomingMatchesList();


        recyclerView = view.findViewById(R.id.recyclerView1);
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        myAdapter = new MyAdapter(context, listLive);




        oldMatchAdapter = new OldMatchAdapter(context, listOld);
        upcomingMatchesAdapter = new UpcomingMatchesAdapter(context, listUpcomingMatches);


        recyclerView.setAdapter(myAdapter);


        swipeRefreshLayoutLiveMatches = view.findViewById(R.id.swipeRefreshLayoutLiveMatches);
        swipeRefreshLayoutLiveMatches.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                requestTOLiveMatches(swipeRefreshLayoutLiveMatches);
            }
        });


        return view;
    }

    private void requestTOLiveMatches(SwipeRefreshLayout swipeRefreshLayoutLiveMatches) {

        String apiUrl = "https://cricapi.com/api/matches?apikey=tcS2HOv2g6bRglcrHf1pXPoOOIn1";
        ApiRead apiRead = new ApiRead(context,swipeRefreshLayoutLiveMatches, myAdapter, oldMatchAdapter, upcomingMatchesAdapter,listLive,listOld, listUpcomingMatches);
        apiRead.execute(apiUrl);
        Toast.makeText(getActivity(), "swipe live matches ", Toast.LENGTH_SHORT).show();

    }



    @Override
    public void onStop() {
        super.onStop();
        DatabasesInsertData databasesInsertData = new DatabasesInsertData(getActivity());
        databasesInsertData.insertData(listLive, listOld, listUpcomingMatches);
    }
}
