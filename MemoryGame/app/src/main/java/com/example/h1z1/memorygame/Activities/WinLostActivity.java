package com.example.h1z1.memorygame.Activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.h1z1.memorygame.R;

import java.util.Timer;
import java.util.TimerTask;

public class WinLostActivity extends AppCompatActivity {

    public static String status = "";
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RelativeLayout layout = new RelativeLayout(this);
        layout.setBackgroundResource(R.drawable.backgroundstartgameleft);

        RelativeLayout.LayoutParams buttonDetails = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );

        buttonDetails.addRule(RelativeLayout.CENTER_HORIZONTAL);
        buttonDetails.addRule(RelativeLayout.CENTER_VERTICAL);

        Button redButton = new Button(this);
        redButton.setText(status);
        redButton.setBackgroundColor(Color.TRANSPARENT);

        layout.addView(redButton, buttonDetails);
        setContentView(layout);

        Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
        if (status.equals(getString(R.string.win_str)))
            redButton.startAnimation(shake);


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                finish();
            }
        }, (GameInterface.DELAY_1000MS + GameInterface.DELAY_1000MS / 2));


    }

    private boolean isInFocus = false;

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        isInFocus = hasFocus;
        if (!hasFocus) {
            finish();
        }
    }
}
