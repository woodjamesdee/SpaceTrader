package edu.jumpstreet.spacetrader.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import edu.jumpstreet.spacetrader.R;
import edu.jumpstreet.spacetrader.model.Model;

public class PlanetActivity extends AppCompatActivity implements View.OnClickListener {
    Button tradeBtn;
    Button spaceshipStoreBtn;
    Button solarSystemBtn;
    Button garageBtn;
    TextView planetName;
    TextView techLevel;
    TextView resourceType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planet);
        planetName = findViewById(R.id.textView4);
        planetName.setText("Planet: " + Model.getInstance().getGameInteractor().getActivePlanet().getName());
        techLevel = findViewById(R.id.OnPlanetTechLevelTV);
        techLevel.setText("Tech Level: " + Model.getInstance().getGameInteractor().getActivePlanet().getTechLevel());
        resourceType = findViewById(R.id.OnPlantResource);
        resourceType.setText("Resource Type: " + Model.getInstance().getGameInteractor().getActivePlanet().getResource());
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
                Intent GargeIntent = new Intent(this, GarageActivity.class);
                PlanetActivity.this.startActivity(GargeIntent);
        }
    }
}
