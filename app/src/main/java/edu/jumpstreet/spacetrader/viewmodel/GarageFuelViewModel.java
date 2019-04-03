package edu.jumpstreet.spacetrader.viewmodel;

import android.arch.lifecycle.ViewModel;

import edu.jumpstreet.spacetrader.model.Model;
import edu.jumpstreet.spacetrader.model.PlayerInteractor;

public class GarageFuelViewModel extends ViewModel {
    private PlayerInteractor interactor;

    /**
     * Creates a new GarageFuelViewModel.
     */
    public GarageFuelViewModel() {
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

    /**
     * Gets the balance of the Player's account.
     * @return the balance
     */
    public int getPlayerCredits() { return interactor.getPlayerBalance(); }

    /**
     * Sets the Player's balance to the given amount.
     * @param value the new balance
     */
    public void setPlayerCredits(int value) { interactor.getPlayer().setCredits(value); }

    /**
     * Sets the Player Ship's remaining fuel to the given amount.
     * @param value the new remaining fuel
     */
    public void setRemainingFuel(int value) { interactor.getPlayer().setCredits(value); }
}
