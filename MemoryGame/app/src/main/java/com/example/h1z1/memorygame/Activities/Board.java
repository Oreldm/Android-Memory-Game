package com.example.h1z1.memorygame.Activities;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
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
    RelativeLayout.LayoutParams relativeParams;
    RelativeLayout parentView;
    TableLayout tableLayout;

    public Timer myTimer;
    TextView text;
    TextView nameText;

    public Board(Context context,int width,int height){ //singleton
        parentView=new RelativeLayout(context);
        text= new TextView(context);
        nameText=new TextView(context);
        LinearLayout linearLayout = new LinearLayout(context);
        this.width=width;
        this.height=height;

        Card cardArr[] = new Card[width*height];
        for(int i=0; i<width*height;i=i+2){
            cardArr[i]=new Card(context,false);
            cardArr[i+1]=new Card(context,true);
        }
        shuffleArray(cardArr);

        relativeParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        relativeParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);


        tableLayout = new TableLayout(context);

        text.setText("timer");
        nameText.setText(GameActivity.nameString);

        for (int i = 0; i < height; i++)
        {
            TableRow tableRow = new TableRow(context);
            for (int j = 0; j < width; j++)
            {

                Card card = new Card(context, cardArr[width*i+j]); //added it because of parent viewgroup exception
                tableRow.addView(card.button);
            }
            tableLayout.addView(tableRow);
        }
        tableLayout.setShrinkAllColumns(true);
        tableLayout.setGravity(Gravity.CENTER_HORIZONTAL+Gravity.CENTER);
        tableLayout.setId(3);

        nameText.setId(244);
        text.setId(245);



        relativeParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        relativeParams.addRule(RelativeLayout.LEFT_OF,244);
        linearLayout.addView(nameText);

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int screenWidth = size.x;
        int placeForCounter=screenWidth-(2*GameActivity.nameString.length()*GameActivity.nameString.length())-70;

        relativeParams.setMargins(0,0,placeForCounter,0);
        linearLayout.addView(text,relativeParams);
        relativeParams.setMargins(0,0,0,0);

        parentView.addView(linearLayout, relativeParams);
        relativeParams.addRule(RelativeLayout.ALIGN_BOTTOM, 3);
        parentView.addView(tableLayout,relativeParams);
        parentView.setLayoutParams(relativeParams);

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
