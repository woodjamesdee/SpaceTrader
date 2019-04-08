package edu.jumpstreet.spacetrader;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

import edu.jumpstreet.spacetrader.model.Model;
import edu.jumpstreet.spacetrader.view.ConfigurationActivity;
import edu.jumpstreet.spacetrader.view.UniverseActivity;

/**
 * sets up main activity
 */
public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    public static final String MyPREFERENCES = "MyPrefs";
    public static final String key = "Key";

    private SharedPreferences sharedPreferences;
    //private Button loadButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConstraintLayout layout = findViewById(R.id.mainConstrainLayout);
        layout.setOnTouchListener(this);
        MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.game_maintheme);
        mediaPlayer.start();
        Button loadButton = findViewById(R.id.loadButton);
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        loadButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String savedGame = sharedPreferences.getString(key, null);
               if (savedGame == null) {
                   //System.out.println("No saved game!");
                   return;
               }
               try {
                   Model.loadGameFromString(savedGame);
                   startUniverse();
               } catch(IOException ioe) {
                   //System.err.println("IOException while loading game.");
                   ioe.printStackTrace();
               } catch (ClassNotFoundException cnfe) {
                   //System.err.println("ClassNotFoundException while loading game");
                   cnfe.printStackTrace();
               }
           }
        });
    }
    @Override
    public boolean onTouch(View v, MotionEvent event){
        Intent intent = new Intent(this, ConfigurationActivity.class);
        MainActivity.this.startActivity(intent);
        return true;
    }

    private void startUniverse() {
        Intent intent = new Intent(this, UniverseActivity.class);
        MainActivity.this.startActivity(intent);
    }

}
