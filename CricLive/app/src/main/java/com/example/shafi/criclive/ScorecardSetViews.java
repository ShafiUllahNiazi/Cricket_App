package com.example.shafi.criclive;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;

public class ScorecardSetViews {

    private ViewPager viewPager;
    private TabLayout mTabLayout;
    private FragmentManager scorecardFragmentManager;


    public ScorecardSetViews(ViewPager viewPager, TabLayout mTabLayout, FragmentManager scorecardFragmentManager) {
        this.viewPager = viewPager;
        this.mTabLayout = mTabLayout;
        this.scorecardFragmentManager = scorecardFragmentManager;

    }


    public void initViews(int numOftabs, ArrayList<Scorecard> scorecardList) {

        viewPager.setOffscreenPageLimit(5);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        setDynamicFragmentToTabLayout(numOftabs,scorecardList);
    }

    private void setDynamicFragmentToTabLayout(int numOftabs, ArrayList<Scorecard> scorecardList) {
        mTabLayout.removeAllTabs();
        for (int i = 0; i < numOftabs; i++) {

            mTabLayout.addTab(mTabLayout.newTab().setText("Category: " + i));
        }
        DynamicFragmentAdapter mDynamicFragmentAdapter = new DynamicFragmentAdapter(scorecardFragmentManager, mTabLayout.getTabCount(),scorecardList);
        viewPager.setAdapter(mDynamicFragmentAdapter);
        viewPager.setCurrentItem(0);
    }

}
