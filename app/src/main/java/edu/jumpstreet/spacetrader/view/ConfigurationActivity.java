package edu.jumpstreet.spacetrader.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import edu.jumpstreet.spacetrader.R;
import edu.jumpstreet.spacetrader.model.PlayerInteractor;
import edu.jumpstreet.spacetrader.viewmodel.ConfigurationViewModel;

public class ConfigurationActivity extends AppCompatActivity implements View.OnClickListener {
    EditText nameET;
    private static final int MAX_POINTS = 16;
    private int remainingPoints = MAX_POINTS;

    TextView skillPointsRemainingTV;

    Button pilotMinusBtn;
    TextView pilotTV;
    Button pilotPlusBtn;
    private int pilotSP = 0;

    Button fighterMinusBtn;
    TextView fighterTV;
    Button fighterPlusBtn;
    private int fighterSP = 0;

    Button traderMinusBtn;
    TextView traderTV;
    Button traderPlusBtn;
    private int traderSP = 0;

    Button engineerMinusBtn;
    TextView engineerTV;
    Button engineerPlusBtn;
    private int engineerSP = 0;

    Spinner difficultySpinner;

    Button confirmationBtn;

    enum Difficulty{
        Beginner, Easy, Normal, Hard, Impossible
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

        linkConfigButtons();
        initializeButtons();
        difficultySpinner = findViewById(R.id.difficultySpinner);
        skillPointsRemainingTV.setText("Remaining Skill Points: " + remainingPoints);
        ArrayAdapter<Difficulty> difficultyAdapter = new ArrayAdapter<Difficulty>(this, android.R.layout.simple_spinner_item, Difficulty.values());
        difficultyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficultySpinner.setAdapter(difficultyAdapter);
    }

    private void linkConfigButtons(){
        nameET = findViewById(R.id.nameEditText);
        pilotMinusBtn = findViewById(R.id.pilotMinusButton);
        pilotTV = findViewById(R.id.configPilotTextView);
        pilotPlusBtn = findViewById(R.id.pilotPlusButton);
        fighterMinusBtn = findViewById(R.id.fighterMinusButton);
        fighterTV = findViewById(R.id.configFighterTextView);
        fighterPlusBtn = findViewById(R.id.fighterPlusButton);
        traderMinusBtn = findViewById(R.id.traderMinusButton);
        traderTV = findViewById(R.id.configTraderTextView);
        traderPlusBtn = findViewById(R.id.traderPlusButton);
        engineerMinusBtn = findViewById(R.id.engineerMinusButton);
        engineerTV = findViewById(R.id.configEngineerTextView);
        engineerPlusBtn = findViewById(R.id.engineerPlusButton);
        confirmationBtn = findViewById(R.id.playerConfigConfirmationButton);
        skillPointsRemainingTV = findViewById(R.id.skillPointsRemainingTV);
    }

    private void initializeButtons(){
        pilotMinusBtn.setEnabled(false);
        fighterMinusBtn.setEnabled(false);
        traderMinusBtn.setEnabled(false);
        engineerMinusBtn.setEnabled(false);
        pilotMinusBtn.setOnClickListener(this);
        pilotPlusBtn.setOnClickListener(this);
        fighterMinusBtn.setOnClickListener(this);
        fighterPlusBtn.setOnClickListener(this);
        traderMinusBtn.setOnClickListener(this);
        traderPlusBtn.setOnClickListener(this);
        engineerMinusBtn.setOnClickListener(this);
        engineerPlusBtn.setOnClickListener(this);
        confirmationBtn.setOnClickListener(this);

    }
    //Onclick methods for each Button
    //0 = pilot
    //1 = fighter
    //2 = trader
    //3 = engineer
        @Override
        public void onClick(View view){
            switch(view.getId()){
                case R.id.pilotMinusButton:
                    changeSkill(0,false);
                    Toast.makeText(ConfigurationActivity.this, "Woof", Toast.LENGTH_SHORT);
                    break;
                case R.id.pilotPlusButton:
                    changeSkill(0,true );
                    break;
                case R.id.fighterMinusButton:
                    changeSkill(1,false );
                    break;
                case R.id.fighterPlusButton:
                    changeSkill(1, true);
                    break;
                case R.id.traderMinusButton:
                    changeSkill(2, false);
                    break;
                case R.id.traderPlusButton:
                    changeSkill(2, true);
                    break;
                case R.id.engineerMinusButton:
                    changeSkill(3, false);
                    break;
                case R.id.engineerPlusButton:
                    changeSkill(3, true);
                    break;
                case R.id.playerConfigConfirmationButton:
                    break;
            }
        }

    private void changeSkill(int skill, boolean isAdded){
        boolean isMinusActive;
        switch (skill){
            case 0:
                pilotSP = isAdded?pilotSP+1:pilotSP-1;
                pilotTV.setText("Pilot Skill: " + pilotSP+ "");
                isMinusActive = pilotSP > 0?true:false;
                pilotMinusBtn.setEnabled(isMinusActive);
                break;
            case 1:
                fighterSP = isAdded?fighterSP+1:fighterSP-1;
                fighterTV.setText("Fighter Skill: " + fighterSP + "");
                isMinusActive = fighterSP > 0?true:false;
                fighterMinusBtn.setEnabled(isMinusActive);
                break;
            case 2:traderSP = isAdded?traderSP+1:traderSP-1;
                traderTV.setText("Trader Skill: " + traderSP +"");
                isMinusActive = traderSP > 0?true:false;
                traderMinusBtn.setEnabled(isMinusActive);
                break;
            case 3:engineerSP = isAdded?engineerSP+1:engineerSP-1;
                engineerTV.setText("Engineer Skill: " + engineerSP + "");
                isMinusActive = engineerSP > 0?true:false;
                engineerMinusBtn.setEnabled(isMinusActive);
                break;
        }
        remainingPoints = isAdded?remainingPoints-1:remainingPoints+1;
        skillPointsRemainingTV.setText("Skill Points Remaining: " + remainingPoints);
        if(remainingPoints <= 0){
            enablePlusButtons(false);
        }else{
            enablePlusButtons(true);
        }
    }



    private void enablePlusButtons(boolean isEnabled){
        pilotPlusBtn.setEnabled(isEnabled);
        fighterPlusBtn.setEnabled(isEnabled);
        traderPlusBtn.setEnabled(isEnabled);
        engineerPlusBtn.setEnabled(isEnabled);
    }
}
