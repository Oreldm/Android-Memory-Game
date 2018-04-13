package com.example.h1z1.memorygame.Activities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;

import com.example.h1z1.memorygame.R;

public class CardListener {
    Context context;
    public Button button;

    public CardListener(Context context, int drawableResource){
        this.context=context;
        button=new Button(context);
        button.setText("CARD");
        button.setBackgroundResource(R.drawable.tile);
        button.setOnClickListener(imgButtonHandler);
    }


    View.OnClickListener imgButtonHandler = new View.OnClickListener() {

        public void onClick(View v) {
            Drawable drawable = ContextCompat.getDrawable(context.getApplicationContext(),R.drawable.animals_1);
            Drawable dr = context.getResources().getDrawable(R.drawable.animals_1);
            Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();
            Drawable d = new BitmapDrawable(context.getResources(), Bitmap.createScaledBitmap(bitmap, 10, 10, true));
            button.setBackground(d);
        }
    };

}
