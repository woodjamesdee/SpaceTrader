package edu.jumpstreet.spacetrader.entity;

import edu.jumpstreet.spacetrader.model.Model;

/**
 * This class is the basis for all Spaceships in Space Trader.
 */
public abstract class Spaceship {

    protected String name;
    protected String description;
    protected int hitpoints;
    protected int maxCargoSpace;
    protected int usedCargoSpace;
    protected int Water;
    protected int Furs;
    protected int Food;
    protected int Ore;
    protected int Games;
    protected int Firearms;
    protected int Medicine;
    protected int Machines;
    protected int Narcotics;
    protected int Robots;

    protected Spaceship() {
    }

    public String getName() {
        return name;
    }
    public String getDescription() { return description; }
    public int getHitpoints() { return hitpoints; }
    public int getMaxCargoSpace(){return maxCargoSpace;}
    public int getUsedCargoSpace(){return usedCargoSpace;}

    public int getWater() {return Water;}
    public int getFurs() {return Furs;}
    public int getFood() {return Food;}
    public int getOre() {return Ore;}
    public int getGames(){return Games;}
    public int getFirearms() {return Firearms;}
    public int getMedicine() {return Medicine;}
    public int getMachines() {return Machines;}
    public int getNarcotics() {return Narcotics;}
    public int getRobots() {return Robots;}

    public void setHitpoints(int hitpoints) { this.hitpoints = hitpoints; }
    public void setMaxCargoSpace(int cargoSpace){this.maxCargoSpace = cargoSpace;}
    public void setUsedCargoSpace(int cargoSpace){this.usedCargoSpace = cargoSpace;}
    public void setWater(int water) {Water = water;}
    public void setFurs(int furs) {Furs = furs;}
    public void setFood(int food) {Food = food; }
    public void setOre(int ore) {Ore = ore;}
    public void setGames(int games) {Games = games;}
    public void setFirearms(int firearms) {Firearms = firearms;}
    public void setMedicine(int medicine) {Medicine = medicine;}
    public void setMachines(int machines) {Machines = machines;}
    public void setNarcotics(int narcotics) {Narcotics = narcotics;}
    public void setRobots(int robots) {Robots = robots;}


    //TODO default is getWater
    public int getIndexedResource(int index){
        switch(index){
            case 0: return getWater();
            case 1: return getFurs();
            case 2: return getFood();
            case 3: return getOre();
            case 4: return getGames();
            case 5: return getFirearms();
            case 6: return getMedicine();
            case 7: return getMachines();
            case 8: return getNarcotics();
            case 9: return getRobots();
            default:return getWater();
        }
    }
    //TODO default sets water to be what it is
    public void setIndexedResource(int index, int amount){
        switch(index){
            case 0: setWater(amount);
            case 1: setFurs(amount);
            case 2: setFood(amount);
            case 3: setOre(amount);
            case 4: setGames(amount);
            case 5: setFirearms(amount);
            case 6: setMedicine(amount);
            case 7: setMachines(amount);
            case 8: setNarcotics(amount);
            case 9: setRobots(amount);
            default:setWater(Model.getInstance().getPlayerInteractor().getPlayerShip().getWater());
        }
    }
}
