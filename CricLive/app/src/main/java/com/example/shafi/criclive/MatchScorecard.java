package com.example.shafi.criclive;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

public class MatchScorecard extends Fragment {

    Button button;
    private String unique_id;


    private ViewPager viewPager;
    private TabLayout mTabLayout;
    private FragmentManager scorecardFragmentManager;
    SwipeRefreshLayout swipeRefreshLayoutScorecard;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.match_scorecard,container,false);

        scorecardFragmentManager = getActivity().getSupportFragmentManager();

        unique_id = getArguments().getString("unique_id");

        viewPager = view.findViewById(R.id.viewpagerScorecard);
        mTabLayout =  view.findViewById(R.id.tabsScorecard);
        swipeRefreshLayoutScorecard = view.findViewById(R.id.swipeRefreshLayoutScorecard);
        swipeRefreshLayoutScorecard.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestTOUpcomingMatches();
            }
        });






        return view;

    }

    private void requestTOUpcomingMatches() {
        String s = "https://cricapi.com/api/fantasySummary?apikey=tcS2HOv2g6bRglcrHf1pXPoOOIn1&unique_id=" + unique_id;
        ApiReadScorecard apiReadScorecard = new ApiReadScorecard();
        apiReadScorecard.setViews(viewPager,mTabLayout,scorecardFragmentManager,swipeRefreshLayoutScorecard);
        apiReadScorecard.execute(s);
    }
}
