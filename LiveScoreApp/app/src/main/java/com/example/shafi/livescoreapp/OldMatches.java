package com.example.shafi.livescoreapp;

public class OldMatches {
    private String title;
    private String description;
    private String unique_id;

    public OldMatches(String title, String description, String unique_id) {
        this.title = title;
        this.description = description;
        this.unique_id = unique_id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUnique_id() {
        return unique_id;
    }
}
