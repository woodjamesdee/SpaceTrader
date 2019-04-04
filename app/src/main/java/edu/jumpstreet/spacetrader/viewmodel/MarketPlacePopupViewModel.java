package edu.jumpstreet.spacetrader.viewmodel;

import android.arch.lifecycle.ViewModel;

import edu.jumpstreet.spacetrader.entity.Commodity;
import edu.jumpstreet.spacetrader.entity.Planet;
import edu.jumpstreet.spacetrader.entity.Spaceship;
import edu.jumpstreet.spacetrader.model.Model;
import edu.jumpstreet.spacetrader.model.PlayerInteractor;

/**
 * Handles all of the backend functionality for the MarketPlacePopupActivity
 */
public class MarketPlacePopupViewModel extends ViewModel {

    private PlayerInteractor interactor;
    private Spaceship ship;
    private Planet planet;

    /**
     * Creates a new MarketPlacePopupViewModel
     */
    MarketPlacePopupViewModel() {
        interactor = Model.getInstance().getPlayerInteractor();
        ship = Model.getInstance().getPlayerInteractor().getPlayerShip();
        planet = Model.getInstance().getGameInteractor().getActivePlanet();
    }

    /**
     * Adds the specified amount of credits to the Player's balance.
     * @param amount    the amount of credits to add
     */
    public void addCreditsToPlayerBalance(int amount) {
        interactor.addCreditsToPlayerBalance(amount);
    }

    /**
     * Gets the used cargo space within the Player's ship
     * @return  the used cargo space
     */
    public int getUsedCargoSpace() { return ship.getUsedCargoSpace(); }

    /**
     * Gets the max cargo space of the Player's ship
     * @return  the max cargo space
     */
    public int getMaxCargoSpace() { return ship.getMaxCargoSpace(); }

    /**
     * Gets the resource quantity by it's name.
     * @param name  the name of the resource
     * @return  the quantity of the resource
     */
    public int getShipResourceQuantityByName(String name) {
        return ship.getQuantityByName(name);
    }

    /**
     * Sets the quantity of the resource by its name
     * @param name  the name of the resource
     * @param value the new quantity
     */
    public void setShipResourceQuantityByName(String name, int value) {
        ship.setResourceQuantityByName(name, value);
    }

    /**
     * Sets the quantity of the resource by its name
     * @param name  the name of the resource
     * @param value the new quantity
     */
    public void setPlanetResourceQuantityByName(String name, int value) {
        planet.setResourceQuantityByName(name, value);
    }

    /**
     * Gets the value of the commodity given
     * @param commodity the commodity to check
     *
     * @return the value
     */
    public int getCommodityValue(Commodity commodity) { return planet.getEconomy().getCommodityValue(commodity); }

    /**
     * Gets the ordinal of the current Planet's TechLevel.
     * @return  the ordinal
     */
    public int getTechLevelOrdinal() { return planet.getTechLevel().ordinal(); }

    /**
     * Sets the amount of used cargo space in the Ship.
     * @param amount    the new amount
     */
    public void setUsedCargoSpace(int amount) {
        ship.setUsedCargoSpace(amount);
    }

}
