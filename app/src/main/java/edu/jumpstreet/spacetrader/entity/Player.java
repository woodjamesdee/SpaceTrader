package edu.jumpstreet.spacetrader.entity;

import java.io.Serializable;

/**
 * The Player entity represents the human player and the attributes that are closely related.
 * This class should not EVER be instantiated outside of a PlayerInteractor.
 */
public class Player implements Serializable {

    private String name;
    private int skillpoints, pilot, fighter, trader, engineer;
    private int credits;
    private Spaceship ship;

    public Player(String name, int skillpoints, int credits, Spaceship ship) {
        this.name = name;
        this.skillpoints = skillpoints;
        pilot = 0;
        fighter = 0;
        trader = 0;
        engineer = 0;
        this.credits = credits;
        this.ship = ship;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSkillpoints() {
        return skillpoints;
    }

    public void setSkillpoints(int skillpoints) {
        this.skillpoints = skillpoints;
    }

    public int getPilot() {
        return pilot;
    }

    public void setPilot(int pilot) {
        this.pilot = pilot;
    }

    public int getFighter() {
        return fighter;
    }

    public void setFighter(int fighter) {
        this.fighter = fighter;
    }

    public int getTrader() {
        return trader;
    }

    public void setTrader(int trader) {
        this.trader = trader;
    }

    public int getEngineer() {
        return engineer;
    }

    public void setEngineer(int engineer) {
        this.engineer = engineer;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public Spaceship getShip() {
        return ship;
    }

    public void setShip(Spaceship ship) {
        this.ship = ship;
    }
}
