package edu.jumpstreet.spacetrader.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

import edu.jumpstreet.spacetrader.R;
import edu.jumpstreet.spacetrader.entity.Planet;
import edu.jumpstreet.spacetrader.entity.SolarSystem;
import edu.jumpstreet.spacetrader.entity.Spaceship;
import edu.jumpstreet.spacetrader.model.GameInteractor;
import edu.jumpstreet.spacetrader.model.Model;
import edu.jumpstreet.spacetrader.entity.System;
import edu.jumpstreet.spacetrader.viewmodel.TravelPopupViewModel;
import edu.jumpstreet.spacetrader.viewmodel.TravelPopupViewModelFactory;

public class TravelPopupActivity extends Activity implements View.OnClickListener{
    private TextView planetTV;
    private TextView techLevelTV;
    private TextView resourceTV;
    private TextView conditionTV;
    private TextView usersFuelTV;
    private TextView requiredFuelTV;
    private Button travelBtn;

    //Model model;
    private TravelPopupViewModel viewModel;
    private Spaceship ship;
    private Planet currentPlanet;
    private Planet travelPlanet;
    private SolarSystem travelSS;
    private final int fuelCostPerUnit = 10;
    private boolean isSolarsystemTravel;
    private System currentEntity;
    private final GameInteractor GI = Model.getInstance().getGameInteractor();
    @Override
    protected void onCreate(Bundle savedInsanceState) {
        super.onCreate(savedInsanceState);
        TravelPopupViewModelFactory factory = new TravelPopupViewModelFactory();
        viewModel = factory.create(TravelPopupViewModel.class);
        DisplayMetrics dM = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dM);
        getWindow().setLayout((int) (dM.widthPixels *.9), (int) (dM.heightPixels *.4));
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;
        setContentView(R.layout.popup_window_travel);
        this.setFinishOnTouchOutside(true);
        isSolarsystemTravel = viewModel.isSolarSystemTravel();
        initializeViews();
        initializeReferences(isSolarsystemTravel);
        setTextViews(currentEntity);
        enableTravelButton(currentEntity);
    }

    private void initializeReferences(boolean isSolarsystemTravel){
        ship = viewModel.getPlayerShip();
        currentPlanet = viewModel.getActivePlanet();
        if(!isSolarsystemTravel) {
            travelPlanet = viewModel.getNextPlanet();
            currentEntity = travelPlanet;
        }else {
            travelSS = viewModel.getNextSolarSystem();
            currentEntity = travelSS;
        }
    }

    private void initializeViews(){
        planetTV = findViewById(R.id.travelPopupPlanetTV);
        techLevelTV = findViewById(R.id.travelPopupTechLevelTV);
        resourceTV = findViewById(R.id.travelPopupResourceTV);
        conditionTV = findViewById(R.id.travelPopupConditionTV);
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

    private void setTextViews(System system){
            planetTV.setText("Planets Name: " + system.getName());
            techLevelTV.setText("Planets Tech Level: " + system.getTechLevel());
            requiredFuelTV.setText("Required Fuel: " + calculateTravelCost(currentEntity));
            usersFuelTV.setText("Users Fuel: " + ship.getRemainingFuel() + "/" + ship.getMaxFuel());
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.travelPopupTravelButton: travel(currentEntity);
            finish();
            Random rand = new Random();
            if(isSolarsystemTravel){
                viewModel.changeActiveSolarSystem(currentEntity.getName());
                if((calculateTravelCost(currentEntity) * .005 )> rand.nextDouble()){
                    Intent intent = new Intent(
                            TravelPopupActivity.this, RandomEncounterActivity.class);
                    TravelPopupActivity.this.startActivity(intent);
                } else {
                    Intent intent = new Intent(TravelPopupActivity.this, SolarSystemActivity.class);
                    TravelPopupActivity.this.startActivity(intent);
                }
            }else{
                viewModel.changeActivePlanet(currentEntity.getName());

                if((calculateTravelCost(currentEntity) * .05 )> rand.nextDouble()){
                    Intent intent = new Intent(
                            TravelPopupActivity.this, RandomEncounterActivity.class);
                    TravelPopupActivity.this.startActivity(intent);
                }else {
                    Intent intent = new Intent(TravelPopupActivity.this, PlanetActivity.class);
                    TravelPopupActivity.this.startActivity(intent);
                }
            }

            break;
        }
    }

    private void travel(System system){
            ship.setRemainingFuel(ship.getRemainingFuel() - calculateTravelCost(system));
            GI.changeActivePlanet(system.getName());
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
        int fuelCost = travelDistance * fuelCostPerUnit;
        if(viewModel.getPlayerPilotSkill() != 0) {
            fuelCost /= viewModel.getPlayerPilotSkill();
        }
        return isSolarsystemTravel?(fuelCost * 10):fuelCost;
    }
}
