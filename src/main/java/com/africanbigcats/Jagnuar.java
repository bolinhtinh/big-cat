package com.africanbigcats;

/*
 * Panthera base class that simulates GPS information
 */
public class Jagnuar extends Panthera {

    // constructor
    public Jagnuar(String name) {

        // call the super-class (parent) to instatiate it
        super(name);

        // initialize attributes
        this.setSpecies("jagnuar");

    }

    // serializes attributes into a string
    @Override // override superclass method
    public String toString() {
        String s;

        // since the object is complex, we return a JSON formatted string
        s = "{ ";
        s += "name: " + name();
        s += ", ";
        s += "species: " + species();
        s += ", ";
        s += "longitude: " + longitude();
        s += ", ";
        s += "latitude: " + latitude();
        s += ", ";
        s += "weight: " + weight();
        s += ", ";
        s += "speed: " + speed();
        s += ", ";
        s += "fur: " + fur();
        s += ", ";
        s += "sleepsInTrees: " + sleepsInTrees();
        s += " }";

        return s;

    }

    public String fur() {
        return "spots";
    }

    public boolean sleepsInTrees() {
        return true;
    }

}
