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

import java.util.ArrayList;

public class UpcomingMatches extends Fragment {

    TextView textView;
    Button button;
    View view;
    Context context;
    private RecyclerView recyclerView;
    private UpcomingMatchesAdapter upcomingMatchesAdapter;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<LiveMatchesModelClass> listLiveMatches;
    ArrayList<OldMatchesModelClass> listOldMatches;
    ArrayList<UpcomingMatchesModelClass> listUpcomingMatches;

    private MyAdapter myAdapter;
    private OldMatchAdapter oldMatchAdapter;


    private static final String TAG ="Tab1Fragment";

    private Button btnTest3;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.upcoming_matches,container,false);

        textView = view.findViewById(R.id.textView3);
        listLiveMatches=new ArrayList<>();
        listOldMatches=new ArrayList<>();
        listUpcomingMatches=new ArrayList<>();
        listUpcomingMatches.add(new UpcomingMatchesModelClass(111,"2","3","","","",true,false));

        context = getActivity();
        button = view.findViewById(R.id.button3);


        recyclerView = view.findViewById(R.id.recyclerView3);

        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        // define an adapter

        upcomingMatchesAdapter = new UpcomingMatchesAdapter(context,listUpcomingMatches);


        myAdapter= new MyAdapter(context,listLiveMatches);
        oldMatchAdapter = new OldMatchAdapter(context,listOldMatches);

//        mAdapter.shaficonfirm(OldMatches.this);



        recyclerView.setAdapter(upcomingMatchesAdapter);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String apiUrl = "https://cricapi.com/api/matches?apikey=tcS2HOv2g6bRglcrHf1pXPoOOIn1";
//                ApiRead apiRead = new ApiRead(mAdapter,listLiveMatches,listOldMatches,listUpcomingMatches);
                ApiRead apiRead = new ApiRead(myAdapter,oldMatchAdapter,upcomingMatchesAdapter,listLiveMatches,listOldMatches,listUpcomingMatches);

                apiRead.execute(apiUrl);



//                listLiveMatches.add(new LiveMatchesModelClass(1,"2","3","","","",true,"",true));
//                mAdapter.notifyItemInserted(listLiveMatches.size()-1);

                Toast.makeText(getActivity(), "Button Tab 3 ", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
