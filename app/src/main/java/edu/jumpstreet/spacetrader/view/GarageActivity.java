package edu.jumpstreet.spacetrader.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import edu.jumpstreet.spacetrader.R;

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
        buyFuelBtn.setEnabled(true);
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
                break;
            case R.id.OnGarageBuySpaceShipBtn:
                break;
            case R.id.OnGarageRepairSpaceshipBtn:
                break;
        }
    }
}
