package edu.jumpstreet.spacetrader.model;

import java.io.Serializable;

import edu.jumpstreet.spacetrader.entity.Game;
import edu.jumpstreet.spacetrader.entity.Planet;
import edu.jumpstreet.spacetrader.entity.SolarSystem;

/**
 * The GameInteractor handles all operations for the Game.
 */
public class GameInteractor implements Serializable {

    private final Game game;

    /**
     * Creates a new GameInteractor, given a UniverseInteractor
     * @param universeInteractor the universeInteractor to use for picking the starting planet.
     */
    GameInteractor(UniverseInteractor universeInteractor) {
        SolarSystem system = universeInteractor.getSolarSystems().iterator().next();
        game = new Game(system, system.getPlanet(system.getName() + " Prime"),
                Game.GameDifficulty.NORMAL);
    }

    /**
     * Gets the current SolarSystem the player is within.
     * @return  the current SolarSystem
     */
    public SolarSystem getActiveSolarSystem() {
        return game.getActiveSolarSystem();
    }

    /**
     * Gets the current Planet of the Player.
     * @return  the current Planet
     */
    public Planet getActivePlanet() {
        return game.getActivePlanet();
    }

    /**
     * Sets the active SolarSystem to another one
     * @param name  the name of the new SolarSystem
     */
    public void changeActiveSolarSystem(String name) {
        if ((name == null) || "".equals(name)) {
            return;
        }
        game.setActiveSolarSystem(Model.getInstance().getUniverseInteractor()
                .getSolarSystemByName(name));
        game.setActivePlanet(Model.getInstance().getGameInteractor().getActiveSolarSystem()
                .getPlanet(Model.getInstance().getGameInteractor()
                        .getActiveSolarSystem().getName() + " Prime"));
    }

    /**
     * Sets the active Planet to another one
     * @param name  the name of the Planet
     */
    public void changeActivePlanet(String name) {
        if ((name == null) || "".equals(name)) {
            return;
        }
        game.setActivePlanet(game.getActiveSolarSystem().getPlanet(name));
    }

    /**
     * Sets the nextPlanet value of this Game
     * @param planet    the new nextPlanet
     */
    public void setNextPlanet(Planet planet) {
        game.setNextPlanet(planet);
    }

    /**
     * Sets the nextSolarSystem value of this Game
     * @param system    the new nextSolarSystem
     */
    public void setNextSolarSystem(SolarSystem system) {
        game.setNextSolarSystem(system);
    }

    /**
     * Gets the nextPlanet of the Game.
     * @return  the nextPlanet
     */
    public Planet getNextPlanet() {
        return game.getNextPlanet();
    }

    /**
     * Gets the nextSolarSystem of the Game.
     * @return the nextSolarSystem
     */
    public SolarSystem getNextSolarSystem() {
        return game.getNextSolarSystem();
    }

    /**
     * Sets the value of the boolean isSolarSystemTravel
     * @param value the new value
     */
    public void setIsSolarSystemTravel(boolean value) {
        game.setIsNextSolarSystemTravel(value);
    }

    /**
     * Gets the value of the boolean isSolarSystemTravel
     * @return the value
     */
    public boolean getIsNextSolarSystemTravel() {
        return game.getIsNextSolarSystemTravel();
    }
}

