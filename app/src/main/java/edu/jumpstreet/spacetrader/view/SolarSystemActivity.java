package edu.jumpstreet.spacetrader.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Space;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import edu.jumpstreet.spacetrader.R;
import edu.jumpstreet.spacetrader.entity.SolarSystem;

public class SolarSystemActivity extends AppCompatActivity implements View.OnClickListener{
    TableLayout tableLayout;
    Button backToUniverseButton;

    @Override
    protected void onCreate(Bundle sav) {
        super.onCreate(sav);
        setContentView(R.layout.activity_solar_system);
        tableLayout = findViewById(R.id.solarSystemLayout);
        backToUniverseButton = findViewById(R.id.InSSBackToUniverseBtn);
        backToUniverseButton.setOnClickListener(this);

        SolarSystem solarSystem = new SolarSystem(5,2 ,8 ,5 );
        createSolarSystem(solarSystem);
    }

    private void createSolarSystem(SolarSystem solarSystem){
        for(int i= 0;i<tableLayout.getChildCount();i++){
            //j<10 for debugging TODO replace with a var
            for(int j=0;j<10;j++){
                if(solarSystem.getY() == j && solarSystem.getX() == i){
                    Button btn = new Button(this);
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //TODO check if 1st param works
                            Intent intent = new Intent(SolarSystemActivity.this, PlanetActivity.class);
                            SolarSystemActivity.this.startActivity(intent);
                        }
                    });
                    TableRow.LayoutParams params = new TableRow.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
                    btn.setLayoutParams(params);
                    TableRow tableRow = (TableRow) tableLayout.getChildAt(i);
                    tableRow.addView(btn);
                }else{
                    TableRow table = (TableRow) tableLayout.getChildAt(i);
                    Space space = new Space(this);
                    TableRow.LayoutParams params = new TableRow.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
                    space.setLayoutParams(params);
                    table.addView(space);
                }
            }
        }
    }
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case 0:
                break;
            case 1:
                break;
            case R.id.InSSBackToUniverseBtn:
                Intent intent = new Intent(this, UniverseActivity.class);
                SolarSystemActivity.this.startActivity(intent);
                break;
        }
    }
}
