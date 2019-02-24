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

public class UniverseActivity extends AppCompatActivity {

    TableLayout tableLayout;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tableLayout = findViewById(R.id.UniverseTableLayout);
    }

    //TODO switch param to universe instead of SS
    private void createUniverse(SolarSystem solarSystem){
        for(int i= 0;i<tableLayout.getChildCount();i++){
            //j<10 for debugging TODO replace with a var
            for(int j=0;j<10;j++){
                if(false){
                    TableRow table = (TableRow) tableLayout.getChildAt(i);
                    table.addView(new Space(this));
                }else{
                    Button btn = new Button(this);
                    btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //TODO check if 1st param works
                            Intent intent = new Intent(UniverseActivity.this, SolarSystem.class);
                            UniverseActivity.this.startActivity(intent);
                        }
                    });
                    TableRow.LayoutParams params = new TableRow.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
                    btn.setLayoutParams(params);
                    TableRow tableRow = (TableRow) tableLayout.getChildAt(i);
                    tableRow.addView(btn);
                }
            }
        }
    }
}
