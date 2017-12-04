/*
*@description Driver class
*
*@author Team 4B : Linh Lam, So Negishi, Duc Nguyen, Hoang Pham
*@version December 3, 2017
*/

import java.util.List;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import net.codejava.swing.CustomOutputStream;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;

public class Driver {
	
	private static Location curLocation = new Location();
	private static ContainerItem inventory = new ContainerItem();
	private static List<Item> itemList = new ArrayList<>();
	private static List<ContainerItem> containerList = new ArrayList<>();
	private static List<Location> locationList = new ArrayList<>();
	private static JFrame window;
	private static JFrame victory;
	private static JFrame popup, popup1, loadScreen;
	private static Container con, con1, con2, loadCon;
	private static JPanel titleNamePanel, startButtonPanel, mainTextPanel, loadTextPanel, lockTextPanel, lockTextPanel1, optionButtonPanel, commandArea, optionArea, loadcommandArea, optionArea1, directionButtonPanel;
	private static JLabel titleNameLabel;
	private static Font titleFont = new Font("Times New Roman", Font.PLAIN, 90);
	private static Font normalFont = new Font("Times New Roman", Font.PLAIN, 20);
	private static JButton startButton, quitButton, LoadButton, Enter, Password, quitLock, WestPassword, enterFileName, hint, musicOn;
	private static JButton choice1, choice2, lookButton, inventoryButton;
	private static JButton dirE, dirW, dirS, dirN;	
	private static JTextArea mainTextArea = new JTextArea();
	private static JTextArea loadTextArea = new JTextArea();
	private static JTextArea outputArea = new JTextArea();
	private static JTextArea outputArea1 = new JTextArea();
	private static JTextArea commandTextArea = new JTextArea();
	private static JTextArea loadcommandTextArea = new JTextArea();
	private static JTextArea EnterIntruct = new JTextArea();
	private static JTextArea EnterInstruct1 = new JTextArea();
	private static JTextField enterCommand = new JTextField( 20 );
	private static JTextField enterPass = new JTextField( 20 );
	private static JTextField enterPass1 = new JTextField( 20 );
	private static JTextField fileName = new JTextField( 20 );
	public static String command;
	public static String start;
	public static String saveScan;
	public static JTextArea mainTextArea1;
	public static PrintStream printStream1 ;
	public static String song;
	
	
	private static ImageIcon imageIcon; // load the image to a imageIcon
	private static Image image; // transform it 
	private static Image newimg; // scale it the smooth way  
	private static JLabel label;
	private static JPanel panel;

	private static TitleScreenHandler tsHandler = new TitleScreenHandler();
	private static commandHit cmdHit = new commandHit();
	private static HelpButton helpButton = new HelpButton();
	private static Quit quit = new Quit();
	private static Save saveGame = new Save();
	private static Load loadGame = new Load();
	private static getPass code = new getPass();
	private static getWestPass westCode = new getWestPass();
	private static getFileName getFile = new getFileName();
	private static lookCommand look = new lookCommand();
	private static inventoryCommand invent = new inventoryCommand();
	private static getHint seeHint = new getHint();
	private static AUDIO aud = new AUDIO();
	private static SoundEffect se = new SoundEffect();
	
	private static goEast e = new goEast();
	private static goWest w = new goWest();
	private static goSouth s = new goSouth();
	private static goNorth n = new goNorth();
		
	static Item mirror = new Item("mirror",
			"tool",
			"This is the mirror of totally deflecting light." +
					" Use it to defend yourself and kill the dragon.");
    static Item scroll = new Item("scroll",
			"hint",
			"The ancient scroll says," +
					" 'You need to find three legendary artifacts to defeat the dragon." +
					"\nThey are the pearl of the Sun, the divine sword of cutting things," +
					"\nand the mirror of totally deflecting light.'" );
    static Item torch = new Item("torch",
			"tool",
			"a small torch to light up the way" );
    static Item pearl = new Item("pearl",
			"tool",
			"This is the pearl of the Sun. Use it to defend yourself and kill the dragon." );
    static Item sword = new Item ("sword",
			"weapon",
			"This is the divine sword of cutting things." +
			" This totally awesome weapon will chop the dragon nicely." );
    static Item key = new Item ("key",
			"tool",
			"This is a golden small key." );
    static ContainerItem box = new ContainerItem("box", 
    		"container", 
    		"A silver box." );
    static ContainerItem satchel = new ContainerItem("satchel", 
    		"container", 
    		"A small satchel" );
    static ContainerItem chest = new ContainerItem("chest", 
    		"container", 
    		"A golden chest." );
    

