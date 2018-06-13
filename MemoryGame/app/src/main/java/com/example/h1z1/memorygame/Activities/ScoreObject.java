package com.example.h1z1.memorygame.Activities;


import com.google.android.gms.maps.model.LatLng;

public class ScoreObject implements Comparable<ScoreObject> {
    private String playerName;
    private int score;
    private LatLng playerLocation;

    public ScoreObject(int score, String playerName) {
        this.score = score;
        this.playerName = playerName;
        this.playerLocation = new LatLng(StartGameActivity.latitude, StartGameActivity.longitude);
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return playerName;
    }

    public LatLng getPlayerLocation() {
        return playerLocation;
    }

    @Override
    public int compareTo(ScoreObject other) {
        if (other == null)
            return 1;
        if (this.score < other.score)
            return -1;
        else if (this.score > other.score)
            return 1;
        return 0;
    }
}