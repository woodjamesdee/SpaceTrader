package edu.jumpstreet.spacetrader.entity;

/**
 * This class is the basis for all Spaceships in Space Trader.
 */
public abstract class Spaceship {

    protected String name;
    protected String description;
    protected int hitpoints;

    protected Spaceship() {
    }

    public String getName() {
        return name;
    }
    public String getDescription() { return description; }

    public int getHitpoints() { return hitpoints; }

    public void setHitpoints(int hitpoints) { this.hitpoints = hitpoints; }
}
