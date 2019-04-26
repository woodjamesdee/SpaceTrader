package edu.jumpstreet.spacetrader.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import edu.jumpstreet.spacetrader.R;
import edu.jumpstreet.spacetrader.entity.SolarSystem;
import edu.jumpstreet.spacetrader.viewmodel.GarageViewModel;
import edu.jumpstreet.spacetrader.viewmodel.GarageViewModelFactory;

/**
 * controls back end of the garage of each planet
 */
public class GarageActivity extends AppCompatActivity implements View.OnClickListener{
    //private Button buySpaceshipBtn;
    //private Button buyFuelBtn;
    //private Button repairSpaceshipBtn;

    private GarageViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garage);
        GarageViewModelFactory factory = new GarageViewModelFactory();
        viewModel = factory.create(GarageViewModel.class);
        initializeButtons();
    }



    private void initializeButtons(){
        Button buyFuelBtn = findViewById(R.id.OnGarageBuyFuelBtn);
        buyFuelBtn.setOnClickListener(this);
        if(viewModel.getRemainingFuel() == viewModel.getMaxFuel()) {
            buyFuelBtn.setEnabled(false);
        }else{
            buyFuelBtn.setEnabled(true);
        }
        Button buySpaceshipBtn = findViewById(R.id.OnGarageBuySpaceShipBtn);
        buySpaceshipBtn.setOnClickListener(this);
        buySpaceshipBtn.setEnabled(true);
        Button repairSpaceshipBtn = findViewById(R.id.OnGarageRepairSpaceshipBtn);
        repairSpaceshipBtn.setOnClickListener(this);
        repairSpaceshipBtn.setEnabled(true);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch(view.getId()){
            case R.id.OnGarageBuyFuelBtn:
                intent = new Intent(GarageActivity.this, GarageFuelActivity.class);
                GarageActivity.this.startActivityForResult(intent, 1);
                break;
            case R.id.OnGarageBuySpaceShipBtn:
                intent = new Intent (GarageActivity.this, GarageSpaceshipBuyActivity.class);
                GarageActivity.this.startActivity(intent);
                break;
            case R.id.OnGarageRepairSpaceshipBtn:
                intent = new Intent(GarageActivity.this, SolarSystemActivity.class);
                GarageActivity.this.startActivity(intent);
                break;
        }
    }
    /*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        super.onActivityResult(requestCode, resultCode, intent);
    }
    */
}
