package com.example.h1z1.memorygame.Activities;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.h1z1.memorygame.R;

import java.util.Timer;
import java.util.TimerTask;

public class Board {
    int width;
    int height;
    RelativeLayout relativeLayout;
    TableLayout tableLayout;

    public Timer myTimer;
    TextView text;

    public Board(Context context,int width,int height){ //singleton
        text= new TextView(context);

        this.width =width;
        this.height=height;

        Card cardArr[] = new Card[width*height];
        for(int i=0; i<width*height;i=i+2){
            cardArr[i]=new Card(context,false);
            cardArr[i+1]=new Card(context,true);
        }


        relativeLayout = new RelativeLayout(context);

        tableLayout = new TableLayout(context);
        for (int i = 0; i < height; i++)
        {
            TableRow tableRow = new TableRow(context);
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
                Card card = new Card(context, cardArr[width*i+j]);
                tableRow.addView(card.button);
                // button.setOnClickListener(imgButtonHandler);
            }
            tableLayout.addView(tableRow);
        }
        tableLayout.setShrinkAllColumns(true);
        tableLayout.setGravity(Gravity.CENTER_HORIZONTAL+Gravity.CENTER);
        text.setText("timer");
        tableLayout.addView(text);
    }

}
