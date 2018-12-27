package com.example.shafi.criclive;

import org.json.JSONObject;

import java.io.Serializable;

public class OldMatchesDescriptiveModelClass implements Serializable {
    private JSONObject jsonObject;
    private OldMatchesModelClass oldMatchesListsItem;


    public OldMatchesDescriptiveModelClass(OldMatchesModelClass oldMatchesListsItem, JSONObject jsonObject) {
        this.oldMatchesListsItem = oldMatchesListsItem;
        this.jsonObject = jsonObject;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public OldMatchesModelClass getOldMatchesListsItem() {
        return oldMatchesListsItem;
    }


}
