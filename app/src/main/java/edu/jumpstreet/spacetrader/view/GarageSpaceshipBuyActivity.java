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
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import edu.jumpstreet.spacetrader.R;
import edu.jumpstreet.spacetrader.entity.Camel;
import edu.jumpstreet.spacetrader.entity.Gnat;
import edu.jumpstreet.spacetrader.entity.HummingBird;
import edu.jumpstreet.spacetrader.entity.MantisShrimp;
import edu.jumpstreet.spacetrader.entity.Mosqueto;
import edu.jumpstreet.spacetrader.entity.Spaceship;

/**
 * manages garage purchasing activity
 */
public class GarageSpaceshipBuyActivity extends Activity implements View.OnClickListener {
    LinearLayout gnatLayout;
    LinearLayout MosquetoLayout;
    LinearLayout HummingBirdLayout;
    LinearLayout MantisShrimpLayout;
    LinearLayout CamelLayout;

    RadioButton GnatRb;
    RadioButton MosquetoRB;
    RadioButton CamelRB;
    RadioButton MantisShrimpRB;
    RadioButton HummingBirdRB;
 //   RadioGroup radioGroup;
    Button confirmationBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DisplayMetrics dM = new DisplayMetrics();
        WindowManager windowManager = getWindowManager();
        Display defaultDisplay = windowManager.getDefaultDisplay();
        defaultDisplay.getMetrics(dM);
        Window window = getWindow();
        window.setLayout((int) (dM.widthPixels * GarageFuelActivity.WIDTH_MODIFIER),
                (int) (dM.heightPixels * GarageFuelActivity.HEIGHT_MODIFIER));
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = GarageFuelActivity.Y_LOCATION;
        setContentView(R.layout.activity_garage_spaceship_buy);
     //   radioGroup = new RadioGroup(this);
        InitalizeViews();
        setLayout(gnatLayout, new Gnat(), 10);
        setLayout(MosquetoLayout, new Mosqueto(), 874);
        setLayout(HummingBirdLayout, new HummingBird(), 1500);
        setLayout(MantisShrimpLayout, new MantisShrimp(), 3000);
        setLayout(CamelLayout, new Camel(), 2500);
    }

    private void InitalizeViews(){
        gnatLayout = findViewById(R.id.GnatLayout);
        MosquetoLayout = findViewById(R.id.MosquetoLayout);
        HummingBirdLayout = findViewById(R.id.HummingBirdLayout);
        MantisShrimpLayout = findViewById(R.id.MantisShrimpLayout);
        CamelLayout = findViewById(R.id.CamelLayout);
        InitalizeButtons();
    }

    private void InitalizeButtons(){
        GnatRb = findViewById(R.id.GnatRB);
        MosquetoRB = findViewById(R.id.MosquetoRB);
        HummingBirdRB = findViewById(R.id.HummingBirdRB);
        MantisShrimpRB = findViewById(R.id.MantisShrimpRB);
        CamelRB = findViewById(R.id.CamelRB);
        confirmationBtn = findViewById(R.id.BuySpaceShipConfirmBtn);

       // radioGroup.addView(GnatRb);
        //radioGroup.addView(MosquetoRB);
        //radioGroup.addView(HummingBirdRB);
       // radioGroup.addView(MantisShrimpRB);
       // radioGroup.addView(CamelRB);
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.GnatRB:
                if (checked)
                    break;
            case R.id.MosquetoRB:
                if (checked)
                    break;
            case R.id.HummingBirdRB:
                    if(checked)
                        break;
            case R.id.MantisShrimpRB:
                if(checked){
                    break;
                }
            case R.id.CamelRB:
                if(checked){
                    break;
                }
        }
    }


    private void setLayout(LinearLayout ll, Spaceship ship, int cost){
        TextView NameTV = (TextView) ll.getChildAt(0);
        TextView HPTV = (TextView) ll.getChildAt(1);
        TextView CSTV = (TextView) ll.getChildAt(2);
        TextView FuelTV = (TextView) ll.getChildAt(3);
        TextView Cost = (TextView) ll.getChildAt( 4);
        NameTV.setText(ship.getName());
        HPTV.setText(ship.getHitpointsMax() + "");
        CSTV.setText(ship.getMaxCargoSpace()+ "");
        FuelTV.setText(ship.getMaxFuel() + "");
        Cost.setText(cost + "");
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.BuySpaceShipConfirmBtn){

        }
    }
}
