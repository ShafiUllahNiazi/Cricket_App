package com.example.shafi.criclive.controllers.activities;


import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.shafi.criclive.controllers.fragments.MatchScorecard;
import com.example.shafi.criclive.MatchSummary;
import com.example.shafi.criclive.R;
import com.example.shafi.criclive.controllers.adapters.SectionPageAdapter;
import com.example.shafi.criclive.controllers.fragments.MatchSummayOldMatches;
import com.example.shafi.criclive.themeUtils;

public class Main2Activity extends AppCompatActivity {


    private static final String TAG = "Main2Activity";
    private SectionPageAdapter mSectionPageAdapter;
    private ViewPager mViewPager;
    long unique_id;
    String score;
    String winner_team;
    String summaryType;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
        unique_id = intent.getLongExtra("id2", 0);
        score = intent.getStringExtra("score");
        winner_team = intent.getStringExtra("winner_team");
        summaryType = intent.getStringExtra("summaryType");
        mSectionPageAdapter = new SectionPageAdapter(getSupportFragmentManager());
        mViewPager = findViewById(R.id.container2);
        setupViewPager(mViewPager);
        TabLayout tabLayout = findViewById(R.id.tabs2);
        tabLayout.setupWithViewPager(mViewPager);
    }


    private void setupViewPager(ViewPager viewPager) {
        SectionPageAdapter adapter = new SectionPageAdapter(getSupportFragmentManager());

//        if(summaryType.equals("Live")){
//
//            MatchSummary matchSummaryFragment = new MatchSummary();
//            Bundle b = new Bundle();
//            b.putString("unique_id", unique_id + "");
//            b.putString("score",score);
//            matchSummaryFragment.setArguments(b);
//            adapter.addFragment(matchSummaryFragment, "Match Summary");
//
//        }else {
//
//
//            MatchSummayOldMatches summayOldMatches = new MatchSummayOldMatches();
//            Bundle b = new Bundle();
//            b.putString("unique_id", unique_id + "");
//            b.putString("score",score);
//            b.putString("winner_team",winner_team);
//            summayOldMatches.setArguments(b);
//            adapter.addFragment(summayOldMatches, "Match Summary");
//        }

        MatchSummayOldMatches summayOldMatches = new MatchSummayOldMatches();
        Bundle b = new Bundle();
        b.putString("unique_id", unique_id + "");
        b.putString("score",score);
        b.putString("winner_team",winner_team);
        summayOldMatches.setArguments(b);
        adapter.addFragment(summayOldMatches, "Match Summary");
        MatchScorecard matchScorecardFragment = new MatchScorecard();
        Bundle bundle = new Bundle();
        bundle.putString("unique_id", unique_id + "");
        matchScorecardFragment.setArguments(bundle);
        adapter.addFragment(matchScorecardFragment, "Scorecard");
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d("mennu", "ddd");
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_Change_Theme) {


            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
