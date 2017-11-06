/*
*@description Location class
*
*@author Team 4B : Linh Lam, So Negishi, Duc Nguyen, Hoang Pham
*@version November 6, 2017
*/

import java.util.List;
import java.util.ArrayList;


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

    public Location( ) {

    }

    // Setter methods
    public void setName( String n ) {
        name = n;
    }

    public void setDesc( String d ) {
        desc = d;
    }

    public void setItem( List<Item> i ) {
        item = i;
    }


    // Getter methods
    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public List<Item> getItem() {
        return item;
    }


    // This method adds an item to the location
    public void addItem( Item item ) {
        if ( this.getItem() == null ) {
            List<Item> temp = new ArrayList<>();
            temp.add(item);
            setItem(temp);

        } else {
            this.getItem().add(item);
        }
    }

    // This method removes an item from the location
    public void removeItem( Item item ) {
    	if ( this.getItem()!= null )
            this.getItem().remove(item);
    }

    // This method retrieves an item given its short name
    public Item retrieveItem( String shortName ) {
        int i = 0;
        Item item = null;

        while ( i < this.getItem().size() ) {
            Item temp = this.getItem().get(i);

            if ( temp.getName().contains(shortName) ) {
                item = temp;
            } else {
                i++;
            }
        }

        return item;
    }

    // This method retrieves a count of the number of items in the location
    public int retrieveNumOfItems() {
        return this.getItem().size();
    }

    // This method returns true if an item entered is a member of the container
	public boolean isMember( String a ) {
        for( Item i : item ) {
            if ( a.contains(i.getName()) )  {
                return true;
            }
        }

        return false;
	}

	// This method gets container
    public ContainerItem getContainers( List<ContainerItem> a, String shortName ) {
        for ( ContainerItem i: a ) {
            if ( i.getName().contains(shortName) ) {
                return i;
            }
        }

        return null;
    }

    public void print() {
        System.out.println( "Current Location:" );
        System.out.println( String.format("\tShortName: %s", this.getName()) );
    }

    public String toString() {
        return "Name: " + name + "\nDescription: " + desc + "\nItem(s): " + item;
    }
}
