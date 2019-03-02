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
        tradeBtn = findViewById(R.id.OnPlanetTradeBtn);
        spaceshipStoreBtn = findViewById(R.id.OnPlanetSpaceshipStoreBtn);
        solarSystemBtn = findViewById(R.id.OnPlanetBackToSolarSystemBtn);
        solarSystemBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.OnPlanetTradeBtn:
                break;
            case R.id.OnPlanetSpaceshipStoreBtn:
                break;
            case R.id.OnPlanetBackToSolarSystemBtn:
                Intent intent = new Intent(this, SolarSystemActivity.class);
                PlanetActivity.this.startActivity(intent);
                break;
        }
    }
}
