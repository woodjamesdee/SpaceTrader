package edu.jumpstreet.spacetrader.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

import edu.jumpstreet.spacetrader.R;
import edu.jumpstreet.spacetrader.entity.Commodity;
import edu.jumpstreet.spacetrader.model.Model;
import edu.jumpstreet.spacetrader.model.PlayerInteractor;


/**
 * class Random Encounter; Activity represents templete for random encounters
 */
public class RandomEncounterActivity extends Activity implements View.OnTouchListener {

    private final PlayerInteractor PI = Model.getInstance().getPlayerInteractor();
    private TextView encounterTV;
    private String[] posEncounters;
    private String[] negEncounters;
    public enum Stats{ Pilot, Engineer, Fighter, Trader, Water, Furs, Food, Ore, Games, Firearms, Medicine, Machines, Narcotics, Robots, Credits}
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_encounter_templete);
        ConstraintLayout layout = findViewById(R.id.REConstraintLayout);
        layout.setOnTouchListener(this);
        InitalizeViews();
        Random rand = new Random();
        int isPos = rand.nextInt(2);
        int statNum = rand.nextInt(Stats.values().length);

        int amount;
        if(statNum < 4){
            amount = 1;
        }else if(statNum == Stats.Credits.ordinal()){
            amount = rand.nextInt(500);
        }else{
            amount = rand.nextInt(20);
        }

        if(isPos != 1){
            amount *= -1;
        }
        changeStats(Stats.values()[statNum], amount);
    }

    private void InitalizeViews(){
        encounterTV = findViewById(R.id.EncounterTV);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event){
        Intent intent = new Intent(this, PlanetActivity.class);
        RandomEncounterActivity.this.startActivity(intent);
        return true;
    }

    private void changeStats(Stats stat, int amount){
        switch(stat){
            case Pilot:
                
                PI.addPlayerPilotSkill(amount);
                encounterTV.setText("You meet a friendly alien who teaches you how to fly, your pilot skill goes up by " + amount);
                break;
            case Engineer:
                PI.addPlayerEngineerSkill(amount);
                encounterTV.setText("You get trapped in an abandoned dwarvin mine, in your solitute you practice the art of Engineering, skill goes up by  " + amount);
                break;
            case Fighter:
                PI.addPlayerFighterSkill(amount);
                encounterTV.setText("You pass a friendly outpost and just whale on the aliens. Your fighter skill increases by "  + amount);
                break;
            case Trader:
                PI.addPlayerTraderSkill(amount);
                encounterTV.setText("You play a high adrenaline game of settlers of Canthon IV, obvs your trading skill goes up by " + amount);
                break;
            case Water:
                PI.getPlayerShip().setResourceQuantityByName("Water", amount);
                encounterTV.setText("You find an old water fountain on an asteroid, you get " + amount + " free water BABY!!!");
                break;
            case Furs:
                PI.getPlayerShip().setResourceQuantityByName("Furs", amount);
                encounterTV.setText("You find a taxadermy alien he gives you " + amount + " wallabunga furs");
                break;
            case Food:
                PI.getPlayerShip().setResourceQuantityByName("Food", amount);
                encounterTV.setText("You get transported to the fabled chicken tendie VII, they give you " + amount + " of crispity chruchidy chicken TENDIESSSSS");
                break;
            case Ore:
                PI.getPlayerShip().setResourceQuantityByName("Ore", amount);
                encounterTV.setText("You notice a poor old lady alien mining in her personal ore vein, you steal " + amount + " of diamondillium, or diamondonium....whatever its called");
                break;
            case Games:
                PI.getPlayerShip().setResourceQuantityByName("Games", amount);
                encounterTV.setText("You go to the XXX planet, they have an over abundance of Twister games, so give you " + amount + " to play with your friends");
                break;
            case Firearms:
                PI.getPlayerShip().setResourceQuantityByName("Firearms", amount);
                encounterTV.setText("You jokingly ask a hot alien if she wants to go to the gun show..... she misunderstands and long story short you have  " + amount +
                        " new flanger tool....for flanging!!!");
                break;
            case Medicine:
                PI.getPlayerShip().setResourceQuantityByName("Medicine", amount);
                encounterTV.setText("You see a blind doctor and ponder and the irony of the situation. then you steal " + amount + " of legally perscribed (to someone) drugs");
                break;
            case Machines:
                PI.getPlayerShip().setResourceQuantityByName("Machines", amount);
                encounterTV.setText("You notice an old station of scrape that has become sentient. You gain  " + amount + " of 'free' machines");
                break;
            case Narcotics:
                PI.getPlayerShip().setResourceQuantityByName("Narcotics", amount);
                encounterTV.setText("You hear loud bang noises and look behind you (in your ship) " +
                        "when you notice what it is you see a flashing asteroid comming behind you. Its the party rock, and its in your house tonight!!!" +
                        "Random ravers and college freshmen give you " + amount + "of drugs, dont get pulled over!");
                break;
            case Robots:
                PI.getPlayerShip().setResourceQuantityByName("Robots", amount);
                encounterTV.setText("You build sentience in your spare lonely time in space, you get " + amount + " money bots, you sell them for CASH CASH RIGHT NOW CASH CASH");
                break;
            case Credits:
                PI.getPlayer().setCredits(amount + PI.getPlayer().getCredits());
                encounterTV.setText("You find a " + amount + " Bill on the ground, just lying in space");
                break;
        }
    }
}
