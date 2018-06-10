package com.example.h1z1.memorygame.Activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.h1z1.memorygame.R;

import java.util.Timer;
import java.util.TimerTask;


public class GameActivity extends AppCompatActivity {
    TextView timerText; //timer text
    TextView nameText;
    static String nameString;
    static int counter = 0;
    static int cardsUp = 0;
    Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        Bundle gameIntentData = getIntent().getExtras();
        nameText = new TextView(this);
        nameString = gameIntentData.getString(getString(R.string.usernamekey));
        int width = gameIntentData.getInt(getString(R.string.card_width_key));
        if (width == GameInterface.LEVELS.HARD.getValue()) {
            counter = GameInterface.HARD_TIMER;
        } else if (width == GameInterface.LEVELS.MEDIUM.getValue()) {
            counter = GameInterface.MEDIUM_TIMER;
        } else
            counter = GameInterface.EASY_TIMER;
        int height = GameInterface.BOARD_WIDTH;
        final Board board = new Board(this, width, height, this);
        timerText = board.counterText;


        setContentView(board.parentView);
    }


    private boolean TimerMethod()
    {
        if(counter==GameInterface.ZERO){
            WinLostActivity.status=getString(R.string.lose_str);
            Intent i = new Intent(this, WinLostActivity.class);
            startActivity(i);
            cardsUp=GameInterface.ZERO;
            finish();
            return false;
        } else if(GameActivity.cardsUp==Board.width*Board.height){
            WinLostActivity.status=getString(R.string.win_str);
            Intent i = new Intent(this, WinLostActivity.class);
            startActivity(i);
            cardsUp=GameInterface.ZERO;
            finish();
            return false;
        }else{
            this.runOnUiThread(Timer_Tick);
            return true;
        }
    }

    private Runnable Timer_Tick = new Runnable() {
        public void run() {
            timerText.setText(Integer.toString(counter));
            counter--;

        }
    };


    private boolean isInFocus = false;

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        isInFocus = hasFocus;
        if(hasFocus){
            Board.myTimer=new Timer();
            Board.myTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    boolean shouldStop=TimerMethod();
                    if(!shouldStop){
                        Board.myTimer.cancel();
                        Board.myTimer.purge();
                    }
                }

            }, GameInterface.ZERO, GameInterface.DELAY_1000MS);
        }
        else{
            Board.myTimer.cancel();
            Board.myTimer.purge();
            finish();
        }
    }

    @Override
    public void onStop() {
        super.onStop();

        if (!isInFocus) finish();
    }






}
