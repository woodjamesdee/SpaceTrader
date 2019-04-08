package edu.jumpstreet.spacetrader.viewmodel;

import android.arch.lifecycle.ViewModel;

import edu.jumpstreet.spacetrader.entity.SolarSystem;
import edu.jumpstreet.spacetrader.model.GameInteractor;
import edu.jumpstreet.spacetrader.model.Model;
import edu.jumpstreet.spacetrader.model.UniverseInteractor;

/**
 * Provides functionality for the UniverseActivity.
 */
public class UniverseViewModel extends ViewModel {

    private final GameInteractor interactor;

    /**
     * Creates a new UniverseViewModel.
     */
    UniverseViewModel() {
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
        SolarSystem system = interactor.getActiveSolarSystem();
        return system.getX();
    }

    /**
     * Get the y coordinate of the active SolarSystem
     * @return the y-coordinate
     */
    public int getActiveSolarSystemY() {
        SolarSystem system = interactor.getActiveSolarSystem();
        return system.getY();
    }

    /**
     * Gets the SolarSystem with the requested name
     * @param name name of the SolarSystem
     * @return  the SolarSystem
     */
    public SolarSystem getSolarSystemByName(String name) {
        Model model = Model.getInstance();
        UniverseInteractor ui = model.getUniverseInteractor();
        return ui.getSolarSystemByName(name);
    }

    /**
     * Sets the next SolarSystem to the given one
     * @param system the next SolarSystem
     */
    public void setNextSolarSystem(SolarSystem system) {
        interactor.setNextSolarSystem(system);
    }
}
