package edu.jumpstreet.spacetrader.model;

import java.util.Random;

/**
 * This is the interface to the entity and model classes in this application.
 *
 * Implemented in a Singleton style.
 */
public class Model {

    private PlayerInteractor playerInteractor;
    private Random random;

    private static Model instance = new Model();

    public static Model getInstance() { return instance; }

    private Model() {
        playerInteractor = new PlayerInteractor();
        random = new Random();
    }

    public PlayerInteractor getPlayerInteractor() {
        return playerInteractor;
    }

}
