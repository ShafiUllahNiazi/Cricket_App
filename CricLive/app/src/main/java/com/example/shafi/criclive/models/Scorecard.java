package com.example.shafi.criclive.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Scorecard implements Parcelable {

    private String titleBatting;
    private String titleBowling;
    private ArrayList<BattingCardModel> battingCardList;
    private ArrayList<BowlingModelClass> bowlingCardList;

    protected Scorecard(Parcel in) {
        titleBatting = in.readString();
        titleBowling = in.readString();

    }

    public static final Creator<Scorecard> CREATOR = new Creator<Scorecard>() {
        @Override
        public Scorecard createFromParcel(Parcel in) {
            return new Scorecard(in);
        }

        @Override
        public Scorecard[] newArray(int size) {
            return new Scorecard[size];
        }
    };

    public String getTitleBatting() {
        return titleBatting;
    }

    public String getTitleBowling() {
        return titleBowling;
    }

    public ArrayList<BattingCardModel> getBattingCardList() {
        return battingCardList;
    }

    public ArrayList<BowlingModelClass> getBowlingCardList() {
        return bowlingCardList;
    }

    public Scorecard(String titleBatting, String titleBowling, ArrayList<BattingCardModel> battingCardList, ArrayList<BowlingModelClass> bowlingCardList) {
        this.titleBatting = titleBatting;
        this.titleBowling = titleBowling;
        this.battingCardList = battingCardList;
        this.bowlingCardList = bowlingCardList;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(titleBatting);
        dest.writeString(titleBowling);
    }
}

