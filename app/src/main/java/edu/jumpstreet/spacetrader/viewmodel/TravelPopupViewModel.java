package edu.jumpstreet.spacetrader.viewmodel;

import android.arch.lifecycle.ViewModel;

import edu.jumpstreet.spacetrader.entity.Planet;
import edu.jumpstreet.spacetrader.entity.SolarSystem;
import edu.jumpstreet.spacetrader.entity.Spaceship;
import edu.jumpstreet.spacetrader.model.GameInteractor;
import edu.jumpstreet.spacetrader.model.Model;
import edu.jumpstreet.spacetrader.model.PlayerInteractor;

/**
 * Provides functionality for the TravelPopup
 */
public class TravelPopupViewModel extends ViewModel {

    private final GameInteractor interactor;
    private final PlayerInteractor PI;

    /**
     * Creates a new TravelPopupViewModel.
     */
    TravelPopupViewModel() {
        interactor = Model.getInstance().getGameInteractor();
        PI = Model.getInstance().getPlayerInteractor();
    }

    /**
     * Gets the piloting skill of the Player.
     * @return  the piloting skill
     */
    public int getPlayerPilotSkill() {
        return PI.getPlayerPilotSkill();
    }

    /**
     * Gets the isSolarSystemTravel attribute.
     * @return isSolarSystemTravel
     */
    public boolean isSolarSystemTravel() {
        return interactor.getIsNextSolarSystemTravel();
    }

    /**
     * Gets the Player's Ship
     * @return  the ship
     */
    public Spaceship getPlayerShip() {
        return PI.getPlayerShip();
    }

    /**
     * Gets the active Planet
     * @return active Planet
     */
    public Planet getActivePlanet() {
        return interactor.getActivePlanet();
    }

    /**
     * Get the next SolarSystem
     * @return the next SolarSystem
     */
    public SolarSystem getNextSolarSystem() {
        return interactor.getNextSolarSystem();
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
     * Gets the next Planet.
     * @return the next Planet
     */
    public Planet getNextPlanet() {
        return interactor.getNextPlanet();
    }

    /**
     * Changes the active SolarSystem to the one with the given name
     * @param name name of the new SolarSystem
     */
    public void changeActiveSolarSystem(String name) {
        interactor.changeActiveSolarSystem(name);
    }

    /**
     * Changes the active Planet to the one with the given name
     * @param name name of the new Planet
     */
    public void changeActivePlanet(String name) {
        interactor.changeActivePlanet(name);
    }
}
