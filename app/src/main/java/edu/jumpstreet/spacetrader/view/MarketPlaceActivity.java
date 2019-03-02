package edu.jumpstreet.spacetrader.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import edu.jumpstreet.spacetrader.R;
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

    @Override
    public void onClick(View view) {
       // LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        //View popupView = inflater.inflate(R.layout.popup_window_marketplace_trade, null);
        //int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        //final PopupWindow popup = new PopupWindow(popupView, width, width, true);
        switch(view.getId()){
            case R.id.waterTradeBtn:
                Intent intent = new Intent(this, MarketPlaceTradePopupActivity.class);
                MarketPlaceActivity.this.startActivity(intent);
                break;
            case R.id.furTradeBtn:
                break;
            case R.id.foodTradeBtn:
                break;
            case R.id.oreTradeBtn:
                break;
            case R.id.gamesTradeBtn:
                break;
            case R.id.firearmsTradeBtn:
                break;
            case R.id.medicineTradeBtn:
                break;
            case R.id.machinesTradeBtn:
                break;
            case R.id.narcoticsTradeBtn:
                break;
            case R.id.robotsTradeBtn:
                break;
        }
       // popup.showAtLocation(view, Gravity.CENTER, 0, 0);
    }

}
