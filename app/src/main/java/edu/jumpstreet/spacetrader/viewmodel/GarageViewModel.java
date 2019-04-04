package edu.jumpstreet.spacetrader.viewmodel;

import android.arch.lifecycle.ViewModel;

import edu.jumpstreet.spacetrader.model.Model;
import edu.jumpstreet.spacetrader.model.PlayerInteractor;

public class GarageViewModel extends ViewModel {

    private PlayerInteractor interactor;

    /**
     * Creates a new GarageViewModel.
     */
    public GarageViewModel() {
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