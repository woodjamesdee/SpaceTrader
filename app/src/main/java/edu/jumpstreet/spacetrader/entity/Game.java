package edu.jumpstreet.spacetrader.entity;

import edu.jumpstreet.spacetrader.entity.Planet;
import edu.jumpstreet.spacetrader.entity.SolarSystem;

public class Game {

    private SolarSystem activeSolarSystem;
    private Planet activePlanet;
    private GameDifficulty difficulty;

    public enum GameDifficulty {
        BEGINNER, EASY, NORMAL, HARD, IMPOSSIBLE
    }

    public enum ResourceCommodities{
        Water, Furs, Food, Ore, Games, Firearms, Medicine, Machines, Narcotics, Robots
    }

    public Game(SolarSystem startingSystem, Planet startingPlanet, GameDifficulty difficulty) {
        activeSolarSystem = startingSystem;
        activePlanet = startingPlanet;
        this.difficulty = difficulty;
    }

    public SolarSystem getActiveSolarSystem() {
        return activeSolarSystem;
    }

    public void setActiveSolarSystem(SolarSystem activeSolarSystem) {
        this.activeSolarSystem = activeSolarSystem;
    }

    public Planet getActivePlanet() {
        return activePlanet;
    }

    public void setActivePlanet(Planet activePlanet) {
        this.activePlanet = activePlanet;
    }

    public GameDifficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(GameDifficulty difficulty) {
        this.difficulty = difficulty;
    }

}
