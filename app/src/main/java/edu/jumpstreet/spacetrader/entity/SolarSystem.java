package edu.jumpstreet.spacetrader.entity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * The SolarSystem entity represents a physical solar system with its attributes
 * as well as a container for Planet objects contained within the system.
 */
public class SolarSystem extends System {

    private static final String[] NAMES = new String[] {
            "Acamar",
            "Adahn",		// The alternate personality for The Nameless One in "Planescape: Torment"
            "Aldea",
            "Andevian",
            "Antedi",
            "Balosnee",
            "Baratas",
            "Brax",			// One of the heroes in Master of Magic
            "Bretel",		// This is a Dutch device for keeping your pants up.
            "Calondia",
            "Campor",
            "Capelle",		// The city I lived in while programming this game
            "Carzon",
            "Castor",		// A Greek demi-god
            "Cestus",
            "Cheron",
            "Courteney",	// After Courteney Cox…
            "Daled",
            "Damast",
            "Davlos",
            "Deneb",
            "Deneva",
            "Devidia",
            "Draylon",
            "Drema",
            "Endor",
            "Esmee",		// One of the witches in Pratchett's Discworld
            "Exo",
            "Ferris",		// Iron
            "Festen",		// A great Scandinavian movie
            "Fourmi",		// An ant, in French
            "Frolix",		// A solar system in one of Philip K. Dick's novels
            "Gemulon",
            "Guinifer",		// One way of writing the name of king Arthur's wife
            "Hades",		// The underworld
            "Hamlet",		// From Shakespeare
            "Helena",		// Of Troy
            "Hulst",		// A Dutch plant
            "Iodine",		// An element
            "Iralius",
            "Janus",		// A seldom encountered Dutch boy's name
            "Japori",
            "Jarada",
            "Jason",		// A Greek hero
            "Kaylon",
            "Khefka",
            "Kira",			// My dog's name
            "Klaatu",		// From a classic SF movie
            "Klaestron",
            "Korma",		// An Indian sauce
            "Kravat",		// Interesting spelling of the French word for "tie"
            "Krios",
            "Laertes",		// A king in a Greek tragedy
            "Largo",
            "Lave",			// The starting system in Elite
            "Ligon",
            "Lowry",		// The name of the "hero" in Terry Gilliam's "Brazil"
            "Magrat",		// The second of the witches in Pratchett's Discworld
            "Malcoria",
            "Melina",
            "Mentar",		// The Psilon home system in Master of Orion
            "Merik",
            "Mintaka",
            "Montor",		// A city in Ultima III and Ultima VII part 2
            "Mordan",
            "Myrthe",		// The name of my daughter
            "Nelvana",
            "Nix",			// An interesting spelling of a word meaning "nothing" in Dutch
            "Nyle",			// An interesting spelling of the great river
            "Odet",
            "Og",			// The last of the witches in Pratchett's Discworld
            "Omega",		// The end of it all
            "Omphalos",		// Greek for navel
            "Orias",
            "Othello",		// From Shakespeare
            "Parade",		// This word means the same in Dutch and in English
            "Penthara",
            "Picard",		// The enigmatic captain from ST:TNG
            "Pollux",		// Brother of Castor
            "Quator",
            "Rakhar",
            "Ran",			// A film by Akira Kurosawa
            "Regulas",
            "Relva",
            "Rhymus",
            "Rochani",
            "Rubicum",		// The river Ceasar crossed to get into Rome
            "Rutia",
            "Sarpeidon",
            "Sefalla",
            "Seltrice",
            "Sigma",
            "Sol",			// That's our own solar system
            "Somari",
            "Stakoron",
            "Styris",
            "Talani",
            "Tamus",
            "Tantalos",		// A king from a Greek tragedy
            "Tanuga",
            "Tarchannen",
            "Terosa",
            "Thera",		// A seldom encountered Dutch girl's name
            "Titan",		// The largest moon of Jupiter
            "Torin",		// A hero from Master of Magic
            "Triacus",
            "Turkana",
            "Tyrus",
            "Umberlee",		// A god from AD&D, which has a prominent role in Baldur's Gate
            "Utopia",		// The ultimate goal
            "Vadera",
            "Vagra",
            "Vandor",
            "Ventax",
            "Xenon",
            "Xerxes",		// A Greek hero
            "Yew",			// A city which is in almost all of the Ultima games
            "Yojimbo",		// A film by Akira Kurosawa
            "Zalkon",
            "Zuul"
    };

