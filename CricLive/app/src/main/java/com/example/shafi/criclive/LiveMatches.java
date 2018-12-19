package com.example.shafi.criclive;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

    private Button btnTest;

    TextView textView;
    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<LiveMatchesModelClass> listLiveMatches;
    ArrayList<OldMatchesModelClass> listOldMatches;
    ArrayList<UpcomingMatchesModelClass> listUpcomingMatches;

    private OldMatchAdapter oldMatchAdapter;
    private UpcomingMatchesAdapter upcomingMatchesAdapter;
    RequestTOApi requestTOApi;
    JSONObject jsonObjectApi;

    ArrayList<JSONObject> jsonObjects;
    ArrayList<LiveMatchesDescriptiveModelClass> listLive;


    Button button;
    View view;
    Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.live_matches,container,false);
        textView = view.findViewById(R.id.textView1);
        listLiveMatches=new ArrayList<>();
        listOldMatches=new ArrayList<>();
        listUpcomingMatches=new ArrayList<>();
        listLive = new ArrayList<>();
//        listLiveMatches.add(new LiveMatchesModelClass(1,"2","3","","","",true,"",true));
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("score","a vs b");
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        listLive.add(new LiveMatchesDescriptiveModelClass(listLiveMatches.get(0),jsonObject));
//        listOldMatches.add(new OldMatchesModelClass(1,"2","3","","","",true,"","",true));
//        listLiveMatches.add(new LiveMatchesModelClass(1,"2","3","","","",true,"",true));
        context = getActivity();
        button = view.findViewById(R.id.button1);


        recyclerView = view.findViewById(R.id.recyclerView1);

        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        // define an adapter

        myAdapter = new MyAdapter(context,listLive);

        myAdapter.shaficonfirm(LiveMatches.this);



        oldMatchAdapter = new OldMatchAdapter(context,listOldMatches);
        upcomingMatchesAdapter = new UpcomingMatchesAdapter(context,listUpcomingMatches);




        recyclerView.setAdapter(myAdapter);




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String apiUrl = "https://cricapi.com/api/matches?apikey=tcS2HOv2g6bRglcrHf1pXPoOOIn1";

//                requestTOApi = new RequestTOApi(mAdapter,listLiveMatches,listOldMatches,listUpcomingMatches);
//
//                requestTOApi.sentRequest();



//                ApiRead apiRead = new ApiRead(mAdapter,list);
//                ApiRead apiRead = new ApiRead(mAdapter,listLiveMatches,listOldMatches,listUpcomingMatches);
                ApiRead apiRead = new ApiRead(myAdapter,oldMatchAdapter,upcomingMatchesAdapter,listLiveMatches,listOldMatches,listUpcomingMatches);

                apiRead.setListLive(listLive);
                apiRead.execute(apiUrl);



//                listLiveMatches.add(new LiveMatchesModelClass(1,"2","3","","","",true,"",true));
//                myAdapter.notifyItemInserted(listLiveMatches.size()-1);

                Toast.makeText(getActivity(), "Button Tab 1 ", Toast.LENGTH_SHORT).show();
            }
        });




        return view;
    }


    @Override
    public void shafimethod(String s) {
        Toast.makeText(getContext(),s,Toast.LENGTH_LONG).show();
    }



}
