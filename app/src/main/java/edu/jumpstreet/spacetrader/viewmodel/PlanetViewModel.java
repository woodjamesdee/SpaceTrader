package edu.jumpstreet.spacetrader.viewmodel;

import android.arch.lifecycle.ViewModel;

import edu.jumpstreet.spacetrader.entity.Planet;
import edu.jumpstreet.spacetrader.entity.System;
import edu.jumpstreet.spacetrader.model.GameInteractor;
import edu.jumpstreet.spacetrader.model.Model;

/**
 * Provides functionality for the Planet activity.
 */
public class PlanetViewModel extends ViewModel {

    private final GameInteractor interactor;

    /**
     * Creates a new PlanetViewModel.
     */
    PlanetViewModel() {
        Model model = Model.getInstance();
        interactor = model.getGameInteractor();
    }

    /**
     * Gets the name of the active planet.
     * @return  the name
     */
    public String getActivePlanetName() {
        Planet planet = interactor.getActivePlanet();
        return planet.getName();
    }

    /**
     * Gets the TechLevel of the active planet.
     * @return  the TechLevel
     */
    public System.TechLevel getActivePlanetTechLevel() {
        Planet planet = interactor.getActivePlanet();
        return planet.getTechLevel();
    }

    /**
     * Gets the Resource of the active planet.
     * @return the TechLevel
     */
    public Planet.Resource getActivePlanetResource() {
        Planet planet = interactor.getActivePlanet();
        return planet.getResource();
    }
}
