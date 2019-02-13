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

    private Model() {
        playerInteractor = new PlayerInteractor();
    }

    public PlayerInteractor getPlayerInteractor() {
        return playerInteractor;
    }

}
