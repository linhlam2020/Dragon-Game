/*
*@description ContainerItem class
*
*@author Team 4B : Linh Lam, So Negishi, Duc Nguyen, Hoang Pham
*@version November 29, 2017
*/

import java.util.List;
import java.util.ArrayList;

public class ContainerItem extends Item {
	
	private static final long serialVersionUID = 1L;
	
    private List<Item> collection;
    private boolean unlocked;


    public ContainerItem( String Name, String Type, String Description ) {
        super( Name,  Type,  Description );
        collection = new ArrayList<>();
        unlocked = true;
    }

    public ContainerItem() {
        collection = new ArrayList<>();
    }

    // This method return name of an item
    public String getName() {
    	return super.getName();
    }

    // This method adds an item to the collection
    public void addItem( Item item ) {
    	if ( collection!= null ) {
            collection.add(item);
        }
    }

    // This method removes an item to the collection
    public void removeItem( Item item ) {
    	if ( collection!=null ) {
            collection.remove(item);
        }
    }

    // This methods queries the collection
    public List<Item> getCollection() {
        return collection;
    }
    
    public void unLocked( boolean bool ) {
    	unlocked = bool;
    }
    
    public boolean isUnlocked() {
    	return unlocked;
    }

    // This method return true if an item is in the container
    public boolean isHolding( String item ) {
    	for ( Item i : this.collection ) {
    		if ( item.contains(i.getName()) )  {
    			return true;
			}
		}

    	return false;
    }

    public void print() {
    	System.out.println( "\tShortname: " + super.getName() + "\n\tType: " + super.getType() + "\n\tDescription: " + super.getDescription() ) ;
    	if ( isUnlocked() ) {
    	    System.out.println( "\n\tIt contains: " );
            int countBox = 0;

            for ( Item i : collection ) {
                countBox++;
                System.out.println( String.format("\t\t%s", i.getName()) ) ;
            }

            if ( countBox == 0 ) {
                System.out.print( "\t\tNothing" );
                System.out.println();
            }

            System.out.println();

        } else
    		System.out.println( "\n\tThis item is locked. You should unlock it to see things inside it." );
        }

    public String toString( ) {
        return "\t\tShortname: " + super.getName( ) + "\n\t\tType: " + super.getType() + "\n\t\tDescription: "+ super.getDescription() ;
    } 
    
}


