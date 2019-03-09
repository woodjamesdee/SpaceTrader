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

import edu.jumpstreet.spacetrader.R;
import edu.jumpstreet.spacetrader.entity.Planet;
import edu.jumpstreet.spacetrader.entity.Spaceship;
import edu.jumpstreet.spacetrader.model.Model;

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
    int travelCost;
    int fuelCostPerUnit = 10;
    @Override
    protected void onCreate(Bundle savedInsanceState) {
        super.onCreate(savedInsanceState);
        DisplayMetrics dM = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dM);
        getWindow().setLayout((int) (dM.widthPixels *.9), (int) (dM.heightPixels *.4));
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;
        setContentView(R.layout.popup_window_travel);
        this.setFinishOnTouchOutside(true);
        initializeViews();
        initializeReferences();
        setTextViews();
    }

    private void initializeReferences(){
        model = Model.getInstance();
        ship = model.getPlayerInteractor().getPlayerShip();
        currentPlanet = model.getGameInteractor().getActivePlanet();
        travelPlanet = getIntent().getParcelableExtra("Travel_Planet");
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
        travelBtn.setEnabled(true);
    }

    private void setTextViews(){
        planetTV.setText("Planets Name: " + travelPlanet.getName());
        techLevelTV.setText("Planets Tech Level: " + travelPlanet.getTechLevel());
        resourceTV.setText("Planets Main Resource: " + travelPlanet.getResource());
        usersFuelTV.setText("Users Fuel: " + ship.getRemainingFuel() + "/" + ship.getMaxFuel());
        requiredFuelTV.setText("Required Fuel: " + calculateTravelCost());
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.travelPopupTravelButton: travel();
            finish();
            Intent intent = new Intent(TravelPopupActivity.this, PlanetActivity.class);
            TravelPopupActivity.this.startActivity(intent);

            break;

        }
    }

    public void travel(){
        ship.setRemainingFuel(ship.getRemainingFuel() - calculateTravelCost());
        Model.getInstance().getGameInteractor().changeActivePlanet(travelPlanet.getName());
    }

    private int calculateTravelCost(){
        int currentX = currentPlanet.getX();
        int currentY = currentPlanet.getY();
        int destinationX = travelPlanet.getX();
        int destinationY = travelPlanet.getY();
        int xMag = Math.abs(currentX - destinationX);
        int yMag = Math.abs(currentY - destinationY);
        xMag *= xMag;
        yMag *= yMag;
        int travelDistance = (int) Math.sqrt(xMag + yMag);
        int fuelCost = travelDistance * fuelCostPerUnit;
        if(Model.getInstance().getPlayerInteractor().getPlayerPilotSkill() != 0) {
            fuelCost /= Model.getInstance().getPlayerInteractor().getPlayerPilotSkill();
        }
        return fuelCost;
    }
}
