package com.example.shafi.criclive.models;

import org.json.JSONObject;

import java.io.Serializable;

public class LiveMatchesDescriptiveModelClass implements Serializable {

    private JSONObject jsonObject;
    private LiveMatchesModelClass liveMatchesListsItem;

    public LiveMatchesDescriptiveModelClass(LiveMatchesModelClass liveMatchesListsItem, JSONObject jsonObject) {
        this.liveMatchesListsItem = liveMatchesListsItem;
        this.jsonObject = jsonObject;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public LiveMatchesModelClass getLiveMatchesListsItem() {
        return liveMatchesListsItem;
    }
}
