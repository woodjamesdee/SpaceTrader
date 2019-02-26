package edu.jumpstreet.spacetrader.model;

import java.util.Random;

/**
 * This is the interface to the entity and model classes in this application.
 *
 * Implemented in a Singleton style.
 */
public class Model {

    private PlayerInteractor playerInteractor;
    private UniverseInteractor universeInteractor;
    private GameInteractor gameInteractor;
    private Random random;

    private static Model instance = new Model();

    public static Model getInstance() { return instance; }

    private Model() {
        playerInteractor = new PlayerInteractor();
        random = new Random();
        universeInteractor = new UniverseInteractor(random);
        gameInteractor = new GameInteractor(universeInteractor);
    }

    public PlayerInteractor getPlayerInteractor() {
        return playerInteractor;
    }

    public UniverseInteractor getUniverseInteractor() { return universeInteractor; }

    public GameInteractor getGameInteractor() { return gameInteractor; }

}
