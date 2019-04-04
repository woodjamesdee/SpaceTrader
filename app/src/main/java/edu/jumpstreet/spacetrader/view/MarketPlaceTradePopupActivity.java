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
import edu.jumpstreet.spacetrader.model.Model;
import edu.jumpstreet.spacetrader.viewmodel.MarketPlacePopupViewModel;
import edu.jumpstreet.spacetrader.viewmodel.MarketPlacePopupViewModelFactory;

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
    Commodity activeCommodity;
    MarketPlacePopupViewModel viewModel;

    //Spaceship ship;
    //Planet currentPlanet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //sets values of the popup window
        MarketPlacePopupViewModelFactory factory = new MarketPlacePopupViewModelFactory();
        viewModel = factory.create(MarketPlacePopupViewModel.class);
        DisplayMetrics dM = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dM);
        getWindow().setLayout((int) (dM.widthPixels *.9), (int) (dM.heightPixels *.6));
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;
        setContentView(R.layout.popup_window_marketplace_trade);
        this.setFinishOnTouchOutside(true);
        initializeViews();
        getResource();


        resourceValue = viewModel.getCommodityValue(activeCommodity);
        quantityOfTransaction = 0;
        quantityTV.setText("" + quantityOfTransaction);
        cargoSpacePerUnitResource = activeCommodity.getWeight();
        disableButtonsAdaptive();

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
        cargoSpaceTV.setText("Cargo Space: " + viewModel.getUsedCargoSpace() + "/" + viewModel.getMaxCargoSpace());
        costTV = findViewById(R.id.tradePopupTotalCostTV);
        costTV.setText("Cost: 0");
        planetsResourceTV = findViewById(R.id.tradePopupPlanetsResourceTV);
        userResourceTV = findViewById(R.id.tradePopupUsersResourceTV);
        cargoSpacePerItemTV = findViewById(R.id.tradePopupCargoPerItemTV);
    }

    private void getResource(){
        activeCommodity = getIntent().getParcelableExtra("Commodity");
        resourceQuantity = activeCommodity.getQuantity();
        updateResourceViews(activeCommodity);
        userResourceTV.setText("Users " + activeCommodity.getResource() + " " + viewModel.getShipResourceQuantityByName(activeCommodity.getResource()));
        disableBuyButtonsByTechLevel(viewModel.getTechLevelOrdinal(), activeCommodity);
        cargoSpacePerItemTV.setText("Space: " + activeCommodity.getWeight());
    }

    private void updateResourceViews(Commodity comm){
        planetsResourceTV.setText("Planets " + comm.getResource() + ": " + comm.getQuantity());
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
                viewModel.setShipResourceQuantityByName(activeCommodity.getResource(), quantityOfTransaction);
                viewModel.setPlanetResourceQuantityByName(activeCommodity.getResource(), resourceQuantity);
                viewModel.setUsedCargoSpace((quantityOfTransaction * cargoSpacePerUnitResource) + viewModel.getUsedCargoSpace());
                finish();
                break;
        }
    }

    private void changeQuantity(int change){
        quantityOfTransaction += change;
        quantityTV.setText("" + quantityOfTransaction);
        costTV.setText("Cost: " + (quantityOfTransaction * resourceValue * -1));
        cargoSpaceTV.setText("Cargo Space: " +  (viewModel.getUsedCargoSpace() + (cargoSpacePerUnitResource * quantityOfTransaction)) + "/"
                + viewModel.getMaxCargoSpace());
        resourceQuantity -= change;
        //TODO make method that gets weight per commodity
       // userResourceTV.setText("Users " + resourceType + " " + Model.getInstance().getPlayerInteractor().getPlayerShip().);
        updateResourceViews(activeCommodity);
        disableButtonsAdaptive();
    }

    private void disableButtonsAdaptive(){
        boolean minus1isActive = viewModel.getShipResourceQuantityByName(activeCommodity.getResource()) >=  Math.abs(quantityOfTransaction - 1)|| quantityOfTransaction >= 1;
        boolean minus10isActive = viewModel.getShipResourceQuantityByName(activeCommodity.getResource()) >= Math.abs(quantityOfTransaction - 10) || quantityOfTransaction >= 10;
        boolean plus1isActive = resourceQuantity >= 1
                && (quantityOfTransaction + 1) * resourceValue <= Model.getInstance().getPlayerInteractor().getPlayerBalance()
                || ((quantityOfTransaction + 1) * activeCommodity.getWeight()) + viewModel.getUsedCargoSpace() <= viewModel.getMaxCargoSpace() ;
        boolean plus10isActive = resourceQuantity >= 10
                && (quantityOfTransaction + 10) * resourceValue <= Model.getInstance().getPlayerInteractor().getPlayerBalance()
                || ((quantityOfTransaction + 10) * activeCommodity.getWeight()) + viewModel.getUsedCargoSpace() <= viewModel.getMaxCargoSpace();
        plus1Btn.setEnabled(plus1isActive);
        plus10Btn.setEnabled(plus10isActive);
        minus1Btn.setEnabled(minus1isActive);
        minus10Btn.setEnabled(minus10isActive);
    }

    private void disableBuyButtonsByTechLevel(int techLevel, Commodity comm){
        boolean isActive = techLevel < comm.getMTLP().ordinal();
        plus1Btn.setEnabled(isActive);
        plus10Btn.setEnabled(isActive);
    }

}
