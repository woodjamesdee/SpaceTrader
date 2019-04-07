package edu.jumpstreet.spacetrader.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * The Universe entity models a collection of SolarSystems.
 */
public class Universe implements Serializable {

    public static final int X_BOUNDS = 10;
    public static final int Y_BOUNDS = 10;
    public static final int MIN_SYSTEMS = 10;
    public static final int MAX_SYSTEMS = 15;

    public static String[][] solarSystemLocations = new String[10][10];

    private Map<String, SolarSystem> solarSystems;

    /**
     * updates the universe on every load
     * @param toUpdate the universe to update to
     */
    public static void updateOnLoad(Universe toUpdate) {
        solarSystemLocations = new String[10][10];
        for (SolarSystem current : toUpdate.solarSystems.values()) {
            solarSystemLocations[current.getX()][current.getY()] = current.getName();
        }
    }

    /**
     * universe constructor
     */
    public Universe() {
        solarSystems = new HashMap<>();
    }

    /**
     * adds solar systems to the universe
     * @param system solar system to be added
     */
    public void addSolarSystem(SolarSystem system) {
        solarSystems.put(system.getName(), system);
        solarSystemLocations[system.getX()][system.getY()] = system.getName();
    }

    /*
    public void removeSolarSystem(SolarSystem system) {
        solarSystems.remove(system.getName());
        solarSystemLocations[system.getX()][system.getY()] = null;
    }
    */

    /**
     * solar system getter
     * @param name of the desired SolarSystem
     * @return SolarSystem of the associated name
     */
    public SolarSystem getSolarSystemWithName(String name){
        return solarSystems.get(name);
    }

    /**
     * solar system getter
     * @return Map<String, SolarSystem> </String,> that will represent a solarsystem
     *
     */
    public Map<String, SolarSystem> getSolarSystems() {
        return solarSystems;
    }

}
