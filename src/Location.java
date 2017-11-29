/*
*@description Location class
*
*@author Team 4B : Linh Lam, So Negishi, Duc Nguyen, Hoang Pham
*@version November 29, 2017
*/

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.Serializable;

public class Location implements Serializable {
	private static final long serialVersionUID = 1L;
    private String name;
    private String desc;
    private List<Item> item;
    private HashMap<String, Location> map = new HashMap<>();
    private boolean unlocked;

    // listItem constructor that takes three parameters and
    // sets the variables accordingly. Parameters must
    // be in order: name, type, description.
    public Location( String Name, String Description, List<Item> Items, boolean bool ) {
        name = Name;
        desc = Description;
        item = Items;        
        map.put( "east", null );
        map.put( "west", null );
        map.put( "south", null );
        map.put( "north", null );
        unlocked = bool;
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
    
    public void setMap( Location e, Location w, Location s, Location n ) {
    	map.put( "east", e );
        map.put( "west", w );
        map.put( "south", s );
        map.put( "north", n );
    }
    
    public void changeMap( String dir, Location e ) {
    	map.put( dir,e );
    }
    
    
    public void unLocked( boolean bool ) {
    	unlocked = bool;
    }
    
    public boolean isUnlocked() {
    	return unlocked;
    }
    
    public HashMap<String, Location> getMap() {
    	return map;
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

    // This method retrieves listItem count of the number of items in the location
    public int retrieveNumOfItems() {
        return this.getItem().size();
    }

    // This method returns true if the item or container item entered is a member of the container
	public boolean isMember( String listItem ) {
        for( Item i : item ) {
            if ( listItem.contains(i.getName()) )  {
                return true;
            }
        }

        return false;
	}

	// This method gets container
    public ContainerItem getContainer( List<ContainerItem> containerList, String containerName ) {
        for ( ContainerItem i: containerList ) {
            if ( i.getName().equals(containerName) ) {
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
        return "Name: " + name + "\nDescription: " + desc + "\nUnlocked: " + unlocked + "\nItem(s): " + item;
    }
}
