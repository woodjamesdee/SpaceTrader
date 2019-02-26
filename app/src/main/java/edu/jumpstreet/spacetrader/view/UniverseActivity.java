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
        createUniverse();
    }

    private void createUniverse() {
        TableRow.LayoutParams params = new TableRow.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
        for (int i = 0; i < tableLayout.getChildCount(); i++) {
            for (int j = 0; j < Universe.Y_BOUNDS; j++) {
                if (Universe.solarSystemLocations[i][j] != null) {
                    Button button = new Button(this);
                    final int i2 = i;
                    final int j2 = j;
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Model.getInstance().getGameInteractor().changeActiveSolarSystem(Universe.solarSystemLocations[i2][j2]);
                            Intent intent = new Intent(UniverseActivity.this, SolarSystemActivity.class);
                            UniverseActivity.this.startActivity(intent);
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
}
