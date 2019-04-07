package edu.jumpstreet.spacetrader.viewmodel;

import android.arch.lifecycle.ViewModel;

import edu.jumpstreet.spacetrader.model.Model;
import edu.jumpstreet.spacetrader.model.PlayerInteractor;

/**
 * sets up garage ViewModel
 */
public class GarageViewModel extends ViewModel {

    private final PlayerInteractor interactor;

    /**
     * Creates a new GarageViewModel.
     */
    GarageViewModel() {
        interactor = Model.getInstance().getPlayerInteractor();
    }

    /**
     * Gets the max fuel value of the Player's ship.
     * @return   the max fuel
     */
    public int getMaxFuel() { return interactor.getPlayerShip().getMaxFuel(); }

    /**
     * Gets the remaining fuel value of the Player's ship.
     * @return the remaining fuel
     */
    public int getRemainingFuel() { return interactor.getPlayerShip().getRemainingFuel(); }
}
