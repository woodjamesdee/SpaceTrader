package edu.jumpstreet.spacetrader.entity;

import edu.jumpstreet.spacetrader.model.Model;

/**
 * This class is the basis for all Spaceships in Space Trader.
 */
public abstract class Spaceship {

    protected String name;
    protected String description;
    protected int hitpoints;
    protected int maxCargoSpace;
    protected int usedCargoSpace;



    protected int remainingFuel;


    protected int maxFuel;
    protected Economy economy;


    public int getRemainingFuel() {
        return remainingFuel;
    }
    public int getMaxFuel() {
        return maxFuel;
    }

    public void setMaxFuel(int maxFuel) {
        this.maxFuel = maxFuel;
    }

    public void setRemainingFuel(int remainingFuel) {
        this.remainingFuel = remainingFuel;
    }
    public String getName() {
        return name;
    }
    public String getDescription() { return description; }
    public int getHitpoints() { return hitpoints; }
    public int getMaxCargoSpace(){return maxCargoSpace;}
    public int getUsedCargoSpace(){return usedCargoSpace;}


    public int getResourceQuantityByIndex(int index){
            return economy.getCommodity(index).getQuantity();
    }

//    public Commodity getCommodity(Commodity comm){
//        economy.getCommodity(co)
//    }
    public void setIndexedResourceQuantity(int index, int amount){
        economy.getCommodity(index).setQuantity(amount);
    }

    public void setResourceQuantityByName(String name, int quantity){
        economy.getCommodityByName(name).setQuantity(quantity);
    }

    public int getQuantityByName (String name){
        return economy.getCommodityByName(name).getQuantity();
    }

    public Economy getEconomy(){return  economy;}
    public void setUsedCargoSpace(int amount){
        usedCargoSpace = amount;
    }
}
