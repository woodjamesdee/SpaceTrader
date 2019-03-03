package edu.jumpstreet.spacetrader.entity;

import java.util.Random;

public class Planet extends System {

    private Resource resource;
    private int waterCommodity = 0;
    private int fursCommodity = 0;
    private int foodCommodity = 0;
    private int oreCommodity = 0;
    private int gamesCommodity = 0;
    private int firearmsCommodity = 0;
    private int medicineCommodity = 0;
    private int machinesCommodity  = 0;
    private int narcoticsCommodity = 0;
    private int robotsCommodity = 0;

    public enum Resource {
        NOSPECIALRESOURCES, MINERALRICH, MINERALPOOR,
        DESERT, LOTSOFWATER, RICHSOIL, POORSOIL,
        RICHFAUNA, LIFELESS, WEIRDMUSHROOMS, LOTSOFHERBS,
        ARTISTIC, WARLIKE
    }

    protected Planet(String name, int x, int y, int techLevelIndex, int resourceIndex) {
        super(x, y, techLevelIndex);
        this.name = name;
        for (Resource current : Resource.values()) {
            if (current.ordinal() == resourceIndex) {
                this.resource = current;
            }
        }
        createCommodities(techLevelIndex);
    }

    private void createCommodities(int techLevel){
        Random rand = new Random();
        waterCommodity = rand.nextInt(1000);
        fursCommodity = rand.nextInt(1000);
        if(techLevel >= 1){
            foodCommodity = rand.nextInt(1000);
        }
        if(techLevel >= 2){
            oreCommodity = rand.nextInt(1000);
        }
        if(techLevel >= 3){
            gamesCommodity = rand.nextInt(1000);
            firearmsCommodity = rand.nextInt(1000);
        }
        if(techLevel >= 4){
            medicineCommodity = rand.nextInt(1000);
            machinesCommodity = rand.nextInt(1000);
        }
        if(techLevel >= 5){
            narcoticsCommodity = rand.nextInt(1000);
        }
        if(techLevel >= 6){
            robotsCommodity = rand.nextInt(1000);
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

    public int getWaterCommodity(){return waterCommodity;}
    public int getFursCommodity(){return fursCommodity;}
    public int getFoodCommodity(){return foodCommodity;}
    public int getOreCommodity(){return oreCommodity;}
    public int getGamesCommodity(){return gamesCommodity;}
    public int getFirearmsCommodity(){return firearmsCommodity;}
    public int getMedicineCommodity(){return medicineCommodity;}
    public int getMachinesCommodity(){return machinesCommodity;}
    public int getNarcoticsCommodity(){return narcoticsCommodity;}
    public int getRobotsCommodity(){return robotsCommodity;}
    public void setWaterCommodity(int water){waterCommodity = water;}
    public void setFursCommodity(int furs){fursCommodity = furs;}
    public void setFoodCommodity(int food){foodCommodity = food;}
    public void setOreCommodity(int ore){oreCommodity = ore;}
    public void setGamesCommodity(int games){gamesCommodity = games;}
    public void setFirearmsCommodity(int firearms){firearmsCommodity = firearms;}
    public void setMedicineCommodity(int medicine){medicineCommodity = medicine;}
    public void setMachinesCommodity(int machines){machinesCommodity = machines;}
    public void setNarcoticsCommodity(int narcotics){narcoticsCommodity = narcotics;}
    public void setRobotsCommodity(int robots){robotsCommodity = robots;}

    //TODO default is getWater
    public int getIndexedResource(int index){
        switch(index){
            case 0: return getWaterCommodity();
            case 1: return getFursCommodity();
            case 2: return getFoodCommodity();
            case 3: return getOreCommodity();
            case 4: return getGamesCommodity();
            case 5: return getFirearmsCommodity();
            case 6: return getMedicineCommodity();
            case 7: return getMachinesCommodity();
            case 8: return getNarcoticsCommodity();
            case 9: return getRobotsCommodity();
            default:return getWaterCommodity();
        }
    }

}
