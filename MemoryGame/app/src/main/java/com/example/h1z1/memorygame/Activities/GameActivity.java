package com.example.h1z1.memorygame.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import tyrantgit.explosionfield.ExplosionField;
import android.widget.TextView;

import com.example.h1z1.memorygame.R;
import java.util.Timer;
import java.util.TimerTask;


public class GameActivity extends AppCompatActivity {
    public AppCompatActivity a = this;
    TextView timerText; //timer text
    TextView nameText;
    static String nameString;
    static int counter = 0;
    static int cardsUp = 0;
    Context mContext;
    static int score=0;
    static int multiplier=0;
    final static int EASY_SCORE = 1;
    final static int MEDIUM_SCORE = 2;
    final static int HARD_SCORE = 3;
    Board board;

    ExplosionField explosionField;

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
            multiplier=HARD_SCORE;
        } else if (width == GameInterface.LEVELS.MEDIUM.getValue()) {
            counter = GameInterface.MEDIUM_TIMER;
            multiplier=MEDIUM_SCORE;
        } else {
            counter = GameInterface.EASY_TIMER;
            multiplier=EASY_SCORE;
        }
        int height = GameInterface.BOARD_WIDTH;
        this.board = new Board(this, width, height, this);
        timerText = board.counterText;

        setContentView(board.parentView);
    }


    private boolean TimerMethod()
    {
        if(counter==GameInterface.ZERO){
            //Lose because of zero
            WinLostActivity.status=getString(R.string.lose_str);
            Intent i = new Intent(this, WinLostActivity.class);
            startActivity(i);
            cardsUp=GameInterface.ZERO;
            score=0;
            finish();
            return false;
        } else if(GameActivity.cardsUp==Board.width*Board.height){
            //WIN
            WinLostActivity.status=getString(R.string.win_str);
            Intent i = new Intent(this, WinLostActivity.class);
            startActivity(i);
            cardsUp=GameInterface.ZERO;
            score=multiplier*counter;
            ScoreObject se= new ScoreObject(score, nameString);
            WelcomePageActivity.hst.addScore(se);
            WelcomePageActivity.hst.tableToMemory();
            finish();
            return false;
        }else{
            //keep going- game is still live
            this.runOnUiThread(Timer_Tick);
            return true;
        }
    }


    private Runnable Timer_Tick = new Runnable() {
        public void run() {
            timerText.setText(Integer.toString(counter));
            counter--;
            if(counter==0){

               /* Animation pulse = AnimationUtils.loadAnimation(Board.cont, R.anim.kaboom);
                board.parentView.startAnimation(pulse);*/
                explosionField = ExplosionField.attach2Window(a);
                explosionField.explode(board.parentView);
            }
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
