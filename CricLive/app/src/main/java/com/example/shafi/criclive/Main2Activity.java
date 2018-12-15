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
    String data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Log.d(TAG,"oncreate starting");
        mSectionPageAdapter = new SectionPageAdapter(getSupportFragmentManager());

        mViewPager = findViewById(R.id.container);
        setupViewPager(mViewPager);
        TabLayout tabLayout = findViewById(R.id.tabs2);
        tabLayout.setupWithViewPager(mViewPager);

        Intent intent = getIntent();
        data = intent.getStringExtra("unique_id");
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();

    }


    private void setupViewPager(ViewPager viewPager){
        SectionPageAdapter adapter = new SectionPageAdapter(getSupportFragmentManager());
        MatchSummary ob= new MatchSummary();
        ob.getData("usman and shafi");
        adapter.addFragment(ob,"Match Summary");


        adapter.addFragment(new UpcomingMatches(),"Upcoming");
        viewPager.setAdapter(adapter);

    }




}
