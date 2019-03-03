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


    int quantityOfTransaction;
    int value;
    int cargoSpacePerUnitResource;
    int PLANET_MAX_RESOURCE;
    int resourceQuantity;
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

        //TODO values are arbitrary for testing
        value = 10;
        cargoSpacePerUnitResource = 2;
        getResource();
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
    }

    private void getResource(){
        Bundle resources = getIntent().getExtras();
        if(resources != null){
            switch(resources.getInt("Resource_Name")){
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
        PLANET_MAX_RESOURCE = resourceQuantity;
        updateResourceViews(resourceType, resourceQuantity);
        userResourceTV.setText("Users " + resourceType + " " + Model.getInstance().getPlayerInteractor().getPlayerShip().getIndexedResource(resources.getInt("Resource_Name")));
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
                break;
        }
    }

    private void changeQuantity(int change){
        quantityOfTransaction += change;
        quantityTV.setText("" + quantityOfTransaction);
        costTV.setText("Cost: " + (quantityOfTransaction * value * -1));
        cargoSpaceTV.setText("Cargo Space: " + Model.getInstance().getPlayerInteractor().getPlayerShip().getUsedCargoSpace() + (cargoSpacePerUnitResource * quantityOfTransaction) + "/"
                + Model.getInstance().getPlayerInteractor().getPlayerShip().getMaxCargoSpace());
        resourceQuantity -= change;
        updateResourceViews(resourceType, resourceQuantity);
    }

    private void disableButtons(){

    }
}
