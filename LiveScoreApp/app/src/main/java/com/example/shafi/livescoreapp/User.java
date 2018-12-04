package com.example.shafi.livescoreapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class User {

    public String description;
//    public String hometown;

//    public User(){
//        this.description = null;
//
//    }
    public User(String name) {
        this.description = name;
//        this.hometown = hometown;
    }

    // Constructor to convert JSON object into a Java class instance
    public User(JSONObject object){
        try {
            this.description = object.getString("description");
//            this.hometown = object.getString("hometown");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // Factory method to convert an array of JSON objects into a list of objects
    // User.fromJson(jsonArray);
    public static ArrayList<User> fromJson(JSONArray jsonObjects) {
        ArrayList<User> users = new ArrayList<User>();
        for (int i = 0; i < jsonObjects.length(); i++) {
            try {
                users.add(new User(jsonObjects.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return users;
    }
}