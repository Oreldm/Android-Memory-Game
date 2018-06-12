package com.example.h1z1.memorygame.Activities;

import android.Manifest;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
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
    LocationManager mLocationManager;
    public static double latitude =0;
    public static double longitude =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);

        //location
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1); // put it at the right place
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1); // put it at the right place
        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5, 5, mLocationListener);


        //setting username & age
        Bundle welcomePageData = getIntent().getExtras();
        if(welcomePageData==null){
            return;
        }
        username = welcomePageData.getString(getString(R.string.usernamekey));
        String age = welcomePageData.getString(getString(R.string.agekey));
        ((TextView)findViewById(R.id.usernameID)).setText(username);
        ((TextView)findViewById(R.id.textView5)).setText(age);

        //levels Spinner
        ArrayList<String> array = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.level_arr)));
        Spinner spinner;
        ArrayAdapter<String> mAdapter;
        spinner = (Spinner) findViewById(R.id.spinner);
        mAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, array);
        spinner.setAdapter(mAdapter);

        //title
        TextView title=findViewById(R.id.TitleID);
        Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
        title.startAnimation(shake);

        //start button
        Button button=findViewById(R.id.button);
        Animation pulse = AnimationUtils.loadAnimation(this, R.anim.pulse);
        button.startAnimation(pulse);

        //highscore button
        Button highScoreButton=findViewById(R.id.buttonHighScore);
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

    public void changeToHighScore(View view){
        Intent i= new Intent(this,leader_board_activity.class);
        startActivity(i);
    }


    private final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }


    };


}
