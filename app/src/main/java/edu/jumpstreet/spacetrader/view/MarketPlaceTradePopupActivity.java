package edu.jumpstreet.spacetrader.view;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import edu.jumpstreet.spacetrader.R;
import edu.jumpstreet.spacetrader.entity.Commodity;
import edu.jumpstreet.spacetrader.entity.Planet;
import edu.jumpstreet.spacetrader.entity.Spaceship;
import edu.jumpstreet.spacetrader.model.Model;

public class MarketPlaceTradePopupActivity extends Activity implements View.OnClickListener{
    Button plus1Btn;
    Button plus10Btn;
    Button minus1Btn;
    Button minus10Btn;
    Button transactionConfirmationBtn;

    TextView quantityTV;
    TextView cargoSpaceTV;
    TextView costTV;

    TextView userResourceTV;
    TextView planetsResourceTV;
    TextView cargoSpacePerItemTV;

    int quantityOfTransaction;
    int resourceValue;
    int cargoSpacePerUnitResource;
    int resourceQuantity;
    int userResourceIndex;
    String resourceType;

    Spaceship ship;
    Planet currentPlanet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ship = Model.getInstance().getPlayerInteractor().getPlayerShip();
        currentPlanet = Model.getInstance().getGameInteractor().getActivePlanet();
        DisplayMetrics dM = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dM);
        getWindow().setLayout((int) (dM.widthPixels *.9), (int) (dM.heightPixels *.4));
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;
        setContentView(R.layout.popup_window_marketplace_trade);
        this.setFinishOnTouchOutside(true);
        initializeViews();

        quantityOfTransaction = 0;
        quantityTV.setText("" + quantityOfTransaction);
        getResource();
        disableButtonsAdaptive();

        //TODO values are arbitrary for testing
        resourceValue = currentPlanet.getEconomy().getCommodityBaseValue(userResourceIndex);
        cargoSpacePerUnitResource = currentPlanet.getEconomy().getCommodityCargoSpace(userResourceIndex);

    }

    private void initializeViews(){
        plus1Btn = findViewById(R.id.tradePopupPlus1Btn);
        plus1Btn.setOnClickListener(this);
        plus10Btn = findViewById(R.id.tradePopupPlus10Btn);
        plus10Btn.setOnClickListener(this);
        minus1Btn = findViewById(R.id.tradePopupMinus1Btn);
        minus1Btn.setOnClickListener(this);
        minus10Btn = findViewById(R.id.tradePopupMinus10Btn);
        minus10Btn.setOnClickListener(this);
        transactionConfirmationBtn = findViewById(R.id.tradePopupConfirmationBtn);
        transactionConfirmationBtn.setOnClickListener(this);
        quantityTV = findViewById(R.id.tradePopupQuantityTV);
        cargoSpaceTV = findViewById(R.id.tradePopUpCargoTV);
        cargoSpaceTV.setText("Cargo Space: " + ship.getUsedCargoSpace() + "/" + ship.getMaxCargoSpace());
        costTV = findViewById(R.id.tradePopupTotalCostTV);
        costTV.setText("Cost: 0");
        planetsResourceTV = findViewById(R.id.tradePopupPlanetsResourceTV);
        userResourceTV = findViewById(R.id.tradePopupUsersResourceTV);
        cargoSpacePerItemTV = findViewById(R.id.tradePopupCargoPerItemTV);
    }

    private void getResource(){
        Bundle resources = getIntent().getExtras();
        userResourceIndex = resources.getInt("Resource_Name");
        if(resources != null){
            switch(userResourceIndex){
                case 0: resourceType = "Water";
                    resourceQuantity = resources.getInt("Water_Quantity");
                    break;
                case 1: resourceType = "Furs";
                    resourceQuantity = resources.getInt("Furs_Quantity");
                    break;
                case 2: resourceType = "Food";
                    resourceQuantity = resources.getInt("Food_Quantity");
                    break;
                case 3: resourceType = "Ore";
                    resourceQuantity = resources.getInt("Ore_Quantity");
                    break;
                case 4: resourceType = "Games";
                    resourceQuantity = resources.getInt("Games_Quantity");
                    break;
                case 5: resourceType = "Firearms";
                    resourceQuantity = resources.getInt("Firearms_Quantity");
                    break;
                case 6: resourceType = "Medicine";
                    resourceQuantity = resources.getInt("Medicine_Quantity");
                    break;
                case 7: resourceType = "Machines";
                    resourceQuantity = resources.getInt("Machines_Quantity");
                    break;
                case 8: resourceType = "Narcotics";
                    resourceQuantity = resources.getInt("Narcotics_Quantity");
                    break;
                case 9: resourceType = "Robots";
                    resourceQuantity = resources.getInt("Robots_Quantity");
                    break;
            }
        }
        updateResourceViews(resourceType, resourceQuantity);
        userResourceTV.setText("Users " + resourceType + " " + ship.getResourceQuantityByIndex(userResourceIndex));
        disableBuyButtonsByTechLevel(currentPlanet.getTechLevel().ordinal(), userResourceIndex);
        cargoSpacePerItemTV.setText("Weight: " + currentPlanet.getEconomy().getCommodity(userResourceIndex).getWeight());
    }

    private void updateResourceViews(String resourceType, int resourceQuantity){
        planetsResourceTV.setText("Planets " + resourceType + ": " + resourceQuantity);
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case(R.id.tradePopupPlus1Btn):
                changeQuantity(1);
                break;
            case(R.id.tradePopupPlus10Btn):
                changeQuantity(10);
                break;
            case(R.id.tradePopupMinus1Btn):
                changeQuantity(-1);
                break;
            case(R.id.tradePopupMinus10Btn):
                changeQuantity(-10);
                break;
            case(R.id.tradePopupConfirmationBtn):
                if(quantityOfTransaction == 0){
                    finish();
                }
                Model.getInstance().getPlayerInteractor().addCreditsToPlayerBalance(-1 * quantityOfTransaction * resourceValue);
                ship.setIndexedResourceQuantity(userResourceIndex, quantityOfTransaction);
                currentPlanet.setIndexedResource(userResourceIndex, resourceQuantity);
                ship.setUsedCargoSpace((quantityOfTransaction * cargoSpacePerUnitResource) + ship.getUsedCargoSpace());
                finish();
                break;
        }
    }

    private void changeQuantity(int change){
        quantityOfTransaction += change;
        quantityTV.setText("" + quantityOfTransaction);
        costTV.setText("Cost: " + (quantityOfTransaction * resourceValue * -1));
        cargoSpaceTV.setText("Cargo Space: " +  (ship.getUsedCargoSpace() + (cargoSpacePerUnitResource * quantityOfTransaction)) + "/"
                + ship.getMaxCargoSpace());
        resourceQuantity -= change;
        //TODO make method that gets weight per commodity
       // userResourceTV.setText("Users " + resourceType + " " + Model.getInstance().getPlayerInteractor().getPlayerShip().);
        updateResourceViews(resourceType, resourceQuantity);
        disableButtonsAdaptive();
    }

    private void disableButtonsAdaptive(){
        boolean minus1isActive = ship.getResourceQuantityByIndex(userResourceIndex) >=  Math.abs(quantityOfTransaction - 1)|| quantityOfTransaction >= 1;
        boolean minus10isActive = ship.getResourceQuantityByIndex(userResourceIndex) >= Math.abs(quantityOfTransaction - 10) || quantityOfTransaction >= 10;
        boolean plus1isActive = resourceQuantity >= 1
                && (quantityOfTransaction + 1) * resourceValue <= Model.getInstance().getPlayerInteractor().getPlayerBalance();
        boolean plus10isActive = resourceQuantity >= 10
                && (quantityOfTransaction + 10) * resourceValue <= Model.getInstance().getPlayerInteractor().getPlayerBalance();
        plus1Btn.setEnabled(plus1isActive);
        plus10Btn.setEnabled(plus10isActive);
        minus1Btn.setEnabled(minus1isActive);
        minus10Btn.setEnabled(minus10isActive);
    }

    private void disableBuyButtonsByTechLevel(int techLevel, int resourceIndex){
        boolean isActive;
        switch(Commodity.CommodityResources.values()[resourceIndex]){
            case Robots: isActive = techLevel >= 6;
                break;
            case Narcotics: isActive = techLevel >= 5;
                break;
            case Machines: case Medicine: isActive = techLevel >= 4;
                break;
            case Firearms: case Games: isActive = techLevel >= 3;
                break;
            case Ore: isActive = techLevel >= 2;
                break;
            case Food: isActive = techLevel >= 1;
                break;
            default: isActive = true;
                break;
        }
        plus1Btn.setEnabled(isActive);
        plus10Btn.setEnabled(isActive);
    }
}
