package edu.jumpstreet.spacetrader.entity;

import edu.jumpstreet.spacetrader.model.Model;

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

    private System.TechLevel techLevel;

    //


    public Economy(System.TechLevel currentTechLevel){
        Water = new Commodity(1, 30, 0, 3, 4, 30, 50, System.TechLevel.PreAgriculture, System.TechLevel.PreAgriculture, System.TechLevel.Medieval, Planet.Resource.LOTSOFWATER, Planet.Resource.DESERT);
        Furs = new Commodity(2, 250, 0, 10, 10, 230, 280, System.TechLevel.PreAgriculture, System.TechLevel.PreAgriculture, System.TechLevel.PreAgriculture, Planet.Resource.RICHFAUNA, Planet.Resource.LIFELESS);
        Food = new Commodity(4, 100, 0, 5, 5, 90, 160, System.TechLevel.Agriculture, System.TechLevel.PreAgriculture, System.TechLevel.Agriculture, Planet.Resource.RICHSOIL, Planet.Resource.POORSOIL);
        Ore = new Commodity(10, 350, 0, 20, 10, 350, 420, System.TechLevel.Medieval, System.TechLevel.Medieval, System.TechLevel.Renaissance, Planet.Resource.MINERALRICH, Planet.Resource.MINERALPOOR);
        Games = new Commodity(5, 250, 0, -10, 5, 160, 270, System.TechLevel.Renaissance, System.TechLevel.Agriculture, System.TechLevel.PostIndustrial, Planet.Resource.ARTISTIC, null);
        Firearms = new Commodity(25, 1250, 0, -75, 100, 600, 1100, System.TechLevel.Renaissance, System.TechLevel.Agriculture, System.TechLevel.Industrial, Planet.Resource.WARLIKE, null);
        Medicine = new Commodity(20, 650, 0, -20, 10, 400, 700, System.TechLevel.EarlyIndustrial, System.TechLevel.Agriculture, System.TechLevel.PostIndustrial, Planet.Resource.LOTSOFHERBS, null);
        Machines = new Commodity(50, 900, 0, -30, 5, 600, 800, System.TechLevel.EarlyIndustrial, System.TechLevel.Renaissance, System.TechLevel.Industrial, null, null);
        Narcotics = new Commodity(1, 3500, 0, -125, 150, 2000, 3000, System.TechLevel.Industrial, System.TechLevel.PreAgriculture, System.TechLevel.Industrial, Planet.Resource.WEIRDMUSHROOMS, null);
        Robots = new Commodity(75, 5000, 0, -150, 100, 3500, 5000, System.TechLevel.PostIndustrial, System.TechLevel.EarlyIndustrial, System.TechLevel.HiTech, null, null);
        techLevel = currentTechLevel;
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

    public int getCommodityValue(int index){
        Commodity comm = getCommodity(index);
        int result = 0;
        int randomVar = Model.getInstance().getRandom().nextInt(comm.getVAR());
        result = comm.getBaseValue() + ((techLevel.ordinal() - comm.getMTLP().ordinal()) * comm.getIPL()) + (comm.getBaseValue() * randomVar / 100);
        return result;
    }


}
