package edu.jumpstreet.spacetrader.viewmodel;

import android.arch.lifecycle.ViewModel;

import edu.jumpstreet.spacetrader.entity.SolarSystem;
import edu.jumpstreet.spacetrader.model.GameInteractor;
import edu.jumpstreet.spacetrader.model.Model;

/**
 * Provides functionality for the UniverseActivity.
 */
public class UniverseViewModel extends ViewModel {

    private GameInteractor interactor;

    /**
     * Creates a new UniverseViewModel.
     */
    UniverseViewModel() {
        interactor = Model.getInstance().getGameInteractor();
    }

    /**
     * Gets the active SolarSystem
     * @return active SolarSystem
     */
    public SolarSystem getActiveSolarSystem() {
        return interactor.getActiveSolarSystem();
    }

    /**
     * Sets the isSolarSystemTravel attribute
     * @param value the new value
     */
    public void setIsSolarSystemTravel(boolean value) {
        interactor.setIsSolarSystemTravel(value);
    }

    /**
     * Get the x coordinate of the active SolarSystem
     * @return the x-coordinate
     */
    public int getActiveSolarSystemX() {
        return interactor.getActiveSolarSystem().getX();
    }

    /**
     * Get the y coordinate of the active SolarSystem
     * @return the y-coordinate
     */
    public int getActiveSolarSystemY() {
        return interactor.getActiveSolarSystem().getY();
    }

    /**
     * Gets the SolarSystem with the requested name
     * @param name name of the SolarSystem
     * @return  the SolarSystem
     */
    public SolarSystem getSolarSystemByName(String name) {
        return Model.getInstance().getUniverseInteractor().getSolarSystemByName(name);
    }

    /**
     * Sets the next SolarSystem to the given one
     * @param system the next SolarSystem
     */
    public void setNextSolarSystem(SolarSystem system) {
        interactor.setNextSolarSystem(system);
    }
}
