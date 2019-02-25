package edu.jumpstreet.spacetrader.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * The Universe entity models a collection of SolarSystems.
 */
public class Universe {

    public static final int X_BOUNDS = 150;
    public static final int Y_BOUNDS = 100;

    Map<String, SolarSystem> solarSystems;

    public Universe() {
        solarSystems = new HashMap<>();
    }

    public void addSolarSystem(SolarSystem system) {
        solarSystems.put(system.getName(), system);
    }

    public void removeSolarSystem(SolarSystem system) {
        solarSystems.remove(system.getName());
    }

}
