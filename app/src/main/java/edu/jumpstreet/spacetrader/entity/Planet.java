package edu.jumpstreet.spacetrader.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Random;

import edu.jumpstreet.spacetrader.model.Model;

public class Planet extends System implements Parcelable {

    private Resource resource;
    private Economy economy;

    public enum Resource {
        NOSPECIALRESOURCES, MINERALRICH, MINERALPOOR,
        DESERT, LOTSOFWATER, RICHSOIL, POORSOIL,
        RICHFAUNA, LIFELESS, WEIRDMUSHROOMS, LOTSOFHERBS,
        ARTISTIC, WARLIKE
    }

    @Override
    public int describeContents(){return 0;}

    @Override
    public void writeToParcel(Parcel dest, int flags){
        if(resource != null){
            dest.writeInt(resource.ordinal());
        }else{
            dest.writeInt(0);
        }
        dest.writeParcelable(economy, flags);
    }

    private Planet(Parcel in){
        this.resource = Resource.values()[in.readInt()];
        this.economy = in.readParcelable(Economy.class.getClassLoader());
    }

    public static final Parcelable.Creator<Planet> CREATOR = new Parcelable.Creator<Planet>(){
        @Override
        public Planet createFromParcel(Parcel source){return new Planet(source);}
        @Override
        public Planet[] newArray(int size){return new Planet[size];}
    };


    protected Planet(String name, int x, int y, int techLevelIndex, int resourceIndex) {
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
        economy.getCommodity(0).setQuantity(rand.nextInt(1000));
        economy.getCommodity(1).setQuantity(rand.nextInt(1000));
        if(techLevel >= 1){
            economy.getCommodity(2).setQuantity(rand.nextInt(1000));
        }
        if(techLevel >= 2){
            economy.getCommodity(3).setQuantity(rand.nextInt(1000));
        }
        if(techLevel >= 3){
            economy.getCommodity(4).setQuantity(rand.nextInt(1000));
            economy.getCommodity(5).setQuantity(rand.nextInt(1000));
        }
        if(techLevel >= 4){
            economy.getCommodity(6).setQuantity(rand.nextInt(1000));
            economy.getCommodity(7).setQuantity(rand.nextInt(1000));
        }
        if(techLevel >= 5){
            economy.getCommodity(8).setQuantity(rand.nextInt(1000));
        }
        if(techLevel >= 6){
            economy.getCommodity(9).setQuantity(rand.nextInt(1000));
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


    //TODO default is getWater
    public int getIndexedResourceQuantity(int index){
        return economy.getCommodity(index).getQuantity();
    }

    public void setIndexedResource(int index, int amount){
        economy.getCommodity(index).setQuantity(amount);
    }

    public void setResourceQuantityByName(String name, int amount){
        economy.getCommodityByName(name).setQuantity(amount);
    }

    public Economy getEconomy(){return economy;}

}
