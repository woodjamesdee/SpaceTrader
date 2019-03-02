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

    //TODO switch this to model var
    final int NUM_OF_RESOURCES = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);
        model = Model.getInstance();
        currentPlanet = model.getGameInteractor().getActivePlanet();
        techLevel = currentPlanet.getTechLevel().ordinal();
        initializeLayouts();
        initializeUserViews();
        setTextViews(1);
        setTextViews(2);
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


    //TODO currently sets all costs to 30, should route through MV to get actual value
    //TODO currently sets all cargo spaces to 10, should route through MV to get actual value
    //index has 1 for cost TV and 2 for Cargo Space
    private void setTextViews(int index){
        for(int i=0;i<NUM_OF_RESOURCES;i++){
            //2 is to increment past header LinearLayouts
            LinearLayout ll = (LinearLayout) fullLayout.getChildAt(2+ i);
            TextView tv = (TextView) ll.getChildAt(index);
            if(index == 1) {
                tv.setText(30 + "");
            }
            if (index == 2){
                tv.setText(10 + "");
            }
        }
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

}