    // Create Item lists
    static List<Item> entranceItem = new ArrayList<>( );
    static List<Item> indoorItem = new ArrayList<>( );
    static List<Item> eastItem = new ArrayList<>( );
    static List<Item> westItem = new ArrayList<>();	
    static List<Item> eastDoor = new ArrayList<>( );
    static List<Item> westDoor = new ArrayList<>( );
    static List<Item> outItem = new ArrayList<>( );
    static List<Item> dragonItem = new ArrayList( );

    // Add location(s)
    static Location entrance = new Location("entrance",
			"You see a mysterious house. You are standing in front of an entrance door. You should try to open it!",
			entranceItem, true);
    static Location inside = new Location("inside",
			"You are now standing inside the house." +
			" There are 2 locked doors: One to the West, One to the East.",
			indoorItem, false);
    static Location east = new Location("east",
    		"You are now standing in the East room." +
    		" There is a mysterious light.", eastItem, false);
    static Location eastEntrance = new Location("The East door",
    		"You are now standing in front of the East door." +
    		" It is a wood door. There is no lock. However, it is stuck and you cannot open it.", eastDoor, true);
    static Location west = new Location("west",
    		"You are now standing in the West room", 
    		westItem, false);
    static Location westEntrance = new Location("The West door",
    		"You are now standing in front of the West door.", westDoor, true);
    static Location outside = new Location("outside",
    		"You are now standing in the middle of a hill. This is your starting point."
    		+ " \n\tThere is a beautiful village here. A villager offers you a torch.", outItem, true);
    static Location dragon = new Location ("dragon", 
    		"You entered a forest and you see the dragon is sleeping.", dragonItem, true);
    
   
	
