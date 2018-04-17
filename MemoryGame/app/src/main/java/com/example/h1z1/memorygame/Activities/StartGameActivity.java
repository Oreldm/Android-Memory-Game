package com.example.h1z1.memorygame.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.h1z1.memorygame.R;

public class StartGameActivity extends AppCompatActivity {
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);

        Bundle welcomePageData = getIntent().getExtras();

        if(welcomePageData==null){
            return;
        }
        username = welcomePageData.getString("username");
        String age = welcomePageData.getString("age");
        ((TextView)findViewById(R.id.usernameID)).setText(username);
        ((TextView)findViewById(R.id.textView5)).setText(age);
    }


    public void onClick(View view){
        Spinner mySpinner=(Spinner) findViewById(R.id.spinner);
        String level = mySpinner.getSelectedItem().toString();
        int width = GameInterface.LEVELS.HARD.getValue();

        if(level.toString().toLowerCase().equals(GameInterface.LEVELS.EASY.toString().toLowerCase())){
            width=GameInterface.LEVELS.EASY.getValue();
        } else if (level.toString().toLowerCase().equals(GameInterface.LEVELS.MEDIUM.toString().toLowerCase())){
            width=GameInterface.LEVELS.MEDIUM.getValue();
        }

        Intent i = new Intent(this, GameActivity.class);
        i.putExtra("cardWidth",width);
        i.putExtra("username",username);
        startActivity(i);
    }

}
