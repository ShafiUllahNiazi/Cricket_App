package com.example.shafi.criclive;

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
import android.widget.Toast;

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

    private Button btnTest3;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.upcoming_matches,container,false);


        listLive=new ArrayList<>();
        listOld=new ArrayList<>();
        listUpcomingMatches=new ArrayList<>();
        listUpcomingMatches.add(new UpcomingMatchesModelClass(111,"2","3","","","",true,false));

        context = getActivity();



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
                String apiUrl = "https://cricapi.com/api/matches?apikey=tcS2HOv2g6bRglcrHf1pXPoOOIn1";
//                ApiRead apiRead = new ApiRead(mAdapter,listLiveMatches,listOldMatches,listUpcomingMatches);
                ApiRead apiRead = new ApiRead(myAdapter,oldMatchAdapter,upcomingMatchesAdapter,listUpcomingMatches);

                apiRead.setListUpcoming(swipeRefreshLayoutUpcomingMatches);
                apiRead.execute(apiUrl);
            }
        });



        return view;
    }
}
