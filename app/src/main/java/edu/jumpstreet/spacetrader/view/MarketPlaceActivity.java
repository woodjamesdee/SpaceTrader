package edu.jumpstreet.spacetrader.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import edu.jumpstreet.spacetrader.R;
import edu.jumpstreet.spacetrader.entity.Planet;
import edu.jumpstreet.spacetrader.model.Model;

public class MarketPlaceActivity extends AppCompatActivity {
    //resource Layouts
    LinearLayout fullLayout;
    LinearLayout waterLayout;
    LinearLayout furLayout;
    LinearLayout foodLayout;
    LinearLayout oreLayout;
    LinearLayout gamesLayout;
    LinearLayout firearmsLayout;
    LinearLayout medicineLayout;
    LinearLayout machineLayout;
    LinearLayout narcoticsLayout;
    LinearLayout robotsLayout;

    //Player determined Views
    TextView remainingCreditsTV;
    TextView reamainingCargoSpaceTV;

    Model model;
    Planet currentPlanet;
    int techLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);
        model = Model.getInstance();
        currentPlanet = model.getGameInteractor().getActivePlanet();
        techLevel = currentPlanet.getTechLevel().ordinal();
        initializeLayouts();
        initializeUserViews();
        deactiveResourceLayouts(techLevel);
    }

    private void initializeLayouts(){
        fullLayout = findViewById(R.id.marketFullLayout);
        waterLayout = findViewById(R.id.waterResourceLayout);
        furLayout = findViewById(R.id.furResourceLayout);
        foodLayout = findViewById(R.id.foodResourceLayout);
        oreLayout = findViewById(R.id.oreResourceLayout);
        gamesLayout = findViewById(R.id.gamesResourceLayout);
        firearmsLayout = findViewById(R.id.firearmsResourceLayout);
        medicineLayout = findViewById(R.id.medicineResourceLayout);
        machineLayout = findViewById(R.id.machinesResourceLayout);
        narcoticsLayout = findViewById(R.id.narcoticsResourceLayout);
        robotsLayout = findViewById(R.id.robotsResourceLayout);
    }

    private void initializeUserViews(){
        remainingCreditsTV = findViewById(R.id.OnMarketUserCredits);
        remainingCreditsTV.setText("Credits: " + model.getPlayerInteractor().getPlayerBalance());
        reamainingCargoSpaceTV = findViewById(R.id.OnMarketCargoSpace);
    }



    private void initializeCostTextView(){

    }

    private void deactiveResourceLayouts(int techLevel){
        if(techLevel < 4){
            fullLayout.removeView(robotsLayout);
        } if(techLevel < 3){
            fullLayout.removeView(machineLayout);
        } if(techLevel < 2){
            fullLayout.removeView(oreLayout);
        } if(techLevel < 1){
            fullLayout.removeView(gamesLayout);
            fullLayout.removeView(firearmsLayout);
            fullLayout.removeView(medicineLayout);
        }
    }

    private void deactivateBuyButtons(){

    }

    private void deactiveSellButtons(){

    }


}
