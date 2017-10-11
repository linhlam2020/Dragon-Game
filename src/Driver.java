/**
*@description Driver class
*
*@author Team 4B : Linh Lam, So Negishi, Hoang Pham, Duc Nguyen
*@version October 9, 2017
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Driver {

	public static void main(String[] args) {

	    // Items
		Item mirror = new Item("mirror", "tool", "a peanut butter and jelly sandwich");
		Item note = new Item("note", "hint", "a buttered flaky bread from France");
		Item i4 = new Item( "flashlight", "tool", "a small LED flashlight");
		Item i5 = new Item( "gun", "weapon", "a combat shotgun" );

		// Add items into an arraylist
		ArrayList<Item> list = new ArrayList<Item>();
		list.add(mirror);
		list.add(note);
		
		for ( Item i : list )
			i.print();

        Scanner in = new Scanner( System.in );
        String command;

        while( true ) {
            System.out.print("Enter a command: ");
            command = in.nextLine().toLowerCase().trim();

            if( command.equals( "quit" ) | command.equals( "exit" )) {
                break;
            }

            System.out.println(command);
        }

        in.close();
	}

}

