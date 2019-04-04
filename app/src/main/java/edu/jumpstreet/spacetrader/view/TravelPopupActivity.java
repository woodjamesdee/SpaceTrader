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
import edu.jumpstreet.spacetrader.model.Model;
import edu.jumpstreet.spacetrader.entity.System;

public class TravelPopupActivity extends Activity implements View.OnClickListener{
    TextView planetTV;
    TextView techLevelTV;
    TextView resourceTV;
    TextView conditionTV;
    TextView usersFuelTV;
    TextView requiredFuelTV;
    Button travelBtn;

    Model model;
    Spaceship ship;
    Planet currentPlanet;
    Planet travelPlanet;
    SolarSystem travelSS;
    int travelCost;
    int fuelCostPerUnit = 10;
    boolean isSolarsystemTravel;
    System currentEntity;
    @Override
    protected void onCreate(Bundle savedInsanceState) {
        super.onCreate(savedInsanceState);
        model = Model.getInstance();
        DisplayMetrics dM = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dM);
        getWindow().setLayout((int) (dM.widthPixels *.9), (int) (dM.heightPixels *.4));
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;
        setContentView(R.layout.popup_window_travel);
        this.setFinishOnTouchOutside(true);
        isSolarsystemTravel = model.getGameInteractor().getIsNextSolarSystemTravel();
        //isSolarsystemTravel = getIntent().getBooleanExtra("Is_Solarsystem_Travel", true);
        initializeViews();
        initializeReferences(isSolarsystemTravel);
        setTextViews(isSolarsystemTravel, currentEntity);
        enableTravelButton(currentEntity);
    }

    private void initializeReferences(boolean isSolarsystemTravel){
        ship = model.getPlayerInteractor().getPlayerShip();
        currentPlanet = model.getGameInteractor().getActivePlanet();
        if(!isSolarsystemTravel) {
            travelPlanet = model.getGameInteractor().getNextPlanet();
            //travelPlanet = getIntent().getParcelableExtra("Travel_Planet");
            currentEntity = travelPlanet;
        }else {
            //travelSS = Model.getInstance().getUniverseInteractor().getUniverse().getSolarSystemWithName(getIntent().getStringExtra("Solarsystem_Name"));
            travelSS = Model.getInstance().getGameInteractor().getNextSolarSystem();
            currentEntity = travelSS;
        }
    }

    private void initializeViews(){
        planetTV = findViewById(R.id.travelPopupPlanetTV);
        techLevelTV = findViewById(R.id.travelPopupTechLevelTV);
        resourceTV = findViewById(R.id.travelPopupResouceTV);
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

    private void setTextViews(boolean isSolarsystemTravel, System system){
        if(!isSolarsystemTravel) {
            //TODO figure this out
           // resourceTV.setText("Planets Main Resource: " + system.getResource());
        }
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
                Model.getInstance().getGameInteractor().changeActiveSolarSystem(currentEntity.getName());
                if(calculateTravelCost(currentEntity) * 0.005 > rand.nextDouble()){
                    Intent intent = new Intent(TravelPopupActivity.this, RandomEncounterActivity.class);
                    TravelPopupActivity.this.startActivity(intent);
                } else {
                    Intent intent = new Intent(TravelPopupActivity.this, SolarSystemActivity.class);
                    TravelPopupActivity.this.startActivity(intent);
                }
            }else{
                Model.getInstance().getGameInteractor().changeActivePlanet(currentEntity.getName());
                if(calculateTravelCost(currentEntity) * 0.05 > rand.nextDouble()){
                    Intent intent = new Intent(TravelPopupActivity.this, RandomEncounterActivity.class);
                    TravelPopupActivity.this.startActivity(intent);
                }else {
                    Intent intent = new Intent(TravelPopupActivity.this, PlanetActivity.class);
                    TravelPopupActivity.this.startActivity(intent);
                }
            }

            break;
        }
    }

    public void travel(System system){
            ship.setRemainingFuel(ship.getRemainingFuel() - calculateTravelCost(system));
            Model.getInstance().getGameInteractor().changeActivePlanet(system.getName());
    }

    //TODO switch to model
    private int calculateTravelCost(System system){
        int currentX;
        int currentY;
        if(!isSolarsystemTravel) {
            currentX = currentPlanet.getX();
            currentY = currentPlanet.getY();
        }else{
            currentX = Model.getInstance().getGameInteractor().getActiveSolarSystem().getX();
            currentY = Model.getInstance().getGameInteractor().getActiveSolarSystem().getY();
        }
        int travelX = system.getX();
        int travelY = system.getY();

        int xMag = Math.abs(currentX - travelX);
        int yMag = Math.abs(currentY - travelY);
        xMag *= xMag;
        yMag *= yMag;
        int travelDistance = (int) Math.sqrt(xMag + yMag);
        int fuelCost = travelDistance * fuelCostPerUnit;
        if(Model.getInstance().getPlayerInteractor().getPlayerPilotSkill() != 0) {
            fuelCost /= Model.getInstance().getPlayerInteractor().getPlayerPilotSkill();
        }
        return isSolarsystemTravel?fuelCost * 10:fuelCost;
    }
}
