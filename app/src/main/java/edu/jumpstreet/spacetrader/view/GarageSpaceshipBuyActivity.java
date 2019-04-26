package edu.jumpstreet.spacetrader.view;

import android.app.Activity;
import android.content.Intent;
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
import edu.jumpstreet.spacetrader.model.Model;

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
    RadioGroup radioGroup;
    Button confirmationBtn;

    Model model;

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
        model = Model.getInstance();
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
        confirmationBtn.setOnClickListener(this);
        confirmationBtn.setEnabled(false);
        int credits = Model.getInstance().getPlayerInteractor().getPlayerBalance();
        if(credits < 10){ GnatRb.setEnabled(false); }
        if(credits < 874) MosquetoRB.setEnabled(false);
        if(credits < 1500) HummingBirdRB.setEnabled(false);
        if(credits < 2500) CamelRB.setEnabled(false);
        if(credits < 3000) MantisShrimpRB.setEnabled(false);
        radioGroup = findViewById(R.id.BuySpaceShipRadioBtnGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                confirmationBtn.setEnabled(true);
            }
        });
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
            int index = radioGroup.getCheckedRadioButtonId();
            RadioButton rb = findViewById(index);
            switch(index){
                case R.id.GnatRB:
                    model.getPlayerInteractor().changeShip(new Gnat());
                    model.getPlayerInteractor().getPlayer().setCredits(model.getPlayerInteractor().getPlayerBalance() - 10);
                    break;
                case R.id.MosquetoRB:
                    model.getPlayerInteractor().changeShip(new Mosqueto());
                    model.getPlayerInteractor().getPlayer().setCredits(model.getPlayerInteractor().getPlayerBalance() - 874);
                    break;
                case R.id.HummingBirdRB:
                    model.getPlayerInteractor().changeShip(new HummingBird());
                    model.getPlayerInteractor().getPlayer().setCredits(model.getPlayerInteractor().getPlayerBalance() - 1500);
                    break;
                case R.id.MantisShrimpRB:
                    model.getPlayerInteractor().changeShip(new MantisShrimp());
                    model.getPlayerInteractor().getPlayer().setCredits(model.getPlayerInteractor().getPlayerBalance() - 3000);
                    break;
                case R.id.CamelRB:
                    model.getPlayerInteractor().changeShip(new Camel());
                    model.getPlayerInteractor().getPlayer().setCredits(model.getPlayerInteractor().getPlayerBalance() - 2500);
                    break;
            }
            finish();
        }
    }
}
