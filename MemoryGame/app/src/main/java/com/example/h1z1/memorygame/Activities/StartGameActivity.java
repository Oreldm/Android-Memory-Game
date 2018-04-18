package com.example.h1z1.memorygame.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import com.example.h1z1.memorygame.R;
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
        username = welcomePageData.getString(getString(R.string.usernamekey));
        String age = welcomePageData.getString(getString(R.string.agekey));
        ((TextView)findViewById(R.id.usernameID)).setText(username);
        ((TextView)findViewById(R.id.textView5)).setText(age);

        ArrayList<String> array = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.level_arr)));

        Spinner spinner;
        ArrayAdapter<String> mAdapter;
        spinner = (Spinner) findViewById(R.id.spinner);
        mAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, array);
        spinner.setAdapter(mAdapter);


        Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
        TextView title=findViewById(R.id.TitleID);
        title.startAnimation(shake);


        Button button=findViewById(R.id.button);
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
        i.putExtra(getString(R.string.card_width_key),width);
        i.putExtra(getString(R.string.usernamekey),username);
        startActivity(i);
    }


}
