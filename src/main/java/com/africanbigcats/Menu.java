package com.africanbigcats;
import java.lang.Math;
import java.io.IOException;
import java.util.*;

/*
 * Menu class for the african big cat app
 */
public class Menu {

    // attributes
    private Scanner input;

    // constructor
    public Menu() {

        // initialize attributes
        this.input = new Scanner(System.in);

    }

    // prints the menu
    public void print() {
        printLine();
        System.out.println("African Big Cats App");
        printLine();
        /*
         * DONE TIP: In this area of the code, the additional commands need to be
         * created and added to the menu.
         */
        printCommand('c', "[C]reates a big cat");
        printCommand('d', "[D]eletes a big cat");
        printCommand('f', "[F]inds a big cat");
        printCommand('l', "[L]ists all big Cats");
        printCommand('r', "[R]isk report");
        printCommand('w', "[W]arning report");
        printCommand('q', "[Q]uits");
        printLine();
    }

    private static void printLine() {
        System.out.println("----------------------------------------------------------");
    }

    private static void printCommand(Character command, String desc) {
        System.out.printf("%s\t%s\n", command, desc);
    }

    // get first character from input
    public Character getCommand() {
        Character command = '_';
        String rawInput = input.nextLine();
        if (rawInput.length() > 0) {
            rawInput = rawInput.toLowerCase();
            command = rawInput.charAt(0);
        }
        return command;
    }

    // command switch
    public Boolean executeCommand(Character command, LinkedList<Panthera> catList) {
        Boolean success = true;
        /*
         * DONE TIP: In this area of the code, the additional commands need to be
         * created and added.
         */
        switch (command) {
        case 'c':
            executeCreate(catList);
            break;
        case 'd':
            executeDetele(catList);
            break;
        case 'f':
            executeFind(catList);
            break;
        case 'l':
            executeList(catList);
            break;
        case 'r':
            executeRisk(catList);
            break;
        case 'w':
            executeWarning(catList);
            break;
        case 'q':
            executeQuit();
            break;
        default:
            System.out.println("ERROR: Unknown commmand");
            success = false;
        }
        return success;
    }

    // update the position of all the cats
    public void update(LinkedList<Panthera> catList) {

        // update by moving all the cats
        for (Panthera cat : catList) {
            cat.move();
        }

    }

    // quit the app
    public void executeQuit() {

        // close the scannner
        input.close();

        System.out.println();
        printLine();
        System.out.println("Thank you for using the African Big Cats App!");
        printLine();
        System.out.println();

    }

    public Panthera getNewCat(String name) {
        /*
         * TIP: In this area of the code, students need to get input from the user for
         * the type of cat and create the correct type.
         * 
         * Currently, the code always create a Tiger. But, support for Lions and Jaguars
         * also needs to be added.
         * 
         */
        // get the number 
        System.out.print("Enter a 1 for Tiger, 2 for Lion, 3 for Jaguar:");
        Integer numberInput = input.nextInt();
        System.out.println();
        Panthera result;
        switch (numberInput) {
        case 1:
            result = new Tiger(name); // DO NOT REMOVE
            break;
        case 2:
            result = new Lion(name);
            break;
        case 3:
            result = new Jagnuar(name);
            break;   
        default:
            result = new Tiger(name); // DO NOT REMOVE
            System.out.println("ERROR: Invalid big cat type. Creating a tiger named " + name);
            break;
        }
        System.out.println("STATUS: " + name + " has been added.");
        return result; // DO NOT REMOVE
    }

    // create a cat, if it's unique
    public void executeCreate(LinkedList<Panthera> catList) {
        // get the name
        System.out.println();
        System.out.print("Enter a name for the big cat to create: ");
        String name = input.nextLine();
        System.out.println();
        Panthera cat = getNewCat(name);
        /*  DEBUG
            TIP:
            In this area of the code, students would need to add in checking if the cat name
            already exists in order to prevent duplicates
        */ 
        if (!catList.contains(cat.name())){
            catList.add(cat);
            System.out.println("Added:" + cat.name());
        } else {
            System.out.println("ERROR: Duplicate " + cat.name());
        }
    }

