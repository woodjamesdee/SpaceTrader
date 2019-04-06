package edu.jumpstreet.spacetrader.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Space;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.io.IOException;

import edu.jumpstreet.spacetrader.MainActivity;
import edu.jumpstreet.spacetrader.R;
import edu.jumpstreet.spacetrader.entity.Universe;
import edu.jumpstreet.spacetrader.model.Model;
import edu.jumpstreet.spacetrader.viewmodel.UniverseViewModel;
import edu.jumpstreet.spacetrader.viewmodel.UniverseViewModelFactory;

public class UniverseActivity extends AppCompatActivity {

    TableLayout tableLayout;
    Button saveButton;
    UniverseViewModel viewModel;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_universe);
        UniverseViewModelFactory factory = new UniverseViewModelFactory();
        viewModel = factory.create(UniverseViewModel.class);
        tableLayout = findViewById(R.id.UniverseTableLayout);
        saveButton = findViewById(R.id.saveButton);
        createUniverse();
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.MyPREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                try {
                    editor.putString(MainActivity.key, Model.saveGameToString());
                    editor.apply();
                } catch (IOException ioe) {
                    System.err.println("Error while saving game!");
                    ioe.printStackTrace();
                }
            }
        });
    }

    private void createUniverse() {
        TableRow.LayoutParams params = new TableRow.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
        for (int i = 0; i < tableLayout.getChildCount(); i++) {
            for (int j = 0; j < Universe.Y_BOUNDS; j++) {
                if (Universe.solarSystemLocations[i][j] != null) {
                    Button button = new Button(this);
                    if(viewModel.getActiveSolarSystemX() == i && viewModel.getActiveSolarSystemY() == j){
                        button.setBackgroundColor(Color.GREEN);
                    }
                    final int i2 = i;
                    final int j2 = j;
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(viewModel.getActiveSolarSystem() != viewModel.getSolarSystemByName(Universe.solarSystemLocations[i2][j2])) {
                                Intent intent = new Intent(UniverseActivity.this, TravelPopupActivity.class);
                                viewModel.setNextSolarSystem(viewModel.getSolarSystemByName(Universe.solarSystemLocations[i2][j2]));
                                //intent.putExtra("Solarsystem_Name", Universe.solarSystemLocations[i2][j2]);
                                viewModel.setIsSolarSystemTravel(true);
                                //intent.putExtra("Is_Solarsystem_Travel", true);
                                UniverseActivity.this.startActivityForResult(intent, 1);
                            }else{
                                Intent intent = new Intent(UniverseActivity.this, SolarSystemActivity.class);
                                UniverseActivity.this.startActivity(intent);
                            }
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
}
