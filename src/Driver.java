/*
*@description Driver class
*
*@author Team 4B : Linh Lam, So Negishi, Hoang Pham, Duc Nguyen
*@version October 23, 2017
*/

import java.io.*;
import java.util.*;


public class Driver {

	public static void main(String[] args) throws FileNotFoundException {
        // Items
        Item mirror = new Item("mirror", "tool", "This is the mirror of totally deflecting light. Use it to defend yourself and kill the dragon.");
        Item note = new Item("note", "hint", "You need to find three legendary artifacts to defeat the dragon. They are the pearl of the Sun, the divine sword of cutting things, and the mirror of totally deflecting light.");
	Item gun = new Item("gun", "weapon", "a combat shotgun");
        Item light = new Item("flashlight", "tool", "a small LED flashlight");
	Item pearl = new Item("pearl", "tool", "This is the pearl of the Sun. Use it to defend yourself and kill the dragon.");
        Item sword = new Item ("sword", "weapon", "This is the divine sword of cutting things. Use it to kill the dragon and defend yourself.");

        // Add items into an arraylist
        List<Item> itemList = new ArrayList<>();
        itemList.add(mirror);
        itemList.add(note);
        itemList.add(light);
        itemList.add(gun);
	itemList.add(sword);
	

        // Add location(s)
        Location entrance = new Location("entrance", "a starting position of the game");
        entrance.addItem(mirror);
        entrance.addItem(note);

        entrance.print();
        entrance.retrieveName();
        entrance.retrieveNumOfItems();

//		for ( Item i : itemList )
//			i.print();

		Scanner in = new Scanner( System.in );
		System.out.print( "\nDo you want to start the game? (y/n): ");
		String start = in.nextLine().toLowerCase().trim();
		if( start.equals("y") ) {
            String command;
            while ( true ) {
                System.out.print("\nEnter a command: ");
                command = in.nextLine().toLowerCase().trim();
//                System.out.println(command);

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