	private static void setImage() {
		String name = curLocation.getName() + ".jpg";
		imageIcon = new ImageIcon(ClassLoader.getSystemResource( name ));
		image = imageIcon.getImage(); 
		newimg = image.getScaledInstance(600, 350, java.awt.Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(newimg);
		label = new JLabel("", imageIcon, JLabel.CENTER);
		panel = new JPanel(new BorderLayout());
		panel.setBackground(Color.black);
		panel.setBounds(10,280,600,350);
		panel.add( label, BorderLayout.CENTER );

	}
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
	
	public static class SoundEffect{
		public static Clip clip;
	
		public void setFile() {
			try{
				URL url = ClassLoader.getSystemResource( "gamebackground.wav" );
		        File file = new File(url.toURI());
				AudioInputStream sound = AudioSystem.getAudioInputStream(file);
				clip = AudioSystem.getClip();
				clip.open(sound);
			}catch(Exception FileNotFound) {
			}
		}
		public void play() {
			clip.setFramePosition(0);
			clip.start();
		}
		public void loop() {
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		}
		public void stop() {
			clip.stop();
			clip.close();
		}
	}
	
	public static void game() {
		PrintStream printStream = new PrintStream(new CustomOutputStream(mainTextArea));
		System.setOut(printStream);
		System.setErr(printStream);
		
		window =  new JFrame();
		window.setSize(2000,1024);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setBackground(Color.black);
		window.setLayout(null);
		window.setVisible(true);
		con = window.getContentPane();
		
		titleNamePanel = new JPanel();
		titleNamePanel.setBounds(300,100,800,150);
		titleNamePanel.setBackground(Color.black);
		titleNameLabel= new JLabel("DRAGON SLAYER");
		titleNameLabel.setForeground(Color.white);
		titleNameLabel.setFont(titleFont);
		
		
		startButtonPanel = new JPanel();
		startButtonPanel.setBounds(550,300, 300, 150);
		startButtonPanel.setBackground(Color.black);
		
		startButton = new JButton("START");
		startButton.setBackground(Color.black);
		startButton.setForeground(Color.white);
		startButton.setFont(normalFont);
		startButton.addActionListener(tsHandler);
		startButton.setFocusPainted(false);
		
		musicOn = new JButton("MUSIC ON");
		musicOn.setBackground(Color.black);
		musicOn.setForeground(Color.white);
		musicOn.setFont(normalFont);
		musicOn.addActionListener(aud);
		musicOn.setFocusPainted(false);
		
		quitButton = new JButton("QUIT");
		quitButton.setBackground(Color.black);
		quitButton.setForeground(Color.white);
		quitButton.setFont(normalFont);
		quitButton.setFocusPainted(false);
		quitButton.addActionListener(quit);
		
		LoadButton = new JButton("LOAD GAME");
		LoadButton.setBackground(Color.black);
		LoadButton.setForeground(Color.white);
		LoadButton.setFont(normalFont);
		LoadButton.setFocusPainted(false);
		LoadButton.addActionListener(loadGame);
		
		titleNamePanel.add(titleNameLabel);
		startButtonPanel.add(startButton);
		startButtonPanel.add(quitButton);
		startButtonPanel.add(LoadButton);
		startButtonPanel.add(musicOn);
		startButtonPanel.setLayout(new GridLayout(4,1));
		
	
		
		con.add(titleNamePanel);
		con.add(startButtonPanel);
	}
			
	public static void createGameScreen() {
			titleNamePanel.setVisible(false);
			startButtonPanel.setVisible(false);
			
			
			
			setImage();
			con.add(panel);
			
			mainTextPanel = new JPanel();
			mainTextPanel.setBounds(10,10,600,250);
			mainTextPanel.setBackground(Color.black);
			con.add(mainTextPanel);
			
		
			
			//mainTextArea = new JTextArea("Main text area");
			mainTextArea.setBounds(100,100,600,250);
			mainTextArea.setBackground(Color.black);
			mainTextArea.setForeground(Color.white);
			mainTextArea.setFont(normalFont);
			mainTextArea.setLineWrap(true);
			mainTextPanel.add(mainTextArea);
		
			
			commandArea = new JPanel();
			commandArea.setBounds(680,120,600,100);
			commandArea.setBackground(Color.black);
			con.add(commandArea);
			
			commandTextArea.setText("Enter a command: ");
			commandTextArea.setBounds(500,100,200,250);
			commandTextArea.setBackground(Color.black);
			commandTextArea.setForeground(Color.white);
			commandTextArea.setFont(normalFont);
			commandTextArea.setLineWrap(true);
			commandArea.add(commandTextArea);
			commandTextArea.setLayout(new GridLayout(2,1));
			
			
			commandArea.add(enterCommand);
			
			
			optionButtonPanel = new JPanel();
			optionButtonPanel.setBounds(850,250,300,150);
			optionButtonPanel.setBackground(Color.yellow);
			optionButtonPanel.setLayout(new GridLayout(5,1));
			con.add(optionButtonPanel);
			
			directionButtonPanel = new JPanel();
			directionButtonPanel.setBounds(800,450,400,100);
			directionButtonPanel.setBackground(Color.blue);
			directionButtonPanel.setLayout(new GridLayout(1,4));
			con.add(directionButtonPanel);
			
			dirE = new JButton("East");
			dirE.setBackground(Color.black);
			dirE.setForeground(Color.white);
			dirE.setFont(normalFont);
			dirE.setFocusPainted(false);
			dirE.addActionListener(e);
			directionButtonPanel.add(dirE);
			
			dirW = new JButton("West");
			dirW.setBackground(Color.black);
			dirW.setForeground(Color.white);
			dirW.setFont(normalFont);
			dirW.setFocusPainted(false);
			dirW.addActionListener(w);
			directionButtonPanel.add(dirW);
			
			dirS = new JButton("South");
			dirS.setBackground(Color.black);
			dirS.setForeground(Color.white);
			dirS.setFont(normalFont);
			dirS.setFocusPainted(false);
			dirS.addActionListener(s);
			directionButtonPanel.add(dirS);
			
			dirN = new JButton("North");
			dirN.setBackground(Color.black);
			dirN.setForeground(Color.white);
			dirN.setFont(normalFont);
			dirN.setFocusPainted(false);
			dirN.addActionListener(n);
			directionButtonPanel.add(dirN);
			
			
			lookButton = new JButton("LOOK");
			lookButton.setBackground(Color.black);
			lookButton.setForeground(Color.white);
			lookButton.setFont(normalFont);
			lookButton.setFocusPainted(false);
			lookButton.addActionListener(look);
			optionButtonPanel.add(lookButton);
			
			inventoryButton = new JButton("INVENTORY");
			inventoryButton.setBackground(Color.black);
			inventoryButton.setForeground(Color.white);
			inventoryButton.setFont(normalFont);
			inventoryButton.setFocusPainted(false);
			inventoryButton.addActionListener(invent);
			optionButtonPanel.add(inventoryButton);
			
			
			choice1 = new JButton("SAVE");
			choice1.setBackground(Color.black);
			choice1.setForeground(Color.white);
			choice1.setFont(normalFont);
			choice1.setFocusPainted(false);
			choice1.addActionListener(saveGame);
			optionButtonPanel.add(choice1);
			
			choice2 = new JButton("HELP");
			choice2.setBackground(Color.black);
			choice2.setForeground(Color.white);
			choice2.setFont(normalFont);
			choice2.setFocusPainted(false);
			choice2.addActionListener(helpButton);
			optionButtonPanel.add(choice2);
			
			
			
			optionButtonPanel.add(quitButton);
			

			
			quitLock = new JButton("QUIT GAME");
			quitLock.setBackground(Color.cyan);
			quitLock.setForeground(Color.black);
			quitLock.setFont(normalFont);
			quitLock.setFocusPainted(false);
			quitLock.addActionListener(quit);
			
			hint = new JButton("GET HINT");
			hint.setBackground(Color.cyan);
			hint.setForeground(Color.black);
			hint.setFont(normalFont);
			hint.setFocusPainted(false);
			hint.addActionListener(seeHint);
			
			
			Enter = new JButton("ENTER");
			Enter.setBackground(Color.black);
			Enter.setForeground(Color.white);
			Enter.setFont(normalFont);
			Enter.setFocusPainted(false);
			commandArea.add(Enter);
			Enter.addActionListener(cmdHit);
			
			Password = new JButton("UNLOCK");
			Password.setBackground(Color.cyan);
			Password.setForeground(Color.black);
			Password.setFont(normalFont);
			Password.setFocusPainted(false);
			Password.addActionListener(code);
			
			WestPassword = new JButton("UNLOCK");
			WestPassword.setBackground(Color.cyan);
			WestPassword.setForeground(Color.black);
			WestPassword.setFont(normalFont);
			WestPassword.setFocusPainted(false);
			WestPassword.addActionListener(westCode);
			
			

		}
		
	public static void updateGameScreen() {
			mainTextPanel.setVisible(false);
			JPanel mainTextPanel1 = new JPanel();
			mainTextPanel1.setBounds(10,10,600,250);
			mainTextPanel1.setBackground(Color.black);
			con.add(mainTextPanel1);

			
			mainTextArea1 = new JTextArea();
			printStream1 = new PrintStream(new CustomOutputStream(mainTextArea1));
			System.setOut(printStream1);
			System.setErr(printStream1);
			
			//mainTextPanel1.setVisible(true);
			mainTextArea1.setBounds(100,100,600,250);
			mainTextArea1.setBackground(Color.black);
			mainTextArea1.setForeground(Color.white);
			mainTextArea1.setFont(normalFont);
			mainTextArea1.setLineWrap(true);
			mainTextPanel1.add(mainTextArea1);
		}
		
/*		public static void updateLockScreen() {	
			
			JTextArea newMessage = new JTextArea();
			PrintStream printStream3 = new PrintStream(new CustomOutputStream(newMessage));
			System.setOut(printStream3);
			System.setErr(printStream3);
			newMessage.setBounds(100,100,600,250);
			newMessage.setBackground(Color.pink);
			newMessage.setForeground(Color.white);
			newMessage.setFont(normalFont);
			newMessage.setLineWrap(true);
			if (curLocation.getName().equals("entrance"))
				lockTextPanel.add(newMessage);	
			else if (curLocation.getName().equals("The West door"))
				lockTextPanel1.add(newMessage);
			
		}
*/
	
	public static void Save(Location curLocation, ContainerItem inventory, List<Location> locationList, List<ContainerItem> containerList) throws IOException  {
    	saveScan = enterCommand.getText();
    	FileOutputStream saveFile = new FileOutputStream(saveScan);    	
    	ObjectOutputStream save = new ObjectOutputStream(saveFile);    	
    	System.out.println("Saved");    	
    	save.writeObject(curLocation);
    	save.writeObject(inventory);
    	save.writeObject(locationList);
    	save.writeObject(containerList);
    	saveFile.close();
    }
    
    public static void Load(Location curLocation, ContainerItem inventory, List<Location> locationList, List<ContainerItem> containerList) throws IOException, ClassNotFoundException {
		saveScan = fileName.getText();
		FileInputStream saveFile = new FileInputStream(saveScan);
		ObjectInputStream save = new ObjectInputStream(saveFile);
		setLocation((Location) save.readObject());
		setInventory((ContainerItem) save.readObject());
		setLocationList((List<Location>) save.readObject());
		setContainerList((List<ContainerItem>) save.readObject());
		System.out.println("Game Loaded");
		save.close();
		loadScreen.dispose();
	
    }

	public static void main( String[] args ) {
		//Create a SaveGame object
//		SaveGame mainSave = new SaveGame();
		
        // All items given in the game
		
			dragon.setMap(null, outside, null, null);
		 	outside.setMap(dragon, null, null, entrance);
		 	entrance.setMap(null, null, outside, inside);
		   	inside.setMap(eastEntrance, westEntrance, entrance, null);
		    
		    east.setMap(null, inside, null, null);
		    west.setMap(inside, null, null, null);
		    eastEntrance.setMap(east, inside, null, null);
		    westEntrance.setMap(inside, west, null, null);
		    
		    satchel.addItem(mirror);
		    satchel.addItem(key);
		    box.addItem(scroll);
		    chest.unLocked(false);
		    chest.addItem(pearl);
		    outItem.add(torch);
		    entranceItem.add(box);
		    indoorItem.add(satchel);
			eastItem.add(chest);
			westItem.add(sword);
		    

        
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
		game();

		System.out.print( "Welcome to The Legendary Tale of the Dragon Slayer Game!");
		System.out.println( "\nYou are an adventurer going on a quest to destroy the mighty dragon " +
					"that is causing terror to the miserable village." );
		setLocation( outside );

	}
    
	public static class TitleScreenHandler implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			createGameScreen();
			start = "y";
			}
	}
	
	public static class HelpButton implements ActionListener{
		public void actionPerformed (ActionEvent event) {
		//TODO: if to elif?
			updateGameScreen();
			
				if (curLocation == outside) {
					System.out.println("You are standing in the middle of a hill. "
							+ "\nAvailable commands:  examine [item], take [item], "
							+ "\ndrop [item], take [item] from [item] , put [item] in [item] ");
				}
			
			
				if (curLocation == entrance) {
					System.out.println("You are standing at the entrance. "
							+ "\nAvailable commands:  open door, examine [item], "
							+ "\ntake [item], drop [item], take [item] from [item] , put [item] in [item] ");
				}
				if (curLocation == inside) {
					System.out.println("You are standing inside the house. "
							+ "\nAvailable commands: examine [item], take [item], drop [item], "
							+ "\ntake [item] from [item] , put [item] in [item] ");
				}
				if (curLocation == eastEntrance){
					System.out.println("You are standing in front of a wood door. This door leads to the East room "
							+ "\nAvailable commands: use...to...door, take [item], drop [item], "
							+ "\ntake [item] from [item] , put [item] in [item] "
							+ "\nThere is no lock so that you cannot use any key. "
							+ "\nHowever, FIRE CAN BURN WOOD");
				}
            	if ( curLocation == westEntrance ) {
					System.out.println("You are standing in front of an iron door. This door leads to the West room "
							+ "\nAvailable commands: open door, examine [item], take [item], drop [item], "
							+ "\ntake [item] from [item] , put [item] in [item] ");
				}
            	if ( curLocation == east ) {
					System.out.println("You are standing in the East room"
							+ "\nAvailable commands: use...to...chest, examine [item], take [item], drop [item], "
							+ "\ntake [item] from [item] , put [item] in [item] "
							+ "\nTry to open the chest. It needs a key to open. Things you find in the chest are necessary to defeat the dragon");
				}
            	if ( curLocation == west ) {
					System.out.println("You are standing in the West room"
							+ "\nAvailable commands: examine [item], take [item], drop [item], "
							+ "\ntake [item] from [item] , put [item] in [item] "
							+ "\nTry to look around. To defeat the dragon, you need a pearl, a mirror, and a sword");
				}
					
				if (inventory.isHolding("mirror") && inventory.isHolding("pearl") && inventory.isHolding("sword") && !curLocation.getName().equals("dragon")) {
					System.out.println("You are now having enough items to defeat the dragon. "
							+ "\nYou should find it as soon as possible!");
				}
				
				if (curLocation == dragon) {
					System.out.println("You do not want to wake it up without having enough weapons. "
							+ "\nWeapons can be found in the house of a legendary sensei");
				}
				
				
				if (inventory.isHolding("mirror") && inventory.isHolding("pearl") && inventory.isHolding("sword") && curLocation.getName().equals("dragon")) {
					System.out.println("You have enough weapons to attack the dragon. "
							+ "\nATTACK IT!");
				}
            	command = enterCommand.getText();
            }
		}
		
	public static class Quit implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			//command = enterCommand.getText();
				System.exit(1);
			} 
	}
	
	public static class AUDIO implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			//song = ".//res//gamemusic.mp3";
			//song = "C:\\Users\\dell\\Desktop\\gamemusic.mp3";
			se.setFile();
			se.play();
		}
	}
	
	public static class Save implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			updateGameScreen();
			System.out.println("Please enter the the name for your save file: [YOURNAME].save");
			} 
	}
	
	public static class Load implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			loadScreen =  new JFrame();
			loadScreen.setSize(600,250);
			//popup.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			loadScreen.getContentPane().setBackground(Color.pink);
			loadScreen.setLayout(null);
			loadScreen.setVisible(true);
			loadCon = loadScreen.getContentPane();
			
			loadTextPanel = new JPanel();
			loadTextPanel.setBounds(0,0,600,250);
			loadTextPanel.setBackground(Color.pink);
			loadCon.add(loadTextPanel);
			
			loadTextArea.setBounds(100,100,600,250);
			loadTextArea.setBackground(Color.pink);
			loadTextArea.setForeground(Color.white);
			loadTextArea.setFont(normalFont);
			loadTextArea.setLineWrap(true);
			loadTextPanel.add(loadTextArea);
			
			
			loadcommandArea = new JPanel();
			loadcommandArea.setBounds(100,250,600,100);
			loadcommandArea.setBackground(Color.pink);
			loadcommandArea.setForeground(Color.white);
			loadTextPanel.add(loadcommandArea);
			
			
			loadcommandTextArea.setText("Enter a file name: ");
			loadcommandTextArea.setBounds(100,100,200,250);
			loadcommandTextArea.setFont(normalFont);
			loadcommandTextArea.setBackground(Color.pink);
			loadcommandTextArea.setForeground(Color.white);
			loadcommandTextArea.setLineWrap(true);
			loadcommandArea.add(loadcommandTextArea);
			loadcommandTextArea.setLayout(new GridLayout(2,1));	
			//System.out.println( "You tried to load a game. ");
			
			PrintStream printStream2 = new PrintStream(new CustomOutputStream(loadTextArea));
			System.setOut(printStream2);
			System.setErr(printStream2);
			System.out.println( "Please type your file name: [yourname].save");
			
			enterFileName = new JButton("OK");
			enterFileName.setBackground(Color.black);
			enterFileName.setForeground(Color.white);
			enterFileName.setFont(normalFont);
			enterFileName.setFocusPainted(false);
			enterFileName.addActionListener(getFile);


			loadTextPanel.add(fileName);
			loadTextPanel.add(enterFileName);
			//loadCon.add(loadTextPanel);
			//loadTextPanel.add(quitLock);
			} 
	}
	
	public static class goEast implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			updateGameScreen();
				if ( curLocation.getMap().get("east") !=null && curLocation.getMap().get("east").isUnlocked() ) {
					setLocation(curLocation.getMap().get("east"));
					System.out.println( "Headed East!" );

				} else {
					System.out.println( "Cannot go to this direction!" );
				}			
		}
	}
	
	public static class goWest implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			updateGameScreen();
				if ( curLocation.getMap().get("west") !=null && curLocation.getMap().get("west").isUnlocked() ) {
					setLocation(curLocation.getMap().get("west"));
					System.out.println( "Headed West!" );

				} else {
					System.out.println( "Cannot go to this direction!" );
				}			
		}
	}
	
	public static class goSouth implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			updateGameScreen();
				if ( curLocation.getMap().get("south") !=null && curLocation.getMap().get("south").isUnlocked() ) {
					setLocation(curLocation.getMap().get("south"));
					System.out.println( "Headed South!" );

				} else {
					System.out.println( "Cannot go to this direction!" );
				}			
		}
	}
	
	public static class goNorth implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			updateGameScreen();
				if ( curLocation.getMap().get("north") !=null && curLocation.getMap().get("north").isUnlocked() ) {
					setLocation(curLocation.getMap().get("north"));
					System.out.println( "Headed North!" );

				} else {
					System.out.println( "Cannot go to this direction!" );
				}			
		}
	}

	public static class getPass implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			String passcode = enterPass.getText();
        	if (passcode!= null&& !passcode.equals("0743") ) {
        		System.out.println("Please try again!");
        	}
        	else if (passcode.equals("0743"))
        	{
        		setLocation(inside);
            	inside.unLocked(true);        		
        		popup.dispose();
        		System.setOut(printStream1);
        		System.setErr(printStream1);        		
        		System.out.println("You unlocked the door");
        	}
		}
	}	

	public static class getWestPass implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			//updateLockScreen();
			String passcode1 = enterPass1.getText().toLowerCase().trim();
            	//String passcode = in.nextLine();
            	boolean reflected = false;

				// If enter wrong passcode (12 trials and 2 hints before giving the option to quit)
            		if (passcode1.contains("mirror")&&passcode1.contains("reflect")&& inventory.isHolding("mirror")) {
						reflected = true;
					}

            		if (reflected) {
						popup1.dispose();
	            		System.setOut(printStream1);
	            		System.setErr(printStream1);        		
	            		System.out.println("You reflected the code and realized that VICDU is the upperhalf of XIEBH");
	            		System.out.println( "You successfully broke the lock. The door is opened and you enter the West room." );
						setLocation(west);
                    	west.unLocked(true);
                    	inside.changeMap("west", west);
                    	west.changeMap("east", inside);
                    	inside.unLocked(true); //handle previous game loaded
            		}

            	else if ( passcode1.equals("xiebh") ) {
            		System.out.println( "You successfully broke the lock. The door is opened and you enter the West room." );
                	setLocation(west);
                	west.unLocked(true);
                	inside.changeMap("west", west);
                	west.changeMap("east", inside);
                	inside.unLocked(true); //handle previous game loaded
            		popup1.dispose();
            		System.setOut(printStream1);
            		System.setErr(printStream1);        		
            		System.out.println( "You successfully broke the lock. The door is opened and you enter the West room." );
            	}
            	else  {
            		System.out.println("Please try again!");
            	}      
		}
	}
		
	public static class getFileName implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			String f = fileName.getText().toLowerCase().trim();
            if (f.contains(".save")){
            	try {
            		Load(curLocation, inventory, locationList, containerList);
            		createGameScreen();
                	updateGameScreen();
                	System.out.println("Game Loaded. All states are updated.");
            	}
            	catch (IOException | ClassNotFoundException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
            		System.out.println("File Not Found");
            	}
            	          	
            }
        }
	}
	
	public static class getHint implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if (curLocation.getName().equals("entrance")) {
				System.out.println("Think about the number of edges/colors that is representative of each figure");
			}
			else if (curLocation.getName().equals("The West door")) {
				System.out.println("The passcode contains letters that are horizontally symmetric. Think about using the mirror to reflect the given letters.");
			}
		}
	}
	
	public static class lookCommand implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			updateGameScreen();
			setImage();
			con.add(panel);
			System.out.println( String.format("\nCurrent location: %s",
				curLocation.getDesc()) );
			if (curLocation.getItem() != null) {
				System.out.println( String.format("Item(s) left there: %d item(s)",
					curLocation.retrieveNumOfItems()) );
				for( Item i : curLocation.getItem() ) {
					System.out.println( String.format("%s", i.getName()) );
				}
			}
			if (inventory.isHolding("mirror") && inventory.isHolding("pearl") && inventory.isHolding("sword") && curLocation.getName().equals("dragon")) {
				System.out.println("You have enough weapons to attack the dragon. "
						+ "\nATTACK IT!");
			}
		}
	}
	
	public static class inventoryCommand implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			updateGameScreen();
			if ( inventory.getCollection().size() == 0 ) {
				System.out.println( "You currently have no item." );

			} else {
				System.out.println( String.format("\nYou are currently having %d items.\nNamely:\n",
						inventory.getCollection().size()) );
				for ( Item i : inventory.getCollection() ) {
					System.out.println( String.format("%s", i.getName()) );
				}
			}
		}
	}
	
	public static class commandHit implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			command = enterCommand.getText();
				
			//Take Command
			if ( command.contains("take") ) {
				updateGameScreen();
				try {
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
				updateGameScreen();
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
						updateGameScreen();
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
				updateGameScreen();
            	// Get the item with the given name from the location and print its description
                // if the item is there, print the description of it
                // if not, print some sort of error message

            	boolean found = false;

                // If just type "examine", as what to examine (several items at a time is ok)

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
					
				} 
            	catch( NullPointerException e ) {
                    System.out.print( "Cannot find the given item.\n" );
                }
            	if ( !found ) {
					System.out.println( "Cannot find the item." );
				}
			}
			
			if ( command!= null && command.contains("open") && command.contains("door") && curLocation.getName().equals("entrance") ) {
				updateGameScreen();
				popup =  new JFrame();
				popup.setSize(1000,600);
				//popup.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				//popup.getContentPane().setBackground(Color.pink);
				popup.setLayout(null);
				popup.setVisible(true);
				con1 = popup.getContentPane();
				
				lockTextPanel = new JPanel();
				lockTextPanel.setBounds(0,0,1000,600);
				lockTextPanel.setBackground(Color.pink);
				con1.add(lockTextPanel);

				outputArea.setBounds(0,0,1000,600);
				outputArea.setBackground(Color.pink);
				outputArea.setForeground(Color.white);
				outputArea.setFont(normalFont);
				outputArea.setLineWrap(true);
				lockTextPanel.add(outputArea);
				
				ImageIcon ent = new ImageIcon(ClassLoader.getSystemResource( "entranceCode.jpg" )); // load the image to a imageIcon
    			Image img = ent.getImage(); 
    			JLabel lab = new JLabel("", ent, JLabel.CENTER);
    			lockTextPanel.add(lab);
				
				optionArea = new JPanel();
				optionArea.setBounds(100,500,600,800);
				optionArea.setBackground(Color.pink);
				
			
				EnterIntruct.setText("Enter a Password: ");
				EnterIntruct.setBounds(100,500,200,250);
				EnterIntruct.setBackground(Color.pink);
				EnterIntruct.setForeground(Color.white);
				EnterIntruct.setFont(normalFont);
				EnterIntruct.setLineWrap(true);
				lockTextPanel.add(EnterIntruct);
				lockTextPanel.add(enterPass);
				System.out.println( "You tried to open the door. Please guess the password");
				
				PrintStream printStream2 = new PrintStream(new CustomOutputStream(outputArea));
				System.setOut(printStream2);
				System.setErr(printStream2);
				System.out.println( "You try to open the door and realized that it was locked with an ancient lock." +
						"\nOn the lock, there are four figure."+
						"\nTo open the door, you have to guess correctly all 4 digits corresponding to 4 figures.");
            	System.out.println( "\nPlease enter the 4 digits (without spaces; for example, 0000)" );
				optionArea.add(Password);
				optionArea.add(hint);
				optionArea.add(quitLock);
				optionArea.setLayout(new GridLayout(3,1));
				lockTextPanel.add(optionArea);
				
			}
			
			if ( command!= null && command.contains("open") && command.contains("door") && curLocation.getName().equals("The West door") ) {
				updateGameScreen();
				popup1 =  new JFrame();
				popup1.setSize(1000,500);
				//popup.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				//popup1.getContentPane().setBackground(Color.cyan);
				popup1.setLayout(null);
				popup1.setVisible(true);
				con2 = popup1.getContentPane();				
				
				lockTextPanel1 = new JPanel();
				lockTextPanel1.setBounds(0,0,1000,600);
				lockTextPanel1.setBackground(Color.pink);
				con2.add(lockTextPanel1);
				
				outputArea1.setBounds(100,100,1000,600);
				outputArea1.setBackground(Color.pink);
				outputArea1.setForeground(Color.white);
				outputArea1.setFont(normalFont);
				outputArea1.setLineWrap(true);
				lockTextPanel1.add(outputArea1);
				
				optionArea1 = new JPanel();
				optionArea1.setBounds(100,250,600,100);
				optionArea1.setBackground(Color.cyan);
				
				
				EnterInstruct1.setText("Enter a Password: ");
				EnterInstruct1.setBounds(100,100,200,250);
				EnterInstruct1.setBackground(Color.pink);
				EnterInstruct1.setForeground(Color.white);
				EnterInstruct1.setFont(normalFont);
				EnterInstruct1.setLineWrap(true);
				
				System.out.println( "You tried to open the West door. Please guess the password");
				
				PrintStream printStream2 = new PrintStream(new CustomOutputStream(outputArea1));
				System.setOut(printStream2);
				System.setErr(printStream2);
				System.out.println( "You try to open the door and realized that it was locked with an ancient lock." +
						"\nOn the lock, there are five letters shown as below: ");
				System.out.println(
						"\nUnder each figure, there is a place for you to put one digit from A to Z on it." +
						"\nTo open the door, you have to guess correctly all 5 letters." );
            	System.out.println( "\nPlease enter the 5 letters (without spaces; for example, vicdu)" );
            	
            	ImageIcon imgW = new ImageIcon(ClassLoader.getSystemResource( "westCode.jpg" )); // load the image to a imageIcon
    			JLabel la = new JLabel("", imgW, JLabel.CENTER);
    			
    			lockTextPanel1.add(la);
    			lockTextPanel1.add(EnterInstruct1);
				lockTextPanel1.add(enterPass1);
    			optionArea1.add(WestPassword);
				optionArea1.add(hint);
				optionArea1.add(quitLock);
				lockTextPanel1.add(optionArea1);
				optionArea1.setLayout(new GridLayout(3,1));
			}
			
			
			else if ( command.contains(".save") ) {
				try {
					Save(curLocation, inventory, locationList, containerList);
				}
            	catch (IOException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
            		System.out.println("Invalid input");
				}
			}
			

			
			else if ( command.contains("torch") && inventory.isHolding("torch") && command.contains("burn") && curLocation.getName().equals("The East door")) {
				
            	System.out.println( "You successfully burnt the door. The door is opened and you enter the East room." );
				setLocation(east);
				east.unLocked(true);
				inside.changeMap("east", east);
				east.changeMap("west", inside);
				inside.unLocked(true); //handle previous game loaded
			}
			
			
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
            }
			
			if (command.contains("attack") && inventory.isHolding("mirror") && inventory.isHolding("pearl") && inventory.isHolding("sword") && curLocation.getName().equals("dragon") ) {
				System.out.println("Congratulations! You have collected all items needed to defeat the dragon right before it was trying to attack you. " +
						 "\nYou wisely used the mirror to reflect the light from the pearl to distract the dragon, then used the sword to kill it! " +
						 "\nYou became the village's hero!!!\n\n\n\n" );
			
			
			victory =  new JFrame();
			//victory.setSize(680,600);
			
			ImageIcon imageIcon1 = new ImageIcon(ClassLoader.getSystemResource( "victory.jpg" )); // load the image to a imageIcon
			Image image1 = imageIcon1.getImage(); // transform it 
			Image newimg1 = image1.getScaledInstance(500, 500,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			imageIcon1 = new ImageIcon(newimg1);
			JLabel victoryNameLabel = new JLabel("", imageIcon1, JLabel.CENTER);
			JPanel panel = new JPanel(new BorderLayout());
			
			panel.add( victoryNameLabel, BorderLayout.CENTER );
			
			victory.setLayout( new FlowLayout() );
			victory.add(panel);
			
			victory.pack();
			victory.setVisible(true);
			victory.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}			
		}
	}

};


