/**
 *@description Location class
 *
 *@author Team 4B : Linh Lam, So Negishi, Hoang Pham, Duc Nguyen
 *@version October 15, 2017
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Location {
    private String name;
    private String desc;

    // Setter methods
    public void setName( String n ) {
        name = n;
    }

    public void setDesc( String d ) {
        desc = d;
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    // This method adds an item to the location
    public void addItem() {

    }

    // This method retrieves an item given its short name
    public void retrieveName() {

    }

    // This method retrieves a count of the number of items in the location
    public void retriveNumOfItems() {

    }

    public String[][] loadMap() throws FileNotFoundException{
        String[][] map = new String[21][21];
        File file = new File("src/1d.txt");

        Scanner scan = new Scanner(file);
        for (int i = 0; i < 21; i++) {
            String line = scan.nextLine();
            Scanner temp = new Scanner(line).useDelimiter(",");
            for (int j = 0; j < 21; j++) {
                map[i][j] = temp.next();
            }
        }
        System.out.println(map);

        scan.close();

        return map;
    }


}
