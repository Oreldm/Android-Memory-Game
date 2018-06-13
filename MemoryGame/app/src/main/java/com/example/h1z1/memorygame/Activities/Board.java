package com.example.h1z1.memorygame.Activities;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.h1z1.memorygame.R;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.concurrent.ThreadLocalRandom;

public class Board {
    static int width;
    static int height;
    RelativeLayout.LayoutParams relativeParams;
    RelativeLayout parentView;
    public TableLayout tableLayout;
    static public Card cardArr[];

    static public Timer myTimer;
    TextView counterText;
    TextView nameText;
    private Activity activity;
    public static Context cont;

    public static Card cardUp=null;
    TableRow tableRow;
    public static ArrayList<Card> cards = new ArrayList<>();


    public Board(Context context,int width,int height, Activity activity){ //should add singleton
        this.cont=context;
        this.activity=activity;
        parentView=new RelativeLayout(context);

        counterText= new TextView(context);
        nameText=new TextView(context);
        LinearLayout linearLayout = new LinearLayout(context);
        this.width=width;
        this.height=height;

        cardArr = new Card[width*height];
        for(int i=0; i<width*height;i=i+2){
            cardArr[i]=new Card(context,false,activity);
            cardArr[i+1]=new Card(context,true,activity);
        }
        shuffleArray(cardArr);

        relativeParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        relativeParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);


        tableLayout = new TableLayout(context);
        parentView.setBackgroundResource(R.drawable.gamebackground);

        counterText.setText(context.getString(R.string.timer_str));
        nameText.setText(GameActivity.nameString);


        //initialize board
        for (int i = 0; i < height; i++)
        {
            tableRow = new TableRow(context);
            for (int j = 0; j < width; j++)
            {
                Card card = new Card(context, cardArr[width*i+j]); //added it because of parent viewgroup exception
                cards.add(card);
                tableRow.addView(card.button);
            }
            tableLayout.addView(tableRow);
        }
        tableLayout.setShrinkAllColumns(true);
        tableLayout.setGravity(Gravity.CENTER_HORIZONTAL+Gravity.CENTER);
        tableLayout.setId(3);

        nameText.setId(244);
        counterText.setId(245);


        linearLayout.addView(counterText);

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int screenWidth = size.x;
        int placeForCounter=screenWidth-(2*GameActivity.nameString.length()*GameActivity.nameString.length())-150;

        relativeParams.setMargins(placeForCounter,GameInterface.ZERO,GameInterface.ZERO,GameInterface.ZERO);
        linearLayout.addView(nameText,relativeParams);
        relativeParams.setMargins(GameInterface.ZERO,GameInterface.ZERO,GameInterface.ZERO,GameInterface.ZERO);

        parentView.addView(linearLayout, relativeParams);
        relativeParams.addRule(RelativeLayout.ALIGN_BOTTOM, 3);
        parentView.addView(tableLayout,relativeParams);
        parentView.setLayoutParams(relativeParams);

        TableLayout.LayoutParams tableRowParams=
                new TableLayout.LayoutParams
                        (TableLayout.LayoutParams.FILL_PARENT,TableLayout.LayoutParams.WRAP_CONTENT);



        tableRowParams.setMargins(GameInterface.ZERO, GameInterface.ZERO, screenWidth/16, GameInterface.ZERO);

        if(width==GameInterface.LEVELS.EASY.getValue()){

            for(int i = 0, j = tableLayout.getChildCount(); i < j; i++) {
                View view = tableLayout.getChildAt(i);
                if (view instanceof TableRow) {
                    TableRow row = (TableRow) view;
                    row.setLayoutParams(tableRowParams);
                }
            }
        }


    }

    private void shuffleArray(Card[] ar) {
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
