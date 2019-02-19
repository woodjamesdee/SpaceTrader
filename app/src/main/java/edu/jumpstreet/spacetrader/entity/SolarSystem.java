package edu.jumpstreet.spacetrader.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * The SolarSystem entity represents a physical solar system with its attributes
 * as well as a container for Planet objects contained within the system.
 */
public class SolarSystem {

    private static final int X_COORDINATE_BOUNDS = 150;
    private static final int Y_COORDINATE_BOUNDS = 100;

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

    private static Set<String> usedNames = new HashSet<>();
    private static Set<Integer[]> usedCoordinates = new HashSet<>();

    private String name;
    private int x, y;
    private TechLevel techLevel;


    enum TechLevel {
        PreAgriculture, Agriculture,
        Medieval, Renaissance,
        EarlyIndustrial, Industrial,
        PostIndustrial, HiTech
    }

    /**
     * Creates a new SolarSystem instance based on inputed (random) values.
     * @param nameIndex         the index of the name to select
     * @param x                 the x coordinate of the SolarSystem
     * @param y                 the y coordinate of the SolarSystem
     * @param techLevelIndex    the index of the tech level of the SolarSystem
     */
    public SolarSystem(int nameIndex, int x, int y, int techLevelIndex) {
        if (usedNames.contains(NAMES[nameIndex])) {
            do {
                nameIndex++;
            } while (usedNames.contains(NAMES[nameIndex]));
        }
        name = NAMES[nameIndex];
        usedNames.add(NAMES[nameIndex]);
        Integer[] coordinate = new Integer[] { x, y };
        while (usedCoordinates.contains(coordinate)) {
            x = (x + 1) % X_COORDINATE_BOUNDS;
            y = (y + 1) % Y_COORDINATE_BOUNDS;
            coordinate = new Integer[] { x, y };
        }
        this.x = x;
        this.y = y;
        usedCoordinates.add(coordinate);
        for (TechLevel current : TechLevel.values()) {
            if (current.ordinal() == techLevelIndex) {
                this.techLevel = current;
            }
        }
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
