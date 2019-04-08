package edu.jumpstreet.spacetrader.viewmodel;

import android.arch.lifecycle.ViewModel;

import edu.jumpstreet.spacetrader.entity.Planet;
import edu.jumpstreet.spacetrader.entity.SolarSystem;
import edu.jumpstreet.spacetrader.entity.System;
import edu.jumpstreet.spacetrader.model.GameInteractor;
import edu.jumpstreet.spacetrader.model.Model;

/**
 * Provides functionality for the SolarSystemActivity
 */
public class SolarSystemViewModel extends ViewModel {

    private final GameInteractor interactor;

    /**
     * Creates a new SolarSystemViewModel.
     */
    SolarSystemViewModel() {
        Model model = Model.getInstance();
        interactor = model.getGameInteractor();
    }

    /**
     * Gets the active SolarSystem
     * @return active SolarSystem
     */
    public SolarSystem getActiveSolarSystem() {
        return interactor.getActiveSolarSystem();
    }

    /**
     * Gets the name of the active SolarSystem
     * @return the name
     */
    public String getActiveSolarSystemName() {
        SolarSystem solarSystem = interactor.getActiveSolarSystem();
        return solarSystem.getName();
    }

    /**
     * Gets the TechLevel of the active SolarSystem
     * @return the TechLevel
     */
    public System.TechLevel getTechLevel() {
        SolarSystem solarSystem = interactor.getActiveSolarSystem();
        return solarSystem.getTechLevel();
    }

    /**
     * Gets the active Planet
     * @return active Planet
     */
    public Planet getActivePlanet() {
        return interactor.getActivePlanet();
    }

    /**
     * Gets the X coordinate of the active planet
     * @return the x-coordinate
     */
    public int getActivePlanetX() {
        Planet planet = interactor.getActivePlanet();
        return planet.getX();
    }

    /**
     * Gets the Y coordinate of the active planet
     * @return the y-coordinate
     */
    public int getActivePlanetY() {
        Planet planet = interactor.getActivePlanet();
        return planet.getY();
    }

    /**
     * Gets a Planet by name from the Active SolarSystem
     * @param name  the name to get
     * @return the planet
     */
    public Planet getPlanet(String name) {
        SolarSystem solarSystem = interactor.getActiveSolarSystem();
        return solarSystem.getPlanet(name);
    }

    /**
     * Sets the next Planet attribute
     * @param next the next Planet
     */
    public void setNextPlanet(Planet next) {
        interactor.setNextPlanet(next);
    }

    /**
     * Sets the isSolarSystemTravel attribute
     * @param value the new value
     */
    public void setIsSolarSystemTravel(boolean value) {
        interactor.setIsSolarSystemTravel(value);
    }
}
