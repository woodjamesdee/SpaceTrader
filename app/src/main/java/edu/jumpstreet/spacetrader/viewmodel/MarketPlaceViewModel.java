package edu.jumpstreet.spacetrader.viewmodel;

import android.arch.lifecycle.ViewModel;

import edu.jumpstreet.spacetrader.entity.Commodity;
import edu.jumpstreet.spacetrader.entity.Economy;
import edu.jumpstreet.spacetrader.entity.Planet;
import edu.jumpstreet.spacetrader.entity.Spaceship;
import edu.jumpstreet.spacetrader.entity.System;
import edu.jumpstreet.spacetrader.model.GameInteractor;
import edu.jumpstreet.spacetrader.model.Model;
import edu.jumpstreet.spacetrader.model.PlayerInteractor;

/**
 * Provides backend functionality for the MarketPlaceActivity.
 */
public class MarketPlaceViewModel extends ViewModel {

    private final PlayerInteractor interactor;
    private final Spaceship ship;
    private final Planet planet;

    /**
     * Creates a new MarketPlaceViewModel.
     */
    MarketPlaceViewModel() {
        Model model = Model.getInstance();
        interactor = model.getPlayerInteractor();
        ship = interactor.getPlayerShip();
        GameInteractor gi = model.getGameInteractor();
        planet = gi.getActivePlanet();
    }

    /**
     * Gets the balance of the Player's account.
     * @return the balance
     */
    public int getPlayerCredits() { return interactor.getPlayerBalance(); }

    /**
     * Gets the ordinal of the current Planet's TechLevel.
     * @return  the ordinal
     */
    public int getTechLevelOrdinal() {
        System.TechLevel techLevel = planet.getTechLevel();
        return techLevel.ordinal();
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
     * Gets the resource quantity of the ship
     * @param index the index to check
     * @return  the resource quantity
     */
    public int getShipResourceQuantityByIndex(int index)
    { return ship.getResourceQuantityByIndex(index); }

    /**
     * Gets the resource quantity of the planet
     * @param index the index to check
     * @return  the resource quantity
     */
    public int getPlanetResourceQuantityByIndex(int index)
    { return planet.getIndexedResourceQuantity(index); }

    /**
     * Gets the value of the commodity at the given index
     * @param index the index to check
     *
     * @return the value
     */
    public int getCommodityValue(int index) {
        Economy economy = planet.getEconomy();
        return economy.getCommodityValue(index);
    }

    /**
     * Gets the Commodity at the given index
     * @param index the index to get
     * @return  the commodity
     */
    public Commodity getCommodity(int index) {
        Economy economy = planet.getEconomy();
        return economy.getCommodity(index);
    }

}
