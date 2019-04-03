package edu.jumpstreet.spacetrader.model;

import java.io.Serializable;

import edu.jumpstreet.spacetrader.entity.Game;
import edu.jumpstreet.spacetrader.entity.Planet;
import edu.jumpstreet.spacetrader.entity.SolarSystem;

public class GameInteractor implements Serializable {

    private Game game;

    public GameInteractor(UniverseInteractor universeInteractor) {
        SolarSystem system = universeInteractor.getSolarSystems().iterator().next();
        game = new Game(system, system.getPlanet(system.getName() + " Prime"), Game.GameDifficulty.NORMAL);
    }

    public SolarSystem getActiveSolarSystem() {
        return game.getActiveSolarSystem();
    }

    public Planet getActivePlanet() {
        return game.getActivePlanet();
    }

    public void changeActiveSolarSystem(String name) {
        if (name == null || name.equals("")) {
            return;
        }
        game.setActiveSolarSystem(Model.getInstance().getUniverseInteractor().getSolarSystemByName(name));
        game.setActivePlanet(Model.getInstance().getGameInteractor().getActiveSolarSystem().getPlanet(Model.getInstance().getGameInteractor().getActiveSolarSystem().getName() + " Prime"));
    }

    public void changeActivePlanet(String name) {
        if (name == null || name.equals("")) {
            return;
        }
        game.setActivePlanet(game.getActiveSolarSystem().getPlanet(name));
    }

    public void setNextPlanet(Planet planet) {
        game.setNextPlanet(planet);
    }

    public void setNextSolarSystem(SolarSystem system) {
        game.setNextSolarSystem(system);
    }

    public Planet getNextPlanet() {
        return game.getNextPlanet();
    }

    public SolarSystem getNextSolarSystem() {
        return game.getNextSolarSystem();
    }

    public void setIsSolarSystemTravel(boolean value) {
        game.setIsNextSolarSystemTravel(value);
    }

    public boolean getIsNextSolarSystemTravel() {
        return game.getIsNextSolarSystemTravel();
    }
}

