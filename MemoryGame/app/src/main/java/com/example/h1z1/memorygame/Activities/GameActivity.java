package com.example.h1z1.memorygame.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.h1z1.memorygame.R;

import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {
    TextView text; //timer text

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle gameIntentData = getIntent().getExtras();
        int width = gameIntentData.getInt("cardWidth");
        int height=4;
        Board board= new Board(this,width,height);
        text=board.text;
        board.myTimer = new Timer();
        board.myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                TimerMethod();
            }

        }, 0, 1000);


        setContentView(board.tableLayout);
    }


    private void TimerMethod()
    {
        //This method is called directly by the timer
        //and runs in the same thread as the timer.

        //We call the method that will work with the UI
        //through the runOnUiThread method.
        this.runOnUiThread(Timer_Tick);
    }
    static int a = 0;
    private Runnable Timer_Tick = new Runnable() {
        public void run() {
            text.setText(Integer.toString(a));
            a++;

        }
    };



}
