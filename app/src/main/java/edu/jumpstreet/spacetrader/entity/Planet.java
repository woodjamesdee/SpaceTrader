package edu.jumpstreet.spacetrader.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

/**
 *
 */
public class Planet extends System {

    private Resource resource;
    private final Economy economy;

    public enum Resource {
        NOSPECIALRESOURCES, MINERALRICH, MINERALPOOR,
        DESERT, LOTSOFWATER, RICHSOIL, POORSOIL,
        RICHFAUNA, LIFELESS, WEIRDMUSHROOMS, LOTSOFHERBS,
        ARTISTIC, WARLIKE;

        /**
         * Gets the Resource by index
         * @param index the index of the Resource to get
         * @return the Resource
         */
        public static Resource getResourceByIndex(int index) {
            return Resource.values()[index];
        }
    }

    Planet(String name, int x, int y, int techLevelIndex, int resourceIndex) {
        super(x, y, techLevelIndex);
        this.name = name;
        for (Resource current : Resource.values()) {
            if (current.ordinal() == resourceIndex) {
                this.resource = current;
            }
        }
        economy = new Economy(System.TechLevel.values()[techLevelIndex]);
        createCommodities(techLevelIndex);
    }

    private void createCommodities(int techLevel){
        Random rand = new Random();
        List<Commodity> commodities = economy.getCommodities();
        Commodity commodity0 = commodities.get(0);
        Commodity commodity1 = commodities.get(1);
        Commodity commodity2 = commodities.get(2);
        Commodity commodity3 = commodities.get(3);
        Commodity commodity4 = commodities.get(4);
        Commodity commodity5 = commodities.get(5);
        Commodity commodity6 = commodities.get(6);
        Commodity commodity7 = commodities.get(7);
        Commodity commodity8 = commodities.get(8);
        Commodity commodity9 = commodities.get(9);

        commodity0.setQuantity(rand.nextInt(1000));
        commodity1.setQuantity(rand.nextInt(1000));
        if(techLevel >= 1){
            commodity2.setQuantity(rand.nextInt(1000));
        }
        if(techLevel >= 2){
            commodity3.setQuantity(rand.nextInt(1000));
        }
        if(techLevel >= 3){
            commodity4.setQuantity(rand.nextInt(1000));
            commodity5.setQuantity(rand.nextInt(1000));
        }
        if(techLevel >= 4){
            commodity6.setQuantity(rand.nextInt(1000));
            commodity7.setQuantity(rand.nextInt(1000));
        }
        if(techLevel >= 5){
            commodity8.setQuantity(rand.nextInt(1000));
        }
        if(techLevel >= 6){
            commodity9.setQuantity(rand.nextInt(1000));
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


    /**
     * resource quantity getter
     * @param index index associated with appropriate int
     * @return int that determines resource quantity
     */
    public int getIndexedResourceQuantity(int index){
        Commodity comm = economy.getCommodity(index);
        return comm.getQuantity();
    }

    /*
    public void setIndexedResource(int index, int amount){
        economy.getCommodity(index).setQuantity(amount);
    }
    */

    /**
     * resource quantity setter
     * @param name the name of the resource
     * @param amount amount to set of given resource
     */
    public void setResourceQuantityByName(String name, int amount){
        Commodity comm = economy.getCommodityByName(name);
        comm.setQuantity(amount);
    }

    /**
     * economy getter
     * @return Economy object associated with given planet
     */
    public Economy getEconomy(){return economy;}

}
