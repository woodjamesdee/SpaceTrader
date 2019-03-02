package edu.jumpstreet.spacetrader.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import edu.jumpstreet.spacetrader.R;

public class MarketPlaceActivity extends AppCompatActivity {
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);
    }

    private void initializeLayouts(){
        waterLayout = findViewById(R.id.waterResourceLayout);
        furLayout = findViewById(R.id.waterResourceLayout);
        foodLayout = findViewById(R.id.waterResourceLayout);
        oreLayout = findViewById(R.id.waterResourceLayout);
        gamesLayout = findViewById(R.id.waterResourceLayout);
        firearmsLayout = findViewById(R.id.waterResourceLayout);
        medicineLayout = findViewById(R.id.waterResourceLayout);
        machineLayout = findViewById(R.id.waterResourceLayout);
        narcoticsLayout = findViewById(R.id.waterResourceLayout);
        robotsLayout = findViewById(R.id.waterResourceLayout);
    }

    private void deactiveLayouts(){

    }

    private void deactiveButtons(){

    }
}
