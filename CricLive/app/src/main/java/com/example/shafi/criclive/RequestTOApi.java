package com.example.shafi.criclive;

import java.util.ArrayList;

public class RequestTOApi {

    String apiUrl;
    MyAdapter mAdapter;
    ArrayList<LiveMatchesModelClass> listLiveMatches;
    ArrayList<OldMatches> listOldMatches;
    ArrayList<OldMatches> listUpcomingMatches;
    ApiRead apiReadRequest;
    public RequestTOApi(String apiUrl, MyAdapter mAdapter, ArrayList<LiveMatchesModelClass> listLiveMatches, ArrayList<OldMatches> listOldMatches, ArrayList<OldMatches> listUpcomingMatches) {

        this.apiUrl=apiUrl;
        this.mAdapter=mAdapter;
        this.listLiveMatches=listLiveMatches;
        this.listOldMatches=listOldMatches;
        this.listUpcomingMatches=listUpcomingMatches;
    }
    public void sentRequest(){
//        apiReadRequest = new ApiRead();
        return;


    }

}
