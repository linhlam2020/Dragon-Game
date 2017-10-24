/*
*@description Location class
*
*@author Team 4B : Linh Lam, So Negishi, Hoang Pham, Duc Nguyen
*@version October 23, 2017
*/

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;


public class Location {
    private String name;
    private String desc;
    private List<Item> itemList;

    // A constructor that takes three parameters and
    // sets the variables accordingly. Parameters must
    // be in order: name, type, description.
    public Location( String Name, String Description, List<Item> Items ) {
        name = Name;
        desc = Description;
        itemList = Items;
    }


    public Location( String Name, String Description ) {
        name = Name;
        desc = Description;
    }


    // Setter methods
    public void setName( String n ) {
        name = n;
    }

    public void setDesc( String d ) {
        desc = d;
    }

    public void setItem( List<Item> i ) { itemList = i;}


    // Getter methods
    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public List<Item> getItem() { return itemList; }


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
    public void retrieveItem(String name) {
        System.out.println(i.getName());
    }

    // This method retrieves a count of the number of items in the location
    public void retrieveNumOfItems() {
        System.out.println(this.getItem().size());
    }

    public void print() {
        System.out.println( "Location:" );
        System.out.println( String.format("\t ShortName: %s", this.getName()) );
        System.out.println( String.format("\t Description: %s", this.getDesc()) );
        System.out.println( String.format("\t Items: %s", this.getItem()) );
    }

//    // This method loads a map from text file
//    public String[][] loadMap() throws FileNotFoundException {
//        String[][] map = new String[21][21];
//        File file = new File("src/1d.txt");
//
//        Scanner scan = new Scanner(file);
//        for (int i = 0; i < 21; i++) {
//            String line = scan.nextLine();
//            Scanner temp = new Scanner(line).useDelimiter(",");
//            for (int j = 0; j < 21; j++) {
//                map[i][j] = temp.next();
//            }
//        }
//        System.out.println(map);
//
//        scan.close();
//
//        return map;
//    }

}
