package com.example.shafi.criclive.models;

import java.util.ArrayList;

public class MatchSummaryModel {

    private String winnerTeam;
    private String scoreSummary;
    private String manOfMan;
    private BattingCardModel battingCardList;

    public MatchSummaryModel(String winnerTeam, String scoreSummary, String manOfMan,BattingCardModel battingCardList) {
        this.winnerTeam = winnerTeam;
        this.scoreSummary = scoreSummary;
        this.manOfMan = manOfMan;
        this.battingCardList = battingCardList;


    }

    public String getWinnerTeam() {
        return winnerTeam;
    }

    public String getScoreSummary() {
        return scoreSummary;
    }

    public String getManOfMan() {
        return manOfMan;
    }

    public BattingCardModel getBattingCardList() {
        return battingCardList;
    }




}
