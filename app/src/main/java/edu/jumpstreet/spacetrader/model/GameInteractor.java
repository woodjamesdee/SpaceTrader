package edu.jumpstreet.spacetrader.model;

import edu.jumpstreet.spacetrader.entity.Game;
import edu.jumpstreet.spacetrader.entity.Planet;
import edu.jumpstreet.spacetrader.entity.SolarSystem;

public class GameInteractor {

    private Game game;

    public GameInteractor() {
        SolarSystem system = Model.getInstance().getUniverseInteractor().getSolarSystems().iterator().next();
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
        game.setActivePlanet(null);
    }

    public void changeActivePlanet(String name) {
        if (name == null || name.equals("")) {
            return;
        }
        game.setActivePlanet(game.getActiveSolarSystem().getPlanet(name));
    }
}

