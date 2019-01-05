package com.example.shafi.criclive.controllers.fragments;

import android.content.Context;
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

import com.example.shafi.criclive.R;
import com.example.shafi.criclive.models.Scorecard;
import com.example.shafi.criclive.controllers.adapters.BattingCardAdapter;
import com.example.shafi.criclive.controllers.adapters.BowlingCardAdapter;
import com.example.shafi.criclive.models.BattingCardModel;
import com.example.shafi.criclive.models.BowlingModelClass;

import java.util.ArrayList;

public class DynamicFragment extends Fragment {
    private RecyclerView recyclerView;
    private BattingCardAdapter battingCardAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerViewBowling;
    private BowlingCardAdapter bowlingCardAdapter;
    private RecyclerView.LayoutManager layoutManagerBowling;
    private ArrayList<Scorecard> scorecardList;
    private ArrayList<BattingCardModel> battingCardList;
    private ArrayList<BowlingModelClass> bowlingCardList;
    public static DynamicFragment newInstance() {
        return new DynamicFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dynamic_fragment_layout, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        scorecardList = getArguments().getParcelableArrayList("scorecardList");
        int position = getArguments().getInt("position");
        battingCardList = scorecardList.get(position).getBattingCardList();
        bowlingCardList = scorecardList.get(position).getBowlingCardList();
        recyclerView = view.findViewById(R.id.recyclerView12);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerViewBowling = view.findViewById(R.id.recyclerViewBowling);
        layoutManagerBowling = new LinearLayoutManager(getActivity());
        recyclerViewBowling.setLayoutManager(layoutManagerBowling);
        battingCardAdapter = new BattingCardAdapter(getActivity(),battingCardList);
        bowlingCardAdapter = new BowlingCardAdapter(getActivity(),bowlingCardList);
        recyclerView.setAdapter(battingCardAdapter);
        recyclerViewBowling.setAdapter(bowlingCardAdapter);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}

