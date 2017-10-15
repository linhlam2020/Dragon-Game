/**
*@description Driver class
*
*@author Team 4B : Linh Lam, So Negishi, Hoang Pham, Duc Nguyen
*@version October 15, 2017
*/

import java.io.*;
import java.util.*;


public class Driver {

	public static void main(String[] args) throws FileNotFoundException {
        // Items
        Item mirror = new Item("mirror", "tool", "a mirror");
        Item note = new Item("note", "hint", "a note with hints to open a door");
        Item light = new Item("flashlight", "tool", "a small LED flashlight");
        Item gun = new Item("gun", "weapon", "a combat shotgun");

        // Add items into an arraylist
        List<Item> itemList = new ArrayList<>();
        itemList.add(mirror);
        itemList.add(note);
        itemList.add(light);
        itemList.add(gun);

		/*
		for ( Item i : itemList )
			i.print();
		*/

		Scanner in = new Scanner( System.in );
		System.out.print( "\nDo you want to start the game? (y/n): ");
		String start = in.nextLine().toLowerCase().trim();
		if( start.equals("y")) {
            String command;
            while (true) {
                System.out.print("\nEnter a command: ");
                command = in.nextLine().toLowerCase().trim();
                //System.out.println(command);

                if (command.contains("look")) {
                    System.out.println("Description of the location: ");
                    System.out.println("Items found there: ");
                } else if (command.contains("examine")) {
                    // Get the item with the given name from the location and print its description
                    // if the item is there, print the description of it
                    // if not, print some sort of error message
                    continue;
                } else if (command.contains("hint")) {
                    System.out.println("Hints are ...");
                } else if (command.equals("quit")) {
                    break;
                } else {
                    System.out.println("I don’t know how to do that.");
                }
            }
        }

		in.close();
    }
}