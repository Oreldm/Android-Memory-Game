package com.example.h1z1.memorygame.Activities;
import com.example.h1z1.memorygame.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Collections;

public class leader_board_activity extends FragmentActivity {
    public final static int SIZE = 10;
    private TableLayout table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);
        showMap();
        this.table = (TableLayout) findViewById(R.id.leaderBoardTable);
        sortTable();
        displayTableView();
    }

    private void sortTable(){
        Collections.sort(WelcomePageActivity.hst.getScoreArr());
        Collections.reverse(WelcomePageActivity.hst.getScoreArr());
    }

    private void showMap(){
        MapFragment fragment = (MapFragment) getFragmentManager().findFragmentById(R.id.actualMap);
        fragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap map) {
                enableGoogleMap(map);
                highScoresLocations();
            }
        });
    }

    private TableRow setTitle(){
        TableRow title = new TableRow(this);
        TextView nameTitle = new TextView(this);
        nameTitle.setText("Name");
        TextView scoreTitle = new TextView(this);
        scoreTitle.setText("Score");
        nameTitle.setTextSize(30);
        scoreTitle.setTextSize(30);
        nameTitle.setPadding(0, 2, 150, 2);
        scoreTitle.setPadding(0, 2, 0, 2);
        nameTitle.setGravity(Gravity.CENTER);
        scoreTitle.setGravity(Gravity.CENTER);
        nameTitle.setTextAlignment(View.TEXT_ALIGNMENT_GRAVITY);
        scoreTitle.setTextAlignment(View.TEXT_ALIGNMENT_GRAVITY);
        title.addView(nameTitle);
        title.addView(scoreTitle);
        return title;
    }

    public TableRow setRow(ScoreObject currentScore){
        TableRow row = new TableRow(this);
        TextView name = new TextView(this);
        TextView score = new TextView(this);
        name.setText(currentScore.getName());
        score.setText(""+currentScore.getScore());
        name.setGravity(Gravity.CENTER);
        score.setGravity(Gravity.CENTER);
        name.setTextAlignment(View.TEXT_ALIGNMENT_GRAVITY);
        score.setTextAlignment(View.TEXT_ALIGNMENT_GRAVITY);
        score.setTextSize(18);
        name.setTextSize(18);
        row.addView(name);
        row.addView(score);
        return row;
    }

    private void displayTableView() {
        table.addView(setTitle(), new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));
        for (ScoreObject currentScore : WelcomePageActivity.hst.getScoreArr()) {
            int i=0;
            if (i < SIZE)
                table.addView(setRow(currentScore), new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));
            else
                break;
            i++;
        }
    }

    private GoogleMap map;
    private void highScoresLocations() {
        try {
            int i=0;
            for (ScoreObject currentScore : WelcomePageActivity.hst.getScoreArr()) {
                if (++i < SIZE) {
                    if (currentScore.getPlayerLocation() != null) {
                        Location locationToPut = new Location(LocationManager.GPS_PROVIDER);
                        locationToPut.setLatitude(currentScore.getPlayerLocation().latitude);
                        locationToPut.setLongitude(currentScore.getPlayerLocation().longitude);
                        map.addMarker(new MarkerOptions().title("Name: " + currentScore.getName() + "    score: " + currentScore.getScore()).position(currentScore.getPlayerLocation())).setIcon(BitmapDescriptorFactory.fromResource(R.drawable.win));
                    }
                }else{
                    break;
                }
            }
        } catch (Exception e) {
            return;
        }
    }

    private void enableGoogleMap(GoogleMap map) {
        this.map = map;
            try {
                map.setMyLocationEnabled(true);
                LatLng locationForHighScore = new LatLng(StartGameActivity.latitude, StartGameActivity.longitude);
                this.map.animateCamera(CameraUpdateFactory.newLatLngZoom(locationForHighScore, 20f));
            } catch (Exception e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
    }

}
