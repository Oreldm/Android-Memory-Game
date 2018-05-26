package com.example.h1z1.memorygame.Activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.h1z1.memorygame.R;

import java.util.Timer;
import java.util.TimerTask;


public class GameActivity extends AppCompatActivity {
    TextView timerText; //timer text
    TextView nameText;
    static String nameString;
    static int counter = 0;
    static int cardsUp = 0;
    Context mContext;
    LocationManager mLocationManager;
    public static double latitude =0;
    public static double longitude =0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        Bundle gameIntentData = getIntent().getExtras();
        nameText = new TextView(this);
        nameString = gameIntentData.getString(getString(R.string.usernamekey));
        int width = gameIntentData.getInt(getString(R.string.card_width_key));
        if (width == GameInterface.LEVELS.HARD.getValue()) {
            counter = GameInterface.HARD_TIMER;
        } else if (width == GameInterface.LEVELS.MEDIUM.getValue()) {
            counter = GameInterface.MEDIUM_TIMER;
        } else
            counter = GameInterface.EASY_TIMER;
        int height = GameInterface.BOARD_WIDTH;
        final Board board = new Board(this, width, height, this);
        timerText = board.counterText;
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

        setContentView(board.parentView);
    }


    private boolean TimerMethod()
    {
        if(counter==GameInterface.ZERO){
            WinLostActivity.status=getString(R.string.lose_str);
            Intent i = new Intent(this, WinLostActivity.class);
            startActivity(i);
            cardsUp=GameInterface.ZERO;
            finish();
            return false;
        } else if(GameActivity.cardsUp==Board.width*Board.height){
            WinLostActivity.status=getString(R.string.win_str);
            Intent i = new Intent(this, WinLostActivity.class);
            startActivity(i);
            cardsUp=GameInterface.ZERO;
            finish();
            return false;
        }else{
            this.runOnUiThread(Timer_Tick);
            return true;
        }
    }

    private Runnable Timer_Tick = new Runnable() {
        public void run() {
            timerText.setText(Integer.toString(counter));
            counter--;

        }
    };


    private boolean isInFocus = false;

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        isInFocus = hasFocus;
        if(hasFocus){
            Board.myTimer=new Timer();
            Board.myTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    boolean shouldStop=TimerMethod();
                    if(!shouldStop){
                        Board.myTimer.cancel();
                        Board.myTimer.purge();
                    }
                }

            }, GameInterface.ZERO, GameInterface.DELAY_1000MS);
        }
        else{
            Board.myTimer.cancel();
            Board.myTimer.purge();
            finish();
        }
    }

    @Override
    public void onStop() {
        super.onStop();

        if (!isInFocus) finish();
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
