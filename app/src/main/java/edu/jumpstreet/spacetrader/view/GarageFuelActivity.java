package edu.jumpstreet.spacetrader.view;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import edu.jumpstreet.spacetrader.R;
import edu.jumpstreet.spacetrader.viewmodel.GarageFuelViewModel;
import edu.jumpstreet.spacetrader.viewmodel.GarageFuelViewModelFactory;

/**
 * in charge of fuel available in garages
 */
public class GarageFuelActivity extends Activity implements View.OnClickListener{

    private Button minus10Btn;
    private Button clearBtn;
    private Button plus10Btn;
    private Button buyMaxBtn;
    //private Button confirmationBtn;

    private TextView usersFuel;
    private TextView quantityOfTransaction;
    private TextView costOfTransaction;
    private TextView usersCredits;
    private GarageFuelViewModel viewModel;
    private int fuelToBePurchased;
    private int costOfFuel;

    protected static final float WIDTH_MODIFIER = 0.9f;
    protected static final float HEIGHT_MODIFIER = 0.6f;

    protected static final int Y_LOCATION = -20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GarageFuelViewModelFactory factory = new GarageFuelViewModelFactory();
        viewModel = factory.create(GarageFuelViewModel.class);
        DisplayMetrics dM = new DisplayMetrics();
        WindowManager windowManager = getWindowManager();
        Display defaultDisplay = windowManager.getDefaultDisplay();
        defaultDisplay.getMetrics(dM);
        Window window = getWindow();
        window.setLayout((int) (dM.widthPixels * WIDTH_MODIFIER), (int) (dM.heightPixels * HEIGHT_MODIFIER));
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = Y_LOCATION;
        setContentView(R.layout.popup_window_garage_fuel);
        fuelToBePurchased = 0;
        costOfFuel = 0;
        this.setFinishOnTouchOutside(true);
        initializeViews();
        updateTextViews();
    }

    private void initializeViews(){
        minus10Btn = findViewById(R.id.OnGarageMinus10btn);
        clearBtn = findViewById(R.id.OnGarageClearTransaction);
        plus10Btn = findViewById(R.id.OnGarageFuelPlus10Btn);
        buyMaxBtn = findViewById(R.id.OnGarageFuelBuyMaxBtn);
        Button confirmationBtn = findViewById(R.id.OnGarageFuelConfirmationBtn);
        usersFuel = findViewById(R.id.OnGarageFuelUsersFuelTV);
        quantityOfTransaction = findViewById(R.id.OnGarageFuelToBuyTV);
        costOfTransaction = findViewById(R.id.OnGarageFuelCostTV);
        usersCredits = findViewById(R.id.OnGarageFuelUsersCredits);
        minus10Btn.setEnabled(false);
        minus10Btn.setOnClickListener(this);
        clearBtn.setEnabled(false);
        clearBtn.setOnClickListener(this);
        plus10Btn.setEnabled(true);
        plus10Btn.setOnClickListener(this);
        buyMaxBtn.setEnabled(true);
        buyMaxBtn.setOnClickListener(this);
        confirmationBtn.setEnabled(true);
        confirmationBtn.setOnClickListener(this);
    }



    private void updateTextViews(){
        usersFuel.setText("Users Fuel: " + (viewModel.getRemainingFuel()
                + fuelToBePurchased) + "/" + viewModel.getMaxFuel());
        quantityOfTransaction.setText("Amount of Fuel to be Purchased: " + fuelToBePurchased);
        costOfTransaction.setText("Cost for Fuel: " + costOfFuel);
        usersCredits.setText("Users Credits: " + (viewModel.getPlayerCredits() - costOfFuel));
        if(((viewModel.getRemainingFuel() + fuelToBePurchased) >= viewModel.getMaxFuel())
                || (viewModel.getPlayerCredits() <= costOfFuel)){
            plus10Btn.setEnabled(false);
            buyMaxBtn.setEnabled(false);
        }else{
            plus10Btn.setEnabled(true);
            buyMaxBtn.setEnabled(true);
        }
        if(fuelToBePurchased > 0){
            minus10Btn.setEnabled(true);
            clearBtn.setEnabled(true);
        }else{
            minus10Btn.setEnabled(false);
            clearBtn.setEnabled(false);
        }

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.OnGarageMinus10btn:
                fuelToBePurchased -= 10;
                costOfFuel -=100;
                updateTextViews();
                break;
            case R.id.OnGarageClearTransaction:
                fuelToBePurchased = 0;
                costOfFuel = 0;
                updateTextViews();
                break;
            case R.id.OnGarageFuelPlus10Btn:
                fuelToBePurchased += 10;
                costOfFuel += 100;
                updateTextViews();
                break;
            case R.id.OnGarageFuelBuyMaxBtn:
                fuelToBePurchased = getMaxFuel();
                costOfFuel = fuelToBePurchased * 10;
                updateTextViews();
                break;
            case R.id.OnGarageFuelConfirmationBtn:
                viewModel.setRemainingFuel(viewModel.getRemainingFuel() + fuelToBePurchased);
                viewModel.setPlayerCredits(viewModel.getPlayerCredits() - costOfFuel);
                finish();
                break;
        }
    }

    private int getMaxFuel(){
        int maxFuel = viewModel.getMaxFuel() - viewModel.getRemainingFuel();
        if((maxFuel * 10) > viewModel.getPlayerCredits()){
            while((maxFuel * 10) > viewModel.getPlayerCredits()){
                maxFuel--;
            }
        }

        return maxFuel;
    }
}
