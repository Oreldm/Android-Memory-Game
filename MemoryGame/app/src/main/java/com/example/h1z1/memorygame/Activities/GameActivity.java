package com.example.h1z1.memorygame.Activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.h1z1.memorygame.R;

import static java.security.AccessController.getContext;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
                Button button = new Button(this);
                button.setText("wr");
                tableRow.addView(button);
            }
            tableLayout.addView(tableRow);
        }
        tableLayout.setShrinkAllColumns(true);
        tableLayout.setGravity(Gravity.CENTER_HORIZONTAL+Gravity.CENTER);
        setContentView(tableLayout);
    }
}
