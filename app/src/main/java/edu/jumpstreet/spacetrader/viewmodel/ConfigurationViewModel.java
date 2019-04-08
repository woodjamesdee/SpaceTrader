package edu.jumpstreet.spacetrader.viewmodel;

import android.arch.lifecycle.ViewModel;

import edu.jumpstreet.spacetrader.entity.Player;
import edu.jumpstreet.spacetrader.model.Model;
import edu.jumpstreet.spacetrader.model.PlayerInteractor;

/**
 * Handles all of the operations between the ConfigurationActivity and the model elements.
 */
public class ConfigurationViewModel extends ViewModel {

    private final PlayerInteractor interactor;

    /**
     * Creates a new ConfigurationViewModel.
     */
    ConfigurationViewModel() {
        Model model = Model.getInstance();
        interactor = model.getPlayerInteractor();
    }

    /**
     * Allows the pilot skill to be increased or decreased.
     * @param increase true if increase, false if decrease
     */
    public void updatePilotSkill(boolean increase) {
        try {
            if (increase) {
                interactor.addPlayerPilotSkill(1);
            } else {
                interactor.addPlayerPilotSkill(-1);
            }
        } catch (IllegalArgumentException e) {
            //System.err.println("Attempt to allocate pilot skill failed!");
            e.printStackTrace();
        }

    }

    /**
     * Allows the fighting skill to be increased or decreased.
     * @param increase true if increase, false if decrease
     */
    public void updateFightingSkill(boolean increase) {
        try {
            if (increase) {
                interactor.addPlayerFighterSkill(1);
            } else {
                interactor.addPlayerFighterSkill(-1);
            }
        } catch (IllegalArgumentException e) {
            //System.err.println("Attempt to allocate fighting skill failed!");
            e.printStackTrace();
        }

    }

    /**
     * Allows the trading skill to be increased or decreased.
     * @param increase true if increase, false if decrease
     */
    public void updateTradingSkill(boolean increase) {
        try {
            if (increase) {
                interactor.addPlayerTraderSkill(1);
            } else {
                interactor.addPlayerTraderSkill(-1);
            }
        } catch(IllegalArgumentException e) {
            //System.err.println("Attempt to allocate trading skill failed!");
            e.printStackTrace();
        }

    }

    /**
     * Allows the engineering skill to be increased or decreased.
     * @param increase true if increase, false if decrease
     */
    public void updateEngineeringSkill(boolean increase) {
        try {
            if (increase) {
                interactor.addPlayerEngineerSkill(1);
            } else {
                interactor.addPlayerEngineerSkill(-1);
            }
        } catch (IllegalArgumentException e) {
            //System.err.println("Attempt to allocate engineering skill failed!");
            e.printStackTrace();
        }

    }

    /**
     * Finds if the player has additional skillpoints left to allocate.
     * @return true if the player can allocate more points, false if not
     */
    public boolean plusShouldBeEnabled() {
        return interactor.playerHasSkillpointsLeft();
    }

    /**
     * Finds if the player has pilot points to remove.
     * @return true if the player does, false if the player does not
     */
    public boolean pilotMinusShouldBeEnabled() {
        return interactor.playerHasPilotPointsLeft();
    }

    /**
     * Finds if the player has fighter points to remove.
     * @return true if the player does, false if the player does not
     */
    public boolean fighterMinusShouldBeEnabled() {
        return interactor.playerHasFighterPointsLeft();
    }

    /**
     * Finds if the player has trader points to remove.
     * @return true if the player does, false if the player does not
     */
    public boolean traderMinusShouldBeEnabled() {
        return interactor.playerHasTraderPointsLeft();
    }

    /**
     * Finds if the player has engineering points to remove.
     * @return true if the player does, false if the player does not
     */
    public boolean engineerMinusShouldBeEnabled() {
        return interactor.playerHasEngineerPointsLeft();
    }

    /**
     * Gets the number of unallocated skillpoints for the Player.
     * @return the number of unallocated points
     */
    public int getUnallocatedPoints() { return interactor.getPlayerUnallocatedSkillpoints(); }

    /**
     * Gets the name of the Player
     * @return player name
     */
    public String getPlayerName() { return interactor.getPlayerName(); }

    /**
     * Sets the name of the Player
     * @param name the new name of the player
     */
    public void setPlayerName(String name) {
        Player player = interactor.getPlayer();
        player.setName(name);
    }

    /**
     * Gets the piloting skill of the player
     * @return the pilot skill
     */
    public int getPlayerPilotSkill() { return interactor.getPlayerPilotSkill(); }

    /**
     * Gets the fighter skill of the player
     * @return the fight skill
     */
    public int getPlayerFighterSkill() { return interactor.getPlayerFighterSkill(); }

    /**
     * Gets the trader skill of the player
     * @return the trade skill
     */
    public int getPlayerTraderSkill() { return interactor.getPlayerTraderSkill(); }

    /**
     * Gets the engineer skill of the player
     * @return the engineer skill
     */
    public int getPlayerEngineerSkill() { return interactor.getPlayerEngineerSkill(); }
}
