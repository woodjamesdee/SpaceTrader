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
        final int waterBaseValue = 30;
        final int waterMTL = 30;
        final int waterMTH = 50;
        Water = new Commodity(1, waterBaseValue, "Water", 0,
                3, 4, waterMTL, waterMTH, System.TechLevel.PreAgriculture,
                System.TechLevel.PreAgriculture, System.TechLevel.Medieval,
                Planet.Resource.LOTSOFWATER, Planet.Resource.DESERT);

        final int fursBaseValue = 250;
        final int fursMTL = 230;
        final int fursMTH = 280;
        Furs = new Commodity(2, fursBaseValue, "Furs",0,
                10, 10, fursMTL, fursMTH, System.TechLevel.PreAgriculture,
                System.TechLevel.PreAgriculture, System.TechLevel.PreAgriculture,
                Planet.Resource.RICHFAUNA, Planet.Resource.LIFELESS);

        final int foodBaseValue = 100;
        final int foodMTL = 90;
        final int foodMTH = 160;
        Food = new Commodity(4, foodBaseValue, "Food", 0,
                5, 5, foodMTL, foodMTH, System.TechLevel.Agriculture,
                System.TechLevel.PreAgriculture, System.TechLevel.Agriculture,
                Planet.Resource.RICHSOIL, Planet.Resource.POORSOIL);

        final int oreBaseValue = 350;
        final int oreMTL = 350;
        final int oreMTH = 420;
        final int oreIPL = 20;
        Ore = new Commodity(10, oreBaseValue, "Ore", 0, oreIPL,
                10, oreMTL, oreMTH, System.TechLevel.Medieval,
                System.TechLevel.Medieval, System.TechLevel.Renaissance,
                Planet.Resource.MINERALRICH, Planet.Resource.MINERALPOOR);

        final int gamesBaseValue = 250;
        final int gamesMTL = 160;
        final int gamesMTH = 270;
        Games = new Commodity(5, gamesBaseValue, "Games",0,
                -10, 5, gamesMTL, gamesMTH, System.TechLevel.Renaissance,
                System.TechLevel.Agriculture, System.TechLevel.PostIndustrial,
                Planet.Resource.ARTISTIC, null);

        final int firearmsWeight = 25;
        final int firearmsBaseValue = 1250;
        final int firearmsIPL = -75;
        final int firearmsMTL = 600;
        final int firearmsMTH = 1100;
        Firearms = new Commodity(firearmsWeight, firearmsBaseValue, "Firearms",0,
                firearmsIPL, 100, firearmsMTL, firearmsMTH, System.TechLevel.Renaissance,
                System.TechLevel.Agriculture, System.TechLevel.Industrial,
                Planet.Resource.WARLIKE, null);

        final int medicineWeight = 20;
        final int medicineBaseValue = 650;
        final int medicineIPL = -20;
        final int medicineMTL = 400;
        final int medicineMTH = 700;
        Medicine = new Commodity(medicineWeight, medicineBaseValue, "Medicine",0,
                medicineIPL, 10, medicineMTL, medicineMTH, System.TechLevel.EarlyIndustrial,
                System.TechLevel.Agriculture, System.TechLevel.PostIndustrial,
                Planet.Resource.LOTSOFHERBS, null);

        final int machinesWeight = 50;
        final int machinesBaseValue = 900;
        final int machinesIPL = -30;
        final int machinesMTL = 600;
        final int machinesMTH = 800;
        Machines = new Commodity(machinesWeight, machinesBaseValue, "Machines",0,
                machinesIPL, 5, machinesMTL, machinesMTH, System.TechLevel.EarlyIndustrial,
                System.TechLevel.Renaissance, System.TechLevel.Industrial, null, null);

        final int narcoticsWeight = 1;
        final int narcoticsBaseValue = 3500;
        final int narcoticsIPL = -125;
        final int narcoticsVAR = 150;
        final int narcoticsMTL = 2000;
        final int narcoticsMTH = 3000;
        Narcotics = new Commodity(narcoticsWeight, narcoticsBaseValue,
                "Narcotics", 0, narcoticsIPL, narcoticsVAR,
                narcoticsMTL, narcoticsMTH, System.TechLevel.Industrial,
                System.TechLevel.PreAgriculture, System.TechLevel.Industrial,
                Planet.Resource.WEIRDMUSHROOMS, null);

        final int robotsWeight = 75;
        final int robotsBaseValue = 5000;
        final int robotsIPL = -150;
        final int robotsVAR = 100;
        final int robotsMTL = 3500;
        final int robotsMTH = 5000;
        Robots = new Commodity(robotsWeight, robotsBaseValue, "Robots", 0,
                robotsIPL, robotsVAR, robotsMTL, robotsMTH, System.TechLevel.PostIndustrial,
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

    /**
     * a getter for commodity
     * @param index an int that defines which commodity is returned
     * @return the commodity that the index identifies
     */
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

    /**
     * a getter for the commodity value based on index
     * @param index defines which commodity to get the value from
     * @return the value of the commodity
     */
    public int getCommodityValue(int index){
        Commodity comm = getCommodity(index);
        int result;
        int randomVar = Model.getInstance().getRandom().nextInt(comm.getVAR());
        result = comm.getBaseValue() + ((techLevel.ordinal() - comm.getMTLP().ordinal())
                * comm.getIPL()) + (comm.getBaseValue() * randomVar / 100);
        return result;
    }

    /**
     * a getter for the commodity value based on the Commodity
     * @param comm the commodity the value is based on
     * @return the value of the commodity
     */
    public int getCommodityValue(Commodity comm){
        int result;
        int randomVar = Model.getInstance().getRandom().nextInt(comm.getVAR());
        result = comm.getBaseValue() + ((techLevel.ordinal() - comm.getMTLP().ordinal())
                * comm.getIPL()) + ((comm.getBaseValue() * randomVar) / 100);
        return result;
    }


}
