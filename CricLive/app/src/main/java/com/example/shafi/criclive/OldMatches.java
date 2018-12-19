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
    ArrayList<LiveMatchesModelClass> listLiveMatches;
    ArrayList<OldMatchesModelClass> listOldMatches;
    ArrayList<UpcomingMatchesModelClass> listUpcomingMatches;
    private MyAdapter myAdapter;
    private UpcomingMatchesAdapter upcomingMatchesAdapter;
    ArrayList<LiveMatchesDescriptiveModelClass> listLive;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.results_matches,container,false);

        textView = view.findViewById(R.id.textView2);
        listLiveMatches=new ArrayList<>();
        listOldMatches=new ArrayList<>();
        listUpcomingMatches=new ArrayList<>();
        listLiveMatches.add(new LiveMatchesModelClass(1,"2","3","","","",true,"",true));
        listOldMatches.add(new OldMatchesModelClass(11,"2","3","","","",true,"","",true));
//        listLiveMatches.add(new LiveMatchesModelClass(1,"2","3","","","",true,"",true));
        context = getActivity();
        button = view.findViewById(R.id.button2);


        recyclerView = view.findViewById(R.id.recyclerView2);

        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        // define an adapter

        oldMatchAdapter = new OldMatchAdapter(context,listOldMatches);

        myAdapter = new MyAdapter(context,listLive);
        upcomingMatchesAdapter = new UpcomingMatchesAdapter(context,listUpcomingMatches);

//        mAdapter.shaficonfirm(OldMatches.this);



        recyclerView.setAdapter(oldMatchAdapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String apiUrl = "https://cricapi.com/api/matches?apikey=tcS2HOv2g6bRglcrHf1pXPoOOIn1";
//                ApiRead apiRead = new ApiRead(mAdapter,listLiveMatches,listOldMatches,listUpcomingMatches);
                ApiRead apiRead = new ApiRead(myAdapter,oldMatchAdapter,upcomingMatchesAdapter,listLiveMatches,listOldMatches,listUpcomingMatches);

                apiRead.execute(apiUrl);



//                listLiveMatches.add(new LiveMatchesModelClass(1,"2","3","","","",true,"",true));
//                oldMatchAdapter.notifyItemInserted(listLiveMatches.size()-1);

                Toast.makeText(getActivity(), "Button Tab 2 ", Toast.LENGTH_SHORT).show();
            }
        });



        return view;
    }

    @Override
    public void shafimethod(String s) {

        Toast.makeText(getContext(),s,Toast.LENGTH_LONG).show();
    }
}
