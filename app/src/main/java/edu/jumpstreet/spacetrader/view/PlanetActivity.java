package edu.jumpstreet.spacetrader.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import edu.jumpstreet.spacetrader.R;
import edu.jumpstreet.spacetrader.viewmodel.PlanetViewModel;
import edu.jumpstreet.spacetrader.viewmodel.PlanetViewModelFactory;

/**
 * class Planet Activity sets up the planet screen
 */
public class PlanetActivity extends AppCompatActivity implements View.OnClickListener {
    Button spaceshipStoreBtn;
    Button solarSystemBtn;
    Button garageBtn;
    TextView planetName;
    TextView techLevel;
    TextView resourceType;
    PlanetViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planet);
        PlanetViewModelFactory factory = new PlanetViewModelFactory();
        viewModel = factory.create(PlanetViewModel.class);
        planetName = findViewById(R.id.textView4);
        planetName.setText("Planet: " + viewModel.getActivePlanetName());
        techLevel = findViewById(R.id.OnPlanetTechLevelTV);
        techLevel.setText("Tech Level: " + viewModel.getActivePlanetTechLevel());
        resourceType = findViewById(R.id.OnPlantResource);
        resourceType.setText("Resource Type: " + viewModel.getActivePlanetResource());
        initializeButtons();
    }

    private void initializeButtons(){
        spaceshipStoreBtn = findViewById(R.id.OnPlanetSpaceshipStoreBtn);
        spaceshipStoreBtn.setOnClickListener(this);
        solarSystemBtn = findViewById(R.id.OnPlanetBackToSolarSystemBtn);
        solarSystemBtn.setOnClickListener(this);
        garageBtn = findViewById(R.id.OnPlanetGarageBtn);
        garageBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.OnPlanetSpaceshipStoreBtn:
                Intent marketIntent = new Intent(this, MarketPlaceActivity.class);
                PlanetActivity.this.startActivity(marketIntent);
                break;
            case R.id.OnPlanetBackToSolarSystemBtn:
                Intent SolarSystemIntent = new Intent(this, SolarSystemActivity.class);
                PlanetActivity.this.startActivity(SolarSystemIntent);
                break;
            case R.id.OnPlanetGarageBtn:
                Intent garageIntent = new Intent(this, GarageActivity.class);
                PlanetActivity.this.startActivity(garageIntent);
        }
    }
}
