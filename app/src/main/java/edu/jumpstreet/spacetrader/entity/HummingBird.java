package edu.jumpstreet.spacetrader.entity;

/**
 * The HummingBird is the first travel focused ship
 */
public class HummingBird extends Spaceship{
    /**
     * HummingBird constructor
     */
    public HummingBird(){
        super.name = "Humming Bird";
        super.hitpointsMax = 75;
        super.maxCargoSpace = 2000;
        super.hitpointsRemaining = hitpointsMax;
        super.economy = new Economy(System.TechLevel.HiTech);
        super.maxFuel = 3000;
        super.remainingFuel = 3000;
        super.description = "The Humming Bird is a small capable ship used " +
        "by professional Smugglers to dart from galaxy to galaxy, what it lacks in " +
        "health and damage it makes up for by being cheap and fast";
    }
}
