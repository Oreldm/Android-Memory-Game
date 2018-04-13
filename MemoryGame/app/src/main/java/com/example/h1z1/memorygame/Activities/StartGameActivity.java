package com.example.h1z1.memorygame.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.h1z1.memorygame.R;

public class StartGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);

        Bundle welcomePageData = getIntent().getExtras();

        if(welcomePageData==null){
            return;
        }
        String username = welcomePageData.getString("username");
        String age = welcomePageData.getString("age");
        ((TextView)findViewById(R.id.usernameID)).setText(username);
        ((TextView)findViewById(R.id.textView5)).setText(age);
    }


    public void onClick(View view){
        Intent i = new Intent(this, GameActivity.class);

        startActivity(i);
    }

}
