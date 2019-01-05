package com.example.shafi.criclive.controllers.activities;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.design.widget.TabLayout;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.MonitoringInstrumentation;
import android.view.View;

import com.example.shafi.criclive.R;
import com.example.shafi.criclive.controllers.fragments.LiveMatches;
import com.example.shafi.criclive.controllers.fragments.OldMatches;
import com.example.shafi.criclive.controllers.fragments.UpcomingMatches;
import com.example.shafi.criclive.database.UpComingMatchesDatabase;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> rule = new ActivityTestRule<MainActivity>(MainActivity.class);
    MainActivity mainActivity = null;

    Instrumentation.ActivityMonitor monitor= getInstrumentation().addMonitor(UpcomingMatches.class.getName(),null,false);

    @Before
    public void setUp() throws Exception {

        mainActivity = rule.getActivity();
    }

    @Test
    public void myTest1(){
        View view;
        view = mainActivity.findViewById(R.id.tabs);
        assertNotNull(view);

    }
    @Test
    public void myTest2(){
        View view;
        view = mainActivity.findViewById(R.id.appbar);
        assertNotNull(view);

    }
    @Test
    public void Test3()
    {
        final View tab;
        tab= mainActivity.findViewById(R.id.tabs).findViewById(R.id.tabItem3);
        mainActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                assertTrue(tab.isActivated());
            }
        });


    }

    @Test
    public void myTest4(){
        View view;
        view = mainActivity.findViewById(R.id.container);
        assertNotNull(view);

    }


    @After
    public void tearDown() throws Exception {
        mainActivity = null;
    }

}