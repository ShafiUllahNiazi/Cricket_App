package com.example.shafi.criclive;

public class OldMatchesModelClass {

    private long unique_id;
    private String date;
    private String dateTimeGMT;
    private String team_1;
    private String team_2;
    private String type;
    private boolean squad;
    private String toss_winner_team;
    private String winner_team;
    private boolean matchStarted;

    public OldMatchesModelClass(long unique_id, String date, String dateTimeGMT, String team_1, String team_2, String type, boolean squad, String toss_winner_team, String winner_team, boolean matchStarted) {
        this.unique_id = unique_id;
        this.date = date;
        this.dateTimeGMT = dateTimeGMT;
        this.team_1 = team_1;
        this.team_2 = team_2;
        this.type = type;
        this.squad = squad;
        this.toss_winner_team = toss_winner_team;
        this.winner_team = winner_team;
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

    public String getToss_winner_team() {
        return toss_winner_team;
    }

    public String getWinner_team() {
        return winner_team;
    }

    public boolean isMatchStarted() {
        return matchStarted;
    }
}
