package edu.jumpstreet.spacetrader.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Random;

import edu.jumpstreet.spacetrader.entity.Planet;
import edu.jumpstreet.spacetrader.entity.SolarSystem;
import edu.jumpstreet.spacetrader.entity.Universe;

/**
 * Handles the usage of the Universe and all of its components.
 */
public class UniverseInteractor implements Serializable {

    /*
     * Gets the Universe of this UniverseInteractor
     * @return  the universe
     */
    //public Universe getUniverse() {
    //    return universe;
    //}

    private Universe universe;

    /**
     * Creates a new UniverseInteractor
     * @param random the randomizer to create the universe
     */
    UniverseInteractor(Random random) {
        universe = new Universe();
        generateSolarSystems(random, random.nextInt(Universe.MAX_SYSTEMS - Universe.MIN_SYSTEMS) + Universe.MIN_SYSTEMS);
        generatePlanets(random);
    }

    /**
     * Gets the SolarSystems within the Universe
     * @return the SolarSystems
     */
    Collection<SolarSystem> getSolarSystems() {
        return universe.getSolarSystems().values();
    }

    /**
     * Gets the SolarSystem object based on the inputted name,
     * @param name the name of the SolarSystem
     * @return  the SolarSystem with the name.
     */
    public SolarSystem getSolarSystemByName(String name) {
        return universe.getSolarSystemWithName(name);
    }

    /**
     * Used when loading the game, updates the positions of objects in the universe.
     */
    void updateUniverseOnLoad() {
        Universe.updateOnLoad(universe);
    }

    /**
     * Helper to generate the SolarSystems
     * @param random    the randomizer to use
     * @param amount    the number of systems to create
     */
    private void generateSolarSystems(Random random, int amount) {
        if (amount > 100) {
            return;
        }
        for (int i = 0; i < amount; i++) {
            SolarSystem current = new SolarSystem(random.nextInt(SolarSystem.getNamesLength()), random.nextInt(Universe.X_BOUNDS), random.nextInt(Universe.Y_BOUNDS), random.nextInt(SolarSystem.TechLevel.values().length));
            universe.addSolarSystem(current);
        }
    }

    /**
     * Helper to generate the Planets
     * @param random    the randomizer to use
     */
    private void generatePlanets(Random random) {
        for (SolarSystem currentSystem : universe.getSolarSystems().values()) {
            for (int i = 0; i < random.nextInt(Universe.MAX_SYSTEMS - Universe.MIN_SYSTEMS) + Universe.MIN_SYSTEMS; i++) {
                currentSystem.addNewPlanet(random.nextInt(Universe.X_BOUNDS), random.nextInt(Universe.Y_BOUNDS), random.nextInt(Planet.Resource.values().length));
            }
        }
    }

}
