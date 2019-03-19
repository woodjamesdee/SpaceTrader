package edu.jumpstreet.spacetrader.model;

import edu.jumpstreet.spacetrader.entity.Gnat;
import edu.jumpstreet.spacetrader.entity.Player;
import edu.jumpstreet.spacetrader.entity.Spaceship;

/**
 * Provides operations on the data contained in the Player entity
 *
 * Interfaces between the Model and the ViewModel
 */
public class PlayerInteractor {

    private Player player;

    /**
     * Creates a new PlayerInteractor, which creates a new Player with default attributes
     */
    public PlayerInteractor() {
        this.player = new Player("Unknown", 16, 1000, new Gnat());
    }

    /**
     * Gets the name of the stored Player.
     * @return name
     */
    public String getPlayerName() {
        return player.getName();
    }

    /**
     * Changes the name of the stored Player.
     * @param name  the new name
     */
    public void changePlayerName(String name) {
        if (name == null || name.equals("")) {
            //throw new IllegalArgumentException("Cannot change the player name to nothing.");
            player.setName("(default)");
            return;
        }
        player.setName(name);
    }

    public Player getPlayer(){return player;}

    /**
     * Gets the currently unallocated skillpoints for this stored Player.
     * @return  the remaining (unallocated) skillpoints
     */
    public int getPlayerUnallocatedSkillpoints() {
        return player.getSkillpoints();
    }

    /**
     * Adds allocatable skillpoints to the stored Player.
     * @param points    the skillpoints to add
     */
    public void addPlayerSkillpoints(int points) {
        if (points == 0) {
            throw new IllegalArgumentException("Cannot add 0 points to player.");
        }
        player.setSkillpoints(player.getSkillpoints() + points);
    }

    /**
     * Gets the piloting skill of the stored Player.
     * @return  the piloting skill
     */
    public int getPlayerPilotSkill() {
        return player.getPilot();
    }

    /**
     * Takes skillpoints from the unallocated pool and adds them to the piloting skill.
     * @param points    the points to add to the piloting skill
     */
    public void addPlayerPilotSkill(int points) {
        int remainingPoints = player.getSkillpoints();
        if (points > remainingPoints) {
            throw new IllegalArgumentException("Cannot add more points to pilot skill than are remaining.");
        }
        player.setPilot(player.getPilot() + points);
        player.setSkillpoints(player.getSkillpoints() - points);
    }

    /**
     * Gets the fighting skill of the stored Player.
     * @return  the fighting skill
     */
    public int getPlayerFighterSkill() {
        return player.getFighter();
    }

    /**
     * Takes skillpoints from the unallocated pool and adds them to the fighting skill.
     * @param points    the points to add to the fighting skill
     */
    public void addPlayerFighterSkill(int points) {
        int remainingPoints = player.getSkillpoints();
        if (points > remainingPoints) {
            throw new IllegalArgumentException("Cannot add more points to fighter skill than are remaining.");
        }
        player.setFighter(player.getFighter() + points);
        player.setSkillpoints(player.getSkillpoints() - points);
    }

    /**
     * Gets the trading skill of the stored Player.
     * @return  the trading skill
     */
    public int getPlayerTraderSkill() {
        return player.getTrader();
    }

    /**
     * Takes skillpoints from the unallocated pool and adds them to the trading skill.
     * @param points    the points to add to the trading skill
     */
    public void addPlayerTraderSkill(int points) {
        int remainingPoints = player.getSkillpoints();
        if (points > remainingPoints) {
            throw new IllegalArgumentException("Cannot add more points to trader skill than are remaining.");
        }
        player.setTrader(player.getTrader() + points);
        player.setSkillpoints(player.getSkillpoints() - points);
    }

    /**
     * Gets the engineering skill of the stored Player.
     * @return  the engineering skill
     */
    public int getPlayerEngineerSkill() {
        return player.getEngineer();
    }

    /**
     * Takes skillpoints from the unallocated pool and adds them to the engineer skill.
     * @param points    the points to add to the engineering skill
     */
    public void addPlayerEngineerSkill(int points) {
        int remainingPoints = player.getSkillpoints();
        if (points > remainingPoints) {
            throw new IllegalArgumentException("Cannot add more points to engineer skill than are remaining.");
        }
        player.setEngineer(player.getEngineer() + points);
        player.setSkillpoints(player.getSkillpoints() - points);
    }

    /**
     * Gets the current balance of credits of the stored Player.
     * @return  the balance of credits
     */
    public int getPlayerBalance() {
        return player.getCredits();
    }

    /**
     * Adds the specified amount of credits to the stored Player balance.
     * @param credits   the credits to add (or take away)
     */
    public void addCreditsToPlayerBalance(int credits) {
        if (credits == 0) {
            throw new IllegalArgumentException("Cannot add 0 credits to player balance");
        }
        player.setCredits(player.getCredits() + credits);
    }

    /**
     * Gets the current spaceship of the stored Player.
     * @return  the ship
     */
    public Spaceship getPlayerShip() {
        return player.getShip();
    }

    /**
     * Changes the stored Player's ship to a new one.
     * @param ship  the new ship
     */
    public void setPlayerShip(Spaceship ship) {
        if (ship == null) {
            throw new IllegalArgumentException("Cannot set the player ship to a null object.");
        }
        player.setShip(ship);
    }

    /**
     * Returns true or false depending on the stored Player skillpoint value.
     * @return  true if greater than 0, false if less
     */
    public boolean playerHasSkillpointsLeft() {
        return player.getSkillpoints() > 0;
    }

    /**
     * Returns true or false depending on the stored Player pilot point value.
     * @return  true if greater than 0, false if less
     */
    public boolean playerHasPilotPointsLeft() {
        return player.getPilot() > 0;
    }

    /**
     * Returns true or false depending on the stored Player fighter point value.
     * @return  true if greater than 0, false if less
     */
    public boolean playerHasFighterPointsLeft() {
        return player.getFighter() > 0;
    }

    /**
     * Returns true or false depending on the stored Player trader point value.
     * @return  true if greater than 0, false if less
     */
    public boolean playerHasTraderPointsLeft() {
        return player.getTrader() > 0;
    }

    /**
     * Returns true or false depending on the stored Player engineer point value.
     * @return  true if greater than 0, false if less
     */
    public boolean playerHasEngineerPointsLeft() {
        return player.getEngineer() > 0;
    }
}
