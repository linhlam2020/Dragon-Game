/*
*@description Location class
*
*@author Team 4B : Linh Lam, So Negishi, Hoang Pham, Duc Nguyen
*@version October 23, 2017
*/

import java.util.*;


public class Location {
    private String name;
    private String desc;
    private List<Item> item;

    // A constructor that takes three parameters and
    // sets the variables accordingly. Parameters must
    // be in order: name, type, description.
    public Location( String Name, String Description, List<Item> Items ) {
        name = Name;
        desc = Description;
        item = Items;
    }
    
    public Location() { }


    // Setter methods
    public void setName( String n ) {
        name = n;
    }

    public void setDesc( String d ) {
        desc = d;
    }

    public void setItem( List<Item> i ) { item = i;}


    // Getter methods
    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public List<Item> getItem() { return item; }


    // This method adds an item to the location
    public void addItem(Item item) {
        if(this.getItem() == null) {
            List<Item> temp = new ArrayList<>();
            temp.add(item);
            setItem(temp);
        } else {
            this.getItem().add(item);
        }
    }

    // This method retrieves an item given its short name
    public Item retrieveItem( String shortName ) {
        int i = 0;
        Item item = null;
	    while( i<this.getItem().size() ) {
	    		Item temp = this.getItem().get(i);
	    		if (temp.getName().contains(shortName))
	    			item = temp;
	    		else 
	    			i++;
	    }
	    return item;
    }

    // This method retrieves a count of the number of items in the location
    public int retrieveNumOfItems() {
        return this.getItem().size();
    }

    
    public void print() {
        System.out.println( "Current Location:" );
        System.out.println( String.format("\t ShortName: %s", this.getName()) );
/*        System.out.println( String.format("\t Description: %s", this.getDesc()) );
        System.out.println( "\t " + this.retrieveNumOfItems() + " Items:" );
        for (int i = 0; i<this.getItem().size(); i++)
        {
        	System.out.println(this.getItem().get(i));
        	System.out.println();*/
        }
    }
