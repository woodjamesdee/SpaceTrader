package edu.jumpstreet.spacetrader.entity;

/**
 * The Mosqueto is the second worst space ship
 */
public class Mosqueto extends Spaceship{
    /**
     * Mosquito constructor
     */
    public Mosqueto(){
        super.name = "Mosqueto";
        super.hitpointsMax = 149;
        super.hitpointsRemaining = hitpointsMax;
        super.maxCargoSpace = 1432;
        super.economy = new Economy(System.TechLevel.HiTech);
        super.maxFuel = 1432;
        super.remainingFuel = 1432;
        super.description = "The Mosqueto is the most annoying ship, " +
                "For some reason it doesnt have whole number stats and the " +
                "inventor of the ship spelled it wrong";
    }
}
