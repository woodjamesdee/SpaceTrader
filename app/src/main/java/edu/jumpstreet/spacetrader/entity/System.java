package edu.jumpstreet.spacetrader.entity;

import android.os.Parcelable;

/**
 * The System class represents a location which is located in space and has a TechLevel.
 */
public abstract class System{

    protected String name;
    protected int x, y;
    protected TechLevel techLevel;

    /**
     * The TechLevel enum represents the technological advancement of the entire System. Affects modifiers.
     */
    public enum TechLevel {
        PreAgriculture, Agriculture,
        Medieval, Renaissance,
        EarlyIndustrial, Industrial,
        PostIndustrial, HiTech
    }

    /**
     * Constructor to be overriden, creates a new System with a given x and y coordinate, as well as the index of the TechLevel index
     * @param x                 the x coordinate of the System
     * @param y                 the y coordinate of the System
     * @param techLevelIndex    the TechLevel to use (given through index)
     */
    protected System(int x, int y, int techLevelIndex) {
        this.x = x;
        this.y = y;
        for (TechLevel current : TechLevel.values()) {
            if (current.ordinal() == techLevelIndex) {
                this.techLevel = current;
            }
        }
    }

    public System(){

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

    /**
     * Sets the TechLevel of this SolarSystem
     * @param techLevel the new TechLevel of the SolarSystem
     */
    public void setTechLevel(TechLevel techLevel) {
        this.techLevel = techLevel;
    }
}
