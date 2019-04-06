package edu.jumpstreet.spacetrader.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import edu.jumpstreet.spacetrader.model.Model;

/**
 * class that represents the economy
 */
public class Economy implements Parcelable, Serializable {
    private Commodity Water;
    private Commodity Furs;
    private Commodity Food;
    private Commodity Ore;
    private Commodity Games;
    private Commodity Firearms;
    private Commodity Medicine;
    private Commodity Machines;
    private Commodity Narcotics;
    private Commodity Robots;

    private List<Commodity> commodities;

    private System.TechLevel techLevel;

    @Override
    public int describeContents(){return 0;}
    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeTypedList(commodities);
        if(techLevel != null) {
            dest.writeInt(techLevel.ordinal());
        }else{
            dest.writeInt(0);
        }
    }

    private Economy(Parcel in){
        commodities = new ArrayList<>();
        commodities =  in.createTypedArrayList(Commodity.CREATOR);
        assert commodities != null;
        Water = commodities.get(0);
        Furs = commodities.get(1);
        Food = commodities.get(2);
        Ore = commodities.get(3);
        Games = commodities.get(4);
        Firearms = commodities.get(5);
        Medicine = commodities.get(6);
        Machines = commodities.get(7);
        Narcotics = commodities.get(8);
        Robots = commodities.get(9);
        this.techLevel = System.TechLevel.values()[in.readInt()];
    }

    public static final Parcelable.Creator<Economy> CREATOR = new Parcelable.Creator<Economy>(){
      @Override
      public Economy createFromParcel(Parcel source){
          return new Economy(source);
        }
      @Override
      public Economy[] newArray(int size){return new Economy[size];}
    };


    Economy(System.TechLevel currentTechLevel){
        Water = new Commodity(1, 30, "Water", 0,
                3, 4, 30, 50, System.TechLevel.PreAgriculture,
                System.TechLevel.PreAgriculture, System.TechLevel.Medieval,
                Planet.Resource.LOTSOFWATER, Planet.Resource.DESERT);
        Furs = new Commodity(2, 250, "Furs",0,
                10, 10, 230, 280, System.TechLevel.PreAgriculture,
                System.TechLevel.PreAgriculture, System.TechLevel.PreAgriculture,
                Planet.Resource.RICHFAUNA, Planet.Resource.LIFELESS);
        Food = new Commodity(4, 100, "Food", 0,
                5, 5, 90, 160, System.TechLevel.Agriculture,
                System.TechLevel.PreAgriculture, System.TechLevel.Agriculture,
                Planet.Resource.RICHSOIL, Planet.Resource.POORSOIL);
        Ore = new Commodity(10, 350, "Ore", 0, 20,
                10, 350, 420, System.TechLevel.Medieval,
                System.TechLevel.Medieval, System.TechLevel.Renaissance,
                Planet.Resource.MINERALRICH, Planet.Resource.MINERALPOOR);
        Games = new Commodity(5, 250, "Games",0,
                -10, 5, 160, 270, System.TechLevel.Renaissance,
                System.TechLevel.Agriculture, System.TechLevel.PostIndustrial,
                Planet.Resource.ARTISTIC, null);
        Firearms = new Commodity(25, 1250, "Firearms",0,
                -75, 100, 600, 1100, System.TechLevel.Renaissance,
                System.TechLevel.Agriculture, System.TechLevel.Industrial,
                Planet.Resource.WARLIKE, null);
        Medicine = new Commodity(20, 650, "Medicine",0,
                -20, 10, 400, 700, System.TechLevel.EarlyIndustrial,
                System.TechLevel.Agriculture, System.TechLevel.PostIndustrial,
                Planet.Resource.LOTSOFHERBS, null);
        Machines = new Commodity(50, 900, "Machines",0,
                -30, 5, 600, 800, System.TechLevel.EarlyIndustrial,
                System.TechLevel.Renaissance, System.TechLevel.Industrial, null, null);
        Narcotics = new Commodity(1, 3500,"Narcotics", 0,
                -125, 150, 2000, 3000, System.TechLevel.Industrial,
                System.TechLevel.PreAgriculture, System.TechLevel.Industrial,
                Planet.Resource.WEIRDMUSHROOMS, null);
        Robots = new Commodity(75, 5000, "Robots", 0,
                -150, 100, 3500, 5000, System.TechLevel.PostIndustrial,
                System.TechLevel.EarlyIndustrial, System.TechLevel.HiTech, null, null);
        techLevel = currentTechLevel;
        commodities = new ArrayList<>();
        commodities.add(Water);
        commodities.add(Furs);
        commodities.add(Food);
        commodities.add(Ore);
        commodities.add(Games);
        commodities.add(Firearms);
        commodities.add(Medicine);
        commodities.add(Machines);
        commodities.add(Narcotics);
        commodities.add(Robots);

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

    Commodity getCommodityByName(String name){
        switch (name){
            case "Water": return Water;
            case "Furs": return Furs;
            case "Food": return Food;
            case "Ore": return Ore;
            case "Games": return Games;
            case "Firearms": return Firearms;
            case "Medicine": return Medicine;
            case "Machines": return Machines;
            case "Narcotics": return Narcotics;
            case "Robots": return Robots;
            default: return Water;
        }
    }

    /*
    public void setCommodityQuantity(int index, int quantity){
        Commodity comm = getCommodity(index);
        comm.setQuantity(quantity);
    }

    public int getCommodityCargoSpace(int index){
        Commodity comm = getCommodity(index);
        return comm.getWeight();
    }
    */

    public int getCommodityValue(int index){
        Commodity comm = getCommodity(index);
        int result;
        int randomVar = Model.getInstance().getRandom().nextInt(comm.getVAR());
        result = comm.getBaseValue() + ((techLevel.ordinal() - comm.getMTLP().ordinal())
                * comm.getIPL()) + (comm.getBaseValue() * randomVar / 100);
        return result;
    }

    public int getCommodityValue(Commodity comm){
        int result;
        int randomVar = Model.getInstance().getRandom().nextInt(comm.getVAR());
        result = comm.getBaseValue() + ((techLevel.ordinal() - comm.getMTLP().ordinal())
                * comm.getIPL()) + ((comm.getBaseValue() * randomVar) / 100);
        return result;
    }


}
