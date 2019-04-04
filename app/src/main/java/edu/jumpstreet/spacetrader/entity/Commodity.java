package edu.jumpstreet.spacetrader.entity;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

import edu.jumpstreet.spacetrader.entity.System.TechLevel;
import edu.jumpstreet.spacetrader.entity.Planet.Resource;

public class Commodity implements Parcelable, Serializable {
    private int weight;
    private int baseValue;
    private String resource;
    private int quantity;
    private int IPL, VAR, MTL, MTH;
    private TechLevel MTLP, MTLU, TTP;
    private Resource CR, ER;

    public enum CommodityResources{
        Water, Furs, Food, Ore, Games, Firearms,
        Medicine, Machines, Narcotics, Robots
    }
    Commodity(int weight, int baseValue, String resource, int quantity, int IPL, int VAR, int MTL, int MTH, TechLevel MTLP, TechLevel MTLU, TechLevel TTP, Resource CR, Resource ER){
        this.weight = weight;
        this.baseValue = baseValue;
        this.resource = resource;
        this.quantity = quantity;
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

    @Override
    public int describeContents(){
        return 0;
    }
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
    private Commodity(Parcel in){
        this.weight = in.readInt();
        this.baseValue = in.readInt();
        this.resource = in.readString();
        this.quantity = in.readInt();
        this.IPL = in.readInt();
        this.VAR = in.readInt();
        this.MTL = in.readInt();
        this.MTH = in.readInt();
        this.MTLP = TechLevel.values()[in.readInt()];
        this.MTLU = TechLevel.values()[in.readInt()];
        this.TTP = TechLevel.values()[in.readInt()];
        this.CR = Resource.values()[in.readInt()];
        this.ER = Resource.values()[in.readInt()];
    }

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


    public int getWeight(){return weight;}
    int getBaseValue(){return baseValue;}
    public int getQuantity(){return  quantity;}

    void setQuantity(int quantity){this.quantity = quantity;}

    /*
    public static CommodityResources getIndexCommodity(int index){
        switch(index){
            case 0: return CommodityResources.Water;
            case 1: return CommodityResources.Furs;
            case 2: return CommodityResources.Food;
            case 3: return CommodityResources.Ore;
            case 4: return CommodityResources.Games;
            case 5: return CommodityResources.Firearms;
            case 6: return CommodityResources.Medicine;
            case 7: return CommodityResources.Machines;
            case 8: return CommodityResources.Narcotics;
            case 9: return CommodityResources.Robots;
            default:return CommodityResources.Water;
        }
    }
    */

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    int getIPL() {
        return IPL;
    }

    int getVAR() {
        return VAR;
    }

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
