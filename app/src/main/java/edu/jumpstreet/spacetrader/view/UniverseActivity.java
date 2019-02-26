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

import edu.jumpstreet.spacetrader.R;
import edu.jumpstreet.spacetrader.entity.SolarSystem;
import edu.jumpstreet.spacetrader.entity.Universe;
import edu.jumpstreet.spacetrader.model.Model;

public class UniverseActivity extends AppCompatActivity {

    TableLayout tableLayout;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_universe);
        tableLayout = findViewById(R.id.UniverseTableLayout);
        //SolarSystem ss = new SolarSystem(5, 5, 5, 5);
        //createUniverse(ss);
        createUniverse();
    }

    //TODO switch param to universe instead of SS
    private void createUniverse(SolarSystem solarSystem){
        for(int i= 0;i<tableLayout.getChildCount();i++){
            //j<10 for debugging TODO replace with a var
            for(int j=0;j<10;j++){
                if(solarSystem.getY() == j && solarSystem.getX() == i){
                    Button btn = new Button(this);
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //TODO check if 1st param works
                            Intent intent = new Intent(UniverseActivity.this, SolarSystemActivity.class);
                            UniverseActivity.this.startActivity(intent);
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

    private void createUniverse() {
        for (int i = 0; i < tableLayout.getChildCount(); i++) {
            for (int j = 0; j < Universe.Y_BOUNDS; j++) {
                if (Universe.solarSystemLocations[i][j] != null) {
                    Button button = new Button(this);
                    final int i2 = i;
                    final int j2 = j;
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //TODO check if 1st param works
                            Model.getInstance().getGameInteractor().changeActiveSolarSystem(Universe.solarSystemLocations[i2][j2]);
                            Intent intent = new Intent(UniverseActivity.this, SolarSystemActivity.class);
                            UniverseActivity.this.startActivity(intent);
                        }
                    });
                    TableRow.LayoutParams params = new TableRow.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
                    button.setLayoutParams(params);
                    TableRow tableRow = (TableRow) tableLayout.getChildAt(i);
                    tableRow.addView(button);
                } else {
                    TableRow table = (TableRow) tableLayout.getChildAt(i);
                    Space space = new Space(this);
                    TableRow.LayoutParams params = new TableRow.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
                    space.setLayoutParams(params);
                    table.addView(space);
                }
            }
        }
    }
}
