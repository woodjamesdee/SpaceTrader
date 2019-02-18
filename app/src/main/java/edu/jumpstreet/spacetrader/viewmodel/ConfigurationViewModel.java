package edu.jumpstreet.spacetrader.viewmodel;

import android.arch.lifecycle.ViewModel;

import edu.jumpstreet.spacetrader.model.Model;
import edu.jumpstreet.spacetrader.model.PlayerInteractor;

public class ConfigurationViewModel extends ViewModel {

    private PlayerInteractor interactor;

    public ConfigurationViewModel() {
        interactor = Model.getInstance().getPlayerInteractor();
    }

    public void updatePilotSkill(boolean increase) {
        try {
            if (increase) {
                interactor.addPlayerPilotSkill(1);
            } else {
                interactor.addPlayerPilotSkill(-1);
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Attempt to allocate pilot skill failed!");
        }

    }

    public void updateFightingSkill(boolean increase) {
        try {
            if (increase) {
                interactor.addPlayerFighterSkill(1);
            } else {
                interactor.addPlayerFighterSkill(-1);
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Attempt to allocate fighting skill failed!");
        }

    }

    public void updateTradingSkill(boolean increase) {
        try {
            if (increase) {
                interactor.addPlayerTraderSkill(1);
            } else {
                interactor.addPlayerTraderSkill(-1);
            }
        } catch(IllegalArgumentException e) {
            System.err.println("Attempt to allocate trading skill failed!");
        }

    }

    public void updateEngineeringSkill(boolean increase) {
        try {
            if (increase) {
                interactor.addPlayerEngineerSkill(1);
            } else {
                interactor.addPlayerEngineerSkill(-1);
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Attempt to allocate engineering skill failed!");
        }

    }

    public boolean plusShouldBeDisabled() {
        return interactor.playerHasSkillpointsLeft();
    }

    public boolean pilotMinusShouldBeDisabled() {
        return interactor.playerHasPilotPointsLeft();
    }

    public boolean fighterMinusShouldBeDisabled() {
        return interactor.playerHasFighterPointsLeft();
    }

    public boolean traderMinusShouldBeDisabled() {
        return interactor.playerHasTraderPointsLeft();
    }

    public boolean engineerMinusShouldBeDisabled() {
        return interactor.playerHasEngineerPointsLeft();
    }
}
