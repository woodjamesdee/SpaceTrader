package edu.jumpstreet.spacetrader.model;

/**
 * This is the interface to the entity and model classes in this application.
 *
 * Implemented in a Singleton style.
 */
public class Model {

    private PlayerInteractor playerInteractor;

    private static Model instance = new Model();

    public static Model getInstance() { return instance; }

    public Model() {
    }

    public void createPlayerInteractor(String name, int pilotSkill, int fighterSkill, int traderSkill, int engineerSkill){
        playerInteractor = new PlayerInteractor(name,pilotSkill, fighterSkill, traderSkill, engineerSkill);
    }

    public PlayerInteractor getPlayerInteractor() {
        return playerInteractor;
    }

}
