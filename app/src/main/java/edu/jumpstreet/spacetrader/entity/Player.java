package edu.jumpstreet.spacetrader.entity;

import java.io.Serializable;

/**
 * The Player entity represents the human player and the attributes that are closely related.
 * This class should not EVER be instantiated outside of a PlayerInteractor.
 */
public class Player implements Serializable {

    private String name;
    private int skillpoints;
    private int pilot;
    private int fighter;
    private int trader;
    private int engineer;
    private int credits;
    private Spaceship ship;

    /**
     * Player constructor
     * @param name String representing the name of the player
     * @param skillpoints int representing the available skillpoints
     * @param credits int representing the amount of money a player has
     * @param ship Spaceship object representing the spaceship the player is using
     */
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

    /**
     * name getter
     * @return String name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * name setter
     * @param name string name the player wants
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * skill points getter
     * @return the amount of skillpoints the player has
     */
    public int getSkillpoints() {
        return skillpoints;
    }

    /**
     * skill points setter
     * @param skillpoints the amount of skill points the player has left
     */
    public void setSkillpoints(int skillpoints) {
        this.skillpoints = skillpoints;
    }

    /**
     * pilot skill getter
     * @return int associated with pilot skill
     */
    public int getPilot() {
        return pilot;
    }

    /**
     * pilot skill setter
     * @param pilot sets the pilot skill to this int
     */
    public void setPilot(int pilot) {
        this.pilot = pilot;
    }

    /**
     * fighter skill getter
     * @return int that represents fighter skill
     */
    public int getFighter() {
        return fighter;
    }

    /**
     * fighter skill setter
     * @param fighter sets the fighter skill to this int
     */
    public void setFighter(int fighter) {
        this.fighter = fighter;
    }

    /**
     * trader skill getter
     * @return int that represents trader skill
     */
    public int getTrader() {
        return trader;
    }

    /**
     * trader skill setter
     * @param trader sets the trader skill to this int
     */
    public void setTrader(int trader) {
        this.trader = trader;
    }

    /**
     * engineer skill getter
     * @return int that represents engineer skill
     */
    public int getEngineer() {
        return engineer;
    }

    /**
     * engineer skill setter
     * @param engineer sets the engineer skill to this int
     */
    public void setEngineer(int engineer) {
        this.engineer = engineer;
    }

    /**
     * credits getter
     * @return how many credits (money) character has
     */
    public int getCredits() {
        return credits;
    }

    /**
     * credits setter
     * @param credits how many credits the character has left
     */
    public void setCredits(int credits) {
        this.credits = credits;
    }

    /**
     * ship getter
     * @return Spaceship item that represents the ship this player has
     */
    public Spaceship getShip() {
        return ship;
    }

    /**
     * ship setter
     * @param ship sets the Spaceship this player has to given ship
     */
    public void setShip(Spaceship ship) {
        this.ship = ship;
    }
}
