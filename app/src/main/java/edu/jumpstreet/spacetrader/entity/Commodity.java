package edu.jumpstreet.spacetrader.entity;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

import edu.jumpstreet.spacetrader.entity.System.TechLevel;
import edu.jumpstreet.spacetrader.entity.Planet.Resource;

/**
 * Commodity entity represents resources the player will buy/trade
 */
public class Commodity implements Parcelable, Serializable {
    private final int weight;
    private final int baseValue;
    private String resource;
    private int quantity;
    private final int IPL;
    private final int VAR;
    private final int MTL;
    private final int MTH;
    private final TechLevel MTLP;
    private final TechLevel MTLU;
    private final TechLevel TTP;
    private final Resource CR;
    private final Resource ER;

    /**
     * Commodity categories
     */
    public enum CommodityResources{
        Water, Furs, Food, Ore, Games, Firearms,
        Medicine, Machines, Narcotics, Robots
    }

    /**
     * Commodity constructor
     *  @param weight int : the weight of the commodity
     * @param baseValue int: Value of the commodity
     * @param resource String: category the commodity belongs to
     * @param IPL Price increase Per Tech Level
     * @param VAR max % that the price can vary above or below
     * @param MTL Min price offered in space trade with random trader
     * @param MTH Max price offered in space trade with random trader
     * @param MTLP Min tech level to produce
     * @param MTLU min tech level to use
     * @param TTP Tech level which produces the most of this item
     * @param CR When present price of resource is low
     * @param ER When present price of resource is high
     */
    Commodity(int weight, int baseValue, String resource,
              int IPL, int VAR, int MTL, int MTH,
              TechLevel MTLP, TechLevel MTLU, TechLevel TTP,
              Resource CR, Resource ER){
        this.weight = weight;
        this.baseValue = baseValue;
        this.resource = resource;
        this.quantity = 0;
        this.IPL = IPL;
        this.VAR = VAR;
        this.MTL = MTL;
        this.MTH = MTH;
        this.MTLP = MTLP;
        this.MTLU = MTLU;
        this.TTP = TTP;
        this.CR = CR;
        this.ER = ER;
    }

    /**
     * overrides the original describeContents()
     * and returns 0 instead
     * @return int 0
     */
    @Override
    public int describeContents(){
        return 0;
    }

    /**
     * overrides writeToParcel
     * @param dest writes containers for given items
     * @param flags int
     */
    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeInt(weight);
        dest.writeInt(baseValue);
        dest.writeString(resource);
        dest.writeInt(quantity);
        dest.writeInt(IPL);
        dest.writeInt(VAR);
        dest.writeInt(MTL);
        dest.writeInt(MTH);
        dest.writeInt(MTLP.ordinal());
        dest.writeInt(MTLU.ordinal());
        dest.writeInt(TTP.ordinal());
        if(CR != null) {
            dest.writeInt(CR.ordinal());
        } else{
            dest.writeInt(0);
        }
        if(ER != null) {
            dest.writeInt(ER.ordinal());
        }{
            dest.writeInt(0);
        }
    }

    /**
     * unwraps parceled data
     * @param in Parcel object that holds data
     */
    private Commodity(Parcel in){
        this.weight = in.readInt();
        this.baseValue = in.readInt();
        this.resource = in.readString();
        this.quantity = in.readInt();
        this.IPL = in.readInt();
        this.VAR = in.readInt();
        this.MTL = in.readInt();
        this.MTH = in.readInt();
        this.MTLP = TechLevel.getTechLevelByIndex(in.readInt());
        this.MTLU = TechLevel.getTechLevelByIndex(in.readInt());
        this.TTP = TechLevel.getTechLevelByIndex(in.readInt());
        this.CR = Resource.getResourceByIndex(in.readInt());
        this.ER = Resource.getResourceByIndex(in.readInt());
    }

    /**
     * creates a Parcelable object
     */
    public static final Parcelable.Creator<Commodity> CREATOR = new Parcelable.Creator<Commodity>(){
        @Override
        public Commodity createFromParcel(Parcel source){
            return new Commodity(source);
        }
        @Override
        public Commodity[] newArray(int size){
            return new Commodity[size];
        }
    };

    /**
     * weight getter
     * @return int of the weight of the commodity
     */
    public int getWeight(){return weight;}

    /**
     * Base Value getter
     * @return int representing Value
     */
    int getBaseValue(){return baseValue;}

    /**
     * Quantity getter
     * @return int representing quantity of resource
     */
    public int getQuantity(){return  quantity;}

    /**
     * Sets the quantity
     * @param quantity to set to this commodity
     */
    void setQuantity(int quantity){this.quantity = quantity;}


    /**
     * resource getter
     * @return returns the resource category of this commodity
     */
    public String getResource() {
        return resource;
    }

    /**
     * resource setter
     * @param resource sets the category of this commodity
     */
    public void setResource(String resource) {
        this.resource = resource;
    }

    /**
     * IPL getter
     * @return the IPL associated with this commodity
     */
    int getIPL() {
        return IPL;
    }

    /**
     * VAR getter
     * @return the VAR associated with this commodity
     */
    int getVAR() {
        return VAR;
    }

    /**
     * MTLP getter
     * @return MTLP associated with this commodity
     */
    public TechLevel getMTLP() {
        return MTLP;
    }

    /*

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setBaseValue(int baseValue) {
        this.baseValue = baseValue;
    }



    public void setIPL(int IPL) {
        this.IPL = IPL;
    }



    public void setVAR(int VAR) {
        this.VAR = VAR;
    }

    public int getMTL() {
        return MTL;
    }

    public void setMTL(int MTL) {
        this.MTL = MTL;
    }

    public int getMTH() {
        return MTH;
    }

    public void setMTH(int MTH) {
        this.MTH = MTH;
    }



    public void setMTLP(TechLevel MTLP) {
        this.MTLP = MTLP;
    }

    public TechLevel getMTLU() {
        return MTLU;
    }

    public void setMTLU(TechLevel MTLU) {
        this.MTLU = MTLU;
    }

    public TechLevel getTTP() {
        return TTP;
    }

    public void setTTP(TechLevel TTP) {
        this.TTP = TTP;
    }

    public Resource getCR() {
        return CR;
    }

    public void setCR(Resource CR) {
        this.CR = CR;
    }

    public Resource getER() {
        return ER;
    }

    public void setER(Resource ER) {
        this.ER = ER;
    }
    */
}
