package com.example.shafi.criclive.controllers.activities;


import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.shafi.criclive.controllers.fragments.LiveMatches;
import com.example.shafi.criclive.controllers.fragments.OldMatches;
import com.example.shafi.criclive.R;
import com.example.shafi.criclive.controllers.adapters.SectionPageAdapter;
import com.example.shafi.criclive.controllers.fragments.UpcomingMatches;
import com.example.shafi.criclive.themeUtils;

public class MainActivity extends AppCompatActivity {


    int mytheme = 1;


    private static final String TAG = "MainActivity";
    private SectionPageAdapter mSectionPageAdapter;
    private ViewPager mViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        themeUtils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "oncreate starting");

//        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(mToolbar);

        mSectionPageAdapter = new SectionPageAdapter(getSupportFragmentManager());

        mViewPager = findViewById(R.id.container);
        setupViewPager(mViewPager);
        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

    }


    private void setupViewPager(ViewPager viewPager) {
        SectionPageAdapter adapter = new SectionPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new LiveMatches(), "Live");
        adapter.addFragment(new OldMatches(), "Results");
        adapter.addFragment(new UpcomingMatches(), "Upcoming");
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


            if (mytheme == 1) {
                themeUtils.changeToTheme(this, themeUtils.BLUE);
                mytheme = 0;

            } else {
                themeUtils.changeToTheme(this, themeUtils.BLACK);
                mytheme = 1;
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
