package com.example.h1z1.memorygame.Activities;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.h1z1.memorygame.R;

import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {
    TextView timerText; //timer text
    TextView nameText;
    static String nameString;
    static int counter = 0;
    static int cardsUp=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle gameIntentData = getIntent().getExtras();
        nameText=new TextView(this);
        nameString=gameIntentData.getString("username");
        int width = gameIntentData.getInt("cardWidth");
        if(width==GameInterface.LEVELS.HARD.getValue()){
            counter=60;
        } else if(width==GameInterface.LEVELS.MEDIUM.getValue()){
            counter=45;
        }else
            counter=30;
        int height=4;
        final Board board= new Board(this,width,height,this);
        timerText=board.text;
        /*board.myTimer = new Timer();
        board.myTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    boolean shouldStop=TimerMethod();
                    if(!shouldStop){
                        board.myTimer.cancel();
                        board.myTimer.purge();
                    }
                }

        }, 0, 1000);
*/

        setContentView(board.parentView);
    }


    private boolean TimerMethod()
    {
        //This method is called directly by the timer
        //and runs in the same thread as the timer.

        //We call the method that will work with the UI
        //through the runOnUiThread method.
        if(counter==0){
            WinLostActivity.status="You Lose!";
            Intent i = new Intent(this, WinLostActivity.class);
            startActivity(i);
            cardsUp=0;
            finish();
            return false;
        } else if(GameActivity.cardsUp==Board.width*Board.height){
            WinLostActivity.status="You Win!";
            Intent i = new Intent(this, WinLostActivity.class);
            startActivity(i);
            cardsUp=0;
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
        /*Board.myTimer.cancel();
        Board.myTimer.purge();*/
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

            }, 0, 1000);
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
        /*
        Board.myTimer.cancel();
        Board.myTimer.purge();*/
        if (!isInFocus) finish();
    }


}
