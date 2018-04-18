package com.example.h1z1.memorygame.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import com.example.h1z1.memorygame.R;

public class WelcomePageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);
    }

    public void onClick(View view){
        Intent i = new Intent(this, StartGameActivity.class);
        final EditText usernameEdit= (EditText) findViewById(R.id.nameInputID);
        final EditText ageEdit = (EditText) findViewById(R.id.ageInputID);
        String username=usernameEdit.getText().toString();
        String age = ageEdit.getText().toString();
        i.putExtra(getString(R.string.usernamekey),username);
        i.putExtra(getString(R.string.agekey),age);

        startActivity(i);
    }

}
