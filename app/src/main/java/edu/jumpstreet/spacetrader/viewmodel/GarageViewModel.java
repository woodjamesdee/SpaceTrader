package edu.jumpstreet.spacetrader.viewmodel;

import android.arch.lifecycle.ViewModel;

import edu.jumpstreet.spacetrader.entity.Spaceship;
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
        Model model = Model.getInstance();
        interactor = model.getPlayerInteractor();
    }

    /**
     * Gets the max fuel value of the Player's ship.
     * @return   the max fuel
     */
    public int getMaxFuel() {
        Spaceship ship = interactor.getPlayerShip();
        return ship.getMaxFuel();
    }

    /**
     * Gets the remaining fuel value of the Player's ship.
     * @return the remaining fuel
     */
    public int getRemainingFuel() {
        Spaceship ship = interactor.getPlayerShip();
        return ship.getRemainingFuel();
    }
}
