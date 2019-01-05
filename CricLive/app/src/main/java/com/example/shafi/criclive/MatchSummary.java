package com.example.shafi.criclive;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shafi.criclive.controllers.ApiReading.ApiReadMatchSummary;
import com.example.shafi.criclive.controllers.ApiReading.ApiReadScorecard;
import com.example.shafi.criclive.controllers.adapters.MatchSummaryAdapter;
import com.example.shafi.criclive.models.MatchSummaryModel;

import java.util.ArrayList;

public class MatchSummary  extends Fragment {



    private String unique_id;
    private String winner_team;
    private String scoresummary;
    private TextView matchWinnerTeam;
    private TextView score;
    private TextView manOFtheMatch;
    RecyclerView recyclerViewMatchSummary;
    ArrayList<MatchSummaryModel> listMatchSummary;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private MatchSummaryAdapter summaryAdapter;
    String scoreSummary;


    public MatchSummary() {

    }
//    public void setData (String data) {
//        this.data = data;
//
//    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.match_summary,container,false);
        matchWinnerTeam = view.findViewById(R.id.matchWinnerTeam);
        score = view.findViewById(R.id.scoresummary);
        manOFtheMatch = view.findViewById(R.id.manOfTheMatch);
        recyclerViewMatchSummary = view.findViewById(R.id.recyclerViewMatchSummary);
        winner_team = getArguments().getString("winner_team");
        unique_id = getArguments().getString("unique_id");
        scoreSummary = getArguments().getString("score");
        matchWinnerTeam.setText(winner_team);
        score.setText(scoreSummary);

        listMatchSummary = new ArrayList<>();

        if(winner_team!=null){
            matchWinnerTeam.setText("kk");
            score.setText(scoreSummary);
        }else {
//            requestforSummary(listMatchSummary);
            recyclerView = view.findViewById(R.id.recyclerViewMatchSummary);
            layoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(layoutManager);
            summaryAdapter = new MatchSummaryAdapter(getActivity(),listMatchSummary);
            recyclerView.setAdapter(summaryAdapter);
        }

//        requestforSummary(listMatchSummary);



        return view;
    }

    private void requestforSummary(ArrayList<MatchSummaryModel> listMatchSummary) {
        String s = "https://cricapi.com/api/fantasySummary?apikey=tcS2HOv2g6bRglcrHf1pXPoOOIn1&unique_id=" + unique_id;
        ApiReadMatchSummary apiReadScorecard = new ApiReadMatchSummary();
//        apiReadScorecard.setlistMatchSummary(listMatchSummary,scoreSummary,summaryAdapter,manOFtheMatch);
        apiReadScorecard.execute(s);
    }
}
