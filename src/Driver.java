/*
*@description Driver class
*
*@author Team 4B : Linh Lam, So Negishi, Hoang Pham, Duc Nguyen
*@version October 23, 2017
*/

import java.io.*;
import java.util.*;



public class Driver {
	
	public static Location curLocation = new Location();
	public static List<Item> curItems = new ArrayList<Item>();
	
	public static void setLocation(Location curLoc) {
		curLocation = curLoc;
		for (int i = 0; i<curLocation.getItem().size(); i++) {
			curItems.add(curLocation.getItem().get(i));
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
        // Items
        Item mirror = new Item("mirror", "tool", "This is the mirror of totally deflecting light. Use it to defend yourself and kill the dragon.");
        Item scroll = new Item("scroll", "hint", "The ancient scroll says: 'You need to find three legendary artifacts to defeat the dragon. They are the pearl of the Sun, the divine sword of cutting things, and the mirror of totally deflecting light.' ");
        Item torch = new Item("torch", "tool", "a small torch to light up the way");
        Item pearl = new Item("pearl", "tool", "This is the pearl of the Sun. Use it to defend yourself and kill the dragon.");
        Item sword = new Item ("sword", "weapon", "This is the divine sword of cutting things. Use it to kill the dragon and defend yourself.");

        // Add items into an arraylist
        List<Item> itemList = new ArrayList<>();
        itemList.add(mirror);
        itemList.add(scroll);
        itemList.add(torch);
        itemList.add(sword);
        itemList.add(pearl);
	

        
        List<Item> entranceItem = new ArrayList<Item>(Arrays.asList(torch,scroll));
        List<Item> indoorItem = new ArrayList<Item>(Arrays.asList(mirror));
        //List<Item> curItems = new ArrayList<Item>();
        
        
        
        
     // Add location(s)
        //Location curLocation = new Location();
        Location entrance = new Location("entrance", "This is the starting position of the game. You are standing in front of a door.",entranceItem);
        Location inside = new Location("inside", "You are now standing in side the house. There is a small mirror hung on the wall. There are 2 locked doors: One to the left, One to the right.", indoorItem);
        

    	Scanner in = new Scanner( System.in );
		System.out.print( "Welcome to the The Legendary Tale of the Dragon Slayer game!" + "\n" +"Do you want to start the game? (y/n): ");
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
			System.out.println("\n" + "You are an adventurer going on a quest to destroy the mighty dragon that is causing terror to the miserable village. At the beginning of the game, you are given an ancient scroll and a torch. ");
			setLocation(entrance);
			//curLocation.print();
	
            String command;
            while ( true ) {
                System.out.print("\nEnter a command: ");
                command = in.nextLine().toLowerCase().trim();

                if (command.contains("look")) {
                    System.out.println( String.format("\tCurrent location: %s", curLocation.getDesc()) );
                    System.out.println( "\t " + curLocation.retrieveNumOfItems() + " Items found there:" );
                    for (int i = 0; i<curLocation.getItem().size(); i++)
                    {
                    	System.out.println("\t\t"+ curLocation.getItem().get(i).getName());	}
                    
                System.out.println("\tYou are currently having " + curItems.size() + " items. Namely: ");
                for(int i =0; i<curItems.size(); i++) 
                	System.out.println("\t\t " +curItems.get(i).getName());
                }
      
                
                
                else if (command.contains("examine"))  	 
                	// Get the item with the given name from the location and print its description
                    // if the item is there, print the description of it
                    // if not, print some sort of error message
                {
                	boolean found = false;
                	if (command.equals("examine")) {
                		System.out.println("\tYou are currently having " + curItems.size() + " items. To see their names, try 'look' command. What do you want to examine?");
                		command = in.nextLine().toLowerCase().trim();
                		}                  	
                	
                	for(int i = 0 ; i< curItems.size(); i++)
                	{
                		if (command.contains(curItems.get(i).getName()))
                		{
                			curItems.get(i).print();
                			found = true;
                		}
                	}                	
                	if (found == false) {
                		System.out.println("Cannot find the item.");
                }
                }
                
                
                else if (command.contains("open") && command.contains("door")) {
                    // Get the item with the given name from the location and print its description
                    // if the item is there, print the description of it
                    // if not, print some sort of error message
                	System.out.println("You try to open the door and realized that it was locked with an ancient lock. On the lock, there are four figures: a circle, a rainbow, a square, and a triangle. Under each figure, there is a place for you to put one digit from 0 to 9 on it. To open the door, you have to guess correctly all 4 digits corresponding to 4 figures.");
                	System.out.println("Please enter the 4 digits (without spaces; for example, 0000)");
                	Scanner in2 = new Scanner( System.in );
                	String passcode = in2.nextLine();
                	int attempt = 1;
                	int hintNo = 1;
                	
                		while (!passcode.equals("0743"))
                		{
                		 if ((attempt == 2) && (hintNo > 3)) {
                				System.out.println("You tried many time. Do you want to quit? (y/n)");
                   				Scanner in3 = new Scanner(System.in);
                				String quitBool  = in3.nextLine().toLowerCase().trim();
                				if (quitBool.equals("y")){
                					 System.out.println("Thanks for playing!");
                	                 System.exit(1);
                				}
                				else if (quitBool.equals("n")) {
                					break;
                  				}
                				
                						
                			}
                		else if (attempt < 3 && (hintNo <= 4) ) {
                			attempt++;
                			System.out.println("Please try again!");
                			passcode  = in2.nextLine();
                			//System.out.println(attempt);
                			}
                			
                			else if ((attempt == 3) && (hintNo == 3)) {
                				System.out.println("You cannot get more hint.Think about the number of edges/colors in each figure");
                				System.out.println("Please try again!");
                				passcode  = in2.nextLine();
                				attempt = 0;
                				hintNo++;
                			}              			
                		
                			
                			else if ((attempt == 3) && (hintNo <= 3)){
                			System.out.println("You tried 3 times. The lock automatically gives you a hint. Enter the figure that you want to see hint");
                			Scanner in1 = new Scanner( System.in );
                        	String hint = in1.nextLine();
                        	while (!(hint.contains("rainbow")||hint.contains("circle")||hint.contains("rectangle")||hint.contains("triangle"))) {
                        		System.out.println("Please enter one of a given figure. For example, 'rainbow'");
                        		hint = in1.nextLine();
                        	}
                        	if (hint.contains("rainbow"))
                        	{
                        		System.out.println("The code for rainbow is 7");
                        		hintNo++;
                        	}
                        	else if (hint.contains("triangle")) {
                        		System.out.println("The code for triangle is 3");
                        		hintNo++;
                        	}
                        	else if (hint.contains("rectangle")) {
                        		System.out.println("The code for rectangle is 4");
                        		hintNo++;
                        	}
                        	else if (hint.contains("circle")) {
                        		System.out.println("The code for circle is 0");
                        		hintNo++;
                        	}
                        		
                        	attempt = 0;
                        	
                		}
                		
                		}	
                		if (passcode.contains("0743")) {
                    		System.out.println("You successfully broke the lock. The door is opened and you enter the house.");
                    		setLocation(inside);                    		
                		}
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
