package edu.jumpstreet.spacetrader.model;

import android.util.Log;

import java.util.Collection;
import java.util.List;
import java.util.Random;

import edu.jumpstreet.spacetrader.entity.Planet;
import edu.jumpstreet.spacetrader.entity.SolarSystem;
import edu.jumpstreet.spacetrader.entity.Universe;

/**
 * Handles the usage of the Universe and all of its components.
 */
public class UniverseInteractor {

    public Universe getUniverse() {
        return universe;
    }

    private Universe universe;

    public UniverseInteractor(Random random) {
        universe = new Universe();
        generateSolarSystems(random, random.nextInt(Universe.MAX_SYSTEMS - Universe.MIN_SYSTEMS) + Universe.MIN_SYSTEMS);
        generatePlanets(random);
    }

    public Collection<SolarSystem> getSolarSystems() {
        return universe.getSolarSystems().values();
    }

    public SolarSystem getSolarSystemByName(String name) {
        return universe.getSolarSystemWithName(name);
    }

    private void generateSolarSystems(Random random, int amount) {
        if (amount > 100) {
            return;
        }
        for (int i = 0; i < amount; i++) {
            SolarSystem current = new SolarSystem(random.nextInt(SolarSystem.getNamesLength()), random.nextInt(Universe.X_BOUNDS), random.nextInt(Universe.Y_BOUNDS), random.nextInt(SolarSystem.TechLevel.values().length));
            universe.addSolarSystem(current);
        }
    }

    private void generatePlanets(Random random) {
        for (SolarSystem currentSystem : universe.getSolarSystems().values()) {
            for (int i = 0; i < random.nextInt(Universe.MAX_SYSTEMS - Universe.MIN_SYSTEMS) + Universe.MIN_SYSTEMS; i++) {
                currentSystem.addNewPlanet(random.nextInt(Universe.X_BOUNDS), random.nextInt(Universe.Y_BOUNDS), random.nextInt(Planet.Resource.values().length));
            }
        }
    }

}
