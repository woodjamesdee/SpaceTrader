package edu.jumpstreet.spacetrader.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import edu.jumpstreet.spacetrader.R;

public class PlanetActivity extends AppCompatActivity implements View.OnClickListener {
    Button tradeBtn;
    Button spaceshipStoreBtn;
    Button solarSystemBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planet);
        initializeButtons();
    }

    private void initializeButtons(){
        tradeBtn = findViewById(R.id.OnPlanetTradeBtn);
        spaceshipStoreBtn = findViewById(R.id.OnPlanetSpaceshipStoreBtn);
        solarSystemBtn = findViewById(R.id.OnPlanetBackToSolarSystemBtn);
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
