package edu.jumpstreet.spacetrader.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import edu.jumpstreet.spacetrader.MainActivity;
import edu.jumpstreet.spacetrader.R;
import edu.jumpstreet.spacetrader.model.Model;

public class RandomEncounterActivity extends Activity implements View.OnTouchListener {

    TextView encounterTV;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_encounter_templete);
        ConstraintLayout layout = findViewById(R.id.REConstraintLayout);
        layout.setOnTouchListener(this);
        InitalizeViews();
        encounterTV.setText("You find a 25 Credit Bill just in the middle of Space!!");
        Model.getInstance().getPlayerInteractor().addCreditsToPlayerBalance(25);
    }

    private void InitalizeViews(){
        encounterTV = findViewById(R.id.EncounterTV);
    }

    public boolean onTouch(View v, MotionEvent event){
        Intent intent = new Intent(this, PlanetActivity.class);
        RandomEncounterActivity.this.startActivity(intent);
        return true;
    }
}