    private static final Set<String> usedNames = new HashSet<>();
    private static final Set<Integer[]> usedCoordinates = new HashSet<>();

    private final Set<Integer[]> usedPlanetCoordinates;
    private final Map<String, Planet> planets;
    private final String[][] planetLocations;

    /**
     * Creates a new SolarSystem instance based on inputed (random) values.
     * @param nameIndex         the index of the name to select
     * @param x                 the x coordinate of the SolarSystem
     * @param y                 the y coordinate of the SolarSystem
     * @param techLevelIndex    the index of the tech level of the SolarSystem
     */
    public SolarSystem(int nameIndex, int x, int y, int techLevelIndex) {
        super(x, y, techLevelIndex);
        planetLocations = new String[10][10];
        Integer[] coordinate = new Integer[] { x, y };
        int tempX = x;
        int tempY = y;
        while (usedCoordinates.contains(coordinate)) {
            tempX = ((5 * tempX) + 1) % Universe.X_BOUNDS;
            tempY = ((2 * tempY) + 1) % Universe.Y_BOUNDS;
            coordinate = new Integer[] { tempX, tempY };
        }
        this.x = tempX;
        this.y = tempY;
        usedCoordinates.add(coordinate);
        int tempNameIndex = nameIndex;
        if (usedNames.contains(NAMES[tempNameIndex])) {
            do {
                tempNameIndex++;
            } while (usedNames.contains(NAMES[tempNameIndex]));
        }
        name = NAMES[tempNameIndex];
        usedNames.add(NAMES[tempNameIndex]);
        usedPlanetCoordinates = new HashSet<>();
        planets = new HashMap<>();
    }

    /**
     *
     * @param planet Planet object
     * @return boolean if the planet is in the solar system
     */
    public boolean isInSolarSystem(Planet planet){
        return planets.containsValue(planet);
    }

    /**
     * Gets the length of the NAMES list.
     * @return  the length of NAMES
     */
    public static int getNamesLength() { return NAMES.length; }

    /**
     * This method returns a planet object for a given planet name
     * @param name the name of the planet
     * @return planet object with given name
     */
    public Planet getPlanet(String name) {
        return planets.get(name);
    }

    /**
     * This method creates a new planet
     * @param x x - coordinate
     * @param y y - coordinate
     * @param resourceIndex a number coordinating to the resource index
     */

    public void addNewPlanet(int x, int y, int resourceIndex) {
        Integer[] coordinate = new Integer[] { x, y };
        int tempX = x;
        int tempY = y;
        while (usedPlanetCoordinates.contains(coordinate)) {
            tempX = ((5 * tempX) + 1) % Universe.X_BOUNDS;
            tempY = ((2 * tempY) + 1) % Universe.Y_BOUNDS;
            coordinate = new Integer[] { tempX, tempY };
        }
        usedPlanetCoordinates.add(coordinate);
        String planetName = this.name + generateEnding();
        Planet newPlanet = new Planet(planetName, tempX, tempY, techLevel.ordinal(), resourceIndex);
        planets.put(newPlanet.getName(), newPlanet);
        planetLocations[newPlanet.x][newPlanet.y] = newPlanet.getName();
    }

    /**
     *
     * @return a String[][]  of the planet location
     */
    public String[][] getPlanetLocations() {
        return planetLocations.clone();
    }

    /**
     *
     * @return String based on the size of the planet
     */
    private String generateEnding() {
        String returnString ;
        switch(planets.size()) {
            case 0:
                returnString = " Prime";
                break;
            case 1:
                returnString = " II";
                break;
            case 2:
                returnString = " III";
                break;
            case 3:
                returnString = " IV";
                break;
            case 4:
                returnString = " V";
                break;
            case 5:
                returnString = " VI";
                break;
            case 6:
                returnString = " VII";
                break;
            case 7:
                returnString = " VIII";
                break;
            case 8:
                returnString = " IX";
                break;
            case 9:
                returnString = " X";
                break;
            case 10:
                returnString = " XI";
                break;
            case 11:
                returnString = " XII";
                break;
            case 12:
                returnString = " XIII";
                break;
            case 13:
                returnString = " XIV";
                break;
            case 14:
                returnString = " XV";
                break;
            case 15:
                returnString = " XVI";
                break;
            default:
                returnString = " NOT IMPLEMENTED!";
        }
        return returnString;
    }

    /**
     * Override the getName()
     * @return String name of the solar system
     */
    @Override
    public String getName(){
        return super.name;
    }
}
