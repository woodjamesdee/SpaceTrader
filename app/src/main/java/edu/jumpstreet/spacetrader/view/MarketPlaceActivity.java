package edu.jumpstreet.spacetrader.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import edu.jumpstreet.spacetrader.R;
import edu.jumpstreet.spacetrader.entity.Commodity;
import edu.jumpstreet.spacetrader.entity.Planet;
import edu.jumpstreet.spacetrader.entity.Spaceship;
import edu.jumpstreet.spacetrader.model.Model;

public class MarketPlaceActivity extends AppCompatActivity implements View.OnClickListener {
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

    //Buttons
    Button waterTradeBtn;
    Button furTradeBtn;
    Button foodTradeBtn;
    Button oreTradeBtn;
    Button gamesTradeBtn;
    Button firearmsTradeBtn;
    Button medicineTradeBtn;
    Button machinesTradeBtn;
    Button narcoticsTradeBtn;
    Button robotsTradeBtn;

    //Player determined Views
    TextView remainingCreditsTV;
    TextView remainingCargoSpaceTV;

    Model model;
    Planet currentPlanet;
    Spaceship ship;
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
        ship = model.getPlayerInteractor().getPlayerShip();
        initilizeAllViews();
        deactiveResourceLayouts(techLevel);
    }

    private void initilizeAllViews(){
        initializeLayouts();
        initializeButtons();
        initializeUserViews();
        setTextViews(1);
        setTextViews(2);
        setTextViews(3);
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

    private void initializeButtons(){
        waterTradeBtn = findViewById(R.id.waterTradeBtn);
        waterTradeBtn.setOnClickListener(this);
        furTradeBtn = findViewById(R.id.furTradeBtn);
        furTradeBtn.setOnClickListener(this);
        foodTradeBtn = findViewById(R.id.foodTradeBtn);
        foodTradeBtn.setOnClickListener(this);
        oreTradeBtn = findViewById(R.id.oreTradeBtn);
        oreTradeBtn.setOnClickListener(this);
        gamesTradeBtn = findViewById(R.id.gamesTradeBtn);
        gamesTradeBtn.setOnClickListener(this);
        firearmsTradeBtn = findViewById(R.id.firearmsTradeBtn);
        firearmsTradeBtn.setOnClickListener(this);
        medicineTradeBtn = findViewById(R.id.medicineTradeBtn);
        medicineTradeBtn.setOnClickListener(this);
        machinesTradeBtn = findViewById(R.id.machinesTradeBtn);
        machinesTradeBtn.setOnClickListener(this);
        narcoticsTradeBtn = findViewById(R.id.narcoticsTradeBtn);
        narcoticsTradeBtn.setOnClickListener(this);
        robotsTradeBtn = findViewById(R.id.robotsTradeBtn);
        robotsTradeBtn.setOnClickListener(this);
    }

    private void initializeUserViews(){
        remainingCreditsTV = findViewById(R.id.OnMarketUserCredits);
        remainingCreditsTV.setText("Credits: " + model.getPlayerInteractor().getPlayerBalance());
        remainingCargoSpaceTV = findViewById(R.id.OnMarketCargoSpace);
        remainingCargoSpaceTV.setText("Cargo Space: " + ship.getUsedCargoSpace() + "/" + ship.getMaxCargoSpace());
    }


    //TODO currently sets all cost spaces to 20, should route through MV to get actual value
    //index has 1 for planets resources, 2 for users resource, and 3 for cost per unit of resources
    private void setTextViews(int index){
        for(int i=0;i<NUM_OF_RESOURCES;i++){
            //2 is to increment past header LinearLayouts
            LinearLayout ll = (LinearLayout) fullLayout.getChildAt(2+ i);
            TextView tv = (TextView) ll.getChildAt(index);
            if(index == 1) {
                tv.setText(currentPlanet.getIndexedResource(i)+ "");
            }
            if (index == 2){
                tv.setText(ship.getResourceQuantityByIndex(i) + "");
            }
            if (index == 3){
                tv.setText(currentPlanet.getEconomy().getCommodityCargoSpace(i) + "");
            }
        }
    }

    private void deactiveResourceLayouts(int techLevel){
        if(techLevel < 4){
            fullLayout.removeView(robotsLayout);
        }
        if(techLevel < 3){
            fullLayout.removeView(machineLayout);
        }
        if(techLevel < 2){
            fullLayout.removeView(oreLayout);
        }
        if(techLevel < 1){
            fullLayout.removeView(gamesLayout);
            fullLayout.removeView(firearmsLayout);
            fullLayout.removeView(medicineLayout);
        }
    }



    //TODO switch from hardcoded resource Indexes
    //Note i do not have any idea what the request code is
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, MarketPlaceTradePopupActivity.class);
        switch(view.getId()){
            case R.id.waterTradeBtn: MarketPlaceActivity.this.startActivityForResult(
                        loadIntent(intent, "Water_Quantity", 0, currentPlanet.getEconomy().getCommodity(Commodity.CommodityResources.Water.ordinal()).getQuantity()), 1);
                break;
            case R.id.furTradeBtn: MarketPlaceActivity.this.startActivityForResult(
                        loadIntent(intent, "Furs_Quantity", 1, currentPlanet.getEconomy().getCommodity(Commodity.CommodityResources.Furs.ordinal()).getQuantity()), 1);
                break;
            case R.id.foodTradeBtn: MarketPlaceActivity.this.startActivityForResult(
                        loadIntent(intent, "Food_Quantity", 2, currentPlanet.getEconomy().getCommodity(Commodity.CommodityResources.Food.ordinal()).getQuantity()), 1);
                break;
            case R.id.oreTradeBtn: MarketPlaceActivity.this.startActivityForResult(
                        loadIntent(intent, "Ore_Quantity", 3, currentPlanet.getEconomy().getCommodity(Commodity.CommodityResources.Ore.ordinal()).getQuantity()), 1);
                break;
            case R.id.gamesTradeBtn: MarketPlaceActivity.this.startActivityForResult(
                        loadIntent(intent, "Games_Quantity", 4, currentPlanet.getEconomy().getCommodity(Commodity.CommodityResources.Games.ordinal()).getQuantity()),1);
                break;
            case R.id.firearmsTradeBtn: MarketPlaceActivity.this.startActivityForResult(
                        loadIntent(intent, "Firearms_Quantity", 5, currentPlanet.getEconomy().getCommodity(Commodity.CommodityResources.Firearms.ordinal()).getQuantity()), 1);
                break;
            case R.id.medicineTradeBtn: MarketPlaceActivity.this.startActivityForResult(
                        loadIntent(intent, "Medicine_Quantity", 6, currentPlanet.getEconomy().getCommodity(Commodity.CommodityResources.Medicine.ordinal()).getQuantity()), 1);
                break;
            case R.id.machinesTradeBtn: MarketPlaceActivity.this.startActivityForResult(
                        loadIntent(intent, "Machines_Quantity", 7, currentPlanet.getEconomy().getCommodity(Commodity.CommodityResources.Machines.ordinal()).getQuantity()), 1);
                break;
            case R.id.narcoticsTradeBtn: MarketPlaceActivity.this.startActivityForResult(
                        loadIntent(intent, "Narcotics_Quantity", 8, currentPlanet.getEconomy().getCommodity(Commodity.CommodityResources.Narcotics.ordinal()).getQuantity()), 1);
                break;
            case R.id.robotsTradeBtn: MarketPlaceActivity.this.startActivityForResult(
                        loadIntent(intent, "Robots_Quantity", 9, currentPlanet.getEconomy().getCommodity(Commodity.CommodityResources.Robots.ordinal()).getQuantity()), 1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        super.onActivityResult(requestCode, resultCode, intent);
        initializeUserViews();
        setTextViews(1);
        setTextViews(2);
        setTextViews(3);
    }

    private Intent loadIntent(Intent intent, String resource, int resourceIndex, int resourceQuantity){
        intent.putExtra(resource, resourceQuantity);
        intent.putExtra("Resource_Name", resourceIndex);
        return intent;
    }
}
