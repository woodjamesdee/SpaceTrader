package edu.jumpstreet.spacetrader.entity;

public class Commodity {
    int weight;
    int baseValue;
    String resource;
    int quantity;

    public enum CommodityResources{
        Water, Furs, Food, Ore, Games, Firearms,
        Medicine, Machines, Narcotics, Robots
    }
    public Commodity(int weight, int baseValue, int quantity){
        this.weight = weight;
        this.baseValue = baseValue;
        this.quantity = quantity;
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

}
