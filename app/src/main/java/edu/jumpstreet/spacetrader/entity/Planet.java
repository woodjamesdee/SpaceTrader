package edu.jumpstreet.spacetrader.entity;

public class Planet extends System {

    private Resource resource;

    public enum Resource {
        NOSPECIALRESOURCES, MINERALRICH, MINERALPOOR,
        DESERT, LOTSOFWATER, RICHSOIL, POORSOIL,
        RICHFAUNA, LIFELESS, WEIRDMUSHROOMS, LOTSOFHERBS,
        ARTISTIC, WARLIKE
    }

    protected Planet(String name, int x, int y, int techLevelIndex, int resourceIndex) {
        super(x, y, techLevelIndex);
        this.name = name;
        for (Resource current : Resource.values()) {
            if (current.ordinal() == resourceIndex) {
                this.resource = current;
            }
        }
    }

    /**
     * Gets the Resource of this Planet
     * @return  the Resource
     */
    public Resource getResource() {
        return resource;
    }

    /**
     * Sets the Resource of this Planet
     * @param resource the new Resource of the Planet
     */
    public void setResource(Resource resource) {
        this.resource = resource;
    }

}
