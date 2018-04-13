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
    private Timer myTimer;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        text= new TextView(this);
        myTimer = new Timer();
        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                TimerMethod();
            }

        }, 0, 1000);

        Bundle gameIntentData = getIntent().getExtras();
        int width = gameIntentData.getInt("cardWidth");
        int height=4;

        RelativeLayout relativeLayout = new RelativeLayout(this);

        TableLayout tableLayout = new TableLayout(this);
        for (int i = 0; i < height; i++)
        {
            TableRow tableRow = new TableRow(this);
            for (int j = 0; j < width; j++)
            {
                /*
                if(width==3 && j==0){
                    //for alignment to center
                    Button buttonTransperant = new Button(this);
                    buttonTransperant.setVisibility(View.VISIBLE);
                    buttonTransperant.setBackgroundColor(Color.TRANSPARENT);
                    buttonTransperant.setWidth(1);
                    buttonTransperant.setHeight(1);
                    tableRow.addView(buttonTransperant);
                }*/
                Card card= new Card(this);
                tableRow.addView(card.button);
               // button.setOnClickListener(imgButtonHandler);
            }
            tableLayout.addView(tableRow);
        }
        tableLayout.setShrinkAllColumns(true);
        tableLayout.setGravity(Gravity.CENTER_HORIZONTAL+Gravity.CENTER);
        text.setText("timer");
        tableLayout.addView(text);
        setContentView(tableLayout);
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
