package edu.jumpstreet.spacetrader.entity;

/**
 * The Gnat is the starter Spaceship, and is the most simple.
 */
public class Gnat extends Spaceship {

    public Gnat() {
        super.name = "Gnat";
        super.hitpoints = 100;
        super.description = "The Gnat is not impressive in any area, " +
                "but most pilots are proud to call it their first ship.";
    }
}
