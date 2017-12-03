/*
*@description Driver class
*
*@author Team 4B : Linh Lam, So Negishi, Duc Nguyen, Hoang Pham
*@version December 3, 2017
*/

import java.util.List;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Driver {
	
	private static Location curLocation = new Location();
	private static ContainerItem inventory = new ContainerItem();
	private static List<Item> itemList = new ArrayList<>();
	private static List<ContainerItem> containerList = new ArrayList<>();
	private static List<Location> locationList = new ArrayList<>();

	// Set attributes to current location
	private static void setLocation( Location curLoc ) {
		curLocation = curLoc;
	}
	private static void setInventory( ContainerItem inv) {
		inventory = inv;
	}
	private static void setLocationList (List<Location> locList) {
		locationList = locList;
	}
	private static void setContainerList (List<ContainerItem> conList) {
		containerList = conList;
	}
	
	//Check if an item is a container item
	private static boolean isContainer( String s ) {
		for (ContainerItem e: containerList)
			if ( (s.equals(e.getName())) )  
    			return true;
		return false;
	}
	
	//Save/Load Methods
	private static Scanner saveScan = new Scanner(System.in);
	
    private static void Save(Location curLocation, ContainerItem inventory, List<Location> locationList, List<ContainerItem> containerList) throws IOException  {
    	System.out.println("Please enter the name for your save file: ");
    	FileOutputStream saveFile = new FileOutputStream(saveScan.nextLine());
    	ObjectOutputStream save = new ObjectOutputStream(saveFile);
    	save.writeObject(curLocation);
    	save.writeObject(inventory);
    	save.writeObject(locationList);
    	save.writeObject(containerList);
    	saveFile.close();
    }
    
    public static void Load(Location curLocation, ContainerItem inventory, List<Location> locationList, List<ContainerItem> containerList) throws IOException, ClassNotFoundException {
		System.out.println("Please enter the name of your save file: ");
		FileInputStream saveFile = new FileInputStream(saveScan.nextLine());
		ObjectInputStream save = new ObjectInputStream(saveFile);
		setLocation((Location) save.readObject());
		setInventory((ContainerItem) save.readObject());
		setLocationList((List<Location>) save.readObject());
		setContainerList((List<ContainerItem>) save.readObject());
		save.close();
    }

	public static void main( String[] args ) {
		
		//Create a SaveGame object
//		SaveGame mainSave = new SaveGame();
		
        // All items given in the game
        Item mirror = new Item("mirror",
				"tool",
				"This is the mirror of totally deflecting light." +
						" Use it to defend yourself and kill the dragon.");
        Item scroll = new Item("scroll",
				"hint",
				"The ancient scroll says," +
						" 'You need to find three legendary artifacts to defeat the dragon." +
						"\n\tThey are the pearl of the Sun, the divine sword of cutting things," +
						"\n\tand the mirror of totally deflecting light.'" );
        Item torch = new Item("torch",
				"tool",
				"a small torch to light up the way" );
        Item pearl = new Item("pearl",
				"tool",
				"This is the pearl of the Sun. Use it to defend yourself and kill the dragon." );
        Item sword = new Item ("sword",
				"weapon",
				"This is the divine sword of cutting things." +
				" This totally awesome weapon will chop the dragon nicely." );
        Item key = new Item ("key",
				"tool",
				"This is a golden small key." );
        
        ContainerItem box = new ContainerItem("box", 
        		"container", 
        		"A silver box." );
        box.addItem(scroll);
        
        ContainerItem satchel = new ContainerItem("satchel", 
        		"container", 
        		"A small satchel" );
        satchel.addItem(mirror);
        satchel.addItem(key);
        
        ContainerItem chest = new ContainerItem("chest", 
        		"container", 
        		"A golden chest." );
        chest.unLocked(false);
        chest.addItem(pearl);

        // Create Item lists
        List<Item> entranceItem = new ArrayList<>( );
        entranceItem.add(box);
	    
		List<Item> indoorItem = new ArrayList<>( );
		indoorItem.add(satchel);
		
		List<Item> eastItem = new ArrayList<>( );
		eastItem.add(chest);
		
		List<Item> westItem = new ArrayList<>();
		westItem.add(sword);
		
		List<Item> eastDoor = new ArrayList<>( );
		List<Item> westDoor = new ArrayList<>( );
		List<Item> outItem = new ArrayList<>( );
        outItem.add(torch);

        // Add location(s)
        Location entrance = new Location("entrance",
				"You see a mysterious house on top of the hill. \n\tThere is an entrance door. You should try to open it!",
				entranceItem, true);
        Location inside = new Location("inside the house",
				"You are now standing inside the house." +
				" There are 2 locked doors: One to the West, One to the East.",
				indoorItem, false);
        Location east = new Location("The East room",
        		"You are now standing in the East room." +
        		" There is a mysterious light.", eastItem, false);
        Location eastEntrance = new Location("The East door",
        		"You are now standing in front of the East door." +
        		" It is a wood door. There is no lock. However, it is stuck and you cannot open it.", eastDoor, true);
        
        Location west = new Location("The West room",
        		"You are now standing in the West room", 
        		westItem, false);
        Location westEntrance = new Location("The West door",
        		"You are now standing in front of the West door.", westDoor, true);
        
        
        Location outside = new Location("outside",
        		"You are now standing in the middle of a hill. This is your starting point."
        		+ " \n\tThere is a beautiful village here but everyone stays indoor. A villager offers you a torch.", outItem, true);
        
        outside.setMap(null, null, null, entrance);
        entrance.setMap(null, null, outside, inside);
        inside.setMap(eastEntrance, westEntrance, entrance, null);
        
        
        east.setMap(null, inside, null, null);
        west.setMap(inside, null, null, null);
        eastEntrance.setMap(east, inside, null, null);
        westEntrance.setMap(inside, west, null, null);
        
		//add all items to the item list
		itemList.add(mirror);
		itemList.add(scroll);
		itemList.add(pearl);
		itemList.add(torch);
		itemList.add(sword);
		itemList.add(key);
		
		//add all container items to the container list
		containerList.add(box);
		containerList.add(satchel);
		containerList.add(chest);
		
		//add all locations to the location list
		locationList.add(outside);
		locationList.add(entrance);
		locationList.add(inside);
		locationList.add(east);
		locationList.add(eastEntrance);
		locationList.add(west);
		locationList.add(westEntrance);
 
    	Scanner in = new Scanner( System.in );

		System.out.print( "Welcome to The Legendary Tale of the Dragon Slayer Game!" +
				"\nDo you want to start the game? (y/n): " );
		String start = in.nextLine().toLowerCase().trim();
		
		while ( !start.equals("n") && !start.equals("y") ) {
			System.out.println( "Please enter y or n to start or close the game." );
			start = in.nextLine().toLowerCase().trim();
		}
		
		if ( start.equals("n") ) {
            System.out.println( "Thanks for playing" );
            System.exit(1);
        }
		
		else if ( start.equals("y") ) {
			System.out.println( "\nYou are an adventurer going on a quest to destroy the mighty dragon " +
					"that is causing terror to a miserable village.");
			setLocation( outside );
	
            String command;
            while ( true ) {
            	
				System.out.print( "\nEnter a command: " );
				command = in.nextLine().toLowerCase().trim();
				
				//Look Command
				if ( command.contains("look") ) {
					System.out.println( String.format("\n\tCurrent location: %s",
							curLocation.getDesc()) );
					
					if (curLocation.getItem() != null) {
						System.out.println( String.format("\tItem(s) left there: %d item(s)",
								curLocation.retrieveNumOfItems()) );

						for( Item i : curLocation.getItem() ) {
							System.out.println( String.format("\t\t%s", i.getName()) );
						}
					}
				} 
				
				//Take Command
				else if ( command.contains("take") ) {
					try {
						if ( command.equals("take") ) {
							System.out.println( String.format("\tThe location is currently having %d items." +
									"\n\tTo see their names, try 'look' command. What do you want to take?",
									curLocation.getItem().size()) );
							command = in.nextLine().toLowerCase().trim();
						}

						if ( command.contains("from") && command.contains(" ")) {

							String temp = command.replaceAll("take", "").replaceAll(" from", "").trim();
							String[] words = temp.split(" ");
							if ( words.length >= 2 ) {
								String objectName = words[0];
								String containerName = words[1];
								//if a command takes something from the container, which is either in inventory or in current location
								ContainerItem tempContainer = curLocation.getContainer( containerList, containerName );
								if ((inventory.isHolding(containerName) || curLocation.isMember(containerName))  && 
									!isContainer(objectName) && isContainer(containerName) && tempContainer.isUnlocked() ) {
									int count = 0;
									for ( Item i : tempContainer.getCollection() ) {
										if ( i.getName().contains(objectName) ) {
											inventory.addItem(i);
											System.out.println( String.format("Took %s", i.getName()) );
											tempContainer.getCollection().remove(i);
											count++;
											break;
										}
									}

									if ( count == 0 ) {
										System.out.println( "The item you entered doesn't exist in the container." );
									}

								} 
								else {
									System.out.println( "Error: Cannot take an item from itself, or the container is not in (your inventory||the current location)" );
								}
							} 
							else {
								System.out.println( "Syntax error: Please type 'take [item] from [container]'" );
							}
						} 
						else { //if command == "take [anItem]"
							int count = 0;

							for( Item i : curLocation.getItem() ) {
								if ( command.contains(i.getName()) ) {
									inventory.addItem(i);
									System.out.println( String.format("Took %s", i.getName()) );
									curLocation.getItem().remove(i);
									count++;
									break;
								}
							}

							if ( count == 0 ) {
								System.out.println( "The item you entered doesn't exist in this location." );
							}
						}
					} catch( NullPointerException e ) {
						System.out.print( "Cannot find item" );
					}
				} 
				
				//Drop Command
				else if ( command.contains("drop") ) {
					try {
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

					} catch( NullPointerException e ) {
						e.printStackTrace();
						System.out.print( "Cannot find item\n" );
					}
				} 
				
				//Inventory Command
				else if ( command.contains("inventory") ) {
					if ( inventory.getCollection().size() == 0 ) {
						System.out.println( "You currently have no item." );

					} else {
						System.out.println( String.format("\nYou are currently having %d items.\nNamely:\n",
								inventory.getCollection().size()) );

						for ( Item i : inventory.getCollection() ) {
							System.out.println( String.format("\t%s", i.getName()) );
						}
					}
				} 
				
				//Put Interacting Command
				else if ( command.contains("put") && command.contains("in") && command.contains(" ") ) {
									
					String temp = command.replaceAll("put", "").replaceAll(" in", "").trim();
					String[] words = temp.split(" ");
					if ( words.length >= 2 ) {
						String object = words[0];
						String tempContainer = words[1];
						ContainerItem target = curLocation.getContainer(containerList, tempContainer);

						// if holding the object and put it into the container, which is either in inventory or at current location
						if ( (((inventory.isHolding(tempContainer) ||curLocation.isMember(tempContainer)) && inventory.isHolding(object)) && !object.equals(tempContainer))
								&& (isContainer(tempContainer) && target.isUnlocked()) ) {
							int count = 0;
							//find a given object to take it from the container
							for ( Item i : inventory.getCollection() ) {
								if ( i.getName().contains(object) ) {
									inventory.removeItem(i);
									System.out.println( String.format("Placed %s", i.getName()) );
									target.getCollection().add(i);
									count++;
									break;
								}
							}

							if ( count == 0 ) {
								System.out.println( "The item you entered doesn't exist in your inventory." );
							}
																
						} else {
							System.out.println( "Error: Cannot put an item into a box that's (not in your inventory || not in curLocation). Cannot put an item into (itself/non-containers). " );
						}
					} else {
						System.out.println("Syntax error. Please type 'put [item] in [container]'");
					}
				} 
				
				//Examine Command
				else if ( command.contains("examine") ) {
                	// Get the item with the given name from the location and print its description
                    // if the item is there, print the description of it
                    // if not, print some sort of error message

                	boolean found = false;

                    // If just type "examine", as what to examine (several items at a time is ok)
                	if ( command.equals("examine") ) {
                		System.out.println( String.format("What do you want to examine?"));
                		command = in.nextLine().toLowerCase().trim(); 
                    }

                    // If command contains the item name that is included in the current state,
					// print the details of the item
                	try {
                		if (curLocation.getItem()!=null) {
                    		for ( Item i : curLocation.getItem() ) {
    							if ( command.contains(i.getName()) ) {
    								i.print( );
    								found = true;
    							}
                    		}
                		}
                		if (inventory.getCollection()!=null) {
    						for ( Item i : inventory.getCollection() ) {
    							if ( command.contains(i.getName()) ) {
    								i.print( );
    								found = true;
    							}
    						}
                		}
						if ( !found ) {
							System.out.println( "Cannot find the item." );
						}
					} catch( NullPointerException e ) {
                        System.out.print( "Cannot find the given item.\n" );
                    }
				} 
				
				//Open Door Entrance Command
				else if ( command.contains("open") && command.contains("door") && curLocation.getName().equals("entrance") ) {
                    // Open the door
                	System.out.println( "You try to open the door and realized that it was locked with an ancient lock." +
							"\nOn the lock, there are four figures: a circle, a rainbow, a square, and a triangle." +
							"\nUnder each figure, there is a place for you to put one digit from 0 to 9 on it." +
							"\nTo open the door, you have to guess correctly all 4 digits corresponding to 4 figures." +
							"\nType \"back\" to back away from the door");
                	System.out.println( "\nPlease enter the 4 digits (without spaces; for example, 0000)" );
                	String passcode = in.nextLine();

                	int attempt = 1;
                	int hintNo = 1;

					// If enter wrong passcode (12 trials and 2 hints before giving the option to quit)
                	while ( !passcode.equals("0743") ) {
                		if ( passcode.equals("help") ) {
                				System.out.println( "Think about the number of edges/colors in each figure." );
                		}
                		if ( passcode.equals("back") ) {
                			break;
                		}
						// If wrong passcode entered more than 3 times after having 2 hints,
						// offer the quit option. If don't quit, start over.
						if ( (attempt == 2) && (hintNo > 3) ) {
							System.out.println( "You tried many time. Do you want to quit? (y/n)" );
							String quitBool = in.nextLine().toLowerCase().trim();

							if ( quitBool.equals("y") ) {
								System.out.println( "Thanks for playing!" );
								System.exit(1);

							} 
							else if ( quitBool.equals("n") ) {
								break;
							}

						} 
						else if ( (attempt < 3) && (hintNo <= 4) ) {
							// If wrong passcode <= 3 times before having 2 hints
							attempt++;
							System.out.println( "Please try again!" );
							passcode = in.nextLine();

						} 
						else if ( (attempt == 3) && (hintNo == 3) ) {
							// If wrong passcode > 3 times after having 2 hints
							System.out.println( "You cannot get more hint." +
									"Think about the number of edges/colors in each figure." );
							System.out.println( "Please try again!" );

							passcode = in.nextLine();
							attempt = 0;
							hintNo++;

						} 
						else if ( (attempt == 3) && (hintNo <= 3) ) {
							// If wrong passcode > 3 times before having 2 hints
							System.out.println( "You tried 3 times." +
									"The lock automatically gives you a hint. Enter the figure that you want to see hint." );
							String hint = in.nextLine().toLowerCase().trim();

							while ( !(hint.contains("rainbow") || hint.contains("circle") || hint.contains("square") || hint.contains("triangle")) ) {
								System.out.println( "Please enter one of a given figure. For example, 'rainbow'" );
								hint = in.nextLine().toLowerCase().trim();
							}

							if ( hint.contains("rainbow") ) {
								System.out.println( "The code for rainbow is 7" );
								hintNo++;

							} 
							else if ( hint.contains("triangle") ) {
								System.out.println( "The code for triangle is 3" );
								hintNo++;

							} 
							else if ( hint.contains("square") ) {
								System.out.println( "The code for square is 4" );
								hintNo++;

							} 
							else if ( hint.contains("circle") ) {
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
                    	inside.unLocked(true);
                	}
                }
				
				//Burn Torch Interactive Command
				else if ( command.contains("torch") && inventory.isHolding("torch") && command.contains("burn") && curLocation.getName().equals("The East door")) {
					
					
				
					
                	System.out.println( "You successfully burnt the door. The door is opened and you enter the East room." );
					setLocation(east);
					east.unLocked(true);
					inside.changeMap("east", east);
					east.changeMap("west", inside);
                	inside.unLocked(true);

				} 
                
				//Go Command
                else if ( command.contains("go") ) {
                	if( command.contains("east") ) {
						if ( curLocation.getMap().get("east") !=null && curLocation.getMap().get("east").isUnlocked() ) {
							setLocation(curLocation.getMap().get("east"));
							System.out.println( "Headed East!" );

						} else {
							System.out.println( "Cannot go to this direction!" );
						}
                	}
                	else if( command.contains("west") ) {
                		if ( curLocation.getMap().get("west") !=null && curLocation.getMap().get("west").isUnlocked() ) {
							setLocation(curLocation.getMap().get("west"));
							System.out.println( "Headed West!" );

						} else {
							System.out.println( "Cannot go to this direction!" );
						}

                	}
                	else if( command.contains("south") ) {
                		if ( curLocation.getMap().get("south") !=null && curLocation.getMap().get("south" ).isUnlocked() ) {
							setLocation(curLocation.getMap().get("south"));
							System.out.println( "Headed South!" );

                		} else {
                			System.out.println( "Cannot go to this direction!" );
                		}

                	}
                	else if( command.contains("north") ) {
                		if ( curLocation.getMap().get("north") !=null && curLocation.getMap().get("north").isUnlocked() ) {
							setLocation(curLocation.getMap().get("north"));
							System.out.println( "Headed North!" );

                		} else {
                			System.out.println( "Cannot go to this direction!" );
                		}

                	}
                	else {
						System.out.println( "Cannot do this command" );
					}
				}
				
				//Key to Chest Interactive Command
                else if ( command.contains("key") && command.contains("chest") && inventory.isHolding("key") && (curLocation.isMember("chest") || inventory.isHolding("chest")) ) {
                	System.out.println( "You sucessfully unlocked the chest. Now you can examine it" );
                	chest.unLocked(true);
                }
                
				//Reflect Mirror Interactive Command
                else if ( command.contains("mirror") && inventory.isHolding("mirror") && command.contains("reflect") && curLocation.getName().equals("The West door")) {
                	System.out.println( "With the mirror, you discovered that 'VICDU' is the upper-half of 'XIEBH'. You then use the code to open the door" );
					setLocation(west);
					west.unLocked(true);
					inside.changeMap("west", west);
					west.changeMap("east", inside);
					inside.unLocked(true);
                } 
                
				//Open Door West Entrance Command
                else if ( command.contains("open") && command.contains("door") && curLocation.getName().equals("The West door") ) {
                    // Open the door
                	System.out.println( "You try to open the door and realized that it was locked with an ancient lock." +
							"\nOn the lock, there are five letters: 'VICDU'." +
							"\nUnder each figure, there is a place for you to put one digit from A to Z on it." +
							"\nTo open the door, you have to guess correctly all 5 letters." );
                	System.out.println( "\nPlease enter the 5 letters (without spaces; for example, vicdu)" );
                	String passcode = in.nextLine();
                	boolean reflected = false;



                	int attempt = 1;
                	int hintNo = 1;

					// If enter wrong passcode (12 trials and 2 hints before giving the option to quit)
                	while ( !passcode.equals("xiebh") ) {
                    	if ( passcode.contains("help") ) {
                    		System.out.println( "\nYou can guess the password yourself. However, holding a mirror may help you, since the mirror can REFLECT letters."
                    				+ "\nThe password contains letter that are symmetrical" );
                    	}
						// If wrong passcode entered more than 3 times after having 2 hints,
						// offer the quit option. If don't quit, start over.
                		if (passcode.contains("mirror")&&passcode.contains("reflect")&& inventory.isHolding("mirror")) {
							reflected = true;
						}

                		if (reflected) {
							System.out.println("You reflected the code and realized that VICDU is the upperhalf of XIEBH");
							System.out.println("You entered the code and successfully open the door.");
							setLocation(west);
	                    	west.unLocked(true);
	                    	inside.changeMap("west", west);
	                    	west.changeMap("east", inside);
	                    	inside.unLocked(true);

							break;
                		}

						if ( (attempt == 2) && (hintNo > 3) && !reflected ) {
							System.out.println( "You tried many time. Do you want to quit? (y/n)" );
							String quitBool = in.nextLine().toLowerCase().trim();

							if ( quitBool.equals("y") ) {
								System.out.println( "Thanks for playing!" );
								System.exit(1);

							} else if ( quitBool.equals("n") ) {
								break;
							}

						} else if ( (attempt < 3) && (hintNo <= 4)  && !reflected ) {
							// If wrong passcode <= 3 times before having 2 hints
							attempt++;
							System.out.println( "Please try again!" );
							passcode = in.nextLine();

						} else if ( (attempt == 3) && (hintNo == 3) && !reflected ) {
							// If wrong passcode > 3 times after having 2 hints
							System.out.println( "You cannot get more hint." +
									"Use the mirror to reflect those given letters" );
							System.out.println( "Please try again!" );

							passcode = in.nextLine();
							attempt = 0;
							hintNo++;

						} else if ( (attempt == 3) && (hintNo <= 3) && !reflected ) {
							// If wrong passcode > 3 times before having 2 hints
							System.out.println( "You tried 3 times." +
									"The lock automatically gives you a hint. Enter the letter that you want to see hint." );
							String hint = in.nextLine().toLowerCase().trim();

							while ( !(hint.contains("v") || hint.contains("i") || hint.contains("c") || hint.contains("d") || hint.contains("u")) ) {
								System.out.println( "Please enter one of a given letter. For example, 'V'" );
								hint = in.nextLine().toLowerCase().trim();
							}

							if ( hint.contains("v") ) {
								System.out.println( "The code for V is X" );
								hintNo++;

							} else if ( hint.contains("i") ) {
								System.out.println( "The code for I is I" );
								hintNo++;

							} else if ( hint.contains("c") ) {
								System.out.println( "The code for C is E" );
								hintNo++;

							} else if ( hint.contains("d") ) {
								System.out.println( "The code for D is B" );
								hintNo++;
							}
							else if ( hint.contains("u") ) {
								System.out.println( "The code for U is H" );
								hintNo++;
							}
							attempt = 0;
						}
					}
					// If passcode is correct
                	if ( passcode.contains("xiebh") ) {
                    	System.out.println( "You successfully broke the lock. The door is opened and you enter the West room." );
                    	setLocation(west);
                    	west.unLocked(true);
                    	inside.changeMap("west", west);
                    	west.changeMap("east", inside);
                    	inside.unLocked(true);
                	}
                }
                
				//Help Command
                else if ( command.contains("help") ) { //TODO: if to elif?
                	
                	if (curLocation == outside) {
						System.out.println("You are standing outside of a house. "
								+ "\nAvailable commands: look, examine, inventory, take, drop, save, load, help, quit"
								+ "\nYou can go to the direction that you want: go east/west/south/north");
					}
					if (curLocation == entrance) {
						System.out.println("You are standing at the entrance. "
								+ "\nAvailable commands: look, examine, inventory, open door, take, drop, take...from..., put...in..., save, load, help, quit"
								+ "\nYou can go to the direction that you want: go east/west/south/north");
					}
					if (curLocation == inside) {
						System.out.println("You are standing inside the house. "
								+ "\nAvailable commands: look, examine, inventory, take, drop, take...from..., put...in..., save, load, help, quit"
								+ "\nYou can go to the direction that you want: go east/west/south/north");
					}
					if (curLocation == eastEntrance){
						System.out.println("You are standing in front of a wood door. This door leads to the East room "
								+ "\nAvailable commands: look, examine, inventory, use...to...door, take, drop, take...from..., put...in..., save, load, help, quit"
								+ "\nYou can go to the direction that you want: go east/west/south/north"
								+ "\nThere is no lock so that you cannot use any key. However, FIRE CAN BURN WOOD");
					}
                	if ( curLocation == westEntrance ) {
						System.out.println("You are standing in front of an iron door. This door leads to the West room "
								+ "\nAvailable commands: look, examine, inventory, open door, take, drop, take...from..., put...in..., save, load, help, quit"
								+ "\nYou can go to the direction that you want: go east/west/south/north"
								+ "\nType 'open door' to open the door.");
					}
                	if ( curLocation == east ) {
						System.out.println("You are standing in the East room"
								+ "\nAvailable commands: look, examine, inventory, use...to...chest, take, drop, take...from..., put...in..., save, load, help, quit"
								+ "\nYou can go to the direction that you want: go east/west/south/north"
								+ "\nTry to open the chest. It needs a key to open. Things you find in the chest are necessary to defeat the dragon");
					}
                	if ( curLocation == west ) {
						System.out.println("You are standing in the West room"
								+ "\nAvailable commands: look, examine, inventory, take, drop, take...from..., put...in..., save, load, help, quit"
								+ "\nYou can go to the direction that you want: go east/west/south/north"
								+ "\nTry to look around. To defeat the dragon, you need a pearl, a mirror, and a sword");
					}
                }
				
				//Save Command
                else if ( command.contains("save") ) {
                	try {
						Save(curLocation, inventory, locationList, containerList);
						System.out.println("Saved");
					}
                	catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                }
				
				//Load Command
                else if ( command.contains("load") ) {
                	try {
                		Load(curLocation, inventory, locationList, containerList);
                		System.out.println("Loaded");
                	}
                	catch (IOException | ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
                	}
                }
				//Quit Command
                else if ( command.equals("quit") ) {
                	System.out.println( "Thanks for playing!" );
                    break;
                } 
                
				//Invalid Command
                else {
                    System.out.println( "I don't know how to do that." );
                }
				
				if (inventory.isHolding("mirror") && inventory.isHolding("pearl") && inventory.isHolding("sword") ) {
					System.out.println("Congratulations! You have collected all items needed to defeat the dragon right before it was trying to attack you. " +
							 "\nYou wisely used the mirror to reflect the light from the pearl to distract the dragon, then used the sword to kill it! " +
							 "\nYou became the village's hero!!!\n\n\n\n" +
							 "                     Tb.          Tb.                                \n" + 
							 "                     :$$b.        $$$b.                              \n" + 
							 "                     :$$$$b.      :$$$$b.                            \n" + 
							 "                     :$$$$$$b     :$$$$$$b                           \n" + 
							 "                      $$$$$$$b     $$$$$$$b                          \n" + 
							 "                      $$$$$$$$b    :$$$$$$$b                         \n" + 
							 "                      :$$$$$$$$b---^$$$$$$$$b                        \n" + 
							 "                      :$$$$$$$$$b      \"\"^Tb                       \n" + 
							 "                       $$$$$$$$$$b    __...__`.                      \n" + 
							 "                       $$$$$$$$$$$b.g$$$$$$$$$pb                     \n" + 
							 "                       $$$$$$$$$$$$$$$$$$$$$$$$$b                    \n" + 
							 "                       $$$$$$$$$$$$$$$$$$$$$$$$$$b                   \n" + 
							 "                       :$$$$$$$$$$$$$$$$$$$$$$$$$$;                  \n" + 
							 "                       :$$$$$$$$$$$$$^T$$$$$$$$$$P;                  \n" + 
							 "                       :$$$$$$$$$$$$$b  \"^T$$$$P' :                  \n" + 
							 "                       :$$$$$$$$$$$$$$b._.g$$$$$p.db                 \n" + 
							 "                       :$$$$$$$$$$$$$$$$$$$$$$$$$$$$;                \n" + 
							 "                       :$$$$$$$$\"\"\"^^T$$$$$$$$$$$$P^;                \n" + 
							 "                       :$$$$$$$$       \"\"^^T$$$P^'  ;                \n" + 
							 "                       :$$$$$$$$    .'       `\"     ;                \n" + 
							 "                       $$$$$$$$;   /                :                \n" + 
							 "                       $$$$$$$$;           .----,   :                \n" + 
							 "                       $$$$$$$$;         ,\"          ;               \n" + 
							 "                       $$$$$$$$$p.                   |               \n" + 
							 "                      :$$$$$$$$$$$$p.                :               \n" + 
							 "                      :$$$$$$$$$$$$$$$p.            .'               \n" + 
							 "                      :$$$$$$$$$$$$$$$$$$p...___..-\"                 \n" + 
							 "                      $$$$$$$$$$$$$$$$$$$$$$$$$;                     \n" + 
							 "   .db.               $$$$$$$$$$$$$$$$$$$$$$$$$$                     \n" + 
							 "  d$$$$bp.            $$$$$$$$$$$$$$$$$$$$$$$$$$;                    \n" + 
							 " d$$$$$$$$$$pp..__..gg$$$$$$$$$$$$$$$$$$$$$$$$$$$                    \n" + 
							 "d$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$p._            .gp. \n" + 
							 "$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$p._.ggp._.d$$$$b");
					break;
				}
            }
        }
		in.close();
    }
}