package edu.jumpstreet.spacetrader.view;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

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

    int quantity;
    //TODO value should be passed in from MarketPlaceActivity
    int value;
    //TODO cargoSpace should be passed in from Market Place Activity
    int cargoSpace;

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

        quantity = 0;
        quantityTV.setText("" + quantity);

        //TODO values are arbitrary for testing
        value = 10;
        cargoSpace = 2;
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
        costTV = findViewById(R.id.tradePopupTotalCostTV);
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
        quantity += change;
        quantityTV.setText("" + quantity);
        costTV.setText("Cost: " + (quantity * value * -1));
        cargoSpaceTV.setText("Cargo Space: " + Model.getInstance().getPlayerInteractor().getPlayerShip().getUsedCargoSpace() + (cargoSpace * quantity) + "/"
                + Model.getInstance().getPlayerInteractor().getPlayerShip().getMaxCargoSpace());
    }
}
