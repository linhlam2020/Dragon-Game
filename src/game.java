
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import net.codejava.swing.CustomOutputStream;
	public class game {
	
	
		
		
	JFrame window;
	Container con;
	JPanel titleNamePanel, startButtonPanel, mainTextPanel, optionButtonPanel;
	JLabel titleNameLabel;
	Font titleFont = new Font("Times New Roman", Font.PLAIN, 90);
	Font normalFont = new Font("Times New Roman", Font.PLAIN, 15);
	JButton startButton, quitButton, loadGame;
	JButton choice1, choice2, choice3, choice4;
	JTextArea mainTextArea = new JTextArea();

	TitleScreenHandler tsHandler = new TitleScreenHandler();
	


	public static void main (String [] args) {		
		new game();		
	}
	
	public game() {
		
		
		
		
		
	   
		PrintStream printStream = new PrintStream(new CustomOutputStream(mainTextArea));
		System.setOut(printStream);
		System.setErr(printStream);
		
		window =  new JFrame();
		window.setSize(800,600);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setBackground(Color.black);
		window.setLayout(null);
		window.setVisible(true);
		con = window.getContentPane();
		
		titleNamePanel = new JPanel();
		titleNamePanel.setBounds(100,100,600,150);
		titleNamePanel.setBackground(Color.black);
		titleNameLabel= new JLabel("ADVENTURE");
		titleNameLabel.setForeground(Color.white);
		titleNameLabel.setFont(titleFont);
		
		
		startButtonPanel = new JPanel();
		startButtonPanel.setBounds(250,300, 300, 150);
		startButtonPanel.setBackground(Color.black);
		
		startButton = new JButton("START");
		startButton.setBackground(Color.black);
		startButton.setForeground(Color.white);
		startButton.setFont(normalFont);
		startButton.addActionListener(tsHandler);
		startButton.setFocusPainted(false);
		
		quitButton = new JButton("QUIT");
		quitButton.setBackground(Color.black);
		quitButton.setForeground(Color.white);
		quitButton.setFont(normalFont);
		quitButton.setFocusPainted(false);
		
		loadGame = new JButton("LOAD GAME");
		loadGame.setBackground(Color.black);
		loadGame.setForeground(Color.white);
		loadGame.setFont(normalFont);
		loadGame.setFocusPainted(false);
		
		titleNamePanel.add(titleNameLabel);
		startButtonPanel.add(startButton);
		startButtonPanel.add(quitButton);
		startButtonPanel.add(loadGame);
		
		
		con.add(titleNamePanel);
		con.add(startButtonPanel);
	}
		
		public void createGameScreen() {
			titleNamePanel.setVisible(false);
			startButtonPanel.setVisible(false);
			
			mainTextPanel = new JPanel();
			mainTextPanel.setBounds(100,100,600,250);
			mainTextPanel.setBackground(Color.black);
			con.add(mainTextPanel);
			
			//mainTextArea = new JTextArea("Main text area");
			mainTextArea.setBounds(100,100,600,250);
			mainTextArea.setBackground(Color.black);
			mainTextArea.setForeground(Color.white);
			mainTextArea.setFont(normalFont);
			mainTextArea.setLineWrap(true);
			mainTextPanel.add(mainTextArea);
			
			
			optionButtonPanel = new JPanel();
			optionButtonPanel.setBounds(250,350,300,150);
			optionButtonPanel.setBackground(Color.black);
			optionButtonPanel.setLayout(new GridLayout(4,1));
			con.add(optionButtonPanel);
			
			choice1 = new JButton("Save");
			choice1.setBackground(Color.black);
			choice1.setForeground(Color.white);
			choice1.setFont(normalFont);
			choice1.setFocusPainted(false);
			//choice1.addActionListener(tsHandler);
			optionButtonPanel.add(choice1);
			
			choice2 = new JButton("Help");
			choice2.setBackground(Color.black);
			choice2.setForeground(Color.white);
			choice2.setFont(normalFont);
			choice2.setFocusPainted(false);
			//choice2.addActionListener(tsHandler);
			optionButtonPanel.add(choice2);
			
			choice3 = new JButton("Quit");
			choice3.setBackground(Color.black);
			choice3.setForeground(Color.white);
			choice3.setFont(normalFont);
			choice3.setFocusPainted(false);
			//choice3.addActionListener(tsHandler);
			optionButtonPanel.add(choice3);
			
			choice4 = new JButton("choice4");
			choice4.setBackground(Color.black);
			choice4.setForeground(Color.white);
			choice4.setFont(normalFont);
			choice4.setFocusPainted(false);
			//choice1.addActionListener(tsHandler);
			optionButtonPanel.add(choice4);
		}
		
		
		public class TitleScreenHandler implements ActionListener{
			public void actionPerformed(ActionEvent event) {
				System.out.print( "Welcome to The Legendary Tale of the Dragon Slayer Game!");
				System.out.println( "\nYou are an adventurer going on a quest to destroy the mighty dragon " +
						" that is causing terror to the miserable village." +
						"\nAt the beginning of the game, you are given a torch." );				
				
					createGameScreen();
			
				}
			}
		}
	
		
		
		
	



