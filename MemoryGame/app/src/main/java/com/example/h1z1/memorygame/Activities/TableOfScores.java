package com.example.h1z1.memorygame.Activities;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

import com.example.h1z1.memorygame.R;
import com.google.gson.Gson;

public class TableOfScores {
    private ArrayList<ScoreObject> scoreArr;
    private int currentMinScore;
    private Context context;

    public TableOfScores(Context context) {
        currentMinScore=-1;
        scoreArr = new ArrayList<>();
        this.context = context;
        SharedPreferences shared = context.getSharedPreferences(context.getString(R.string.score_table_name), android.content.Context.MODE_PRIVATE);
        Map<String,?> scoreToJson = shared.getAll();
        for (Map.Entry<String, ?> e : scoreToJson.entrySet()){
            String jsonStr = e.getValue().toString();
            scoreArr.add(new Gson().fromJson(jsonStr,ScoreObject.class));
        }
    }

    public void tableToMemory() {
        SharedPreferences.Editor e = context.getSharedPreferences(context.getString(R.string.score_table_name), android.content.Context.MODE_PRIVATE).edit();
        int i=0;
        while(i < scoreArr.size()) {
            e.putString(this.getClass().getSimpleName() + i, new Gson().toJson(this.scoreArr.get(i)));
            i++;
        }
        e.apply();
    }

    public void addScore(ScoreObject s) {
        if (scoreArr.size() < leader_board_activity.SIZE) {
            //do nothing
        } else if (s.getScore() > currentMinScore) {
            scoreArr.remove(scoreArr.size() - 1);
        } else
            return;
        scoreArr.add(s);
        Collections.sort(scoreArr);
        this.currentMinScore = scoreArr.get(scoreArr.size() - 1).getScore();
    }

    public  ArrayList<ScoreObject> getScoreArr() {
        return this.scoreArr;
    }
}