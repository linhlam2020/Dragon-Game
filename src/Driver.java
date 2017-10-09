/**
*@description Driver class
*
*@author Team 4B : Linh Lam, So Negishi, Hoang Pham, Duc Nguyen
*@version October 9, 2017
*/
import java.util.ArrayList;


public class Driver {

	public static void main(String[] args) {
		ArrayList<Item> list = new ArrayList<Item>();
		Item i1 = new Item();
		Item i2 = new Item("sandwich", "consumable", "a peanut butter and jelly sandwich");
		Item i3 = new Item("croissant", "consumable", "a buttered flaky bread from France");
		
		i1.setName("milk");
		i1.setType("consumable");
		i1.setDescription("while drink taken from cows");

		i1.print();
		i1.toString();

		i2.print();
		i3.print();
		
		Item i4 = new Item( "flashlight", "tool", "a small LED flashlight");
		Item i5 = new Item( "gun", "weapon", "a combat shotgun" );
		
		
		list.add( i1 );
		list.add( i2 );
		list.add( i3 );
		list.add( i4 );
		list.add( i5 );
		
		for ( Item i : list )
			System.out.println( i );
	}

}