    // list all big cats
    public void executeList(LinkedList<Panthera> catList) {

        System.out.println();
        printLine();
        System.out.println("African Big Cat List");
        printLine();

        Panthera cat;
        if (catList.size() > 0) {
            for (Integer i = 0; i < catList.size(); i++) {
                cat = catList.get(i);
                System.out.println(cat);
            }
        } else {
            System.out.println("There are no African Big Cats. :(");
        }

        System.out.println();

    }

    /*
     * TIP: Additional methods and functionality need to be added to this class.
     */
    private void executeWarning(LinkedList<Panthera> catList) {
        System.out.println();
        System.out.print("Enter your current longitude: ");
        Float my_longitude = input.nextFloat();
        System.out.print("Enter your current latitude: ");
        Float my_latitude = input.nextFloat();

        printLine();
        System.out.println("African Big Cats Warning Report");
        printLine();

        double max_x = 0;
        int max_x_index = 0; 
        for (int i=0 ; i<catList.size() ; i++) {
            if (Math.abs( catList.get(i).longitude() - my_longitude) > max_x){
                max_x = Math.abs(catList.get(i).longitude());
                max_x_index = i;
            }
        }
        double max_y = 0;
        int max_y_index = 0;
        for (int j=0 ; j<catList.size() ; j++) {    
            if (Math.abs( catList.get(j).latitude() - my_latitude) > max_y){
                max_y = Math.abs(catList.get(j).longitude());
                max_y_index = j;
            }
        }
        printLine();
        double most_safe_distance = 0;
        if ( max_y > max_x) {
            most_safe_distance = max_y;
            System.out.println("The closest cat is " +catList.get(max_y_index).name()+ " which is at a distance of " +most_safe_distance);
        } else {
            most_safe_distance = max_x;
            System.out.println("The closest cat is " +catList.get(max_x_index).name()+ " which is at a distance of " +most_safe_distance);
        }
    }

    private void executeRisk(LinkedList<Panthera> catList) {
        System.out.println();
        System.out.print("Enter a name for the first big cat: ");
        String cat1 = input.nextLine();
        System.out.print("Enter a name for the second big cat: ");
        String cat2 = input.nextLine();

        printLine();
        System.out.println("African Big Cats Risk Report");
        printLine();
        Integer cat1_index = 0;
        for (int i=0 ; i<catList.size() ; i++) {
            if (catList.get(i).name() == cat1) { //cat1_index
                cat1_index = i;
            }
        }
        Integer cat2_index = 0;
        for (int j=0 ; j<catList.size() ; j++) {
            if (catList.get(j).name() == cat2) { //cat2_index
                cat2_index = j;
            }
        }
        double x1 = catList.get(cat1_index).longitude();
        double x2 = catList.get(cat2_index).longitude();
        double y1 = catList.get(cat1_index).latitude();
        double y2 = catList.get(cat2_index).latitude();

        printLine();
        double x = Math.abs(x2-x1);
        double y = Math.abs(y2-y1);
        double distance = Math.sqrt(Math.pow(x,x)+Math.pow(y,y));
        System.out.println("The distance between " + cat1 + " and " + cat2 + " is " + distance);
    }

    private void executeFind(LinkedList<Panthera> catList) {
        System.out.println();
        System.out.print("Enter name: ");
        String name = input.nextLine();
        System.out.println();

        for (int i=0 ; i<catList.size() ; i++) {
            if (catList.get(i).name() == name) {
                System.out.println("Name found at index: " + i);
                return;
            }
        }
        System.out.println(name + " not exist");
    }

    private void executeDetele(LinkedList<Panthera> catList) {
        System.out.println();
        System.out.print("Enter name: ");
        String name = input.nextLine();
        System.out.println();

        for (int i=0 ; i<catList.size() ; i++) {
            if (catList.get(i).name() == name) { //troubleshoot
                catList.remove(i);
                System.out.println("Name removed");
                return;
            }
        }
        System.out.println(name + " not exist");
    }

}
