package com.example.shafi.criclive.models;

public class BowlingModelClass {

    private String pid;
    private String bowler;
    private String o;
    private String m;
    private String r;
    private String w;
    private String econ;
    private int zeros;
    private int fours;
    private int sixes;


    public BowlingModelClass(String pid, String bowler, String o, String m, String r, String w, String econ, int zeros, int fours, int sixes) {
        this.pid = pid;
        this.bowler = bowler;
        this.o = o;
        this.m = m;
        this.r = r;
        this.w = w;
        this.econ = econ;
        this.zeros = zeros;
        this.fours = fours;
        this.sixes = sixes;
    }



    public String getPid() {
        return pid;
    }

    public String getBowler() {
        return bowler;
    }

    public String getO() {
        return o;
    }

    public String getM() {
        return m;
    }

    public String getR() {
        return r;
    }

    public String getW() {
        return w;
    }

    public String getEcon() {
        return econ;
    }

    public int getZeros() {
        return zeros;
    }

    public int getFours() {
        return fours;
    }

    public int getSixes() {
        return sixes;
    }





}
