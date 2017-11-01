/*
*@description Driver class
*
*@author Team 4B : Linh Lam, So Negishi, Hoang Pham, Duc Nguyen
*@version November 1, 2017
*/

import java.util.List;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class Driver {
	
	private static Location curLocation = new Location();
	private static ContainerItem inventory = new ContainerItem();

	// Set attributes to current location
	private static void setLocation( Location curLoc ) {
//		for ( Item i : curLoc.getItem() ) {
//			curLocation.addItem(i);
//		}
		curLocation = curLoc;
	}

	public static void main(String[] args) {
        // All items given in the game
        Item mirror = new Item("mirror",
				"tool",
				"This is the mirror of totally deflecting light." +
						" Use it to defend yourself and kill the dragon.");
        Item scroll = new Item("scroll",
				"hint",
				"The ancient scroll says," +
						" 'You need to find three legendary artifacts to defeat the dragon." +
						" They are the pearl of the Sun, the divine sword of cutting things," +
						" and the mirror of totally deflecting light.'" );
        Item torch = new Item("torch",
				"tool",
				"a small torch to light up the way" );
        Item pearl = new Item("pearl",
				"tool",
				"This is the pearl of the Sun. Use it to defend yourself and kill the dragon." );
        Item sword = new Item ("sword",
				"weapon",
				"This is the divine sword of cutting things." +
						" Use it to kill the dragon and defend yourself." );


        // Create Item lists
        List<Item> entranceItem = new ArrayList<>(Arrays.asList(torch,scroll)); //items given at entrance
		List<Item> indoorItem = Collections.singletonList(mirror); //items given in the 1st room

        // Add location(s)
        Location entrance = new Location("entrance",
				"This is the starting position of the game. You are standing in front of a door.",
				entranceItem);
        Location inside = new Location("inside the house",
				"You are now standing in side the house." +
						" There is a small mirror hung on the wall." +
						" There are 2 locked doors: One to the left, One to the right.",
				indoorItem);
        

    	Scanner in = new Scanner( System.in );

		System.out.print( "Welcome to the The Legendary Tale of the Dragon Slayer game!" +
				"\nDo you want to start the game? (y/n): " );
		String start = in.nextLine().toLowerCase().trim();
		
		while ( !start.equals("n") && !start.equals("y") ) {
			System.out.println( "Please enter y or n to start or close the game." );
			start = in.nextLine().toLowerCase().trim();
		}
		
		if ( start.equals("n") ) { //TODO
            System.out.println( "Thanks for playing" );
            System.exit(1);

        } else if( start.equals("y") ) {
			System.out.println("\nYou are an adventurer going on a quest to destroy the mighty dragon " +
					" that is causing terror to the miserable village." +
					"\nAt the beginning of the game, you are given an ancient scroll and a torch." );
			setLocation( entrance );
	
            String command;
            while ( true ) {
				System.out.print( "\nEnter a command: " );
				command = in.nextLine().toLowerCase().trim();

				if ( command.contains("look") ) {
					System.out.println( String.format("\n\tCurrent location: %s",
							curLocation.getDesc()) );
					System.out.println( String.format("\tItem(s) found there: %d item(s)",
							curLocation.retrieveNumOfItems()) );

                    for( Item i : curLocation.getItem() ) {
                    	System.out.println( String.format("\t\t%s", i.getName()) );
					}

				} else if ( command.contains("take") ) {
					if ( command.contains("from") ) {
						String temp = command.replaceAll("take", "").replaceAll("from", "").trim();
						String[] words = temp.split(" ");
						String name = words[0];
						String tempContainer = words[1];


						//TODO: take item from container and put it in the inventory method
//						for ( Item i : tempContainer.getCollection() ) { //TODO
//							if ( i.getName().equals(name) ) {
//								inventory.addItem(i);
//								System.out.println( String.format("Taken %s from %s", i.getName(), name) );
//								tempContainer.removeItem(i); //TODO
//							}
//						}

					} else {
						String temp = command.replaceAll("take", "").trim();
						int count = 0;

						for ( Item i : curLocation.getItem() ) {
							if ( i.getName().equals(temp) ) {
								inventory.addItem(i);
								System.out.println( String.format("Taken %s", i.getName()) );
								curLocation.getItem().remove(i);
								count++;
								break;
							}
						}

						if ( count == 0 ) {
							System.out.println( "The item you entered doesn't exist in this location." );
						}
					}

				} else if ( command.contains("drop") ) {
					String temp = command.replaceAll("drop", "").trim();
					int count = 0;

					for ( Item i : inventory.getCollection() ) {
						if ( i.getName().equals(temp) ) {
							curLocation.getItem().add(i);
							inventory.removeItem(i);
							System.out.println( String.format("Dropped %s", i.getName()) );
							count++;
							break;
						}
					}

					if ( count == 0 ) {
						System.out.println( "The item you entered doesn't exist in your inventory." );
					}

				} else if ( command.contains("inventory") ) {
					if ( inventory.getCollection().size() == 0 ) {
						System.out.println( "You currently have no item." );

					} else {
						System.out.println( String.format("\nYou are currently having %d items.\nNamely:\n",
								inventory.getCollection().size()) );

						for ( Item i : inventory.getCollection() ) {
							System.out.println( String.format("\t%s", i.getName()) );
						}
					}

				} else if ( command.contains("put") && command.contains("in")) {
					String temp = command.replaceAll("put", "").replaceAll("in", "").trim();
					String[] words = temp.split(" ");
					String name = words[0];
					String tempContainer = words[1];

					// TODO: put name in container method:
//					for ( Item i : inventory.getCollection() ) {
//						if ( i.getName().equals(words[0]) ) {
//							tempContainer.addItem(i); //TODO
//							inventory.removeItem(i);
//							System.out.println( String.format("Put %s into %s from inventory", i.getName(), name) );
//						}
//					}

				} else if ( command.contains("examine") ) {
                	// Get the item with the given name from the location and print its description
                    // if the item is there, print the description of it
                    // if not, print some sort of error message

                	boolean found = false;

                    // If just type "examine", as what to examine (several items at a time is ok)
                	if ( command.equals("examine") ) {
                		System.out.println( String.format("\tYou are currently having %d items." +
                                "\n\tTo see their names, try 'look' command. What do you want to examine?",
								curLocation.getItem().size()) );
                		command = in.nextLine().toLowerCase().trim(); //TODO
                    }

                    // If command contains the item name that is included in the current state,
					// print the details of the item
                	for( Item i : curLocation.getItem() ) {
                		if ( command.contains(i.getName()) ) {
                			i.print();
                			found = true;
						}
					}

                	if ( !found ) {
                		System.out.println( "Cannot find the item." );
                	}

                } else if ( command.contains("open") && command.contains("door") ) {
                    // Open the door
                	System.out.println( "You try to open the door and realized that it was locked with an ancient lock. " +
							"On the lock, there are four figures: a circle, a rainbow, a square, and a triangle." +
							"Under each figure, there is a place for you to put one digit from 0 to 9 on it." +
							"To open the door, you have to guess correctly all 4 digits corresponding to 4 figures." );
                	System.out.println( "Please enter the 4 digits (without spaces; for example, 0000)" );
                	String passcode = in.nextLine();

                	int attempt = 1;
                	int hintNo = 1;

					// If enter wrong passcode (12 trials and 2 hints before giving the option to quit)
                	while ( !passcode.equals("0743") ) {
						// If wrong passcode entered more than 3 times after having 2 hints,
						// offer the quit option. If don't quit, start over.
						if ( (attempt == 2) && (hintNo > 3) ) {
							System.out.println( "You tried many time. Do you want to quit? (y/n)" ); //TODO
							String quitBool = in.nextLine().toLowerCase().trim();

							if (quitBool.equals("y")) {
								System.out.println( "Thanks for playing!" );
								System.exit(1);

							} else if ( quitBool.equals("n") ) {
								break;
							}

						} else if ( (attempt < 3) && (hintNo <= 4) ) {
							// If wrong passcode <= 3 times before having 2 hints
							attempt++;
							System.out.println( "Please try again!" );
							passcode = in.nextLine();

						} else if ( (attempt == 3) && (hintNo == 3) ) {
							// If wrong passcode > 3 times after having 2 hints
							System.out.println( "You cannot get more hint." +
									"Think about the number of edges/colors in each figure." );
							System.out.println( "Please try again!" );

							passcode = in.nextLine();
							attempt = 0;
							hintNo++;

						} else if ( (attempt == 3) && (hintNo <= 3) ) {
							// If wrong passcode > 3 times before having 2 hints
							System.out.println( "You tried 3 times." +
									"The lock automatically gives you a hint. Enter the figure that you want to see hint." );
							String hint = in.nextLine().toLowerCase().trim(); //TODO

							while ( !(hint.contains("rainbow") || hint.contains("circle") || hint.contains("square") || hint.contains("triangle")) ) {
								System.out.println( "Please enter one of a given figure. For example, 'rainbow'" );
								hint = in.nextLine().toLowerCase().trim(); //TODO
							}

							if ( hint.contains("rainbow") ) {
								System.out.println( "The code for rainbow is 7" );
								hintNo++;

							} else if ( hint.contains("triangle") ) {
								System.out.println( "The code for triangle is 3" );
								hintNo++;

							} else if ( hint.contains("square") ) {
								System.out.println( "The code for square is 4" );
								hintNo++;

							} else if ( hint.contains("circle") ) {
								System.out.println( "The code for circle is 0" );
								hintNo++;
							}

							attempt = 0;
						}
					}

					// If passcode is correct
                	if ( passcode.contains("0743") ) {
                    	System.out.println( "You successfully broke the lock. The door is opened and you enter the house." );
                    	setLocation(inside);
                	}
                } else if ( command.equals("quit") ) { //TODO
                	System.out.println( "Thanks for playing!" );
                    break;

                } else {
                    System.out.println( "I don't know how to do that." );
                }
            }
        }

		in.close();
    }
}