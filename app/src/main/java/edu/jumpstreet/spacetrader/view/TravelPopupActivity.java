package edu.jumpstreet.spacetrader.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

import edu.jumpstreet.spacetrader.R;
import edu.jumpstreet.spacetrader.entity.Planet;
import edu.jumpstreet.spacetrader.entity.Spaceship;
import edu.jumpstreet.spacetrader.model.GameInteractor;
import edu.jumpstreet.spacetrader.model.Model;
import edu.jumpstreet.spacetrader.entity.System;
import edu.jumpstreet.spacetrader.viewmodel.TravelPopupViewModel;
import edu.jumpstreet.spacetrader.viewmodel.TravelPopupViewModelFactory;

/**
 * sets up popup travel activity
 */
public class TravelPopupActivity extends Activity implements View.OnClickListener{
    private TextView planetTV;
    private TextView techLevelTV;
    //private TextView resourceTV;
    //private TextView conditionTV;
    private TextView usersFuelTV;
    private TextView requiredFuelTV;
    private Button travelBtn;

    //Model model;
    private TravelPopupViewModel viewModel;
    private Spaceship ship;
    private Planet currentPlanet;
    //private Planet travelPlanet;
    //private SolarSystem travelSS;
    private static final int FUEL_COST_PER_UNIT = 10;
    private boolean isSolarsystemTravel;
    private System currentEntity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TravelPopupViewModelFactory factory = new TravelPopupViewModelFactory();
        viewModel = factory.create(TravelPopupViewModel.class);
        DisplayMetrics dM = new DisplayMetrics();
        WindowManager windowManager = getWindowManager();
        Display defaultDisplay = windowManager.getDefaultDisplay();
        defaultDisplay.getMetrics(dM);
        Window window = getWindow();
        window.setLayout((int) (dM.widthPixels * GarageFuelActivity.WIDTH_MODIFIER),
                (int) (dM.heightPixels * GarageFuelActivity.HEIGHT_MODIFIER));
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = GarageFuelActivity.Y_LOCATION;
        setContentView(R.layout.popup_window_travel);
        this.setFinishOnTouchOutside(true);
        isSolarsystemTravel = viewModel.isSolarSystemTravel();
        //isSolarsystemTravel = getIntent().getBooleanExtra("Is_Solarsystem_Travel", true);
        initializeViews();
        initializeReferences(isSolarsystemTravel);
        setTextViews(isSolarsystemTravel, currentEntity);
        enableTravelButton(currentEntity);
    }

    private void initializeReferences(boolean isSolarsystemTravel){
        ship = viewModel.getPlayerShip();
        currentPlanet = viewModel.getActivePlanet();
        if(!isSolarsystemTravel) {
            currentEntity =  viewModel.getNextPlanet();
        }else {
            currentEntity = viewModel.getNextSolarSystem();
        }
    }

    private void initializeViews(){
        planetTV = findViewById(R.id.travelPopupPlanetTV);
        techLevelTV = findViewById(R.id.travelPopupTechLevelTV);
        //TextView resourceTV = findViewById(R.id.travelPopupResourceTV);
        //TextView conditionTV = findViewById(R.id.travelPopupConditionTV);
        usersFuelTV = findViewById(R.id.travelPopupUsersFuelTV);
        requiredFuelTV = findViewById(R.id.travelPopupFuelRequiredTV);
        travelBtn = findViewById(R.id.travelPopupTravelButton);
        travelBtn.setOnClickListener(this);
    }

    private void enableTravelButton(System system){
            if (calculateTravelCost(system) > ship.getRemainingFuel()) {
                travelBtn.setEnabled(false);
            } else {
                travelBtn.setEnabled(true);
            }
    }

    private void setTextViews(boolean isSolarSystemTravel, System system){
        //if(!isSolarSystemTravel) {
           // resourceTV.setText("Planets Main Resource: " + system.getResource());
       // }
        if (!isSolarSystemTravel) {

        }
        planetTV.setText("Planets Name: " + system.getName());
        techLevelTV.setText("Planets Tech Level: " + system.getTechLevel());
        requiredFuelTV.setText("Required Fuel: " + calculateTravelCost(currentEntity));
        usersFuelTV.setText("Users Fuel: " + ship.getRemainingFuel() + "/" + ship.getMaxFuel());
    }

    @Override
    public void onClick(View view) {
        int travelCost = calculateTravelCost(currentEntity);
        Random rand = new Random();
        double odds = rand.nextDouble();
        odds *= 100;

        switch(view.getId()){
            case R.id.travelPopupTravelButton: travel(currentEntity);
            finish();
            if(isSolarsystemTravel){
                viewModel.changeActiveSolarSystem(currentEntity.getName());
                travelCost *= .1;
                if(true){
                    Intent intent = new Intent(TravelPopupActivity.this, RandomEncounterActivity.class);
                    TravelPopupActivity.this.startActivity(intent);
                }else {
                    Intent intent = new Intent(TravelPopupActivity.this,
                            SolarSystemActivity.class);
                    TravelPopupActivity.this.startActivity(intent);
                }
            }else{
                viewModel.changeActivePlanet(currentEntity.getName());
                if(odds < travelCost){
                    Intent intent = new Intent(TravelPopupActivity.this, RandomEncounterActivity.class);
                    TravelPopupActivity.this.startActivity(intent);
                }else {
                    Intent intent = new Intent(TravelPopupActivity.this,
                            PlanetActivity.class);
                    TravelPopupActivity.this.startActivity(intent);
                }
            }

            break;
        }
    }

    /**
     * a method that calculates the values associated with travel from
     * planet to another
     * @param system the goal of the travel
     */
    private void travel(System system){
        ship.setRemainingFuel(ship.getRemainingFuel() - calculateTravelCost(system));
        Model model = Model.getInstance();
        GameInteractor gi = model.getGameInteractor();
        gi.changeActivePlanet(system.getName());
    }

    private int calculateTravelCost(System system){
        int currentX;
        int currentY;
        if(!isSolarsystemTravel) {
            currentX = currentPlanet.getX();
            currentY = currentPlanet.getY();
        }else{
            currentX = viewModel.getActiveSolarSystemX();
            currentY = viewModel.getActiveSolarSystemY();
        }
        int travelX = system.getX();
        int travelY = system.getY();

        int xMag = Math.abs(currentX - travelX);
        int yMag = Math.abs(currentY - travelY);
        xMag *= xMag;
        yMag *= yMag;
        int travelDistance = (int) Math.sqrt(xMag + yMag);
        int fuelCost = travelDistance * FUEL_COST_PER_UNIT;
        if(viewModel.getPlayerPilotSkill() != 0) {
            fuelCost /= viewModel.getPlayerPilotSkill();
        }
        return isSolarsystemTravel ? (fuelCost * 10) : fuelCost;
    }
}
