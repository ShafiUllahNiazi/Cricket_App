package com.example.shafi.criclive.controllers.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.shafi.criclive.controllers.fragments.DynamicFragment;
import com.example.shafi.criclive.models.Scorecard;

import java.util.ArrayList;

public class DynamicFragmentAdapter extends FragmentStatePagerAdapter {


    private int mNumOfTabs;
    private ArrayList<Scorecard> scorecardList;



    public DynamicFragmentAdapter(FragmentManager fm, int numOfTabs, ArrayList<Scorecard> scorecardList) {

        super(fm);
        this.mNumOfTabs= numOfTabs;
        this.scorecardList = scorecardList;


    }

    @Override
    public Fragment getItem(int i) {
        Bundle b = new Bundle();
        b.putParcelableArrayList("scorecardList",scorecardList);
        b.putInt("position", i);
        Fragment frag = DynamicFragment.newInstance();
        frag.setArguments(b);





        return frag;
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
