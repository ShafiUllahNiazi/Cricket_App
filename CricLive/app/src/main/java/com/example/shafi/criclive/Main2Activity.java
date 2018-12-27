package com.example.shafi.criclive;



import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {


    private static final String TAG ="Main2Activity";
    private SectionPageAdapter mSectionPageAdapter;
    private ViewPager mViewPager;
    long unique_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Log.d(TAG,"oncreate starting");

        Intent intent = getIntent();
        unique_id =intent.getLongExtra("id2",0);
        Log.d("Jaleel",unique_id+"");



        mSectionPageAdapter = new SectionPageAdapter(getSupportFragmentManager());

        mViewPager = findViewById(R.id.container2);
        setupViewPager(mViewPager);
        TabLayout tabLayout = findViewById(R.id.tabs2);
        tabLayout.setupWithViewPager(mViewPager);


//        LiveMatchesDescriptiveModelClass stuff = (LiveMatchesDescriptiveModelClass) intent.getSerializableExtra("id");
//        Toast.makeText(this, stuff.getLiveMatchesListsItem().getUnique_id() + " Waleed", Toast.LENGTH_SHORT).show();

//        Toast.makeText(this, "333 " + g , Toast.LENGTH_SHORT).show();
    }


    private void setupViewPager(ViewPager viewPager){
        SectionPageAdapter adapter = new SectionPageAdapter(getSupportFragmentManager());
        MatchSummary matchSummaryFragment= new MatchSummary();
        Bundle b = new Bundle();
        b.putString("unique_id",unique_id+"");
//        ob.setData(unique_id+"");
        matchSummaryFragment.setArguments(b);
        adapter.addFragment(matchSummaryFragment,"Match Summary");

        MatchScorecard matchScorecardFragment = new MatchScorecard();
        Bundle bundle = new Bundle();
        bundle.putString("unique_id",unique_id+"");
        matchScorecardFragment.setArguments(bundle);



        adapter.addFragment(matchScorecardFragment,"Scorecard");
        viewPager.setAdapter(adapter);
    }




}
