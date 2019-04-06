package edu.jumpstreet.spacetrader.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageButton;

import edu.jumpstreet.spacetrader.R;
import edu.jumpstreet.spacetrader.viewmodel.ConfigurationViewModel;
import edu.jumpstreet.spacetrader.viewmodel.ConfigurationViewModelFactory;

public class ConfigurationActivity extends AppCompatActivity implements View.OnClickListener {
    EditText nameET;
    TextView skillPointsRemainingTV;
    ImageButton pilotMinusBtn;
    TextView pilotTV;
    ImageButton pilotPlusBtn;

    ImageButton fighterMinusBtn;
    TextView fighterTV;
    ImageButton fighterPlusBtn;

    ImageButton traderMinusBtn;
    TextView traderTV;
    ImageButton traderPlusBtn;

    ImageButton engineerMinusBtn;
    TextView engineerTV;
    ImageButton engineerPlusBtn;

    Spinner difficultySpinner;

    Button confirmationBtn;
    enum Difficulty{
        Beginner, Easy, Normal, Hard, Impossible
    }

    ConfigurationViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_configuration);

        ConfigurationViewModelFactory factory = new ConfigurationViewModelFactory();
        viewModel = factory.create(ConfigurationViewModel.class);

        linkConfigButtons();
        initializeButtons();
        difficultySpinner = findViewById(R.id.difficultySpinner);
        skillPointsRemainingTV.setText("Remaining Skill Points: "
                + viewModel.getUnallocatedPoints());
        ArrayAdapter<Difficulty> difficultyAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, Difficulty.values());
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
                viewModel.updatePilotSkill(false);
                updateButtons(0,false);
                break;
            case R.id.pilotPlusButton:
                viewModel.updatePilotSkill(true);
                updateButtons(0,true );
                break;
            case R.id.fighterMinusButton:
                viewModel.updateFightingSkill(false);
                updateButtons(1,false );
                break;
            case R.id.fighterPlusButton:
                viewModel.updateFightingSkill(true);
                updateButtons(1, true);
                break;
            case R.id.traderMinusButton:
                viewModel.updateTradingSkill(false);
                updateButtons(2, false);
                break;
            case R.id.traderPlusButton:
                viewModel.updateTradingSkill(true);
                updateButtons(2, true);
                break;
            case R.id.engineerMinusButton:
                viewModel.updateEngineeringSkill(false);
                updateButtons(3, false);
                break;
            case R.id.engineerPlusButton:
                viewModel.updateEngineeringSkill(true);
                updateButtons(3, true);
                break;
            case R.id.playerConfigConfirmationButton:
                //model.getPlayerInteractor().addPlayerPilotSkill(pilotSP);
                //model.getPlayerInteractor().addPlayerFighterSkill(fighterSP);
                //model.getPlayerInteractor().addPlayerTraderSkill(traderSP);
                //model.getPlayerInteractor().addPlayerEngineerSkill(engineerSP);
                //model.getPlayerInteractor().changePlayerName(nameET.getText().toString());
                viewModel.setPlayerName(nameET.getText().toString());

                Toast.makeText(this, "Player Configured\nName: " + viewModel.getPlayerName()
                                +"\nPilot Skill: " + viewModel.getPlayerPilotSkill()
                                +"\nFighter Skill: " + viewModel.getPlayerFighterSkill()
                                +"\nTrader Skill: " + viewModel.getPlayerTraderSkill()
                                +"\nEngineer Skill: " + viewModel.getPlayerEngineerSkill()
                                +"\nUnallocated Points: " + viewModel.getUnallocatedPoints(),
                        Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, UniverseActivity.class);
                ConfigurationActivity.this.startActivity(intent);
                break;
        }
    }

    private void updateButtons(int skill, boolean isAdded){
        switch (skill){
            case 0:
                pilotTV.setText("Pilot Skill: " + viewModel.getPlayerPilotSkill()+ "");
                pilotMinusBtn.setEnabled(viewModel.pilotMinusShouldBeEnabled());
                break;
            case 1:
                fighterTV.setText("Fighter Skill: " + viewModel.getPlayerFighterSkill() + "");
                fighterMinusBtn.setEnabled(viewModel.fighterMinusShouldBeEnabled());
                break;
            case 2:
                traderTV.setText("Trader Skill: " + viewModel.getPlayerTraderSkill() +"");
                traderMinusBtn.setEnabled(viewModel.traderMinusShouldBeEnabled());
                break;
            case 3:
                engineerTV.setText("Engineer Skill: " + viewModel.getPlayerEngineerSkill() + "");
                engineerMinusBtn.setEnabled(viewModel.engineerMinusShouldBeEnabled());
                break;
        }
        skillPointsRemainingTV.setText("Skill Points Remaining: "
                + viewModel.getUnallocatedPoints());
        enablePlusButtons(viewModel.plusShouldBeEnabled());
    }

    private void enablePlusButtons(boolean isEnabled){
        pilotPlusBtn.setEnabled(isEnabled);
        fighterPlusBtn.setEnabled(isEnabled);
        traderPlusBtn.setEnabled(isEnabled);
        engineerPlusBtn.setEnabled(isEnabled);
    }
}