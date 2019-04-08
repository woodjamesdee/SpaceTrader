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
import edu.jumpstreet.spacetrader.entity.Planet;
import edu.jumpstreet.spacetrader.viewmodel.MarketPlaceViewModel;
import edu.jumpstreet.spacetrader.viewmodel.MarketPlaceViewModelFactory;

/**
 * sets up Market Place layout
 */
public class MarketPlaceActivity extends AppCompatActivity implements View.OnClickListener{
    //resource Layouts
    private LinearLayout fullLayout;
    //private LinearLayout waterLayout;
    //private LinearLayout furLayout;
    //private LinearLayout foodLayout;
    private LinearLayout oreLayout;
    private LinearLayout gamesLayout;
    private LinearLayout firearmsLayout;
    private LinearLayout medicineLayout;
    private LinearLayout machineLayout;
    //private LinearLayout narcoticsLayout;
    private LinearLayout robotsLayout;

    //Buttons
    /*
    private Button waterTradeBtn;
    private Button furTradeBtn;
    private Button foodTradeBtn;
    private Button oreTradeBtn;
    private Button gamesTradeBtn;
    private Button firearmsTradeBtn;
    private Button medicineTradeBtn;
    private Button machinesTradeBtn;
    private Button narcoticsTradeBtn;
    private Button robotsTradeBtn;
    */

    //Player determined Views
    //private TextView remainingCreditsTV;
    //private TextView remainingCargoSpaceTV;

    //Model model;
    //Planet currentPlanet;
    //Spaceship ship;
    //private int techLevel;
    private MarketPlaceViewModel viewModel;

    //private final int NUM_OF_RESOURCES = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);
        MarketPlaceViewModelFactory factory = new MarketPlaceViewModelFactory();
        viewModel = factory.create(MarketPlaceViewModel.class);
        int techLevel = viewModel.getTechLevelOrdinal();
        initializeAllViews();
        inactiveResourceLayouts(techLevel);
    }

    private void initializeAllViews(){
        initializeLayouts();
        initializeButtons();
        initializeUserViews();
        setTextViews(1);
        setTextViews(2);
        setTextViews(3);
    }

    private void initializeLayouts(){
        fullLayout = findViewById(R.id.marketFullLayout);
        //LinearLayout waterLayout = findViewById(R.id.waterResourceLayout);
        //LinearLayout furLayout = findViewById(R.id.furResourceLayout);
        //LinearLayout foodLayout = findViewById(R.id.foodResourceLayout);
        oreLayout = findViewById(R.id.oreResourceLayout);
        gamesLayout = findViewById(R.id.gamesResourceLayout);
        firearmsLayout = findViewById(R.id.firearmsResourceLayout);
        medicineLayout = findViewById(R.id.medicineResourceLayout);
        machineLayout = findViewById(R.id.machinesResourceLayout);
        //LinearLayout narcoticsLayout = findViewById(R.id.narcoticsResourceLayout);
        robotsLayout = findViewById(R.id.robotsResourceLayout);
    }

    private void initializeButtons(){
        Button waterTradeBtn = findViewById(R.id.waterTradeBtn);
        waterTradeBtn.setOnClickListener(this);
        Button furTradeBtn = findViewById(R.id.furTradeBtn);
        furTradeBtn.setOnClickListener(this);
        Button foodTradeBtn = findViewById(R.id.foodTradeBtn);
        foodTradeBtn.setOnClickListener(this);
        Button oreTradeBtn = findViewById(R.id.oreTradeBtn);
        oreTradeBtn.setOnClickListener(this);
        Button gamesTradeBtn = findViewById(R.id.gamesTradeBtn);
        gamesTradeBtn.setOnClickListener(this);
        Button firearmsTradeBtn = findViewById(R.id.firearmsTradeBtn);
        firearmsTradeBtn.setOnClickListener(this);
        Button medicineTradeBtn = findViewById(R.id.medicineTradeBtn);
        medicineTradeBtn.setOnClickListener(this);
        Button machinesTradeBtn = findViewById(R.id.machinesTradeBtn);
        machinesTradeBtn.setOnClickListener(this);
        Button narcoticsTradeBtn = findViewById(R.id.narcoticsTradeBtn);
        narcoticsTradeBtn.setOnClickListener(this);
        Button robotsTradeBtn = findViewById(R.id.robotsTradeBtn);
        robotsTradeBtn.setOnClickListener(this);
    }

    private void initializeUserViews(){
        TextView remainingCreditsTV = findViewById(R.id.OnMarketUserCredits);
        remainingCreditsTV.setText("Credits: " + viewModel.getPlayerCredits());
        TextView remainingCargoSpaceTV = findViewById(R.id.OnMarketCargoSpace);
        remainingCargoSpaceTV.setText("Cargo Space: " + viewModel.getUsedCargoSpace() + "/"
                + viewModel.getMaxCargoSpace());
    }


    //index has 1 for planets resources, 2 for users resource, and 3 for cost per unit of resources
    private void setTextViews(int index){
        for(int i = 0; i< Planet.Resource.values().length; i++){
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

    private void inactiveResourceLayouts(int techLevel){
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

    //Note i do not have any idea what the request code is
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, MarketPlaceTradePopupActivity.class);
        Commodity comm;
        switch(view.getId()){
            case R.id.waterTradeBtn:comm = viewModel
                    .getCommodity(Commodity.CommodityResources.Water.ordinal());
                break;
            case R.id.furTradeBtn:comm = viewModel
                    .getCommodity(Commodity.CommodityResources.Furs.ordinal());
                break;
            case R.id.foodTradeBtn:comm = viewModel
                    .getCommodity(Commodity.CommodityResources.Food.ordinal());
                break;
            case R.id.oreTradeBtn:comm = viewModel
                    .getCommodity(Commodity.CommodityResources.Ore.ordinal());
                break;
            case R.id.gamesTradeBtn:comm = viewModel
                    .getCommodity(Commodity.CommodityResources.Games.ordinal());
                break;
            case R.id.firearmsTradeBtn:comm = viewModel
                    .getCommodity(Commodity.CommodityResources.Firearms.ordinal());
                break;
            case R.id.medicineTradeBtn:comm = viewModel
                    .getCommodity(Commodity.CommodityResources.Medicine.ordinal());
                break;
            case R.id.machinesTradeBtn:comm = viewModel
                    .getCommodity(Commodity.CommodityResources.Machines.ordinal());
                break;
            case R.id.narcoticsTradeBtn:comm = viewModel
                    .getCommodity(Commodity.CommodityResources.Narcotics.ordinal());
                break;
            case R.id.robotsTradeBtn:comm = viewModel
                    .getCommodity(Commodity.CommodityResources.Robots.ordinal());
                break;
                default:comm = viewModel
                        .getCommodity(Commodity.CommodityResources.Water.ordinal());
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
