/**
*@description Driver class
*
*@author So Negishi
*@version September 27, 2017
*/


public class Driver {

	public static void main(String[] args) {
		Item i1 = new Item();
		Item i2 = new Item("sandwich", "consumable", "a peanut butter and jelly sandwich");
		Item i3 = new Item("croissant", "consumable", "a buttered flaky bread from France");
		
		i1.setName("milk");
		i1.setType("consumable");
		i1.setDesc("while drink taken from cows");

		i1.print();
		i1.toString();

		i2.print();
		i3.print();

		System.out.println(i2.getName() + " " + i2.getType() + " " + i2.getDesc());
	}

}
