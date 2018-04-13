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
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.h1z1.memorygame.R;

import static java.security.AccessController.getContext;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int width=5;
        int weigth=4;
        TableLayout tableLayout = new TableLayout(this);
        for (int i = 0; i < 10; i++)
        {
            TableRow tableRow = new TableRow(this);
            Button button = new Button(this);
            button.setText("1");
            tableRow.addView(button);

            button = new Button(this);
            button.setText("2");
            tableRow.addView(button);

            button = new Button(this);
            button.setText("3");
            tableRow.addView(button);

            tableLayout.addView(tableRow);
        }
        setContentView(tableLayout);
    }



}
