package edu.jumpstreet.spacetrader.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import edu.jumpstreet.spacetrader.R;
import edu.jumpstreet.spacetrader.model.Model;

public class GarageActivity extends AppCompatActivity implements View.OnClickListener{
    Button buySpaceshipBtn;
    Button buyFuelBtn;
    Button repairSpaceshipBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garage);
        initializeButtons();
    }



    private void initializeButtons(){
        buyFuelBtn = findViewById(R.id.OnGarageBuyFuelBtn);
        buyFuelBtn.setOnClickListener(this);
        if(Model.getInstance().getPlayerInteractor().getPlayerShip().getRemainingFuel() == Model.getInstance().getPlayerInteractor().getPlayerShip().getMaxFuel()) {
            buyFuelBtn.setEnabled(false);
        }else{
            buyFuelBtn.setEnabled(true);
        }
        buySpaceshipBtn = findViewById(R.id.OnGarageBuySpaceShipBtn);
        buySpaceshipBtn.setOnClickListener(this);
        buySpaceshipBtn.setEnabled(true);
        repairSpaceshipBtn = findViewById(R.id.OnGarageRepairSpaceshipBtn);
        repairSpaceshipBtn.setOnClickListener(this);
        repairSpaceshipBtn.setEnabled(true);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.OnGarageBuyFuelBtn:
                Intent intent = new Intent(GarageActivity.this, GarageFuelActivity.class);
                GarageActivity.this.startActivityForResult(intent, 1);
                break;
            case R.id.OnGarageBuySpaceShipBtn:
                break;
            case R.id.OnGarageRepairSpaceshipBtn:
                break;
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        super.onActivityResult(requestCode, resultCode, intent);
    }
}