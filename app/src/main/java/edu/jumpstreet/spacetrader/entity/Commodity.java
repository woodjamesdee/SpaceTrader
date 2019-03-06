package edu.jumpstreet.spacetrader.entity;
import edu.jumpstreet.spacetrader.entity.System.TechLevel;
import edu.jumpstreet.spacetrader.entity.Planet.Resource;

public class Commodity {
    int weight;
    int baseValue;
    String resource;
    int quantity;
    int IPL, VAR, MTL, MTH;
    TechLevel MTLP, MTLU, TTP;
    Resource CR, ER;

    public enum CommodityResources{
        Water, Furs, Food, Ore, Games, Firearms,
        Medicine, Machines, Narcotics, Robots
    }
    public Commodity(int weight, int baseValue, int quantity, int IPL, int VAR, int MTL, int MTH, TechLevel MTLP, TechLevel MTLU, TechLevel TTP, Resource CR, Resource ER){
        this.weight = weight;
        this.baseValue = baseValue;
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

    public int getWeight(){return weight;}
    public int getBaseValue(){return baseValue;}
    public int getQuantity(){return  quantity;}

    public void setQuantity(int quantity){this.quantity = quantity;}

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

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setBaseValue(int baseValue) {
        this.baseValue = baseValue;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public int getIPL() {
        return IPL;
    }

    public void setIPL(int IPL) {
        this.IPL = IPL;
    }

    public int getVAR() {
        return VAR;
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

    public TechLevel getMTLP() {
        return MTLP;
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
}
