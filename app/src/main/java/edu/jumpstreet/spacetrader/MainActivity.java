package edu.jumpstreet.spacetrader;

import android.content.Intent;
import android.os.Parcelable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import edu.jumpstreet.spacetrader.model.Model;
import edu.jumpstreet.spacetrader.view.ConfigurationActivity;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    private Model model = new Model();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Model model = new Model();
        setContentView(R.layout.activity_main);
        ConstraintLayout layout = findViewById(R.id.mainConstrainLayout);
        layout.setOnTouchListener(this);
    }

    public boolean onTouch(View v, MotionEvent event){
        Intent intent = new Intent(this, ConfigurationActivity.class);
        MainActivity.this.startActivity(intent);
        return true;
    }



}
