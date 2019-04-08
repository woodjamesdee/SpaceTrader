package edu.jumpstreet.spacetrader.entity;

import java.io.Serializable;

/**
 * The System class represents a location which is located in space and has a TechLevel.
 */
public abstract class System implements Serializable {

    String name;
    int x;
    int y;
    TechLevel techLevel;

    /**
     * The TechLevel enum represents the technological advancement
     * of the entire System. Affects modifiers.
     */
    public enum TechLevel {
        PreAgriculture, Agriculture,
        Medieval, Renaissance,
        EarlyIndustrial, Industrial,
        PostIndustrial, HiTech;

        /**
         * Gets the desired TechLevel by integer.
         * @param index the index of TechLevel
         * @return the TechLevel
         */
        public static TechLevel getTechLevelByIndex(int index) {
            return TechLevel.values()[index];
        }
    }

    /**
     * Constructor to be overriden, creates a new System with a given x
     * and y coordinate, as well as the index of the TechLevel index
     * @param x                 the x coordinate of the System
     * @param y                 the y coordinate of the System
     * @param techLevelIndex    the TechLevel to use (given through index)
     */
    System(int x, int y, int techLevelIndex) {
        this.x = x;
        this.y = y;
        for (TechLevel current : TechLevel.values()) {
            if (current.ordinal() == techLevelIndex) {
                this.techLevel = current;
            }
        }
    }

    /*
    public void writeToParcel(Parcel out, int flags){
        out.writeString(name);
        out.writeInt(x);
        out.writeInt(y);
        if(techLevel != null) {
            out.writeInt(techLevel.ordinal());
        }else{
            out.writeInt(0);
        }
    }

    public void readFromParcel(Parcel in){
        name = in.readString();
        x = in.readInt();
        y = in.readInt();
        techLevel = TechLevel.values()[in.readInt()];
    }
    */

    /**
     * System constructor
     */
    System(){

    }

    /**
     * Gets the name of this SolarSystem
     * @return  the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the x coordinate of the SolarSystem
     * @return  the x coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the y coordinate of the SolarSystem
     * @return  the y coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Gets the TechLevel of this SolarSystem
     * @return  the TechLevel
     */
    public TechLevel getTechLevel() {
        return techLevel;
    }


    /*
     * Sets the TechLevel of this SolarSystem
     * @param techLevel the new TechLevel of the SolarSystem
     */
    //public void setTechLevel(TechLevel techLevel) {
    //    this.techLevel = techLevel;
    //}
}
