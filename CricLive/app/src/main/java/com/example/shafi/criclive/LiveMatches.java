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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LiveMatches extends Fragment implements MyAdapter.shaficlass {

    private static final String TAG ="Tab1Fragment";

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

//        listLive.add(new LiveMatchesDescriptiveModelClass(listLiveMatches.get(0),jsonObject));
//        listOldMatches.add(new OldMatchesModelClass(1,"2","3","","","",true,"","",true));
//        listLiveMatches.add(new LiveMatchesModelClass(1,"2","3","","","",true,"",true));
        context = getActivity();



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
        String apiUrl = "https://cricapi.com/api/matches?apikey=tcS2HOv2g6bRglcrHf1pXPoOOIn1";
        ApiRead apiRead = new ApiRead(myAdapter,oldMatchAdapter,upcomingMatchesAdapter,listUpcomingMatches);
        apiRead.setListLive(listLive,swipeRefreshLayoutLiveMatches);
        apiRead.execute(apiUrl);
        Toast.makeText(getActivity(), "swipe live matches ", Toast.LENGTH_SHORT).show();

    }


    @Override
    public void shafimethod(String s) {
        Toast.makeText(getContext(),s,Toast.LENGTH_LONG).show();
    }



}
