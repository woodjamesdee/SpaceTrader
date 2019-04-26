package edu.jumpstreet.spacetrader.entity;

/**
 * The Mantis Shrimp is the first damage class ship
 */
public class MantisShrimp extends Spaceship{
    /**
     * MantisShrimp constructor
     */
    public MantisShrimp(){
        super.name = "Mantis Shrimp";
        super.hitpointsMax = 500;
        super.hitpointsRemaining = hitpointsMax;
        super.maxCargoSpace = 500;
        super.economy = new Economy(System.TechLevel.HiTech);
        super.maxFuel = 750;
        super.remainingFuel = 750;
        super.description = "The Mantis Shrimp is a rainbow coloured spaceship " +
                "but dont let that fool you. What it lacks in cargo space it makes up for in " +
                "hitpointsMax";
    }
}

