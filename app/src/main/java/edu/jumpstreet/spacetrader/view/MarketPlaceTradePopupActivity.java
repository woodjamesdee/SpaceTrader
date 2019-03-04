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
    int userResource;
    String resourceType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        resourceValue = 20;
        cargoSpacePerUnitResource = 2;

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
        cargoSpaceTV.setText("Cargo Space: " + Model.getInstance().getPlayerInteractor().getPlayerShip().getUsedCargoSpace() + "/"
                + Model.getInstance().getPlayerInteractor().getPlayerShip().getMaxCargoSpace());
        costTV = findViewById(R.id.tradePopupTotalCostTV);
        costTV.setText("Cost: 0");
        planetsResourceTV = findViewById(R.id.tradePopupPlanetsResourceTV);
        userResourceTV = findViewById(R.id.tradePopupUsersResourceTV);
        cargoSpacePerItemTV = findViewById(R.id.tradePopupCargoPerItemTV);
    }

    private void getResource(){
        Bundle resources = getIntent().getExtras();
        userResource = resources.getInt("Resource_Name");
        if(resources != null){
            switch(userResource){
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
        userResourceTV.setText("Users " + resourceType + " " + Model.getInstance().getPlayerInteractor().getPlayerShip().getResourceQuantityByIndex(userResource));
        disableBuyButtonsByTechLevel(Model.getInstance().getGameInteractor().getActivePlanet().getTechLevel().ordinal(), userResource);
        cargoSpacePerItemTV.setText("Weight: " + Model.getInstance().getGameInteractor().getActivePlanet().getEconomy().getCommodity(userResource).getWeight());
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
                Model.getInstance().getPlayerInteractor().getPlayerShip().setIndexedResourceQuantity(userResource, quantityOfTransaction);
                Model.getInstance().getGameInteractor().getActivePlanet().setIndexedResource(userResource, resourceQuantity);
                Model.getInstance().getPlayerInteractor().getPlayerShip().setUsedCargoSpace((quantityOfTransaction * cargoSpacePerUnitResource) + Model.getInstance().getPlayerInteractor().getPlayerShip().getUsedCargoSpace());
                finish();
                break;
        }
    }

    private void changeQuantity(int change){
        quantityOfTransaction += change;
        quantityTV.setText("" + quantityOfTransaction);
        costTV.setText("Cost: " + (quantityOfTransaction * resourceValue * -1));
        cargoSpaceTV.setText("Cargo Space: " + (int) (Model.getInstance().getPlayerInteractor().getPlayerShip().getUsedCargoSpace() + (cargoSpacePerUnitResource * quantityOfTransaction)) + "/"
                + Model.getInstance().getPlayerInteractor().getPlayerShip().getMaxCargoSpace());
        resourceQuantity -= change;
        updateResourceViews(resourceType, resourceQuantity);
        disableButtonsAdaptive();
    }

    private void disableButtonsAdaptive(){
        boolean minus1isActive = userResource >= 1 || quantityOfTransaction >= 1;
        boolean minus10isActive = userResource >= 10 || quantityOfTransaction >= 10;
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
        switch(resourceIndex){
            case 9: isActive = techLevel >= 6;
                break;
            case 8: isActive = techLevel >= 5;
                break;
            case 7 : case 6: isActive = techLevel >= 4;
                break;
            case 5: case 4: isActive = techLevel >= 3;
                break;
            case 3: isActive = techLevel >= 2;
                break;
            case 2: isActive = techLevel >= 1;
                break;
            default: isActive = true;
                break;
        }
        plus1Btn.setEnabled(isActive);
        plus10Btn.setEnabled(isActive);
    }
}
