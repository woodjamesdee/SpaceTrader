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

    private GameInteractor interactor;

    /**
     * Creates a new PlanetViewModel.
     */
    public PlanetViewModel() {
        interactor = Model.getInstance().getGameInteractor();
    }

    /**
     * Gets the name of the active planet.
     * @return  the name
     */
    public String getActivePlanetName() {
        return interactor.getActivePlanet().getName();
    }

    /**
     * Gets the TechLevel of the active planet.
     * @return  the TechLevel
     */
    public System.TechLevel getActivePlanetTechLevel() {
        return interactor.getActivePlanet().getTechLevel();
    }

    /**
     * Gets the Resource of the active planet.
     * @return the TechLevel
     */
    public Planet.Resource getActivePlanetResource() {
        return interactor.getActivePlanet().getResource();
    }
}
