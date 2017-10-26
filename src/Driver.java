/*
*@description Driver class
*
*@author Team 4B : Linh Lam, So Negishi, Hoang Pham, Duc Nguyen
*@version October 26, 2017
*/

import java.io.*;
import java.util.*;


public class Driver {
	
	private static Location curLocation = new Location();
	private static List<Item> curItems = new ArrayList<>();

    //set attributes to current location
	private static void setLocation(Location curLoc) {
		curLocation = curLoc;

		for (int i = 0; i<curLocation.getItem().size(); i++) { //TODO
			curItems.add(curLocation.getItem().get(i));
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
        // All items given in the game
        Item mirror = new Item("mirror", "tool", "This is the mirror of totally deflecting light. Use it to defend yourself and kill the dragon.");
        Item scroll = new Item("scroll", "hint", "The ancient scroll says: 'You need to find three legendary artifacts to defeat the dragon. They are the pearl of the Sun, the divine sword of cutting things, and the mirror of totally deflecting light.' ");
        Item torch = new Item("torch", "tool", "a small torch to light up the way");
        Item pearl = new Item("pearl", "tool", "This is the pearl of the Sun. Use it to defend yourself and kill the dragon.");
        Item sword = new Item ("sword", "weapon", "This is the divine sword of cutting things. Use it to kill the dragon and defend yourself.");

        // Add items into an ArrayList
        List<Item> itemList = new ArrayList<>();
        itemList.add(mirror);
        itemList.add(scroll);
        itemList.add(torch);
        itemList.add(sword);
        itemList.add(pearl);

        //
        List<Item> entranceItem = new ArrayList<>(Arrays.asList(torch,scroll)); //items given at entrance
        List<Item> indoorItem = new ArrayList<>(Arrays.asList(mirror)); //items given in the 1st room //TODO
        
        
        // Add location(s)
        Location entrance = new Location("entrance", "This is the starting position of the game. You are standing in front of a door.",entranceItem);
        Location inside = new Location("inside", "You are now standing in side the house. There is a small mirror hung on the wall. There are 2 locked doors: One to the left, One to the right.", indoorItem);
        

    	Scanner in = new Scanner( System.in );
		System.out.print( "Welcome to the The Legendary Tale of the Dragon Slayer game!\nDo you want to start the game? (y/n): " );
		String start = in.nextLine().toLowerCase().trim();
		
		while ( !start.equals("n") && !start.equals("y") ) {
			System.out.println("Please enter y or n to start or close the game");
			start = in.nextLine().toLowerCase().trim();
		}
		
		if ( start.equals("n") ) { //TODO
            System.out.println( "Thanks for playing" );
            System.exit(1);

        } else if( start.equals("y") ) {
			System.out.println("\nYou are an adventurer going on a quest to destroy the mighty dragon that is causing terror to the miserable village." +
					"At the beginning of the game, you are given an ancient scroll and a torch." );
			setLocation(entrance);
	
            String command;
            while ( true ) {
                System.out.print( "\nEnter a command: " );
                command = in.nextLine().toLowerCase().trim();

                if ( command.contains("look") ) {
                    System.out.println( String.format("\tCurrent location: %s", curLocation.getDesc()) );
                    System.out.println( String.format("\t%d\nItem found there:", curLocation.retrieveNumOfItems()) );
                    for (int i = 0; i<curLocation.getItem().size(); i++) { //TODO
                    	System.out.println("\t\t"+ curLocation.getItem().get(i).getName());
                    }
                    
                    System.out.println("\tYou are currently having " + curItems.size() + " items. Namely: ");
                    System.out.println( String.format("\tYou are currently having %d items.\nNamely:", curItems.size()) );
                    for(int i =0; i<curItems.size(); i++) { //TODO
                    	System.out.println( String.format("\t\t%s", curItems.get(i).getName()) );
                    }

                } else if ( command.contains("examine") ) {
                	// Get the item with the given name from the location and print its description
                    // if the item is there, print the description of it
                    // if not, print some sort of error message

                	boolean found = false;

                    // If just type "examine", as what to examine (several items at a time is ok)
                	if ( command.equals("examine") ) {
                		System.out.println( String.format("\tYou are currently having %d items." +
                                "\nTo see their names, try 'look' command. What do you want to examine?", curItems.size()) );
                		command = in.nextLine().toLowerCase().trim(); //TODO
                    }

                    // If command contains the item name that is included in the current state, print the details of the item
                	for(int i = 0 ; i< curItems.size(); i++) { //TODO
                		if ( command.contains(curItems.get(i).getName()) ) {
                			curItems.get(i).print();
                			found = true;
                		}
                	}
                	if ( !found ) {
                		System.out.println( "Cannot find the item." );
                	}

                } else if ( command.contains("open") && command.contains("door") ) {
                    // Open the door
                	System.out.println("You try to open the door and realized that it was locked with an ancient lock." +
							"On the lock, there are four figures: a circle, a rainbow, a square, and a triangle." +
							"Under each figure, there is a place for you to put one digit from 0 to 9 on it." +
							"To open the door, you have to guess correctly all 4 digits corresponding to 4 figures.");
                	System.out.println("Please enter the 4 digits (without spaces; for example, 0000)");
                	String passcode = in.nextLine();

                	int attempt = 1;
                	int hintNo = 1;

					// If enter wrong passcode (12 trials and 2 hints before giving the option to quit)
                	while ( !passcode.equals("0743") ) {
						// If wrong passcode more than 3 times after having 2 hints, offer the quit option. If don't quit, start over.
						if ((attempt == 2) && (hintNo > 3)) {
							System.out.println("You tried many time. Do you want to quit? (y/n)"); //TODO
							String quitBool = in.nextLine().toLowerCase().trim();
							if (quitBool.equals("y")) { //TODO
								System.out.println("Thanks for playing!");
								System.exit(1);

							} else if (quitBool.equals("n")) {
								break;
							}

						} else if ((attempt < 3) && (hintNo <= 4)) {
							// If wrong passcode <= 3 times before having 2 hints
							attempt++;
							System.out.println("Please try again!");
							passcode = in.nextLine();

						} else if ((attempt == 3) && (hintNo == 3)) {
							// If wrong passcode > 3 times after having 2 hints
							System.out.println("You cannot get more hint." +
									"Think about the number of edges/colors in each figure.");
							System.out.println("Please try again!");
							passcode = in.nextLine();
							attempt = 0;
							hintNo++;

						} else if ( (attempt == 3) && (hintNo <= 3) ) {
							// If wrong passcode > 3 times before having 2 hints
							System.out.println("You tried 3 times." +
									"The lock automatically gives you a hint. Enter the figure that you want to see hint");
							String hint = in.nextLine();

							while (!(hint.contains("rainbow") || hint.contains("circle") || hint.contains("square") || hint.contains("triangle"))) {
								System.out.println("Please enter one of a given figure. For example, 'rainbow'");
								hint = in.nextLine();
							}

							if (hint.contains("rainbow")) {
								System.out.println("The code for rainbow is 7");
								hintNo++;

							} else if (hint.contains("triangle")) {
								System.out.println("The code for triangle is 3");
								hintNo++;

							} else if (hint.contains("square")) {
								System.out.println("The code for square is 4");
								hintNo++;

							} else if (hint.contains("circle")) {
								System.out.println("The code for circle is 0");
								hintNo++;
							}

							attempt = 0;
						}
					}

					// If passcode is correct
                	if ( passcode.contains("0743") ) {
                    	System.out.println("You successfully broke the lock. The door is opened and you enter the house.");
                    	setLocation(inside);
                	}
                } else if ( command.equals("quit") ) { //TODO
                	System.out.println("Thanks for playing!");
                    break;

                } else {
                    System.out.println("I don't know how to do that.");
                }
            }
        }

		in.close();
    }
}