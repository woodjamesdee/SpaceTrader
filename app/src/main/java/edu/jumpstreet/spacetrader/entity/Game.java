package edu.jumpstreet.spacetrader.entity;

import java.io.Serializable;

import edu.jumpstreet.spacetrader.entity.Planet;
import edu.jumpstreet.spacetrader.entity.SolarSystem;

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

    public Game(SolarSystem startingSystem, Planet startingPlanet, GameDifficulty difficulty) {
        activeSolarSystem = startingSystem;
        nextSolarSystem = null;
        activePlanet = startingPlanet;
        nextPlanet = null;
        this.difficulty = difficulty;
        isSolarSystemTravel = true;
    }

    public SolarSystem getActiveSolarSystem() {
        return activeSolarSystem;
    }

    public SolarSystem getNextSolarSystem() { return nextSolarSystem; }

    public void setActiveSolarSystem(SolarSystem activeSolarSystem) {
        this.activeSolarSystem = activeSolarSystem;
    }

    public void setNextSolarSystem(SolarSystem nextSolarSystem) {
        this.nextSolarSystem = nextSolarSystem;
    }

    public Planet getActivePlanet() {
        return activePlanet;
    }

    public Planet getNextPlanet() { return nextPlanet; }

    public void setActivePlanet(Planet activePlanet) {
        this.activePlanet = activePlanet;
    }

    public void setNextPlanet(Planet nextPlanet) {
        this.nextPlanet = nextPlanet;
    }

    public GameDifficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(GameDifficulty difficulty) {
        this.difficulty = difficulty;
    }

    public void setIsNextSolarSystemTravel(boolean value) { this.isSolarSystemTravel = value; }

    public boolean getIsNextSolarSystemTravel() { return isSolarSystemTravel; }
}
