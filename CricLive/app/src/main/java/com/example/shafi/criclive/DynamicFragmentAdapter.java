package com.example.shafi.criclive;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class DynamicFragmentAdapter extends FragmentStatePagerAdapter {


    private int mNumOfTabs;
    private ArrayList<Scorecard> scorecardList;
    Context context;


    public DynamicFragmentAdapter(FragmentManager fm, int numOfTabs, ArrayList<Scorecard> scorecardList, Context context) {

        super(fm);
        this.mNumOfTabs= numOfTabs;
        this.scorecardList = scorecardList;
        this.context = context;

    }

    @Override
    public Fragment getItem(int i) {
        Bundle b = new Bundle();
        b.putParcelableArrayList("scorecardList",scorecardList);
        b.putInt("position", i);
        Fragment frag = DynamicFragment.newInstance();
        frag.setArguments(b);

//        Fragment fragment = new DynamicFragment(scorecardList);



        return frag;
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
