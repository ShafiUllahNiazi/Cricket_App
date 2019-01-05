package com.example.shafi.criclive;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import com.example.shafi.criclive.controllers.activities.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class LiveMatchesTest {

    @Rule
    public ActivityTestRule<MainActivity> liveMatchesActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);
    MainActivity mainActivity = null;


    @Before
    public void setUp() throws Exception {

        mainActivity = liveMatchesActivityTestRule.getActivity();
    }

    @Test
    public void myTest(){
        View view;
        view = mainActivity.findViewById(R.id.tabs);
        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception {
        mainActivity = null;
    }
}