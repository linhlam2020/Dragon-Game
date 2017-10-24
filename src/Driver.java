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
        Item light = new Item("flashlight", "tool", "a small LED flashlight");
        Item pearl = new Item("pearl", "tool", "This is the pearl of the Sun. Use it to defend yourself and kill the dragon.");
        Item sword = new Item ("sword", "weapon", "This is the divine sword of cutting things. Use it to kill the dragon and defend yourself.");

        // Add items into an arraylist
        List<Item> itemList = new ArrayList<>();
        itemList.add(mirror);
        itemList.add(note);
        itemList.add(light);
        itemList.add(sword);
        itemList.add(pearl);
	

        // Add location(s)
        List<Item> entranceItem = new ArrayList<Item>(Arrays.asList(mirror,note));
        Location curLocation = new Location();
        Location entrance = new Location("entrance", "a starting position of the game",entranceItem);

    	Scanner in = new Scanner( System.in );
		System.out.print( "\nDo you want to start the game? (y/n): ");
		String start = in.nextLine().toLowerCase().trim();
		
		while (!start.equals("n") && !start.equals("y"))
		{
			System.out.println("Please enter y or n to start or close the game");
			start = in.nextLine().toLowerCase().trim();
		}
		
		if (start.equals("n"))
		{
			System.out.println("Thanks for playing");
			System.exit(1);
		}
		
		else if( start.equals("y") ) {
			curLocation=entrance;
			entrance.print();
	
            String command;
            while ( true ) {
                System.out.print("\nEnter a command: ");
                command = in.nextLine().toLowerCase().trim();

                if (command.contains("look")) {
                    System.out.println( String.format("\tDescription of the location: %s", curLocation.getDesc()) );
                    System.out.println( "\t " + curLocation.retrieveNumOfItems() + " Items found there:" );
                    for (int i = 0; i<curLocation.getItem().size(); i++)
                    {
                    	System.out.println("\t\t"+ curLocation.getItem().get(i).getName());	}}
                else if (command.contains("examine")) {
                    // Get the item with the given name from the location and print its description
                    // if the item is there, print the description of it
                    // if not, print some sort of error message
                	boolean a = false;
                	for(int i = 0 ; i< curLocation.getItem().size(); i++)
                	{
                		if (command.contains(curLocation.getItem().get(i).getName()))
                		{
                			curLocation.getItem().get(i).print();
                			a = true;
                		}
                	}
                	if (a == false)
                		System.out.println("Cannot find the item");
                			
                } 
                else if (command.equals("quit")) {
		    System.out.println("Thanks for playing!");
                    break;
                } else {
                    System.out.println("I don’t know how to do that.");
                }
            }
        }
		

		in.close();
    }
}
