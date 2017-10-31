/*
*@description Location class
*
*@author Team 4B : Linh Lam, So Negishi, Hoang Pham, Duc Nguyen
*@version October 28, 2017
*/

import java.util.*;

public class ContainerItem extends Item {
    private List<Item> collection;

    public ContainerItem() {
        collection = new ArrayList<>();
    }

    public List<Item> getCollection() {
        return collection;
    }

    // This method adds an item to the collection
    public void addItem( Item item ) {
        this.getCollection().add(item);
    }

    // This method removes an item to the collection
    public void removeItem( Item item ) {
        this.getCollection().remove(item);
    }

    // This methods queries the collection
    public void queryColl() {

    }

}
