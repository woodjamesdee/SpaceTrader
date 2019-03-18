package edu.jumpstreet.spacetrader.model;

import edu.jumpstreet.spacetrader.entity.Planet;
import edu.jumpstreet.spacetrader.entity.SolarSystem;

public class TravelInteractor {
    private Planet travelPlanet;
    private SolarSystem travelSS;

    public TravelInteractor (){

    }

    public void ChangeTravelPlanet(Planet planet){
        travelPlanet = planet;
    }

    public void ChangeTravelSolarSystem(SolarSystem solarSystem){
        travelSS = solarSystem;
    }

    public Planet getTravelPlanet(){
        return travelPlanet;
    }

    public SolarSystem getTravelSS(){
        return travelSS;
    }
}
