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

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;

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
        shuffleArray(cardArr);

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
                Card card = new Card(context, cardArr[width*i+j]); //added it because of parent viewgroup exception
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

    private void shuffleArray(Card[] ar)
    {
        // Fisher–Yates shuffle
        Random randIndex = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--)
        {
            int index = randIndex.nextInt(i + 1);

            //swap
            Card a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }

}
