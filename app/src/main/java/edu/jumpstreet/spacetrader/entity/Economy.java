package edu.jumpstreet.spacetrader.entity;

public class Economy {
    protected Commodity Water;
    protected Commodity Furs;
    protected Commodity Food;
    protected Commodity Ore;
    protected Commodity Games;
    protected Commodity Firearms;
    protected Commodity Medicine;
    protected Commodity Machines;
    protected Commodity Narcotics;
    protected Commodity Robots;

    //


    public Economy(){
        Water = new Commodity(1, 10, 0);
        Furs = new Commodity(2, 20, 0);
        Food = new Commodity(4, 15, 0);
        Ore = new Commodity(10, 25, 0);
        Games = new Commodity(5, 50, 0);
        Firearms = new Commodity(25, 100, 0);
        Medicine = new Commodity(20, 200, 0);
        Machines = new Commodity(50, 1000, 0);
        Narcotics = new Commodity(1, 100, 0);
        Robots = new Commodity(75, 1500, 0);
    }
    public Commodity getCommodity(int index){
        switch (Commodity.CommodityResources.values()[index]){
            case Water: return Water;
            case Furs: return Furs;
            case Food: return Food;
            case Ore: return Ore;
            case Games: return Games;
            case Firearms: return Firearms;
            case Medicine: return Medicine;
            case Machines: return Machines;
            case Narcotics: return Narcotics;
            case Robots: return Robots;
            default: return Water;
        }
    }

    public void setCommodityQuantity(int index, int quantity){
        Commodity comm = getCommodity(index);
        comm.setQuantity(quantity);
    }

    public int getCommodityCargoSpace(int index){
        Commodity comm = getCommodity(index);
        return comm.getWeight();
    }

    public int getCommodityBaseValue(int index){
        Commodity comm = getCommodity(index);
        return comm.getBaseValue();
    }

}
