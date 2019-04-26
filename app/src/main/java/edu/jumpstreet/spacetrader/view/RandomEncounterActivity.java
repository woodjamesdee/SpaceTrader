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
    int isPos;
    public enum Stats{ Pilot, Engineer, Fighter, Trader, Water, Furs, Food, Ore, Games, Firearms, Medicine, Machines, Narcotics, Robots, Credits}
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_encounter_templete);
        ConstraintLayout layout = findViewById(R.id.REConstraintLayout);
        layout.setOnTouchListener(this);
        InitalizeViews();
        Random rand = new Random();
        isPos = rand.nextInt(2);
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
                if(isPos == 1) {
                    PI.addPlayerPilotSkill(amount);
                    encounterTV.setText("You meet a friendly alien who teaches you how to fly, your pilot skill goes up by " + amount);
                }else{
                    if(PI.getPlayerPilotSkill() < amount){
                        PI.addPlayerPilotSkill(PI.getPlayerPilotSkill() * -1);
                        encounterTV.setText("You meet a mean alien who steals your ability how to fly, your pilot skill goes down by " + amount);
                    }
                }
                break;
            case Engineer:
                if(isPos == 1) {
                    PI.addPlayerEngineerSkill(amount);
                    encounterTV.setText("You get trapped in an abandoned dwarvin mine, in your solitute you practice the art of Engineering, skill goes up by  " + amount);
                }else{
                    if(PI.getPlayerEngineerSkill() < amount){
                        PI.addPlayerEngineerSkill(PI.getPlayerEngineerSkill() * -1);
                        encounterTV.setText("You meet a mean alien who steals your ability how to engineer, your enineert skill goes down by " + amount);
                    }
                }
                break;
            case Fighter:
                if(isPos == 1) {
                    PI.addPlayerFighterSkill(amount);
                    encounterTV.setText("You pass a friendly outpost and just whale on the aliens. Your fighter skill increases by " + amount);
                }else{
                    if(PI.getPlayerFighterSkill() < amount){
                        PI.addPlayerFighterSkill(PI.getPlayerFighterSkill() * -1);
                        encounterTV.setText("You meet a mean alien who steals your ability how to fight, your fighter skill goes down by " + amount);
                    }
                }
                break;
            case Trader:
                if(isPos == 1) {
                    PI.addPlayerTraderSkill(amount);
                    encounterTV.setText("You play a high adrenaline game of settlers of Canthon IV, obvs your trading skill goes up by " + amount);
                }else{
                    if(PI.getPlayerTraderSkill() < amount){
                        PI.addPlayerTraderSkill(PI.getPlayerTraderSkill() * -1);
                        encounterTV.setText("You meet a mean alien who steals your ability how to trade, your trader skill goes down by " + amount);
                    }
                }
                break;
            case Water:
                if(isPos == 1) {
                    amount = reduceCommodity(PI.getPlayerShip().getEconomy().getCommodity(0), amount);
                    PI.getPlayerShip().setResourceQuantityByName("Water", amount);
                    PI.getPlayerShip().setUsedCargoSpace(PI.getPlayerShip().getUsedCargoSpace() + amount * PI.getPlayerShip().getEconomy().getCommodity(0).getWeight());
                    encounterTV.setText("You find an old water fountain on an asteroid, you get " + amount + " free water BABY!!!");
                }else{
                    if(PI.getPlayerShip().getResourceQuantityByIndex(0) < Math.abs(amount)){
                        amount = PI.getPlayerShip().getResourceQuantityByIndex(0);
                    }
                    PI.getPlayerShip().setResourceQuantityByName("Water", amount);
                    PI.getPlayerShip().setUsedCargoSpace(PI.getPlayerShip().getUsedCargoSpace() - amount * PI.getPlayerShip().getEconomy().getCommodity(0).getWeight());
                    encounterTV.setText("You find a thristy aline, you give it water cuz yolo");
                }
                break;
            case Furs:
                if(isPos == 1) {
                    amount = reduceCommodity(PI.getPlayerShip().getEconomy().getCommodity(1), amount);
                    PI.getPlayerShip().setResourceQuantityByName("Furs", amount);
                    PI.getPlayerShip().setUsedCargoSpace(PI.getPlayerShip().getUsedCargoSpace() + amount * PI.getPlayerShip().getEconomy().getCommodity(1).getWeight());
                    encounterTV.setText("You find a taxadermy alien he gives you " + amount + " wallabunga furs");
                }else{
                    if(PI.getPlayerShip().getResourceQuantityByIndex(1) < Math.abs(amount)){
                        amount = PI.getPlayerShip().getResourceQuantityByIndex(1);
                    }
                    PI.getPlayerShip().setUsedCargoSpace(PI.getPlayerShip().getUsedCargoSpace() - amount * PI.getPlayerShip().getEconomy().getCommodity(1).getWeight());
                    PI.getPlayerShip().setResourceQuantityByName("Furs", amount);
                    encounterTV.setText("You drop some furs, you lose " + amount + " of them");
                }
                break;
            case Food:
                if(isPos == 1) {
                    amount = reduceCommodity(PI.getPlayerShip().getEconomy().getCommodity(2), amount);
                    PI.getPlayerShip().setResourceQuantityByName("Food", amount);
                    PI.getPlayerShip().setUsedCargoSpace(PI.getPlayerShip().getUsedCargoSpace() + amount * PI.getPlayerShip().getEconomy().getCommodity(2).getWeight());
                    encounterTV.setText("You get transported to the fabled chicken tendie VII, they give you " + amount + " of crispity chruchidy chicken TENDIESSSSS");
                }else{
                    if(PI.getPlayerShip().getResourceQuantityByIndex(2) < Math.abs(amount)){
                        amount = PI.getPlayerShip().getResourceQuantityByIndex(2);
                    }
                    PI.getPlayerShip().setUsedCargoSpace(PI.getPlayerShip().getUsedCargoSpace() - amount * PI.getPlayerShip().getEconomy().getCommodity(2).getWeight());
                    PI.getPlayerShip().setResourceQuantityByName("Food", amount);
                    encounterTV.setText("You get super hungry and eat " + amount + " units of food");
                }
                break;
            case Ore:
                if(isPos == 1) {
                    amount = reduceCommodity(PI.getPlayerShip().getEconomy().getCommodity(3), amount);
                    PI.getPlayerShip().setResourceQuantityByName("Ore", amount);
                    PI.getPlayerShip().setUsedCargoSpace(PI.getPlayerShip().getUsedCargoSpace() + amount * PI.getPlayerShip().getEconomy().getCommodity(3).getWeight());
                    encounterTV.setText("You notice a poor old lady alien mining in her personal ore vein, you steal " + amount + " of diamondillium, or diamondonium....whatever its called");
                }else{
                    if(PI.getPlayerShip().getResourceQuantityByIndex(3) < Math.abs(amount)){
                        amount = PI.getPlayerShip().getResourceQuantityByIndex(3);
                    }
                    PI.getPlayerShip().setUsedCargoSpace(PI.getPlayerShip().getUsedCargoSpace() - amount * PI.getPlayerShip().getEconomy().getCommodity(3).getWeight());
                    PI.getPlayerShip().setResourceQuantityByName("Ore", amount);
                    encounterTV.setText("you were trying to break a speed record but were too heavy so you dropped  " + amount + " of ore away");
                }
                break;
            case Games:
                if(isPos == 1) {
                    amount = reduceCommodity(PI.getPlayerShip().getEconomy().getCommodity(4), amount);
                    PI.getPlayerShip().setResourceQuantityByName("Games", amount);
                    PI.getPlayerShip().setUsedCargoSpace(PI.getPlayerShip().getUsedCargoSpace() + amount * PI.getPlayerShip().getEconomy().getCommodity(4).getWeight());
                    encounterTV.setText("You go to the XXX planet, they have an over abundance of Twister games, so give you " + amount + " to play with your friends");
                }else{
                    if(PI.getPlayerShip().getResourceQuantityByIndex(4) < Math.abs(amount)){
                        amount = PI.getPlayerShip().getResourceQuantityByIndex(4);
                    }
                    PI.getPlayerShip().setUsedCargoSpace(PI.getPlayerShip().getUsedCargoSpace() - amount * PI.getPlayerShip().getEconomy().getCommodity(4).getWeight());
                    PI.getPlayerShip().setResourceQuantityByName("Games", amount);
                    encounterTV.setText("You play a game and lose, you rage and destroy  " + amount + " copies of it");
                }
                break;
            case Firearms:
                if(isPos == 1) {
                    amount = reduceCommodity(PI.getPlayerShip().getEconomy().getCommodity(5), amount);
                    PI.getPlayerShip().setResourceQuantityByName("Firearms", amount);
                    PI.getPlayerShip().setUsedCargoSpace(PI.getPlayerShip().getUsedCargoSpace() + amount * PI.getPlayerShip().getEconomy().getCommodity(5).getWeight());
                    encounterTV.setText("You jokingly ask a hot alien if she wants to go to the gun show..... she misunderstands and long story short you have  " + amount +
                            " new flanger tool....for flanging!!!");
                }else{
                    if(PI.getPlayerShip().getResourceQuantityByIndex(5) < Math.abs(amount)){
                        amount = PI.getPlayerShip().getResourceQuantityByIndex(5);
                    }
                    PI.getPlayerShip().setUsedCargoSpace(PI.getPlayerShip().getUsedCargoSpace() - amount * PI.getPlayerShip().getEconomy().getCommodity(5).getWeight());
                    PI.getPlayerShip().setResourceQuantityByName("Firearms", amount);
                    encounterTV.setText("st8 robbed of guns. -" + amount);
                }
                break;
            case Medicine:
                if(isPos == 1) {
                    amount = reduceCommodity(PI.getPlayerShip().getEconomy().getCommodity(6), amount);
                    PI.getPlayerShip().setResourceQuantityByName("Medicine", amount);
                    PI.getPlayerShip().setUsedCargoSpace(PI.getPlayerShip().getUsedCargoSpace() + amount * PI.getPlayerShip().getEconomy().getCommodity(6).getWeight());
                    encounterTV.setText("You see a blind doctor and ponder and the irony of the situation. then you steal " + amount + " of legally perscribed (to someone) drugs");
                }else{
                    if(PI.getPlayerShip().getResourceQuantityByIndex(6) < Math.abs(amount)){
                        amount = PI.getPlayerShip().getResourceQuantityByIndex(6);
                    }
                    PI.getPlayerShip().setUsedCargoSpace(PI.getPlayerShip().getUsedCargoSpace() - amount * PI.getPlayerShip().getEconomy().getCommodity(6).getWeight());
                    PI.getPlayerShip().setResourceQuantityByName("Medicine", amount);
                    encounterTV.setText("you get the snifffles, lose " + amount + " units of medicine");
                }
                break;
            case Machines:
                if(isPos == 1) {
                    amount = reduceCommodity(PI.getPlayerShip().getEconomy().getCommodity(7), amount);
                    PI.getPlayerShip().setResourceQuantityByName("Machines", amount);
                    PI.getPlayerShip().setUsedCargoSpace(PI.getPlayerShip().getUsedCargoSpace() + amount * PI.getPlayerShip().getEconomy().getCommodity(7).getWeight());
                    encounterTV.setText("You notice an old station of scrape that has become sentient. You gain  " + amount + " of 'free' machines");
                }else{
                    if(PI.getPlayerShip().getResourceQuantityByIndex(7) < Math.abs(amount)){
                        amount = PI.getPlayerShip().getResourceQuantityByIndex(7);
                    }
                    PI.getPlayerShip().setUsedCargoSpace(PI.getPlayerShip().getUsedCargoSpace() - amount * PI.getPlayerShip().getEconomy().getCommodity(7).getWeight());
                    PI.getPlayerShip().setResourceQuantityByName("Machines", amount);
                    encounterTV.setText("The machines develop intellegence and leave " + amount + " machines gone...");
                }
                break;
            case Narcotics:
                if(isPos == 1) {
                    amount = reduceCommodity(PI.getPlayerShip().getEconomy().getCommodity(8), amount);
                    PI.getPlayerShip().setResourceQuantityByName("Narcotics", amount);
                    PI.getPlayerShip().setUsedCargoSpace(PI.getPlayerShip().getUsedCargoSpace() + amount * PI.getPlayerShip().getEconomy().getCommodity(8).getWeight());
                    encounterTV.setText("You hear loud bang noises and look behind you (in your ship) " +
                            "when you notice what it is you see a flashing asteroid comming behind you. Its the party rock, and its in your house tonight!!!" +
                            "Random ravers and college freshmen give you " + amount + "of drugs, dont get pulled over!");
                }else{
                    if(PI.getPlayerShip().getResourceQuantityByIndex(8) < Math.abs(amount)){
                        amount = PI.getPlayerShip().getResourceQuantityByIndex(8);
                    }
                    PI.getPlayerShip().setUsedCargoSpace(PI.getPlayerShip().getUsedCargoSpace() - amount * PI.getPlayerShip().getEconomy().getCommodity(8).getWeight());
                    PI.getPlayerShip().setResourceQuantityByName("Narcotics", amount);
                    encounterTV.setText("Well back to AA.... -" + amount + " narcotics");
                }
                break;
            case Robots:
                if(isPos == 1) {
                    amount = reduceCommodity(PI.getPlayerShip().getEconomy().getCommodity(9), amount);
                    PI.getPlayerShip().setResourceQuantityByName("Robots", amount);
                    PI.getPlayerShip().setUsedCargoSpace(PI.getPlayerShip().getUsedCargoSpace() + amount * PI.getPlayerShip().getEconomy().getCommodity(9).getWeight());
                    encounterTV.setText("You build sentience in your spare lonely time in space, you get " + amount + " money bots, you sell them for CASH CASH RIGHT NOW CASH CASH");
                }else{
                    if(PI.getPlayerShip().getResourceQuantityByIndex(9) < Math.abs(amount)){
                        amount = PI.getPlayerShip().getResourceQuantityByIndex(9);
                    }
                    PI.getPlayerShip().setUsedCargoSpace(PI.getPlayerShip().getUsedCargoSpace() - amount * PI.getPlayerShip().getEconomy().getCommodity(9).getWeight());
                    PI.getPlayerShip().setResourceQuantityByName("Robots", amount);
                    encounterTV.setText("You find an old water fountain on an asteroid, you get " + amount + " free water BABY!!!");
                }
                break;
            case Credits:
                    PI.getPlayer().setCredits(amount + PI.getPlayer().getCredits());
                    encounterTV.setText("You find a " + amount + " Bill on the ground, just lying in space");
                break;
        }
    }
    private int reduceCommodity(Commodity comm, int amount){
        while(amount * comm.getWeight() + PI.getPlayer().getShip().getQuantityByName(comm.getResource()) <= PI.getPlayerShip().getMaxCargoSpace()){
            amount --;
        }
        return amount;

    }
}
