package com.example.shafi.criclive.models;

import org.json.JSONObject;

public class BattingCardModel {

    private String pid;
    private String batsman;
    private String dismissal_info;
    private int runs;
    private int balls;
    private int fours;
    private int sixes;
    private int sR;
    private String dismissal;
    private JSONObject dismissal_by;

    public String getPid() {
        return pid;
    }

    public String getBatsman() {
        return batsman;
    }

    public String getDismissal_info() {
        return dismissal_info;
    }

    public int getRuns() {
        return runs;
    }

    public int getBalls() {
        return balls;
    }

    public int getFours() {
        return fours;
    }

    public int getSixes() {
        return sixes;
    }

    public int getsR() {
        return sR;
    }

    public String getDismissal() {
        return dismissal;
    }

    public JSONObject getDismissal_by() {
        return dismissal_by;
    }

    public BattingCardModel(String pid, String batsman, String dismissal_info, int runs, int balls, int fours, int sixes, int sR, String dismissal) {
        this.pid = pid;
        this.batsman = batsman;
        this.dismissal_info = dismissal_info;
        this.runs = runs;
        this.balls = balls;
        this.fours = fours;
        this.sixes = sixes;
        this.sR = sR;
        this.dismissal = dismissal;
        this.dismissal_by = dismissal_by;
    }
}
