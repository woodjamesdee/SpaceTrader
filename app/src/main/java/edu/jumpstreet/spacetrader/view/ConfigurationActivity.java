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

import edu.jumpstreet.spacetrader.MainActivity;
import edu.jumpstreet.spacetrader.R;
import edu.jumpstreet.spacetrader.model.Model;
import edu.jumpstreet.spacetrader.viewmodel.ConfigurationViewModel;
import edu.jumpstreet.spacetrader.viewmodel.ConfigurationViewModelFactory;

public class ConfigurationActivity extends AppCompatActivity implements View.OnClickListener {
    Model model = Model.getInstance();

    EditText nameET;
    TextView skillPointsRemainingTV;
    Button pilotMinusBtn;
    TextView pilotTV;
    Button pilotPlusBtn;

    Button fighterMinusBtn;
    TextView fighterTV;
    Button fighterPlusBtn;

    Button traderMinusBtn;
    TextView traderTV;
    Button traderPlusBtn;

    Button engineerMinusBtn;
    TextView engineerTV;
    Button engineerPlusBtn;

    Spinner difficultySpinner;

    Button confirmationBtn;
    enum Difficulty{
        Beginner, Easy, Normal, Hard, Impossible
    }

    ConfigurationViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

        ConfigurationViewModelFactory factory = new ConfigurationViewModelFactory();
        viewModel = factory.create(ConfigurationViewModel.class);

        linkConfigButtons();
        initializeButtons();
        difficultySpinner = findViewById(R.id.difficultySpinner);
        skillPointsRemainingTV.setText("Remaining Skill Points: " + model.getPlayerInteractor().getPlayerUnallocatedSkillpoints());
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
                model.getPlayerInteractor().changePlayerName(nameET.getText().toString());

                Toast.makeText(this, "Player Configured\nName: " + model.getPlayerInteractor().getPlayerName()
                                +"\nPilot Skill: " + model.getPlayerInteractor().getPlayerPilotSkill()
                                +"\nFighter Skill: " + model.getPlayerInteractor().getPlayerFighterSkill()
                                +"\nTrader Skill: " + model.getPlayerInteractor().getPlayerTraderSkill()
                                +"\nEngineer Skill: " + model.getPlayerInteractor().getPlayerEngineerSkill(),
                        Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, SolarSystemActivity.class);
                ConfigurationActivity.this.startActivity(intent);
                break;
        }
    }

    private void updateButtons(int skill, boolean isAdded){
        switch (skill){
            case 0:
                pilotTV.setText("Pilot Skill: " + model.getPlayerInteractor().getPlayerPilotSkill()+ "");
                pilotMinusBtn.setEnabled(model.getPlayerInteractor().playerHasPilotPointsLeft());
                break;
            case 1:
                fighterTV.setText("Fighter Skill: " + model.getPlayerInteractor().getPlayerFighterSkill() + "");
                fighterMinusBtn.setEnabled(model.getPlayerInteractor().playerHasFighterPointsLeft());
                break;
            case 2:
                traderTV.setText("Trader Skill: " + model.getPlayerInteractor().getPlayerTraderSkill() +"");
                traderMinusBtn.setEnabled(model.getPlayerInteractor().playerHasTraderPointsLeft());
                break;
            case 3:
                engineerTV.setText("Engineer Skill: " + model.getPlayerInteractor().getPlayerEngineerSkill() + "");
                engineerMinusBtn.setEnabled(model.getPlayerInteractor().playerHasEngineerPointsLeft());
                break;
        }
        skillPointsRemainingTV.setText("Skill Points Remaining: " + model.getPlayerInteractor().getPlayerUnallocatedSkillpoints());
        enablePlusButtons(model.getPlayerInteractor().playerHasSkillpointsLeft());
    }

    private void enablePlusButtons(boolean isEnabled){
        pilotPlusBtn.setEnabled(isEnabled);
        fighterPlusBtn.setEnabled(isEnabled);
        traderPlusBtn.setEnabled(isEnabled);
        engineerPlusBtn.setEnabled(isEnabled);
    }
}