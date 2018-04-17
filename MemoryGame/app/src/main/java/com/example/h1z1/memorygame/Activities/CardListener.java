package com.example.h1z1.memorygame.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.example.h1z1.memorygame.R;

import java.util.Timer;
import java.util.TimerTask;

public class CardListener implements View.OnClickListener
{
    Button button;
    int cardIndex;
    Context context;
    Card card;
    Activity activity;


    public CardListener(Button button, int cardIndex, Context context,Card card, Activity activity) {
        this.button=button;
        this.cardIndex=cardIndex;
        this.context=context;
        this.card=card;
        this.activity=activity;
    }

    @Override
    public void onClick(View v)
    {

        if(card.isUp || (Board.cardUp!=null && Board.cardUp.isUp==false)){
            return;
        }
        card.isUp=true;
        button.setText("");
        Drawable dr = context.getResources().getDrawable(GameInterface.animals[cardIndex]);
        Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();
        Drawable d = new BitmapDrawable(context.getResources(), Bitmap.createScaledBitmap(bitmap, 30, 50, true));
        button.setBackground(d);


        if(Board.cardUp!=null){



            if(Board.cardUp.cardIndex==this.cardIndex){
                Board.cardUp=null;
                return;
            } else{
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        button.setText("CARD");
                        button.setBackgroundResource(R.drawable.tile);
                        card.isUp=false;
                        Board.cardUp.button.setText("CARD");
                        Board.cardUp.button.setBackgroundResource(R.drawable.tile);
                        Board.cardUp=null;
                    }
                }, 2000);
            }
            Board.cardUp.isUp=false;
        }else{
            Board.cardUp=this.card;
        }
    }


};