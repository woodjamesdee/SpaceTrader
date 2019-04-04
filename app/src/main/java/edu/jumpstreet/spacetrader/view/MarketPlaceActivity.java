package edu.jumpstreet.spacetrader.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import edu.jumpstreet.spacetrader.R;
import edu.jumpstreet.spacetrader.entity.Commodity;
import edu.jumpstreet.spacetrader.viewmodel.MarketPlaceViewModel;
import edu.jumpstreet.spacetrader.viewmodel.MarketPlaceViewModelFactory;

public class MarketPlaceActivity extends AppCompatActivity implements View.OnClickListener{
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

    //Model model;
    //Planet currentPlanet;
    //Spaceship ship;
    int techLevel;
    MarketPlaceViewModel viewModel;

    //TODO switch this to model var
    final int NUM_OF_RESOURCES = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);
        MarketPlaceViewModelFactory factory = new MarketPlaceViewModelFactory();
        viewModel = factory.create(MarketPlaceViewModel.class);
        techLevel = viewModel.getTechLevelOrdinal();
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
        remainingCreditsTV.setText("Credits: " + viewModel.getPlayerCredits());
        remainingCargoSpaceTV = findViewById(R.id.OnMarketCargoSpace);
        remainingCargoSpaceTV.setText("Cargo Space: " + viewModel.getUsedCargoSpace() + "/" + viewModel.getMaxCargoSpace());
    }


    //index has 1 for planets resources, 2 for users resource, and 3 for cost per unit of resources
    private void setTextViews(int index){
        for(int i=0;i<NUM_OF_RESOURCES;i++){
            //2 is to increment past header LinearLayouts
            LinearLayout ll = (LinearLayout) fullLayout.getChildAt(2+ i);
            TextView tv = (TextView) ll.getChildAt(index);
            if(index == 1) {
                tv.setText(viewModel.getPlanetResourceQuantityByIndex(i)+ "");
            }
            if (index == 2){
                tv.setText(viewModel.getShipResourceQuantityByIndex(i) + "");
            }
            if (index == 3){
                tv.setText(viewModel.getCommodityValue(i) + "");
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
        Commodity comm;
        switch(view.getId()){
            case R.id.waterTradeBtn:comm = viewModel.getCommodity(Commodity.CommodityResources.Water.ordinal());
                break;
            case R.id.furTradeBtn:comm = viewModel.getCommodity(Commodity.CommodityResources.Furs.ordinal());
                break;
            case R.id.foodTradeBtn:comm = viewModel.getCommodity(Commodity.CommodityResources.Food.ordinal());
                break;
            case R.id.oreTradeBtn:comm = viewModel.getCommodity(Commodity.CommodityResources.Ore.ordinal());
                break;
            case R.id.gamesTradeBtn:comm = viewModel.getCommodity(Commodity.CommodityResources.Games.ordinal());
                break;
            case R.id.firearmsTradeBtn:comm = viewModel.getCommodity(Commodity.CommodityResources.Firearms.ordinal());
                break;
            case R.id.medicineTradeBtn:comm = viewModel.getCommodity(Commodity.CommodityResources.Medicine.ordinal());
                break;
            case R.id.machinesTradeBtn:comm = viewModel.getCommodity(Commodity.CommodityResources.Machines.ordinal());
                break;
            case R.id.narcoticsTradeBtn:comm = viewModel.getCommodity(Commodity.CommodityResources.Narcotics.ordinal());
                break;
            case R.id.robotsTradeBtn:comm = viewModel.getCommodity(Commodity.CommodityResources.Robots.ordinal());
                break;
                default:comm = viewModel.getCommodity(Commodity.CommodityResources.Water.ordinal());
        }
        intent.putExtra("Commodity", (Parcelable) comm);
        MarketPlaceActivity.this.startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        super.onActivityResult(requestCode, resultCode, intent);
        initializeUserViews();
        setTextViews(1);
        setTextViews(2);
        setTextViews(3);
    }

    /*
    private Intent loadIntent(Intent intent, Commodity comm){
        intent.putExtra("Commodity", (Parcelable) comm);
        return intent;
    }
    */
}
