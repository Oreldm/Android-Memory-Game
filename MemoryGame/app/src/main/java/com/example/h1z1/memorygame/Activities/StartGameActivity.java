package com.example.h1z1.memorygame.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.h1z1.memorygame.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;

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

        ArrayList<String> array = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.level_arr)));

        Spinner spinner;
        ArrayAdapter<String> mAdapter;
        spinner = (Spinner) findViewById(R.id.spinner);
        mAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, array);
        spinner.setAdapter(mAdapter);
        Button button=findViewById(R.id.button);
        /*
        Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
        Button button=findViewById(R.id.button);
        button.startAnimation(shake);


        Animation resizeAnimationSmall = new ResizeAnimation(this,button,button.getWidth(),button.getHeight(),button.getWidth()-60,button.getHeight()-60);
        button.startAnimation(resizeAnimationSmall);
        */
        Animation pulse = AnimationUtils.loadAnimation(this, R.anim.pulse);
        button.startAnimation(pulse);

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
