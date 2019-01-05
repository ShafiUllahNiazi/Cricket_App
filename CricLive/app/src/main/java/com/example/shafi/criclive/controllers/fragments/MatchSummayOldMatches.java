package com.example.shafi.criclive.controllers.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shafi.criclive.R;

public class MatchSummayOldMatches extends Fragment {

    TextView matchWinnerTeam;
    TextView scoreDetail;
    TextView manOFtheMatch;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.match_summaryold,container,false);

        matchWinnerTeam = view.findViewById(R.id.matchWinnerTeam);
        scoreDetail = view.findViewById(R.id.scoresummary);
        manOFtheMatch = view.findViewById(R.id.manOfTheMatch);
        String winner_team = getArguments().getString("winner_team");
        String unique_id = getArguments().getString("unique_id");
        String scoreSummary = getArguments().getString("score");
        matchWinnerTeam.setText(winner_team);
        scoreDetail.setText(scoreSummary);

        return view;
    }
}
