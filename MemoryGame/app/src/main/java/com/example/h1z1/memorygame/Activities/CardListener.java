package com.example.h1z1.memorygame.Activities;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.example.h1z1.memorygame.R;


public class CardListener implements View.OnClickListener {
    private final static int MARGIN_TO_ADD = 100;
    Button button;
    int cardIndex;
    Context context;
    Card card;
    Activity activity;
    private final static int PAIR = 2;


    public CardListener(Button button, int cardIndex, Context context, Card card, Activity activity) {
        this.button = button;
        this.cardIndex = cardIndex;
        this.context = context;
        this.card = card;
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {

        if (card.isUp || (Board.cardUp != null && Board.cardUp.isUp == false)) {
            return;
        }
        card.isUp = true;
        button.setText("");
        Drawable dr = context.getResources().getDrawable(GameInterface.animals[cardIndex]);
        Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();
        BitmapFactory.Options dimensions = new BitmapFactory.Options();
        dimensions.inJustDecodeBounds = true;
        Bitmap mBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.tile, dimensions);
        int height = dimensions.outHeight;
        int width = dimensions.outWidth;

        Drawable d = new BitmapDrawable(context.getResources(), Bitmap.createScaledBitmap(bitmap, width + MARGIN_TO_ADD, height + MARGIN_TO_ADD, true));
        button.setBackground(d);


        if (Board.cardUp != null) {
            if (Board.cardUp.cardIndex == this.cardIndex) {
                GameActivity.cardsUp += PAIR;
                Board.cardUp = null;
                return;
            } else {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        button.setText(context.getString(R.string.card_str));
                        button.setBackgroundResource(R.drawable.tile);
                        card.isUp = false;
                        Board.cardUp.button.setText(context.getString(R.string.card_str));
                        Board.cardUp.button.setBackgroundResource(R.drawable.tile);
                        Board.cardUp = null;
                    }
                }, GameInterface.DELAY_1000MS);
            }
            Board.cardUp.isUp = false;
        } else {
            Board.cardUp = this.card;
        }
    }


};