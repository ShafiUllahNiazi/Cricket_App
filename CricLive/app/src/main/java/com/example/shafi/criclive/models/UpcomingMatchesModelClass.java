package com.example.shafi.criclive.models;

public class UpcomingMatchesModelClass {
    private long unique_id;
    private String date;
    private String dateTimeGMT;
    private String team_1;
    private String team_2;
    private String type;
    private boolean squad;
    private boolean matchStarted;

    public UpcomingMatchesModelClass(long unique_id, String date, String dateTimeGMT, String team_1, String team_2, String type, boolean squad, boolean matchStarted) {
        this.unique_id = unique_id;
        this.date = date;
        this.dateTimeGMT = dateTimeGMT;
        this.team_1 = team_1;
        this.team_2 = team_2;
        this.type = type;
        this.squad = squad;

        this.matchStarted = matchStarted;
    }

    public long getUnique_id() {
        return unique_id;
    }

    public String getDate() {
        return date;
    }

    public String getDateTimeGMT() {
        return dateTimeGMT;
    }

    public String getTeam_1() {
        return team_1;
    }

    public String getTeam_2() {
        return team_2;
    }

    public String getType() {
        return type;
    }

    public boolean isSquad() {
        return squad;
    }

    public boolean isMatchStarted() {
        return matchStarted;
    }
}
