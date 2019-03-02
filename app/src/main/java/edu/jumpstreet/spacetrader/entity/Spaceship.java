package edu.jumpstreet.spacetrader.entity;

/**
 * This class is the basis for all Spaceships in Space Trader.
 */
public abstract class Spaceship {

    protected String name;
    protected String description;
    protected int hitpoints;
    protected int maxCargoSpace;
    protected int usedCargoSpace;

    protected Spaceship() {
    }

    public String getName() {
        return name;
    }
    public String getDescription() { return description; }
    public int getHitpoints() { return hitpoints; }
    public int getMaxCargoSpace(){return maxCargoSpace;}
    public int getUsedCargoSpace(){return usedCargoSpace;}

    public void setHitpoints(int hitpoints) { this.hitpoints = hitpoints; }
    public void setMaxCargoSpace(int cargoSpace){this.maxCargoSpace = cargoSpace;}
    public void setUsedCargoSpace(int cargoSpace){this.usedCargoSpace = cargoSpace;}
}
