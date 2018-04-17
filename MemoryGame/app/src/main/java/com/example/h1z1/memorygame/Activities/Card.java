package com.example.h1z1.memorygame.Activities;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;

import com.example.h1z1.memorygame.R;

public class Card {
    public static int lastCardIndex = 0;
    public boolean isUp=false;

    Context context;
    public int cardIndex;
    public Button button;
    private Activity activity;

    public Card(Context context, boolean isNextCard, Activity activity){
        this.activity=activity;
        this.context=context;
        button=new Button(context);
        button.setText("CARD");
        button.setBackgroundResource(R.drawable.tile);
        this.cardIndex=lastCardIndex;
        button.setOnClickListener(new CardListener(button,cardIndex,context,this,activity));
        if(isNextCard)
            lastCardIndex++;
        if(lastCardIndex==10)
            lastCardIndex=0;
    }

    public Card(Context context, Card card){
        this.context=context;
        button=new Button(context);
        button.setText("CARD");
        button.setBackgroundResource(R.drawable.tile);
        this.cardIndex=card.cardIndex;
        button.setOnClickListener(new CardListener(button,cardIndex,context,this,activity));
    }


}
