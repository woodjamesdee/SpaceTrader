package edu.jumpstreet.spacetrader.entity;

/**
 * The Gnat is the starter Spaceship, and is the most simple.
 */
public class Gnat extends Spaceship {
    /**
     * Gnat constructor
     */
    public Gnat() {
        super.name = "Gnat";
        super.hitpointsMax = 100;
        super.maxCargoSpace = 1000;
        super.hitpointsRemaining = hitpointsMax;
        super.usedCargoSpace = 0;
        super.economy = new Economy(System.TechLevel.HiTech);
        super.remainingFuel = 1000;
        super.maxFuel = 1000;
        super.description = "The Gnat is not impressive in any area, " +
                "but most pilots are proud to call it their first ship.";
    }
}
