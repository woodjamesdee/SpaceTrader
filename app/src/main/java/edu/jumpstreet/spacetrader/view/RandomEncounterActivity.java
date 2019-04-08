package edu.jumpstreet.spacetrader.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import edu.jumpstreet.spacetrader.R;
import edu.jumpstreet.spacetrader.model.Model;
import edu.jumpstreet.spacetrader.model.PlayerInteractor;

public class RandomEncounterActivity extends Activity implements View.OnTouchListener {

    private final PlayerInteractor PI = Model.getInstance().getPlayerInteractor();
    private TextView encounterTV;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_encounter_templete);
        ConstraintLayout layout = findViewById(R.id.REConstraintLayout);
        layout.setOnTouchListener(this);
        InitalizeViews();
        encounterTV.setText("You find a 25 Credit Bill just in the middle of Space!!");
        PI.addCreditsToPlayerBalance(25);
    }

    private void InitalizeViews(){
        encounterTV = findViewById(R.id.EncounterTV);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event){
        Intent intent = new Intent(this, PlanetActivity.class);
        RandomEncounterActivity.this.startActivity(intent);
        return true;
    }
}
