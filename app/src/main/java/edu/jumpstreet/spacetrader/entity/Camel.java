package edu.jumpstreet.spacetrader.entity;

/**
 * The Camel is a medium transport ship
 */
public class Camel extends Spaceship{
    /**
     * Camel constructor
     */
    public Camel(){
        super.name = "Camel";
        super.hitpointsMax = 200;
        super.hitpointsRemaining = hitpointsMax;
        super.maxCargoSpace = 4000;
        super.economy = new Economy(System.TechLevel.HiTech);
        super.maxFuel = 2000;
        super.remainingFuel = 2000;
        super.description = "The Camel is most notable for its massive storage space " +
        "its like a camel....get it?";
    }
}
