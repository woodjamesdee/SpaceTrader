package edu.jumpstreet.spacetrader.entity;

import java.io.Serializable;

/**
 * sets up a unique game profile
 */
public class Game implements Serializable {

    private SolarSystem activeSolarSystem;
    private SolarSystem nextSolarSystem;
    private Planet activePlanet;
    private Planet nextPlanet;
    private GameDifficulty difficulty;
    private boolean isSolarSystemTravel;

    public enum GameDifficulty {
        BEGINNER, EASY, NORMAL, HARD, IMPOSSIBLE
    }

    /**
     * Game constructor
     * @param startingSystem the solar system
     * @param startingPlanet the beginning planet
     * @param difficulty game difficulty
     */
    public Game(SolarSystem startingSystem, Planet startingPlanet, GameDifficulty difficulty) {
        activeSolarSystem = startingSystem;
        //nextSolarSystem = null;
        activePlanet = startingPlanet;
        //nextPlanet = null;
        this.difficulty = difficulty;
        isSolarSystemTravel = true;
    }

    /**
     * active solar system getter
     * @return current SolarSystem
     */
    public SolarSystem getActiveSolarSystem() {
        return activeSolarSystem;
    }

    /**
     * next solar system getter
     * @return next SolarSystem
     */
    public SolarSystem getNextSolarSystem() { return nextSolarSystem; }

    /**
     * current solar system setter
     * @param activeSolarSystem current solar system item
     */
    public void setActiveSolarSystem(SolarSystem activeSolarSystem) {
        this.activeSolarSystem = activeSolarSystem;
    }

    /**
     * next solar system setter
     * @param nextSolarSystem the next solar system item
     */
    public void setNextSolarSystem(SolarSystem nextSolarSystem) {
        this.nextSolarSystem = nextSolarSystem;
    }

    /**
     * current planet getter
     * @return the current planet associated with the game profile
     */
    public Planet getActivePlanet() {
        return activePlanet;
    }

    /**
     * next planet getter
     * @return the next planet to go to
     */
    public Planet getNextPlanet() { return nextPlanet; }

    /**
     * active planet setter
     * @param activePlanet sets to this planet
     */
    public void setActivePlanet(Planet activePlanet) {
        this.activePlanet = activePlanet;
    }

    /**
     * next planet setter
     * @param nextPlanet sets the next planet to given planet
     */
    public void setNextPlanet(Planet nextPlanet) {
        this.nextPlanet = nextPlanet;
    }

    /*
     * difficulty getter
     * @return the desired game difficulty
     */
    //public GameDifficulty getDifficulty() {
    //    return difficulty;
    //}

    /*
     * difficulty setter
     * @param difficulty sets the difficulty to the desired game difficulty
     */
    //public void setDifficulty(GameDifficulty difficulty) {
    //    this.difficulty = difficulty;
    //}

    /**
     * set if the next solar system is available for travel
     * @param value if it is ok to travel
     */
    public void setIsNextSolarSystemTravel(boolean value) { this.isSolarSystemTravel = value; }

    /**
     * getter for if the next solar system is available for travel
     * @return if it is ok to travel to the next solar system
     */
    public boolean getIsNextSolarSystemTravel() { return isSolarSystemTravel; }
}
