package edu.jumpstreet.spacetrader.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;

import edu.jumpstreet.spacetrader.vendor.Base64Coder;

/**
 * This is the interface to the entity and model classes in this application.
 *
 * Implemented in a Singleton style.
 */
public class Model implements Serializable {

    private PlayerInteractor playerInteractor;
    private UniverseInteractor universeInteractor;
    private GameInteractor gameInteractor;
    private Random random;
    public boolean isSaved;

    private static Model instance = new Model();

    public static Model getInstance() { return instance; }

    private Model() {
        playerInteractor = new PlayerInteractor();
        random = new Random();
        universeInteractor = new UniverseInteractor(random);
        gameInteractor = new GameInteractor(universeInteractor);
        isSaved = false;
    }

    public PlayerInteractor getPlayerInteractor() {
        return playerInteractor;
    }

    public UniverseInteractor getUniverseInteractor() { return universeInteractor; }

    public GameInteractor getGameInteractor() { return gameInteractor; }

    public Random getRandom() { return random; } // TODO: This is really bad, need to change this, only used to make the M7 demo work

    public static void loadGameFromString(String input) throws IOException, ClassNotFoundException{
        System.out.println(input);
        byte[] data = Base64Coder.decode(input);
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
        Object object = ois.readObject();
        ois.close();
        Model model = (Model) object;
        System.out.println(model.isSaved);
        System.out.println(model.getUniverseInteractor().isSaved);
        System.out.println(model.getUniverseInteractor().getUniverse().isSaved);
        instance = model;
    }

    public static String saveGameToString() throws IOException {
        instance.isSaved = true;
        instance.getUniverseInteractor().isSaved = true;
        instance.getUniverseInteractor().getUniverse().isSaved = true;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(instance);
        oos.close();
        String result = new String(Base64Coder.encode(baos.toByteArray()));
        System.out.println(result);
        return result;
    }
}
