package edu.jumpstreet.spacetrader;

import android.content.Intent;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import java.io.FileInputStream;
import java.io.IOException;

import edu.jumpstreet.spacetrader.view.ConfigurationActivity;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConstraintLayout layout = findViewById(R.id.mainConstrainLayout);
        layout.setOnTouchListener(this);
        MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.game_maintheme);
        mediaPlayer.start();
    }

    public boolean onTouch(View v, MotionEvent event){
        Intent intent = new Intent(this, ConfigurationActivity.class);
        MainActivity.this.startActivity(intent);
        return true;
    }

}
