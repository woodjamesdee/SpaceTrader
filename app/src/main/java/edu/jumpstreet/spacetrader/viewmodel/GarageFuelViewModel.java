package edu.jumpstreet.spacetrader.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.widget.Space;

import edu.jumpstreet.spacetrader.entity.Player;
import edu.jumpstreet.spacetrader.entity.Spaceship;
import edu.jumpstreet.spacetrader.model.Model;
import edu.jumpstreet.spacetrader.model.PlayerInteractor;

/**
 * ViewModel for GarageFuel view
 */
public class GarageFuelViewModel extends ViewModel {
    private final PlayerInteractor interactor;

    /**
     * Creates a new GarageFuelViewModel.
     */
    GarageFuelViewModel() {
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

    /**
     * Gets the balance of the Player's account.
     * @return the balance
     */
    public int getPlayerCredits() { return interactor.getPlayerBalance(); }

    /**
     * Sets the Player's balance to the given amount.
     * @param value the new balance
     */
    public void setPlayerCredits(int value) {
        Player player = interactor.getPlayer();
        player.setCredits(value);
    }

    /**
     * Sets the Player Ship's remaining fuel to the given amount.
     * @param value the new remaining fuel
     */
    public void setRemainingFuel(int value) {
        Spaceship ship = interactor.getPlayerShip();
        ship.setRemainingFuel(value);
    }
}
