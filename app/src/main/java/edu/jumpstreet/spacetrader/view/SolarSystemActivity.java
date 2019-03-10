package edu.jumpstreet.spacetrader.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Space;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import edu.jumpstreet.spacetrader.R;
import edu.jumpstreet.spacetrader.entity.Planet;
import edu.jumpstreet.spacetrader.entity.SolarSystem;
import edu.jumpstreet.spacetrader.entity.Universe;
import edu.jumpstreet.spacetrader.model.Model;

public class SolarSystemActivity extends AppCompatActivity implements View.OnClickListener{
    TableLayout tableLayout;
    Button backToUniverseButton;
    TextView solarSystemName;
    TextView solarSystemTechView;
    Model model;
    @Override
    protected void onCreate(Bundle sav) {
        super.onCreate(sav);
        setContentView(R.layout.activity_solar_system);
        tableLayout = findViewById(R.id.solarSystemLayout);
        backToUniverseButton = findViewById(R.id.InSSBackToUniverseBtn);
        backToUniverseButton.setOnClickListener(this);
        solarSystemName = findViewById(R.id.textView3);
        solarSystemName.setText("Solar System: " + Model.getInstance().getGameInteractor().getActiveSolarSystem().getName());
        solarSystemTechView = findViewById(R.id.SolarSystemTechLevelTV);
        solarSystemTechView.setText(Model.getInstance().getGameInteractor().getActiveSolarSystem().getTechLevel() + "");
        model = Model.getInstance();
        createSolarSystem();
    }

    private void createSolarSystem() {
        final SolarSystem activeSystem = Model.getInstance().getGameInteractor().getActiveSolarSystem();
        TableRow.LayoutParams params = new TableRow.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
        for (int i = 0; i < tableLayout.getChildCount(); i++) {
            for (int j = 0; j < Universe.Y_BOUNDS; j++) {
                if (activeSystem.getPlanetLocations()[i][j] != null) {
                    Button button = new Button(this);
                    if(Model.getInstance().getGameInteractor().getActivePlanet().getX() == i &&
                            Model.getInstance().getGameInteractor().getActivePlanet().getY() == j
                    && activeSystem.isInSolarSystem(Model.getInstance().getGameInteractor().getActivePlanet())){
                        button.setBackgroundColor(Color.GREEN);
                    }
                    final int i2 = i;
                    final int j2 = j;
                    final SolarSystem activeSystem2 = activeSystem;
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(SolarSystemActivity.this, TravelPopupActivity.class);
                            intent.putExtra("Travel_Planet", activeSystem2.getPlanet(activeSystem2.getPlanetLocations()[i2][j2]));
                            intent.putExtra("Is_Solarsystem_Travel", false);
                            SolarSystemActivity.this.startActivityForResult(intent, 1);
                        }
                    });
                    button.setLayoutParams(params);
                    TableRow tableRow = (TableRow) tableLayout.getChildAt(i);
                    tableRow.addView(button);
                } else {
                    TableRow table = (TableRow) tableLayout.getChildAt(i);
                    Space space = new Space(this);
                    space.setLayoutParams(params);
                    table.addView(space);
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        super.onActivityResult(requestCode, resultCode, intent);
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
