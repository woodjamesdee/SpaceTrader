package edu.jumpstreet.spacetrader.entity;

import java.io.Serializable;

/**
 * This class is the basis for all Spaceships in Space Trader.
 */
public abstract class Spaceship implements Serializable {

    String name;
    String description;
    int hitpoints;
    int maxCargoSpace;
    int usedCargoSpace;



    int remainingFuel;


    int maxFuel;
    Economy economy;

    /**
     * fuel getter
     * @return int representing how many units fuel is left
     */
    public int getRemainingFuel() {
        return remainingFuel;
    }

    /**
     * max fuel getter
     * @return int representing max fuel the ship can hold
     */
    public int getMaxFuel() {
        return maxFuel;
    }

    /*
    public void setMaxFuel(int maxFuel) {
        this.maxFuel = maxFuel;
    }
    */

    /**
     * remaining fuel setter
     * @param remainingFuel how much fuel is left for this ship
     */
    public void setRemainingFuel(int remainingFuel) {
        this.remainingFuel = remainingFuel;
    }

    /**
     * name getter
     * @return the string name of this ship
     */
    public String getName() {
        return name;
    }
    //public String getDescription() { return description; }
    //public int getHitpoints() { return hitpoints; }

    /**
     * max cargo space getter
     * @return int representing the cargo space the ship has total
     */
    public int getMaxCargoSpace(){return maxCargoSpace;}

    /**
     * used cargo space getter
     * @return int representing how much cargo space is being used
     */
    public int getUsedCargoSpace(){return usedCargoSpace;}

    /**
     * resource quantity getter
     * @param index the index of the quantity of the given commodity
     * @return int representing how much of that resource is there
     */
    public int getResourceQuantityByIndex(int index){
            return economy.getCommodity(index).getQuantity();
    }

//    public Commodity getCommodity(Commodity comm){
//        economy.getCommodity(co)
//    }

    /*
    public void setIndexedResourceQuantity(int index, int amount){
        economy.getCommodity(index).setQuantity(amount);
    }
    */

    /**
     * resource quantity setter
     * @param name the name of the resource
     * @param quantity the quantity of the resource
     */
    public void setResourceQuantityByName(String name, int quantity){
        economy.getCommodityByName(name).setQuantity(quantity);
    }

    /**
     * resource quantity getter
     * @param name string representing commodity you want to find the quantity of
     * @return int of how much of the given commodity is there
     */
    public int getQuantityByName (String name){
        return economy.getCommodityByName(name).getQuantity();
    }

    //public Economy getEconomy(){return  economy;}

    /**
     * used cargo space setter
     * @param amount int to set the cargo space used to
     */
    public void setUsedCargoSpace(int amount){
        usedCargoSpace = amount;
    }
}
